package com.testing.tests;

import com.testing.pages.DashboardPage;
import com.testing.pages.LoginPage;
import com.testing.utils.ConfigReader;
import com.testing.utils.DriverManager;
import com.testing.utils.ExcelDataProvider;
import jdk.jfr.Description;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;


public class LoginTest {


        private WebDriver driver;
        private LoginPage loginPage;
        private DashboardPage dashboardPage;

        @BeforeMethod
        @Parameters("browser")
        public void setup(String browser) {
            DriverManager.setDriver(browser);
            driver = DriverManager.getDriver();
            driver.get(ConfigReader.getProperty("app.url"));
            loginPage = new LoginPage(driver);
            dashboardPage = new DashboardPage(driver);
        }

        @Test(dataProvider = "loginData", dataProviderClass = ExcelDataProvider.class)
        @Description("Verify login functionality and dashboard visibility")
        public void testLogin(String username, String password) {
            loginPage.login(username, password);

            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(dashboardPage.isDashboardVisible())
                    .as("Dashboard should be visible after login")
                    .isTrue();

            softly.assertThat(dashboardPage.getLoggedInUsername())
                    .as("Logged in username should match expected username")
                    .isEqualTo("Abhishek Singh");

            dashboardPage.clickOnProfileIcon();
            dashboardPage.clickOnLogoutBtn();

            softly.assertThat(loginPage.isLoginPageVisible())
                    .as("Login Page should be visible after logout")
                    .isTrue();

            softly.assertAll();
        }

        @AfterMethod
        public void tearDown() {
            DriverManager.quitDriver();
        }
}
