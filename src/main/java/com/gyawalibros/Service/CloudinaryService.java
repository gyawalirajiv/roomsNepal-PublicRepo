package com.gyawalibros.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.gyawalibros.config.CloudinaryConfig;
import com.gyawalibros.domain.PhotoURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    CloudinaryConfig cloudinaryConfig;

    public String upload(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();

        Cloudinary cloudinary = cloudinaryConfig.getCloudinary();
        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return uploadResult.get("public_id").toString();
    }

    public String getCloudinaryImage(String imageId) {
        Cloudinary cloudinary = cloudinaryConfig.getCloudinary();
        String photoURL = cloudinary.url().imageTag(imageId);
        return photoURL.substring(10, photoURL.length() - 3);
    }

    public List<PhotoURL> processCloudinaryPhotoURLs(List<PhotoURL> photoURLs) {
        List<PhotoURL> photoURLSProcessed = new ArrayList<>();
        List<String> photoURLSProcessedStrings = new ArrayList<>();
        for (PhotoURL photoURL :
                photoURLs) {
            photoURLSProcessedStrings.add(getCloudinaryImage(photoURL.getPhotoURL()));
        }
        for (String photoURLSProcessedString :
                photoURLSProcessedStrings) {
            PhotoURL photoURL = new PhotoURL();
            photoURL.setPhotoURL(photoURLSProcessedString);
            photoURLSProcessed.add(photoURL);
        }

        return photoURLSProcessed;
    }

    public String getCloudinaryImageForHome(String imageId) {
        Cloudinary cloudinary = cloudinaryConfig.getCloudinary();
        String photoURL = cloudinary.url().transformation(new Transformation().height(248).width(343).crop("fill")).imageTag(imageId);
        return photoURL.substring(10, photoURL.length() - 3);
    }

    public List<PhotoURL> processCloudinaryPhotoURLsForHome(List<PhotoURL> photoURLs) {
        List<PhotoURL> photoURLSProcessed = new ArrayList<>();
        List<String> photoURLSProcessedStrings = new ArrayList<>();
        for (PhotoURL photoURL :
                photoURLs) {
            photoURLSProcessedStrings.add(getCloudinaryImageForHome(photoURL.getPhotoURL()));
        }
        for (String photoURLSProcessedString :
                photoURLSProcessedStrings) {
            PhotoURL photoURL = new PhotoURL();
            photoURL.setPhotoURL(photoURLSProcessedString);
            photoURLSProcessed.add(photoURL);
        }

        return photoURLSProcessed;
    }

    public String getCloudinaryImageForPropertyThumbnail(String imageId) {
        Cloudinary cloudinary = cloudinaryConfig.getCloudinary();
        String photoURL = cloudinary.url().transformation(new Transformation().height(488).width(675).crop("fill")).imageTag(imageId);
        return photoURL.substring(10, photoURL.length() - 28);
    }

    public List<PhotoURL> processCloudinaryPhotoURLsForPropertyThumbnail(List<PhotoURL> photoURLs) {
        List<PhotoURL> photoURLSProcessed = new ArrayList<>();
        List<String> photoURLSProcessedStrings = new ArrayList<>();
        for (PhotoURL photoURL :
                photoURLs) {
            photoURLSProcessedStrings.add(getCloudinaryImageForPropertyThumbnail(photoURL.getPhotoURL()));
        }
        for (String photoURLSProcessedString :
                photoURLSProcessedStrings) {
            PhotoURL photoURL = new PhotoURL();
            photoURL.setPhotoURL(photoURLSProcessedString);
            photoURLSProcessed.add(photoURL);
        }

        return photoURLSProcessed;
    }

    public String getCloudinaryImageForAdvancedSearch(String imageId) {
        Cloudinary cloudinary = cloudinaryConfig.getCloudinary();
        String photoURL = cloudinary.url().transformation(new Transformation().height(217).width(300).crop("fill")).imageTag(imageId);
        return photoURL.substring(10, photoURL.length() - 28);
    }

    public List<PhotoURL> processCloudinaryPhotoURLsForAdvancedSearch(List<PhotoURL> photoURLs) {
        List<PhotoURL> photoURLSProcessed = new ArrayList<>();
        List<String> photoURLSProcessedStrings = new ArrayList<>();
        for (PhotoURL photoURL :
                photoURLs) {
            photoURLSProcessedStrings.add(getCloudinaryImageForAdvancedSearch(photoURL.getPhotoURL()));
        }
        for (String photoURLSProcessedString :
                photoURLSProcessedStrings) {
            PhotoURL photoURL = new PhotoURL();
            photoURL.setPhotoURL(photoURLSProcessedString);
            photoURLSProcessed.add(photoURL);
        }

        return photoURLSProcessed;
    }

    public String getCloudinaryImageForRelatedProperties(String imageId) {
        Cloudinary cloudinary = cloudinaryConfig.getCloudinary();
        String photoURL = cloudinary.url().transformation(new Transformation().height(248).width(343).crop("fill")).imageTag(imageId);
        return photoURL.substring(10, photoURL.length() - 28);
    }

    public List<PhotoURL> processCloudinaryPhotoURLsForRelatedProperties(List<PhotoURL> photoURLs) {
        List<PhotoURL> photoURLSProcessed = new ArrayList<>();
        List<String> photoURLSProcessedStrings = new ArrayList<>();
        for (PhotoURL photoURL :
                photoURLs) {
            photoURLSProcessedStrings.add(getCloudinaryImageForRelatedProperties(photoURL.getPhotoURL()));
        }
        for (String photoURLSProcessedString :
                photoURLSProcessedStrings) {
            PhotoURL photoURL = new PhotoURL();
            photoURL.setPhotoURL(photoURLSProcessedString);
            photoURLSProcessed.add(photoURL);
        }

        return photoURLSProcessed;
    }

}
