package steps;

import com.grip.DBconnection.ProdvsQASQLStatements;
import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import cucumber.api.java.en.Given;

import java.io.IOException;
import java.sql.SQLException;

public class ProdvsQA extends CucumberRunner {
	
	@Given("^Compare index threshold between QA and Production Environment$")
	public void Prod() throws ClassNotFoundException, SQLException, IOException {
		
		ProdvsQASQLStatements.compare();
	}
	

}
