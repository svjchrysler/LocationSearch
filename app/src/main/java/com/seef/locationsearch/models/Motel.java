package com.seef.locationsearch.models;

/**
 * Created by jhonsalguero on 6/1/17.
 */

public class Motel {
    private int Id;
    private String Name;
    private float Latitude;
    private float Longitude;
    private float Score;
    private int photos;

    public Motel(String name, float latitude, float longitude, float score, int photos) {
        Name = name;
        Latitude = latitude;
        Longitude = longitude;
        Score = score;
        this.photos = photos;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getLatitude() {
        return Latitude;
    }

    public void setLatitude(float latitude) {
        Latitude = latitude;
    }

    public float getLongitude() {
        return Longitude;
    }

    public void setLongitude(float longitude) {
        Longitude = longitude;
    }

    public float getScore() {
        return Score;
    }

    public void setScore(float score) {
        Score = score;
    }

    public int getPhotos() {
        return photos;
    }

    public void setPhotos(int photos) {
        this.photos = photos;
    }
}
