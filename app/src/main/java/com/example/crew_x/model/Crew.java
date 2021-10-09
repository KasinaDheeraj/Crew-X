package com.example.crew_x.model;

import com.google.gson.annotations.SerializedName;

public class Crew {
    private String name;
    private String agency;
    @SerializedName("image")
    private String imageUrl;
    @SerializedName("wikipedia")
    private String wikiLink;
    private String status;

    public String getName() {
        return name;
    }

    public String getAgency() {
        return agency;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public String getStatus() {
        return status;
    }
}
