package org.example.model;

public class Art {
    //Holds no logic, just a box holding the info of 1 piece of Art
    private String title;
    private String artist;
    private String imgUrl;

    public Art(String title, String artist, String imgUrl) {
        this.title = title;
        this.artist = artist;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
