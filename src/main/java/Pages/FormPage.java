package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormPage {

    private WebDriver driver;
    private WebElement username;
    private WebElement password;
    private WebElement loginButton;

    public FormPage(WebDriver driver){
        this.driver= driver;

        username = driver.findElement(By.name("user-name"));
        password = driver.findElement(By.name("password"));
        loginButton = driver.findElement(By.name("login-button"));
    }


    public String field_get_attribute(String field,String attribute){
        if (field.equals("user-name")){
            return username.getAttribute(attribute);
        } else if (field.equals("password")) {
            return password.getAttribute(attribute);
        } else if (field.equals("login-button")) {
            return loginButton.getAttribute(attribute);
        }
        return null;

    }
    public void enterCredentials(String user, String pass){

        username.clear();
        password.clear();

        username.sendKeys(user);
        password.sendKeys(pass);
        loginButton.click();
    }

    public String validLogin(String user,String pass){
        enterCredentials(user,pass);

        return driver.findElement(By.cssSelector(".app_logo")).getText();
    }


    public String wrongCredentials(String user,String pass){
        enterCredentials(user,pass);
        return driver.findElement(By.cssSelector(".error-message-container.error")).getText();
    }


}
