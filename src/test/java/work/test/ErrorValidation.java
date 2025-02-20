
package work.test;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import work.testcomponent.Basetest;

	public class ErrorValidation extends Basetest {
		@Test
		public  void Submitorder() throws IOException {
			// TODO Auto-generated method stub
			 
	        check.loginapplication("aradhyajoshi587@gmaill.com", "Ajajaj27");
	        
	        Assert.assertEquals(check.error(), "Incorrect email or password.", "Error message does not match!");

	        //Incorrect email or password.

		}
		


	}
