package Homework;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;


public class TestCases1_5 {

    private WebDriver driver;
    private String  URL = "https://practice-cybertekschool.herokuapp.com/";



   //for driver setup

   @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver =new ChromeDriver();
        driver.get(URL);
        BrowserUtility.wait(3);
        //driver.manage().window().maximize();
        //BrowserUtility.wait(3);

    }

    /*
    Test case #1
    Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
    Step 2. Click on “Registration Form”
    Step 3. Enter “wrong_dob” into date of birth input box.
    Step 4. Verify that warning message is displayed: “The date of birth is not valid”
     */
@Test
public void testCase1(){

    driver.findElement(By.linkText("Registration Form")).click();
    driver.findElement(By.name("birthday")).sendKeys("10-12-1000", Keys.ENTER);

    BrowserUtility.wait(3);
    WebElement warningMessage = driver.findElement(By.cssSelector("small[data-bv-validator='date']"));
    assertTrue(warningMessage.isDisplayed());
     String excepted = "The date of birth is not valid";
     String actual =warningMessage.getText();
    Assert.assertEquals(actual,excepted);


}
/*
Test case #2
Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
Step 2. Click on “Registration Form”
Step 3. Verify that following options for programming languages are displayed: c++, java, JavaScript
 */

@Test
 public void testCase2() {
    driver.findElement(By.linkText("Registration Form")).click();
    BrowserUtility.wait(3);
    List<WebElement> elements = driver.findElements(By.className("form-check-input"));
    for(WebElement each : elements){
       Assert.assertTrue(each.isDisplayed());
    }

}

/*
Test case #3
 Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
 Step 2. Click on “Registration Form”
 Step 3. Enter only one alphabetic character into first name input box.
 Step 4. Verify that warning message is displayed: “first name must be more than 2 and less than 64 characters long”
 */

@Test
public void testcase3(){
    driver.findElement(By.linkText("Registration Form")).click();
    BrowserUtility.wait(3);
     driver.findElement(By.name("firstname")).sendKeys("T",Keys.ENTER);
   // BrowserUtility.wait(3);
    WebElement warningElement = driver.findElement(By.cssSelector("small[data-bv-validator='stringLength']"));
    assertTrue(warningElement.isDisplayed());

    String expected ="first name must be more than 2 and less than 64 characters long";
     String actual = warningElement.getText();
     Assert.assertEquals(expected,actual);


}

/*
Test case #4
Step 1. Go to https://practice-cybertekschool.herokuapp.com
Step 2. Click on “Registration Form”
Step 3. Enter only one alphabetic character into last name input box.
Step 4. Verify that warning message is displayed: “The last name must be more than 2 and less than 64 characters long”
 */
@Test
public void testcase4(){
    driver.findElement(By.linkText("Registration Form")).click();
    BrowserUtility.wait(3);
    driver.findElement(By.name("lastname")).sendKeys("B",Keys.ENTER);
    //BrowserUtility.wait(3);
    WebElement warningMessage = driver.findElement(By.xpath("//*[@id='registrationForm']/div[2]/div/small[2]"));
    assertTrue(warningMessage.isDisplayed());

    String expected = "The last name must be more than 2 and less than 64 characters long";
    String actual = warningMessage.getText();
    Assert.assertEquals(expected,actual);

}
/*
Test case #5
Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
Step 2. Click on “Registration Form”
Step 3. Enter any valid first name.
Step 4. Enter any valid last name.
Step 5. Enter any valid user name.
Step 6. Enter any valid password.
Step 7. Enter any valid phone number.
Step 8. Select gender.
Step 9. Enter any valid date of birth.
Step 10. Select any department.
Step 11. Enter any job title.
Step 12. Select java as a programming language.
 Step 13. Click Sign up.
 Step 14. Verify that following success message is displayed: “You've successfully completed registration!”
 Note: for using dropdown, please refer to the documentation: https://selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/Select.html or,
  please watch short video about drop-downs that is posted on canvas.
 */

@Test
public void testcase5(){
    driver.findElement(By.linkText("Registration Form")).click();
   // BrowserUtility.wait(3);
   driver.findElement(By.name("firstname")).sendKeys("Tugba",Keys.ENTER);
   driver.findElement(By.name("lastname")).sendKeys("Bekar",Keys.ENTER);
   driver.findElement(By.name("username")).sendKeys("Tbekar",Keys.ENTER);
   driver.findElement(By.name("email")).sendKeys("tugba@gmail.com",Keys.ENTER);
   driver.findElement(By.name("password")).sendKeys("12345678TB",Keys.ENTER);
   driver.findElement(By.name("phone")).sendKeys("571-678-4565",Keys.ENTER);
   driver.findElement(By.cssSelector("input[value='female']")).click();
    driver.findElement(By.name("birthday")).sendKeys("02/09/1989");

    Select department = new Select(driver.findElement(By.name("department")));
    department.selectByVisibleText("Department of Engineering");

    Select jobTitle =new Select(driver.findElement(By.name("job_title")));
    jobTitle.selectByVisibleText("SDET");

   driver.findElement(By.id("inlineCheckbox2")).click();
   driver.findElement(By.id("wooden_spoon")).click();

   String expected = "You've successfully completed registration!";
   String actual =driver.findElement(By.tagName("p")).getText();
   Assert.assertEquals(expected,actual);



}
//for driver close
    @AfterMethod
    public void teardownn(){
    BrowserUtility.wait(3);
        driver.quit();

    }

}
