package org.example.controller;

import org.example.logic.ArtGallery;
import org.example.model.Art;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getPreviousArtwork() throws IOException, JSONException {
        if (artGallery.getCurrentId() <= artGallery.getMinId()) {
            JSONObject error = new JSONObject();
            error.put("message", "You're already at the first artwork. No previous artwork.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.toString());
        }

        Art art = artGallery.getPreviousArtwork();
        return ResponseEntity.ok(art);
    }


    @PostMapping("/artwork/{id}")
    public ResponseEntity<?> jumpToArtwork(@PathVariable("id") int id) throws IOException, JSONException {
        try {
            artGallery.setCurrentId(id);
            Art art = artGallery.getCurrentArtwork();
            return ResponseEntity.ok(art);
        } catch (IOException e) {
            JSONObject error = new JSONObject();
            error.put("message", "Could not find artwork with ID " + id + " or any nearby artwork.");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.toString());
        }
    }
}
