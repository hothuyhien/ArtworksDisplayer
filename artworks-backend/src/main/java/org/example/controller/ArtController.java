package org.example.controller;

import org.example.logic.ArtGallery;
import org.example.model.Art;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ArtController {
    //gives the data to the outside world

    private final ArtGallery artGallery;

    @Autowired
    //auto create and give ArtGallery object when ArtController constructor runs
    public ArtController(ArtGallery artGallery) {
        this.artGallery = artGallery;
    }

    @GetMapping("/artwork")
    public Art getCurrentArtwork() throws IOException, JSONException {
        return artGallery.getCurrentArtwork();
    }

    @GetMapping("/artwork/next")
    public Art getNextArtwork() throws IOException, JSONException {
        return artGallery.getNextArtwork();
    }

    @GetMapping("/artwork/previous")
    public Art getPreviousArtwork() throws IOException, JSONException {
        return artGallery.getPreviousArtwork();
    }

    @PostMapping("/artwork/{id}")
    public Art jumpToArtwork(@PathVariable int id) throws IOException, JSONException {
        artGallery.setCurrentId(id);
        return artGallery.getCurrentArtwork();
    }
}
