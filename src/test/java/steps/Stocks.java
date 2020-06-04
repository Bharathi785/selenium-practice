package steps;

import com.grip.page.Researchpage;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.io.IOException;


public class Stocks extends CucumberRunner {


	Researchpage researchpage = new Researchpage(getDriver());


	@Given("^Enter URL and Validate_Research page$")
	public void Researchpage() throws Throwable {
		researchpage = new Researchpage(getDriver());
		researchpage.open();
		researchpage.validate__Research_Page();
	}


	@Then("^Select exchange and verify autorelease after change base Line Up$")
	public void Autorelease_Up() throws Throwable {
		int BasePercenup = 3;
		researchpage.Select_Exchange(BasePercenup);


	}

	@Then("^Select exchange and verify autorelease after change base Line Down$")
	public void Autorelease_Down() throws Throwable {
		 
		 int  BasePercendown=4;
		 researchpage.Select_Exchange(BasePercendown);
		 
		 
	 }
	 

	 @Then("^Select exchange and verify autorelease after change Previous Tick Up$")
	 public void Previous_Tick_UP() throws Throwable
	 {
		 
		 
		 
		 int Change_Pre_Up =5;
		
		 researchpage.Select_Exchange(Change_Pre_Up);
		 
		 
	 }
	 
	 @Then("^Select exchange and verify autorelease after change Previous Tick Down$")
	 public void Previous_Tick_Down() throws Throwable
	 {
		 
		 int Change_Pre_Down =6;
		
		 researchpage.Select_Exchange(Change_Pre_Down);
		  }

	

	
     @Then("^Select a stock right click and click on Hold option$")
     public void hold_stock() throws Throwable
     {
             
              researchpage.hold_Stock();
             
     }
     
     @Then("^Click on Stock tab and Search Stocks in Stock list Window$")
     public void Search_Stocks() throws IOException
     {
    	 researchpage.Search_stock();
    	 
     }
     
     @And("^Right Click on stocks and Click on CopyID link and Validate$")
     public void Copy_ID() throws InterruptedException, IOException
     {
    	 researchpage.Copy_ID();
     }
     
     @And("^Right Click on stocks and Click on Report link and Validate$")
     public void Report() throws InterruptedException, IOException
     {
    	 researchpage.Report();
     }
     @And("^Right Click on stocks and Click on CopyName link and Validate$")
     public void Copy_Name()
     {
    	 researchpage.Copy_name();
     }

     @And("^Enter and verify Stocks delimited with Comma$")
     public void Search()
     {
    	 researchpage.Search_Stock_Comma();
    	 
     }
     
     @And("^Click on each Filter and Validate Columns$")
     public void Verify_Filter()
     {
    	 researchpage.Verify_filter();
    	 
     }
	 
     @And("^Right click on Stock and Select Show Related Indices$")
     public void Show_Indices()
     {
    	 researchpage.Show_Indices();
    	 
     }
	 
     @And("^Right click on Stock and Select Show Exchanges")
     public void Show_Exchanges()
     {
    	 researchpage.Show_Exchanges();
    	 
     }
     
     @And("Search Exchanges in Exchange List Window and Right Click Show Stocks")
     public void Show_Stocks() throws Exception
     {
    	 researchpage.Show_Stocks();
    	 
     }
     
     @Then("Hold Multiple Stocks in Exchange and Release")
     public void  Hold_Stocks()
     {
    	 researchpage.Hold_Stock();
     }
     

	 @Then("^Select stock right click and click on Hold option$")
	 public void hold_a_stock() throws Throwable
	 {
		 
		 researchpage.Manual_Hold_Stock();
		
	 }

	
	 @Then("^manually hold a stock and click ok$")
	 public void manual_hold() throws Throwable
	 {
		 researchpage.manual_hold(); 
		 
	 }
	 @Then("^Release a stock$")
	 public void release() throws Throwable
	 {
		 researchpage.Release();
		 
		 
	 }
	 @Then("^Auto hold a stock and click ok$")
	 public void Auto_hold() throws Throwable
	 {
		 researchpage.hold_Stock_Auto();
	 }
	 @Then("^input a negative number in held price column$")
	 public void hold_at_negative() throws Throwable
	 {
		 researchpage.held_at_negative();
	 }

     
}

