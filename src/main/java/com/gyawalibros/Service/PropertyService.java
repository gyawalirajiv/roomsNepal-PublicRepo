package com.gyawalibros.Service;

import com.gyawalibros.Repository.PhotoURLRepository;
import com.gyawalibros.Repository.PropertyRepository;
import com.gyawalibros.Repository.UserRepository;
import com.gyawalibros.domain.PhotoURL;
import com.gyawalibros.domain.Property;
import com.gyawalibros.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PhotoURLRepository photoURLRepository;

    @Autowired
    private UserRepository userRepository;

    public void addProperty(Property property, UserDetailsImpl userDetail, String mapButtonPressed) {
        User user = userDetail.getUser();

        Date currentDate = Calendar.getInstance().getTime();
        property.setCreatedDate(currentDate);
        property.setPropertyAids("3 Months");
        property.setUser(user);

        boolean isMapInput = false;
        if (mapButtonPressed.equals("true")) {
            isMapInput = true;
        }


        property.setMapInput(isMapInput);

        property.setActive(true);

        propertyRepository.save(property);
    }

    public List<Property> findUserProperties(UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        Iterable<Property> properties = propertyRepository.findAllByUser(user);
        return (List<Property>) properties;
    }

    public Property getUserProperty(Long propertyId, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        Property property = propertyRepository.findOne(propertyId);
        if (property.getUser().getId() == user.getId()) {
            return property;
        }

        return null;
    }

    public void updatePropertyByUser(Property property, UserDetailsImpl userDetails) {
        Property propertyFromRepo = propertyRepository.findOne(property.getId());
        property.setUser(propertyFromRepo.getUser());

        property.setCreatedDate(propertyFromRepo.getCreatedDate());
        property.setLatitude(propertyFromRepo.getLatitude());
        property.setLongitude(propertyFromRepo.getLongitude());

        User user = userDetails.getUser();
        if (propertyFromRepo.getUser().getId() == user.getId()) {
            propertyRepository.save(property);
        }
    }

    public void removeUserProperty(Long propertyId, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        Property property = propertyRepository.findOne(propertyId);
        if (property.getUser().getId() == user.getId()) {
            propertyRepository.delete(propertyId);
        }
    }

    public void sold(Property property) {
        if (property.isSold()) {
            property.setSold(false);
        } else {
            property.setSold(true);
        }
        propertyRepository.save(property);
    }

    public Property getProperty(String propertyId) {
        Long propertyIdLong = Long.valueOf(propertyId);
        Property property = propertyRepository.findOne(propertyIdLong);
        property.setUser(null);
        return property;
    }

    public List<Property> reverseProperties(List<Property> properties) {
        List<Property> reversedProperties = new ArrayList<Property>();
        for (int i = properties.size() - 1; i >= 0; i--) {
            reversedProperties.add(properties.get(i));
        }
        return reversedProperties;
    }

    public List<Property> getAllProperties() {
        List<Property> properties = (List<Property>) propertyRepository.findAll();
        List<Property> unsoldProperties = new ArrayList<>();
        for (Property property :
                properties) {
            if (!property.isSold()) {
                unsoldProperties.add(property);
            }
        }
        return reverseProperties(unsoldProperties);
    }


    public List<Property> getSingleRoomProperties() {
        List<Property> properties = (List<Property>) propertyRepository.findAll();
        List<Property> singleRoomProperties = new ArrayList<Property>();
        for (Property property :
                properties) {
            if (property.getType().equals("Room")) {
                singleRoomProperties.add(property);
            }
        }
        List<Property> unsoldProperties = new ArrayList<>();
        for (Property property :
                singleRoomProperties) {
            if (!property.isSold()) {
                unsoldProperties.add(property);
            }
        }
        return reverseProperties(unsoldProperties);
    }

    public List<Property> getFlatProperties() {
        List<Property> properties = (List<Property>) propertyRepository.findAll();
        List<Property> flatProperties = new ArrayList<Property>();
        for (Property property :
                properties) {
            if (property.getType().equals("Flat")) {
                flatProperties.add(property);
            }
        }
        List<Property> unsoldProperties = new ArrayList<>();
        for (Property property :
                flatProperties) {
            if (!property.isSold()) {
                unsoldProperties.add(property);
            }
        }
        return reverseProperties(unsoldProperties);
    }

    public List<Property> getHouseProperties() {
        List<Property> properties = (List<Property>) propertyRepository.findAll();
        List<Property> houseProperties = new ArrayList<Property>();
        for (Property property :
                properties) {
            if (property.getType().equals("House")) {
                houseProperties.add(property);
            }
        }
        List<Property> unsoldProperties = new ArrayList<>();
        for (Property property :
                houseProperties) {
            if (!property.isSold()) {
                unsoldProperties.add(property);
            }
        }
        return reverseProperties(unsoldProperties);
    }

    public Property getPropertyAndUsername(String propertyId) {
        Long propertyIdLong = Long.valueOf(propertyId);
        Property property = propertyRepository.findOne(propertyIdLong);
        return property;
    }

    public List<Property> getPropertyByLocation(String location) {
        List<Property> properties = (List<Property>) propertyRepository.findAllByLocation(location);
        return properties;
    }


}
