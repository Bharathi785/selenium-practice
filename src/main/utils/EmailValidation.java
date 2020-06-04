package com.grip.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.qaprosoft.carina.core.foundation.utils.R;

public class EmailValidation {

	public static final String USERS_MAIL_ID = R.TESTDATA.get("UserMailID");
	public static final String USERS_MAIL_PASSWORD = R.TESTDATA.get("UserMailPassword");
	public static final String MAILBOX_FOLDER_NAME = R.TESTDATA.get("MailboxFolderName");
	public static Folder mailFolder;
	public static SearchTerm searchTerm = null;
	public static Calendar calendar = null;
	public static Date timestamp = null;
	public static int mailRecivedCount = 0;
	public final static int MAIL_DURATION_VALUE = 4;
	static String parsed = null, capturedsystemTime;
	static Logger LOGGER = Logger.getLogger(EmailValidation.class);

	public static void validateEmail(String emailSubject, String emailBodyContent, String capturedSystemTime) {
		try {
			isMailReceivedByTimeAndSubject(emailSubject, emailBodyContent, capturedSystemTime);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unabe to validate the email content-" + e.getMessage());
		}
	}

	public static boolean isMailReceivedByTimeAndSubject(String emailSubject, String emailBodyContent,
			String capturedSystemTime) throws Exception {
		boolean emailReceived = false;
		capturedsystemTime = capturedSystemTime;
		LOGGER.info("Captured UTC time from System is - " + capturedsystemTime);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("GMT+09:00"));
		timestamp = df.parse(capturedsystemTime);
		df.setTimeZone(TimeZone.getTimeZone("GMT+05:00"));
		parsed = df.format(timestamp);

		Store store = getStore(USERS_MAIL_ID, USERS_MAIL_PASSWORD);

		try {
			mailFolder = store.getFolder(MAILBOX_FOLDER_NAME);
			mailFolder.open(Folder.READ_ONLY);
			calendar = Calendar.getInstance();
			calendar.setTime(timestamp);

			Date minDate = new Date(calendar.getTimeInMillis());
			minDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(parsed);
			LOGGER.info("Minimum date & time  - " + minDate);

			calendar.setTime(minDate);
			calendar.add(Calendar.MINUTE, MAIL_DURATION_VALUE);

			Date maxDate = new Date(calendar.getTimeInMillis());
			LOGGER.info("Maximum date & time - " + maxDate);

			ReceivedDateTerm minDateTerm = new ReceivedDateTerm(ComparisonTerm.GE, minDate);
			ReceivedDateTerm maxDateTerm = new ReceivedDateTerm(ComparisonTerm.LE, maxDate);

			SubjectTerm subjectTerm = new SubjectTerm(emailSubject);
			SearchTerm[] termArray = { minDateTerm, maxDateTerm, subjectTerm };

			searchTerm = new AndTerm(termArray);

			LOGGER.info("Total number of mails in the folder " + mailFolder.getMessageCount());

			Message messages[] = mailFolder.search(searchTerm);
			LOGGER.info("Total No. of mails available in the specified term duration - " + messages.length);

			for (Message message : messages) {
				if (message.getReceivedDate().after(minDate) && message.getReceivedDate().before(maxDate)) {
					mailRecivedCount = mailRecivedCount + 1;
					LOGGER.info("Found the required Emails with Subject : " + message.getSubject());
					String emailbody = getTextFromMessage(message);

					if (emailbody.contains(emailBodyContent)) {
						Assert.assertTrue(emailbody.contains(emailBodyContent),
								"Unable to verify the Email content subject");
						LOGGER.info("Email body content verified");
					}

					if (message.getContentType().contains("multipart")) {
						Multipart multiPart = (Multipart) message.getContent();
						for (int j = 0; j < multiPart.getCount(); j++) {
							MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(mailRecivedCount);
							if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
								String downloadsPath = System.getProperty("user.home") + "/Downloads/";
								part.saveFile(downloadsPath + part.getFileName());
								LOGGER.info("File saved in - " + downloadsPath + part.getFileName());
							} else {
								LOGGER.info("No attachment found in this mail");
							}
						}
					}
				}
			}
			LOGGER.info("No of emails in required criteria --> " + mailRecivedCount);
			Assert.assertEquals(mailRecivedCount, 1);
			emailReceived = true;
		} finally {
			if (store != null) {
				store.close();
			}
		}
		return emailReceived;
	}

	private static String getTextFromMessage(Message message) throws MessagingException, IOException {
		String result = "";
		if (message.isMimeType("text/plain")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart(mimeMultipart);
		}
		return result;
	}

	private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
		String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent();
				break; // without break same text appears twice in my tests
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
			}
		}
		return result;
	}

	private static Store getStore(String emailId, String password) throws Exception {
		Properties props = System.getProperties();

		props.setProperty("mail.imaps.auth.plain.disable", "true");
		props.setProperty("mail.imaps.auth.ntlm.disable", "true");

		Session mailSession = Session.getInstance(props, null);
		Store mailStore = mailSession.getStore("imaps");

		mailStore.connect("outlook.office365.com", 993, USERS_MAIL_ID + "\\GRIPQAMBX", USERS_MAIL_PASSWORD);
		return mailStore;
	}

	public static boolean isMailReceivedBySubject(String emailSubject, String emailBodyContent) throws Exception {
		boolean emailReceived = false;
		Store store = getStore(USERS_MAIL_ID, USERS_MAIL_PASSWORD);

		try {
			mailFolder = store.getFolder(MAILBOX_FOLDER_NAME);
			mailFolder.open(Folder.READ_ONLY);
			searchTerm = new SubjectTerm(emailSubject);
			Message[] messages = mailFolder.search(searchTerm);
			//LOGGER.info("Total no of EMails " + messages.length);

			for (Message message : messages) {
				if (message.getSubject().contains(emailSubject)) {
					LOGGER.info("Found the Emails with Subject : " + emailSubject);
					emailReceived = true;
					String emailbody = getTextFromMessage(message);

					if (emailbody.contains(emailBodyContent)) {
						Assert.assertTrue(emailbody.contains(emailBodyContent),
								"Unable to verify the Email content subject");
						LOGGER.info("Email body content verified");
					}
					emailReceived = true;
					break;
				}
			}
		} finally {
			if (store != null) {
				store.close();
			}
		}
		return emailReceived;
	}

}
