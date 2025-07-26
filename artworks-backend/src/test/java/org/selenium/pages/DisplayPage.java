package org.selenium.pages;

import org.openqa.selenium.By;
import org.selenium.base.BasePage;

public class DisplayPage extends BasePage {
    private By title = By.id("art-title");
    private By artist = By.id("art-artist");
    private By image = By.id("art-image");
    private By prevButton = By.id("prev-button");
    private By nextButton = By.id("next-button");
    private By errorMsg = By.id("error-message");

    public String getTitle() {
        return find(title).getText();
    }

    public String getArtist() {
        return find(artist).getText();
    }

    public boolean isImageDisplayed() {
        return find(image).isDisplayed();
    }

    public void clickNext() {
        click(nextButton);
    }

    public void clickPrev() {
        click(prevButton);
    }

    public String getErrorMessage() {
        return find(errorMsg).getText();
    }
}
