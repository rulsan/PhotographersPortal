package com.portal.service;

import com.portal.domain.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {
    /**
     * Get photo URL
     *
     * @param userId
     * @param pictureId
     */
    String getPictureUrl(int userId, String pictureId);

    /**
     * Getting existing list of photo urls
     *
     * @param userId
     * @return list of picture urls
     */
    List<String> getExistingPictures(int userId);

    /**
     * Uploading photo on the server
     *
     * @param userId
     * @param uploadPicture picture file to upload
     */
    String savePicture(int userId, MultipartFile uploadPicture);
    
    /**
     * Updates photo
     * 
     * @param photo 
     */
    void updatePhoto (Photo photo);
    
    /**
     * Finds user by ID
     * 
     * @param photoId
     * @return Photo
     */
    Photo findById(String photoId);

    /**
     * Deletes the picture from the server
     *
     * @param userId
     * @param pictureId
     */
    void deletePicture(int userId, String pictureId);

    /**
     * Getting existing list of photo
     *
     * @param userId
     * @return list of pictures
     */
    List<Photo> getAllUserPhoto(int userId);
}
