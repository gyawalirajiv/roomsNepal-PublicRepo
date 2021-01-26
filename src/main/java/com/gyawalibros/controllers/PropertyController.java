package com.gyawalibros.controllers;

import com.gyawalibros.Service.CloudinaryService;
import com.gyawalibros.Service.PropertyService;
import com.gyawalibros.Service.UserDetailsImpl;
import com.gyawalibros.domain.PhotoURL;
import com.gyawalibros.domain.Property;
import com.gyawalibros.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.text.*;
import java.util.*;

@Controller
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @RequestMapping("/list")
    public String getAllProperties(Model model) {
        List<Property> properties = propertyService.getAllProperties();
        model.addAttribute("properties", properties);
        return "property/list";
    }

    @RequestMapping("/{propertyId}")
    public String getProperty(Model model, @PathVariable String propertyId) throws ParseException {
        Property property = propertyService.getPropertyAndUsername(propertyId);

        model.addAttribute("username", property.getUser().getFullName());
        model.addAttribute("userNumber", property.getUser().getPrimaryPhoneNumber());
        model.addAttribute("userSecondaryNumber", property.getUser().getSecondaryPhoneNumber());
        property.setUser(null);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = property.getCreatedDate();
        String dateString = dateFormat.format(date);
        model.addAttribute("date", dateString);
        model.addAttribute("property", property);

        String otherFacilities = "false";
        if (property.isParkingSpace() || property.isInternetFacility() || property.isTvCable() || property.isPetsAllowed()) {
            otherFacilities = "true";
        }
        model.addAttribute("otherFacilities", otherFacilities);

        //Related Properties
        List<Property> relatedProperties = propertyService.getPropertyByLocation(property.getLocation());
        relatedProperties.remove(property);

        Collections.shuffle(relatedProperties);
        if (relatedProperties.size() > 4) {
            relatedProperties = relatedProperties.subList(0, 4);
        }

        for (Property relatedProoerty :
                relatedProperties) {
            relatedProoerty.setBathroom(convertCurrency(relatedProoerty.getPrice()));
            List<PhotoURL> relatedPropertyProcessedURLs = new ArrayList<>();
            relatedPropertyProcessedURLs = cloudinaryService.processCloudinaryPhotoURLsForRelatedProperties(relatedProoerty.getPhotoURLs());
            relatedProoerty.setPhotoURLs(relatedPropertyProcessedURLs);
        }

        List<PhotoURL> photoURLSProcessed = cloudinaryService.processCloudinaryPhotoURLsForPropertyThumbnail(property.getPhotoURLs());
        property.setPhotoURLs(photoURLSProcessed);


        model.addAttribute("priceString", convertCurrency(property.getPrice()));
        model.addAttribute("relatedProperties", relatedProperties);

        return "property/property";
    }

    public static String convertCurrency(int price) {
        Format format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        String priceString = format.format(price);
        priceString = priceString.substring(4, priceString.length() - 3);
        return priceString;
    }
}
