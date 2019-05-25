package br.com.bakingapp.services.recipeService.response;

import com.squareup.moshi.Json;

public class Step {
    @Json(name = "id")
    private final int id;

    @Json(name = "shortDescription")
    private final String shortDescription;

    @Json(name = "description")
    private final String description;

    @Json(name = "videoURL")
    private final String videoURL;

    @Json(name = "thumbnailURL")
    private final String thumbnailURL;

    public Step(int id, String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }
}
