package com.gyawalibros.controllers;

import com.gyawalibros.Service.CloudinaryService;
import com.gyawalibros.Service.PropertyService;
import com.gyawalibros.domain.PhotoURL;
import com.gyawalibros.domain.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestPropertyController {

    @Autowired
    PropertyService propertyService;

    @Autowired
    CloudinaryService cloudinaryService;

    @GetMapping("/propertyList")
    public ResponseEntity<Object> getAllProperty() {
        List<Property> properties = propertyService.getAllProperties();
        List<Property> activeProperties = new ArrayList<>();
        for (Property property :
                properties) {
            property.setUser(null);
            property.setWaterSupply(PropertyController.convertCurrency(property.getPrice()));
            List<PhotoURL> photoURLProcessed = cloudinaryService.processCloudinaryPhotoURLsForHome(property.getPhotoURLs());
            property.setBathroom(photoURLProcessed.get(0).getPhotoURL());

            property.setPhotoURLs(null);
        }
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/propertyListSingleRoom")
    public ResponseEntity<Object> getSingleRoomProperty() {
        List<Property> properties = propertyService.getSingleRoomProperties();
        for (Property property :
                properties) {
            property.setUser(null);
            property.setWaterSupply(PropertyController.convertCurrency(property.getPrice()));
            property.setWaterSupply(PropertyController.convertCurrency(property.getPrice()));
            List<PhotoURL> photoURLProcessed = cloudinaryService.processCloudinaryPhotoURLsForHome(property.getPhotoURLs());
            property.setBathroom(photoURLProcessed.get(0).getPhotoURL());

            property.setPhotoURLs(null);
        }
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/propertyListFlat")
    public ResponseEntity<Object> getFlatProperty() {
        List<Property> properties = propertyService.getFlatProperties();
        for (Property property :
                properties) {
            property.setUser(null);
            property.setWaterSupply(PropertyController.convertCurrency(property.getPrice()));
            property.setWaterSupply(PropertyController.convertCurrency(property.getPrice()));
            List<PhotoURL> photoURLProcessed = cloudinaryService.processCloudinaryPhotoURLsForHome(property.getPhotoURLs());
            property.setBathroom(photoURLProcessed.get(0).getPhotoURL());

            property.setPhotoURLs(null);
        }
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/propertyListHouse")
    public ResponseEntity<Object> getHouseProperty() {
        List<Property> properties = propertyService.getHouseProperties();
        for (Property property :
                properties) {
            property.setUser(null);
            property.setWaterSupply(PropertyController.convertCurrency(property.getPrice()));
            property.setWaterSupply(PropertyController.convertCurrency(property.getPrice()));
            List<PhotoURL> photoURLProcessed = cloudinaryService.processCloudinaryPhotoURLsForHome(property.getPhotoURLs());
            property.setBathroom(photoURLProcessed.get(0).getPhotoURL());

            property.setPhotoURLs(null);
        }
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/propertyList/{propertyId}")
    public ResponseEntity<Object> getProperty(@PathVariable String propertyId) {
        Property property = propertyService.getProperty(propertyId);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }

}
