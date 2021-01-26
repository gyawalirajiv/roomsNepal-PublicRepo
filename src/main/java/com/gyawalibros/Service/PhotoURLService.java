package com.gyawalibros.Service;

import com.gyawalibros.Repository.PhotoURLRepository;
import com.gyawalibros.domain.PhotoURL;
import com.gyawalibros.domain.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoURLService {

    @Autowired
    private PhotoURLRepository photoURLRepository;


    public void addPhotoURLToProperty(Property property, String photoURLString) {
        PhotoURL photoURL = new PhotoURL();
        photoURL.setProperty(property);
        photoURL.setPhotoURL(photoURLString);
        photoURLRepository.save(photoURL);
    }
}
