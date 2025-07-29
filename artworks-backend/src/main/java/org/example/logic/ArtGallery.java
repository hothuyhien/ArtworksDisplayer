package org.example.logic;

import org.example.model.Art;
import org.example.service.ArtService;
import org.json.JSONException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ArtGallery {
    //The logic: get data from ArtService, add logic interactions

    private int currentId;
    private final int MIN_ID = 1;
    private final int MAX_ID = 129000; //dunno yet

    private final ArtService artService;

    public ArtGallery(ArtService artService) {
        this.artService = artService;
        this.currentId = 0;
    }

    public Art getCurrentArtwork() throws JSONException, IOException {
        // If currentId is 0 (initial state), start from MIN_ID
        if (currentId == 0) {
            currentId = MIN_ID;
        }
        Art art = artService.getArtworkByIdSkippingInvalid(currentId, 1);
        this.currentId = artService.lastUsedId; // Update currentId with the actual found ID
        return art;
    }

    public Art getNextArtwork() throws JSONException, IOException {
        if (currentId < MAX_ID) currentId++;
        Art art = artService.getArtworkByIdSkippingInvalid(currentId, 1);
        this.currentId = artService.lastUsedId;
        return art;
    }

    public Art getPreviousArtwork() throws JSONException, IOException {
        if (currentId > MIN_ID) currentId--;
        Art art = artService.getArtworkByIdSkippingInvalid(currentId, -1);
        this.currentId = artService.lastUsedId;
        return art;
    }

    public int getCurrentId() {
        return currentId;
    }

    public int getMinId() {
        return MIN_ID;
    }

    public void setCurrentId(int id) {
        if (id >= MIN_ID && id <= MAX_ID)
            this.currentId = id;
    }
}