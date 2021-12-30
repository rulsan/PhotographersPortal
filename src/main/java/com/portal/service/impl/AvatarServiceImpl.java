package com.portal.service.impl;

import com.portal.service.AvatarService;
import com.portal.util.Constants;
import com.portal.util.UserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Avatar Service
 */
@Service("AvatarService")
public class AvatarServiceImpl implements AvatarService {

    @Value("${photos.url}")
    private String photosUrl;

    @Value("${photos.root}")
    private String photosRoot;

    /**
     * Getting Avatar URL
     *
     * @param userId to get avatar
     * @return photosUrl+"defaultAvatar.jpg" if haven't pictureId
     * or photosUrl+userId+"/avatars/"+pictureId else
     */
    @Override
    public String getPictureUrl(int userId, String pictureId) {
        if (pictureId == null) {
            return photosUrl + "defaultAvatar.jpg";
        } else {
            return photosUrl + userId + Constants.Pathes.AVATARS + pictureId;
        }
    }

    /**
     * Getting existing avatar urls
     *
     * @param userId
     * @return list of avatar urls
     */
    @Override
    public List<String> getExistingPictures(int userId) {
        File[] listOfFiles = new File(photosRoot + userId + Constants.Pathes.AVATARS).listFiles();
        List<String> listOfUrl = new ArrayList<>();

        if (listOfFiles == null) {
            return listOfUrl;
        } else {
            for (File listOfFile : listOfFiles) {
                listOfUrl.add("/portal" + getPictureUrl(userId, listOfFile.getName()));
            }
        }
        return listOfUrl;
    }

    /**
     * Saving avatar on the server and generating photoName
     *
     * @param userId
     * @param uploadPicture avatar to save
     * @return generated avatarName
     */
    @Override
    public String savePicture(int userId, MultipartFile uploadPicture) {
        String avatarName = UserUtils.generatePhotoName(uploadPicture.getOriginalFilename());
        try {
            File file = new File(photosRoot + userId + Constants.Pathes.AVATARS + avatarName);
            file.getParentFile().mkdirs();
            uploadPicture.transferTo(file);
        } catch (IOException e) {
            Logger.getLogger(PhotoServiceImpl.class.getName())
                    .log(Level.WARNING, "Uploading avatar", e);
        }
        return avatarName;
    }
    
    @Override
    public void deletePicture(int userId, String avatarName) {
        new File(photosRoot + userId + Constants.Pathes.AVATARS + avatarName).delete();
    }

    public String getPhotosUrl() {
        return photosUrl;
    }

    public void setPhotosUrl(String photosUrl) {
        this.photosUrl = photosUrl;
    }

    public String getPhotosRoot() {
        return photosRoot;
    }

    public void setPhotosRoot(String photosRoot) {
        this.photosRoot = photosRoot;
    }
}
