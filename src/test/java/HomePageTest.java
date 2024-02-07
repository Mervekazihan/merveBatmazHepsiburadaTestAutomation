import jdk.jfr.Description;
import org.example.BasePage;
import org.example.HomePage;
import org.example.ProductDetailPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static util.Constants.HOME_PAGE_URL;

public class HomePageTest extends BasePageTest{

    BasePage basePage;
    HomePage homePage;
    ProductDetailPage productDetailPage;

    @BeforeTest
    public void before() {
        super.before();
        homePage = goToUrl(new HomePage(driver, wait), HOME_PAGE_URL);
        basePage = new BasePage(driver, wait);
        basePage.closeCookieWindow();
    }

    @Description("Case-1: The product is selected, go to the product detail page and then click thumbsUp on the comments of the selected product. The incoming message is checked.")
    @Test(priority = 1)
    public void searchProductAndGo() {
        homePage.searchProductAndGoProductDetailPage("iphone");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        productDetailPage = goToUrl(new ProductDetailPage(driver, wait), driver.getCurrentUrl());
        String message = productDetailPage.goProductReviewSectionAndSelectThumbsUp();
        Assert.assertEquals(message, "Teşekkür Ederiz.");
    }

    @Description("Case-2 : The product is selected, go to the product detail page and then click Like button and check.")
    @Test(priority = 2)
    public void checkProductLikeButton() {
        homePage.searchProductAndGoProductDetailPage("iphone");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        productDetailPage = goToUrl(new ProductDetailPage(driver, wait), driver.getCurrentUrl());
        productDetailPage.clickLikeButtonThenCheck();

    }
}
