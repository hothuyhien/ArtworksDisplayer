package org.example.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.example.model.Art;
import org.json.*;
import org.springframework.stereotype.Component;

@Component
public class ArtService {
    // the data fetcher: go to API -> bring back artworks

    public int lastUsedId = -1;

    public Art getArtworkByIdSkippingInvalid(int id, int step) throws IOException, JSONException {
        int maxRetries = 100; //max loop, avoid loop forever
        for (int i=0; i<maxRetries; i++) {
            try{
                String StringUrl = "https://api.artic.edu/api/v1/artworks/" + id;

                //Making API calls - Java networking

                //Connect to the API
                URL url = new URL(StringUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                if (conn.getResponseCode() == 404) {
                    id += step;
                    continue;
                }

                //Reading the response
                Scanner sc = new Scanner(conn.getInputStream());
                StringBuilder jsonString = new StringBuilder();
                while (sc.hasNext()) {
                    jsonString.append(sc.nextLine());
                }
                sc.close();

                //Parsing the JSON
                JSONObject jsonObject = new JSONObject(jsonString.toString());
                JSONObject data = jsonObject.getJSONObject("data");
                JSONObject config = jsonObject.getJSONObject("config");

                //Get title and artist
                String title = data.optString("title", "Error");
                String artist = data.optString("artist_title", "Error");

                //Get image
                String imageId = data.optString("image_id");
                String iiifUrl = config.optString("iiif_url");
                String imageUrl = imageId.isEmpty()
                        ? ""
                        : iiifUrl + "/" + imageId + "/full/843,/0/default.jpg";

                this.lastUsedId = id;
                System.out.println(id);

                return new Art(title, artist, imageUrl);
            } catch (IOException e) {
                id += step; //skip if fetch fails (timeout, 500, etc)
            }
        }
        throw new IOException("Couldnt find a valid artwork after " + maxRetries + " tries." );
    }


//    public List<Art> fetchArtworks(String query) {
//
//        List<Art> artworks = new ArrayList<>();
//
//        //talk to Art Institute API
//
//        String urlString = ""
//
//        //get JSON, turn it into a list of Art
//
//    }

}
