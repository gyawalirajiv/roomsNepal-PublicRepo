package com.gyawalibros.controllers;

import com.gyawalibros.Service.CloudinaryService;
import com.gyawalibros.Service.PropertyService;
import com.gyawalibros.domain.PhotoURL;
import com.gyawalibros.domain.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class SearchFilterController {

    @Autowired
    PropertyService propertyService;

    @Autowired
    CloudinaryService cloudinaryService;

    @GetMapping("/searchFilter")
    public String searchFilter(Model model, @RequestParam(required = false) String page, @RequestParam(required = false) String minPrice, @RequestParam(required = false) String maxPrice, @RequestParam(required = false) String location, @RequestParam(required = false) String type) throws Exception {
        if (page == null) {
            page = "0";
        }

        int pageInt = Integer.parseInt(page);

        List<Property> properties = propertyService.getAllProperties();

        //Filter property for advanced search
        String filterDisplayMessage = new String();
        if (type == null) {
            type = "Select Rent Type";
        }
        if (location == null) {
            location = "";
        }
        if (minPrice == null) {
            minPrice = "";
        }
        if (maxPrice == null) {
            maxPrice = "";
        }
        if (!(type.equals("Select Rent Type"))) {
            List<Property> typeFilterProperties = new ArrayList<>();
            for (Property property :
                    properties) {
                if (property.getType().equals(type)) {
                    typeFilterProperties.add(property);
                }
            }
            properties = typeFilterProperties;
            filterDisplayMessage += " '" + type + "' ";
            model.addAttribute("type", type);
        }
        if (!location.equals("")) {
            List<Property> locationFilterProperties = new ArrayList<>();
            for (Property property :
                    properties) {
                if (property.getLocation().equals(location)) {
                    locationFilterProperties.add(property);
                }
            }
            filterDisplayMessage += " '" + location + "' ";
            model.addAttribute("location", location);
            properties = locationFilterProperties;
        }
        if (!minPrice.equals("")) {
            int minPriceInt = Integer.parseInt(minPrice);
            List<Property> minPriceFilterProperties = new ArrayList<>();
            for (Property property :
                    properties) {
                if (property.getPrice() > minPriceInt) {
                    minPriceFilterProperties.add(property);
                }
            }
            filterDisplayMessage += " 'Min Price: " + minPrice + "' ";
            model.addAttribute("minPrice", minPrice);
            properties = minPriceFilterProperties;
        }
        if (!maxPrice.equals("")) {
            int maxPriceInt = Integer.parseInt(maxPrice);
            List<Property> maxPriceFilterProperties = new ArrayList<>();
            for (Property property :
                    properties) {
                if (property.getPrice() < maxPriceInt) {
                    maxPriceFilterProperties.add(property);
                }
            }
            filterDisplayMessage += " 'Max Price: " + maxPrice + "' ";
            model.addAttribute("maxPrice", maxPrice);
            properties = maxPriceFilterProperties;
        }

        for (Property property :
                properties) {
            property.setUser(null);
            property.setWaterSupply(PropertyController.convertCurrency(property.getPrice()));
        }

        List<Property> propertiesFiltered = new ArrayList<Property>();

        int loopEnd = (pageInt * 10) + 10;
        for (int i = pageInt * 10; i < loopEnd; i++) {
            if (properties.size() == i) {
                break;
            }
            propertiesFiltered.add(properties.get(i));
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (Property property :
                properties) {

            property.setBathroom(dateFormat.format(property.getCreatedDate()));
            List<PhotoURL> photoURLSProcessed = cloudinaryService.processCloudinaryPhotoURLsForAdvancedSearch(property.getPhotoURLs());
            property.setPhotoURLs(photoURLSProcessed);
        }

        model.addAttribute("filterDisplayMessage", filterDisplayMessage);
        model.addAttribute("page", page);
        model.addAttribute("properties", propertiesFiltered);
        model.addAttribute("propertiesCount", properties.size());

        return "searchFilter";
    }
}
