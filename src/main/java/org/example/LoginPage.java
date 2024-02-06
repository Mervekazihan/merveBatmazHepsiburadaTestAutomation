package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static util.Constants.PASSWORD;
import static util.Constants.USER_NAME;

public class LoginPage extends BasePage{

    By userNameFieldId = By.id("txtUserName");
    By passwordFieldId = By.id("txtPassword");
    By buttonLoginId = By.id("btnLogin");
    By buttonEmailSelectId = By.id("btnEmailSelect");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void login() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameFieldId));
        WebElement userNameField = driver.findElement(userNameFieldId);
        userNameField.sendKeys(USER_NAME);
        WebElement buttonLogin = driver.findElement(buttonLoginId);
        buttonLogin.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFieldId));
        WebElement passwordField = driver.findElement(passwordFieldId);
        passwordField.sendKeys(PASSWORD);
        WebElement buttonEmailSelect = driver.findElement(buttonEmailSelectId);
        buttonEmailSelect.click();
    }
}
