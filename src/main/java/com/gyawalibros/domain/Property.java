package com.gyawalibros.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 5000)
    private String description;

    private String propertyAids;

    private String type;

    private String area;

    private String longitude;

    private String latitude;

    private String accessRoad;

    private String roomSize;

    private String floorNumber;

    private String numberOfRooms;

    private String bathroom;

    private String waterSupply;

    private Date createdDate;

    private Date modifiedDate;

    private String views;

    private boolean sold;

    private boolean active;

    private boolean parkingSpace;

    private boolean internetFacility;

    private boolean TvCable;

    private boolean petsAllowed;

    @NotNull
    @Min(value = 1000, message = "Price should be above Rs. 1,000")
    @Max(value = 1000000, message = "Price should be below Rs. 10,00,000")
    private int price;

    private boolean isMapInput;

    @NotNull
    @Size(min = 3, max = 50, message = "Please input a valid location.")
    private String location;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "property")
    private List<PhotoURL> photoURLs;

    @ManyToOne()
    private User user;

    public Property() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPropertyAids() {
        return propertyAids;
    }

    public void setPropertyAids(String propertyAids) {
        this.propertyAids = propertyAids;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAccessRoad() {
        return accessRoad;
    }

    public void setAccessRoad(String accessRoad) {
        this.accessRoad = accessRoad;
    }

    public String getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(String roomSize) {
        this.roomSize = roomSize;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(String numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public String getWaterSupply() {
        return waterSupply;
    }

    public void setWaterSupply(String waterSupply) {
        this.waterSupply = waterSupply;
    }

    public boolean isMapInput() {
        return isMapInput;
    }

    public void setMapInput(boolean mapInput) {
        isMapInput = mapInput;
    }

    public boolean isParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(boolean parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public boolean isInternetFacility() {
        return internetFacility;
    }

    public void setInternetFacility(boolean internetFacility) {
        this.internetFacility = internetFacility;
    }

    public boolean isTvCable() {
        return TvCable;
    }

    public void setTvCable(boolean tvCable) {
        TvCable = tvCable;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PhotoURL> getPhotoURLs() {
        return photoURLs;
    }

    public void setPhotoURLs(List<PhotoURL> photoURLs) {
        this.photoURLs = photoURLs;
    }
}
