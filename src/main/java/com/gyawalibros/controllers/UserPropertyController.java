package com.gyawalibros.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.gyawalibros.Repository.PhotoURLRepository;
import com.gyawalibros.Repository.UserRepository;
import com.gyawalibros.Service.*;
import com.gyawalibros.domain.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserPropertyController {

    @Autowired
    PropertyService propertyService;

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    PhotoURLService photoURLService;

    @GetMapping("/property/list")
    public String listUserProperties(Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Property> properties = propertyService.findUserProperties(userDetails);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (Property property :
                properties) {
            Date date = property.getCreatedDate();
            property.setBathroom(dateFormat.format(property.getCreatedDate()));
            property.setWaterSupply(PropertyController.convertCurrency(property.getPrice()));
        }

        model.addAttribute("properties", properties);

        return "user/property/list";
    }

    @GetMapping("/property/add")
    public String addProperty(Model model) {
        Property property = new Property();
        model.addAttribute("property", property);
        return "user/property/add";
    }

    @PostMapping("/property/add")
    public String appPostProperty(@Valid @ModelAttribute("property") Property property, BindingResult bindingResult, @RequestParam("images") MultipartFile[] images, HttpServletRequest httpServletRequest, Model model) throws IOException {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean bind = bindingResult.hasErrors();
        if (bindingResult.hasErrors()) {
            model.addAttribute("property", property);
            return "/user//property/add";
        }

        String mapButtonPressed = httpServletRequest.getParameter("map-button-pressed");

        propertyService.addProperty(property, userDetails, mapButtonPressed);

        int imagesCount = 0;
        for (MultipartFile image :
                images) {
            if (!image.isEmpty()) {
                String imageUrl = cloudinaryService.upload(image);
                photoURLService.addPhotoURLToProperty(property, imageUrl);
                imagesCount++;
            }
        }

        if (imagesCount == 0) {
            photoURLService.addPhotoURLToProperty(property, "alryoavowbfd2rvsmqsz");
        }

        return "redirect:/user/property/list";
    }

    @RequestMapping("/property/view/{propertyId}")
    public String viewUserProperty(@PathVariable String propertyId, Model model) {
        Long propertyIdLong = Long.valueOf(propertyId);

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Property property = propertyService.getUserProperty(propertyIdLong, userDetails);

        model.addAttribute("property", property);
        return "user/property/view";
    }

    @RequestMapping("/property/update/{propertyId}")
    public String updateUserProperty(@PathVariable String propertyId, Model model) {
        Long propertyIdLong = Long.valueOf(propertyId);
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Property property = propertyService.getUserProperty(propertyIdLong, userDetails);
        property.setUser(null);
        model.addAttribute("property", property);
        return "user/property/update";
    }

    @PostMapping("/property/update")
    public String updateByPutUserProperty(@ModelAttribute Property property) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        propertyService.updatePropertyByUser(property, userDetails);
        return "redirect:/user/property/list";
    }

    @GetMapping("/property/delete/{propertyId}")
    public String deleteUserProperty(@PathVariable String propertyId) {
        Long propertyIdLong = Long.valueOf(propertyId);
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        propertyService.removeUserProperty(propertyIdLong, userDetails);
        return "redirect:/user/property/list";
    }

    @GetMapping("/property/sold/{id}")
    public String sold(@PathVariable Long id) {
        Long propertyIdLong = Long.valueOf(id);
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Property property = propertyService.getUserProperty(propertyIdLong, userDetails);
        propertyService.sold(property);
        return "redirect:/user/property/list";
    }
}
