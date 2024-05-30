package tests;

import Pages.FormPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FormTests {

    private WebDriver driver;
    private FormPage formPage;

    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver();
    }


    @BeforeMethod
    public void goHome() {

        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

        formPage= new FormPage(driver);
    }


    @Test
    public void CheckFieldsIDsTest(){
        String id = "id";

        SoftAssert soft = new SoftAssert();

        soft.assertEquals(formPage.field_get_attribute("user-name",id),"user-name","First assertion");
        soft.assertEquals(formPage.field_get_attribute("password",id),"password","Second assertion");
        soft.assertEquals(formPage.field_get_attribute("login-button",id),"login-button","Third assertion");

        soft.assertAll();
    }

    @Test
    public void validLoginTest(){
        assertEquals(formPage.validLogin("standard_user","secret_sauce"),"Swag Labs");
    }


    @Test
    public void invalidLoginTest() {
        assertEquals(formPage.wrongCredentials("ss","aa"),"Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void blankUsernameAndPasswordTest(){
        assertEquals(formPage.wrongCredentials("",""),"Epic sadface: Username is required");

    }

    @Test
    public void blankUsernameTest(){
        assertEquals(formPage.wrongCredentials("","aa"),"Epic sadface: Username is required");
    }

    @Test
    public void blankPasswordTest(){
        assertEquals(formPage.wrongCredentials("aa",""),"Epic sadface: Password is required");
    }



    @AfterTest
    public void TearDown(){
        driver.quit();
    }
}
