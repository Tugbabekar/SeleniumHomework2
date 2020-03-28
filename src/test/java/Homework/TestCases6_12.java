package Homework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TestCases6_12 {
  private WebDriver driver;

  @BeforeMethod
    public void setup(){
        driver= DriverUtility.createDriver("chrome");
      driver.get("https://practice-cybertekschool.herokuapp.com");
    }
/*
Test case #6
 Step 1. Go to "https://www.tempmailaddress.com/"
 Step 2. Copy and save email as a string.
 Step 3. Then go to “https://practice-cybertekschool.herokuapp.com”
 Step 4. And click on “Sign Up For Mailing List".
 Step 5. Enter any valid name.
 Step 6. Enter email from the Step 2.
 Step 7. Click Sign Up
 Step 8. Verify that following message is displayed: “Thank you for signing up. Click the button below to return to the home page.”
 Step 9. Navigate back to the “https://www.tempmailaddress.com/”
 Step 10. Verify that you’ve received an email from “do-not-reply@practice.cybertekschool.com”
 Step 11. Click on that email to open it.
 Step 12. Verify that email is from: “do-not-reply@practice.cybertekschool.com”
 Step 13. Verify that subject is: “Thanks for subscribing to practice.cybertekschool.com!”
 */
@Test
public void testcase6() {
  driver.get("https://temp-mail.org/");
  String emailName =driver.findElement(By.id("mail")).getAttribute("value");
  System.out.println(emailName);
  driver.navigate().to("https://practice-cybertekschool.herokuapp.com/");
  driver.findElement(By.linkText("Sign Up For Mailing List")).click();
  driver.findElement(By.name("full_name")).sendKeys("Ayse");
  driver.findElement(By.name("email")).sendKeys(emailName);
  BrowserUtility.wait(2);
  driver.findElement(By.name("wooden_spoon")).click();
  String expected ="Thank you for signing up. Click the button below to return to the home page.";
  String verifyMessage =driver.findElement(By.tagName("h3")).getText();
  Assert.assertEquals(expected,verifyMessage);
 driver.navigate().back();
 driver.navigate().to("https://temp-mail.org/");













}
/*
Test case #7
 Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
 Step 2. And click on “File Upload".
 Step 3. Upload any file with .txt extension from your computer.
 Step 4. Click “Upload” button.
 Step 5. Verify that subject is: “File Uploaded!”
 Step 6. Verify that uploaded file name is displayed.
 Note: use element.sendKeys(“/file/path”) with specifying path to the file for uploading. Run this method against “Choose File” button.
 */

@Test
public void testcase7(){

driver.findElement(By.linkText("File Upload")).click();
driver.findElement(By.id("file-upload")).sendKeys("/Users/tugba/Desktop/What is the method overloading?.txt");
BrowserUtility.wait(2);
driver.findElement(By.id("file-submit")).click();
String expected = "File Uploaded!";
WebElement actual =driver.findElement(By.tagName("h3"));
  assertTrue(actual.isDisplayed());

  Assert.assertEquals(expected,actual.getText());





}
/*
Test case #8
 Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
 Step 2. And click on “Autocomplete”.
 Step 3. Enter “United States of America” into country input box.
 Step 4. Verify that following message is displayed: “You selected: United States of America”
Optional: If you want to to be a real selenium hero, use @DataProvider for for tests cases from 9 through 12.
 Please use following documentation: https://testng.org/doc/documentation-main.html#parameters-dataproviders

 */
@Test
public void testcase8(){
driver.findElement(By.linkText("Autocomplete")).click();
driver.findElement(By.id("myCountry")).sendKeys("United States of America");
driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/input")).click();
String expected ="You selected: United States of America";
WebElement actual = driver.findElement(By.id("result"));
assertTrue(actual.isDisplayed());
Assert.assertEquals(expected,actual.getText());


}

/*
Test case #9
 Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
 Step 2. And click on “Status Codes”.
 Step 3. Then click on “200”.
 Step 4. Verify that following message is displayed: “This page returned a 200 status code”
 */
@Test
//public void testcase9(){
//  driver.findElement(By.linkText("Status Codes")).click();
//  driver.findElement(By.cssSelector("a[href='status_codes/200']")).click();
//    String expectedMessage ="This page returned a status code";
//    WebElement displayedMessageElement = driver.findElement(By.xpath("//p"));
//    String actualMessage = displayedMessageElement.getText();
//    Assert.assertTrue(actualMessage.contains(expectedMessage),"The status code does not exist");
//}
/*
 Test case #10
 Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
 Step 2. And click on “Status Codes”.
 Step 3. Then click on “301”.
 Step 4. Verify that following message is displayed: “This page returned a 301 status code”

 */
/*
Test case #11
Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
Step 3. And click on “Status Codes”.
Step 4. Then click on “404”.
Step 5. Verify that following message is displayed: “This page returned a 404 status code”
 */
/*
Test case #12
Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
Step 3. And click on “Status Codes”.
Step 4. Then click on “500”.
Step 5. Verify that following message is displayed: “This page returned a 500 status code”
 */

    @DataProvider(name ="testData")
    public static Object [] testData(){
        return new Object [] {"404","500","301","200"};
    }
    @Test(dataProvider = "testData")
    public void statusCodes(String code ){
        WebElement statusCodeLink = driver.findElement(By.linkText("Status Codes"));
        statusCodeLink.click();
        WebElement statusCode = driver.findElement(By.linkText(code));
        statusCode.click();
        String expectedMessage ="This page returned a "+code+" status code";
        WebElement displayedMessageElement = driver.findElement(By.xpath("//p"));
        String actualMessage = displayedMessageElement.getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage),"The status code does not exist");
    }



    @AfterMethod
    public void teardown(){
  BrowserUtility.wait(3);
      driver.quit();
    }
}
