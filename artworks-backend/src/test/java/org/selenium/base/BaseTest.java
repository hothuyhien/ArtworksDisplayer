package org.selenium.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.selenium.pages.DisplayPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected WebDriver driver;
    protected BasePage basePage;
    protected DisplayPage displayPage;
    private String url = "http://localhost:3000";

    @BeforeClass
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        basePage = new BasePage();
        basePage.setDriver(driver);
        displayPage = new DisplayPage();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
