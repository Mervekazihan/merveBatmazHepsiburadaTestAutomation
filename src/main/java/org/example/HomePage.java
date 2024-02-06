package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class HomePage extends BasePage{

    By searchFieldClassName = By.className("searchBoxOld-M1esqHPyWSuRUjMCALPK");
    By searchInputClassName = By.className("theme-IYtZzqYPto8PhOx3ku3c");
    By searchButtonClassName = By.className("searchBoxOld-yDJzsIfi_S5gVgoapx6f");
    By productsClassName = By.className("productListContent-zAP0Y5msy8OHn5z7T_K_");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void searchProductAndGoProductDetailPage(String searchValue) {
        WebElement searchField = driver.findElement(searchFieldClassName);
        searchField.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        searchProduct(searchValue);

        WebElement randomProduct = selectRandomProduct();
        randomProduct.click();

        switchToProductTab();
    }

    public void searchProduct(String searchValue) {
        WebElement searchInput = driver.findElement(searchInputClassName);
        searchInput.sendKeys(searchValue);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        closeCookieWindow();

        WebElement searchButton = driver.findElement(searchButtonClassName);
        searchButton.click();
    }

    public WebElement selectRandomProduct() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        List<WebElement> products = driver.findElements(productsClassName);
        Random random = new Random();
        int selectRandomProductNumber = random.nextInt(products.size());
        WebElement selectedProduct = products.get(selectRandomProductNumber);
        return selectedProduct;
    }

    public void switchToProductTab() {
        wait.until(numberOfWindowsToBe(2));
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

}
