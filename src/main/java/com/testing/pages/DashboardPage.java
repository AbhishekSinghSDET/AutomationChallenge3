package com.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {

    private final WebDriver driver;
    private final By usernameLabel = By.cssSelector("p span.ng-binding");
    private final By btnProfileIcon = By.xpath("(//button[@title='User menu'])[2]");
    private final By btnLogout = By.xpath("//li[@class='dropdown-menu-list-item' and text()='Logout'] ");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLoggedInUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(usernameLabel)).getText();
    }

    public boolean isDashboardVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlContains("/dashboard"));
    }

    public void clickOnProfileIcon() {
        driver.findElement(btnProfileIcon).click();
    }

    public void clickOnLogoutBtn() {
        driver.findElement(btnLogout).click();
    }
}
