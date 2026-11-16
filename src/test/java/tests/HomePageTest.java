package tests;

import constants.AppConstant;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test(priority = 1)
    void verifyTitle() {
        String actualTitle = homePage.getPageTitle();
        Assert.assertEquals(actualTitle, AppConstant.HOME_PAGE_TITLE);
    }

    @Test(priority = 2)
    void searchProdcut() {
        String product = "Macbook";
        homePage.setSearchText(product);
        homePage.clickSearchBtn();
        String actualSearchHeader = homePage.getSearchResultHeader();
        Assert.assertEquals(actualSearchHeader, "Search - " + product);
    }

    @Test(priority = 3, dataProvider = "getProduct")
    void searchProdcutDD(String product) {
        homePage.setSearchText(product);
        homePage.clickSearchBtn();
        String actualSearchHeader = homePage.getSearchResultHeader();
        Assert.assertEquals(actualSearchHeader, "Search - " + product);
    }

    @DataProvider
    public String[] getProduct() {
        return new String[]{"Macbook", "iMac", "iPod", "iPhone"};
    }
}
