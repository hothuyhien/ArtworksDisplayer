package org.selenium.tests;

import org.testng.annotations.Test;
import org.selenium.base.BaseTest;

import static org.testng.Assert.*;

public class DisplayTest extends BaseTest {
    @Test
    public void testArtworkIsVisibleOnLoad() {
        assertTrue(displayPage.isImageDisplayed());
        assertFalse(displayPage.getTitle().isEmpty());
        assertFalse(displayPage.getArtist().isEmpty());
    }

    @Test
    public void testNextArtworkLoadsOnClick() throws InterruptedException {
        String initialTitle = displayPage.getTitle();
        displayPage.clickNext();
        wait(3000);
        assertNotEquals(displayPage.getTitle(), initialTitle);
    }

    @Test
    public void testPrevArtworkLoadsOnClick() throws InterruptedException {
        displayPage.clickNext();
        wait(3000);
        String nextTitle = displayPage.getTitle();
        displayPage.clickPrev();
        wait(3000);
        assertNotEquals(displayPage.getTitle(), nextTitle);
    }

//    @Test
//    public void testErrorDisplayedWhenBackendDown() {
//        //Backend is OFF
//        assertFalse(displayPage.getErrorMessage().isEmpty());
//    }
}
