package com.techelevator.model;

public class Landmark {


    private int landmarkId;
    private String landmarkName;
    private String landmarkPhoto;
    private String landmarkDescription;
    private String landmarkAddress;
    private String landmarkHoursOfOperation;
    private String landmarkVenueType;
    private int landmarkLikeCount;


    public Landmark(int landmarkId, String landmarkName, String landmarkPhoto, String landmarkDescription, String landmarkAddress, String landmarkHoursOfOperation, String landmarkVenueType, int landmarkLikeCount) {
        this.landmarkId = landmarkId;
        this.landmarkName = landmarkName;
        this.landmarkPhoto = landmarkPhoto;
        this.landmarkDescription = landmarkDescription;
        this.landmarkAddress = landmarkAddress;
        this.landmarkHoursOfOperation = landmarkHoursOfOperation;
        this.landmarkVenueType = landmarkVenueType;
    }

    public Landmark(String landmarkName, String landmarkPhoto, String landmarkDescription, String landmarkAddress, String landmarkHoursOfOperation, String landmarkVenueType, int landmarkLikeCount) {
        this.landmarkName = landmarkName;
        this.landmarkPhoto = landmarkPhoto;
        this.landmarkDescription = landmarkDescription;
        this.landmarkAddress = landmarkAddress;
        this.landmarkHoursOfOperation = landmarkHoursOfOperation;
        this.landmarkVenueType = landmarkVenueType;
    }

    public Landmark() {
    }

    public int getLandmarkLikeCount() {
        return landmarkLikeCount;
    }

    public void setLandmarkLikeCount(int landmarkLikeCount) {
        this.landmarkLikeCount = landmarkLikeCount;
    }

    public String getLandmarkHoursOfOperation() {
        return landmarkHoursOfOperation;
    }

    public void setLandmarkHoursOfOperation(String landmarkHoursOfOperation) {
        this.landmarkHoursOfOperation = landmarkHoursOfOperation;
    }

    public String getLandmarkVenueType() {
        return landmarkVenueType;
    }

    public void setLandmarkVenueType(String landmarkVenueType) {
        this.landmarkVenueType = landmarkVenueType;
    }

    public int getLandmarkId() {
        return landmarkId;
    }

    public void setLandmarkId(int landmarkId) {
        this.landmarkId = landmarkId;
    }

    public String getLandmarkName() {
        return landmarkName;
    }

    public void setLandmarkName(String landmarkName) {
        this.landmarkName = landmarkName;
    }

    public String getLandmarkPhoto() {
        return landmarkPhoto;
    }

    public void setLandmarkPhoto(String landmarkPhoto) {
        this.landmarkPhoto = landmarkPhoto;
    }

    public String getLandmarkDescription() {
        return landmarkDescription;
    }

    public void setLandmarkDescription(String landmarkDescription) {
        this.landmarkDescription = landmarkDescription;
    }

    public String getLandmarkAddress() {
        return landmarkAddress;
    }

    public void setLandmarkAddress(String landmarkAddress) {
        this.landmarkAddress = landmarkAddress;
    }

    @Override
    public String toString() {
        return "Landmark{" +
                "landmarkId=" + landmarkId +
                ", landmarkName='" + landmarkName + '\'' +
                ", landmarkPhoto='" + landmarkPhoto + '\'' +
                ", landmarkDescription='" + landmarkDescription + '\'' +
                ", landmarkAddress='" + landmarkAddress + '\'' +
                '}';
    }
}
