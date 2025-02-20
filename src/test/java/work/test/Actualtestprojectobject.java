
package work.test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import work.projectobject.Orderingproduct;
import work.projectobject.Ordervalidcheck;
import work.projectobject.PersonalDetail;
import work.projectobject.Productcatalouge;
import work.testcomponent.Basetest;

	public class Actualtestprojectobject extends Basetest {
		
		String name="QWERTY";
		@Test(dataProvider="getData",groups= {"purchase"})
		public  void Submitorder(HashMap<String,String>input) throws IOException {
			// TODO Auto-generated method stub
			
			String country="india";
			//launchaplication();	        
	        //object for login page to 
	        //Landingpage login=new Landingpage(driver);
	        //login.Goto(); 
	        check.loginapplication(input.get("email"), input.get("password"));
	        
	        //product cataloge page
	        Productcatalouge cataluge=new Productcatalouge(driver);
	        cataluge.addtocart(name);
	        
	        //order the product
	        Orderingproduct ordering =new Orderingproduct(driver);
	        ordering.NumberOfProduct(input.get("name"));
	        
	        //filling address
	        PersonalDetail detail=new PersonalDetail(driver);
	        detail.SelectCountry(country);     

		}
		
		@Test(dependsOnMethods= {"Submitorder"})
		public  void OrderValidation() {
			check.loginapplication("aradhyajoshi587@gmail.com", "Ajajaj27");
			Ordervalidcheck validate = new Ordervalidcheck(driver);
			validate.click();
			Boolean result=validate.validate(name);
			Assert.assertTrue(result);
		}
		@DataProvider
		public Object[][] getData() throws IOException{
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("email","aradhyajoshi587@gmail.com");
//			map.put("pass","Ajajaj27");
//			map.put("name","QWERTY");
//			
//			Map<String, String> map1 = new HashMap<String, String>();
//			map.put("email","aradhyajoshi587@gmail.com");
//			map.put("pass","Ajajaj27");
//			map.put("name","QWERTY");
			List<HashMap<String, String>>data= mapping() ;
			return new Object[][] {{data.get(0)},{data.get(1)}};

			
		}

	}
