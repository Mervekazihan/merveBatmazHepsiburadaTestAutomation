package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class ProductDetailPage extends BasePage{

    By productReviewSectionXpath = By.xpath("//*[@id=\"comments-container\"]/a");
    By dropDownFieldClassName = By.className("hermes-Sort-module-Zwr_VRf_e4tZXl5J1PgT");
    By dropDownFieldOptionXpath = By.xpath("//*[@id=\"hermes-voltran-comments\"]/div[5]/div[1]/div[1]/div[3]/div/div[2]/div/div[2]/div/div[3]");
    By commentClassName = By.className("hermes-ReviewCard-module-dY_oaYMIo0DJcUiSeaVW");
    By thumbsUpClassName = By.className("hermes-ReviewCard-module-PbSfiSWIgfswGGBaOrw7");
    By thankYouMessageClassName = By.className("hermes-ReviewCard-module-eFtSSTU4lYZLCnzHtzca");
    By likeButtonClassName = By.className("customerAccount-Like-13gj2");

    public ProductDetailPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String goProductReviewSectionAndSelectThumbsUp() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productReviewSectionXpath));
        WebElement productReviewSection = driver.findElement(productReviewSectionXpath);
        productReviewSection.click();

        selectSortingFromDropdown();
        String thanksMessage = clickThumbsUpAndCheckThanksMessage();
        return thanksMessage;
    }

    public void selectSortingFromDropdown() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(dropDownFieldClassName));
        Actions actions = new Actions(driver);
        Actions clickDropDown= actions.moveToElement(driver.findElement(dropDownFieldClassName));
        clickDropDown.scrollToElement(driver.findElement(dropDownFieldClassName)).perform();
        clickDropDown.click().build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(dropDownFieldOptionXpath));
        WebElement productReviewSelectSorting = driver.findElement(dropDownFieldOptionXpath);
        productReviewSelectSorting.click();
    }

    public WebElement selectRandomCustomerComment() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        List<WebElement> comments = driver.findElements(commentClassName);
        Random random = new Random();
        int selectRandomCommentNumber = random.nextInt(comments.size());
        WebElement selectedComment = comments.get(selectRandomCommentNumber);
        return selectedComment;
    }

    public String clickThumbsUpAndCheckThanksMessage () {
        WebElement customerComment = selectRandomCustomerComment();
        if (customerComment != null) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(thumbsUpClassName));
            Actions actions = new Actions(driver);
            WebElement clickThumbs = customerComment.findElement(thumbsUpClassName);
            actions.scrollToElement(customerComment).perform();
            clickThumbs.click();
            WebElement getMessage = customerComment.findElement(thankYouMessageClassName);
            return getMessage.getText();
        }
        return null;
    }

    public void clickLikeButtonThenCheck() {
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(likeButtonClassName));
        WebElement likeButton = driver.findElement(likeButtonClassName);
        actions.scrollToElement(likeButton).perform();
        likeButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(likeButtonClassName));
        if (likeButton.getText().equals("Beğen")) {
            likeButton.click();
        }
    }

}
