package org.example;

import static org.junit.jupiter.api.Assertions.*;

class ArtGalleryTest {
    //A blueprint
    //holds no logic, just the information of 1 piece of art

    private String title;
    private String artist;

    public ArtGalleryTest(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

}