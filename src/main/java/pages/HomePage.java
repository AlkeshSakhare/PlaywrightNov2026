package pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private Page page;
    private String myAccountBtn = "span:has-text('My Account')";
    private String loginBtn = "a:text('Login')";
    private String registerBtn = "a:text('Register')";
    private String searchBar = "[name='search']";
    private String searchBtn = "#search button";
    private String searchResultHeader = "#content h1";

    public HomePage(Page page) {
        this.page = page;
    }

    public String getSearchResultHeader() {
        String searchHeaderText = page.textContent(searchResultHeader);
        System.out.println("searchHeaderText : " + searchHeaderText);
        return searchHeaderText;
    }

    public String getPageTitle() {
        String title = page.title();
        System.out.println("Title : " + title);
        return title;
    }

    public void clickRegisterBtn() {
        page.click(registerBtn);
    }

    public void clickMyAccountBtn() {
        page.click(myAccountBtn);
    }

    public void clickLoginBtn() {
        page.click(loginBtn);
    }

    public void setSearchText(String item) {
        page.fill(searchBar, item);
    }

    public void clickSearchBtn() {
        page.click(searchBtn);
    }
}
