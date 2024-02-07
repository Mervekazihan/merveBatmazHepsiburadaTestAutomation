package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    By acceptCookieButtonId = By.id("onetrust-accept-btn-handler");


    public BasePage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public void closeCookieWindow() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(acceptCookieButtonId));
        if (driver.findElement(acceptCookieButtonId).isDisplayed()) {
            driver.findElement(acceptCookieButtonId).click();
        }
    }
}
