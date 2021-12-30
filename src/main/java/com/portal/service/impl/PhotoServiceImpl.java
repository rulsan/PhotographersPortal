package com.portal.service.impl;

import com.portal.dao.PhotoDAO;
import com.portal.domain.Photo;
import com.portal.service.PhotoService;
import com.portal.util.Constants;
import com.portal.util.UserUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Photo Service Implementation
 *
 * @author SkillsUpPracticeTeam
 */
@Service("PhotoService")
public class PhotoServiceImpl implements PhotoService {

    @Value("${photos.url}")
    private String photosUrl;

    @Value("${photos.root}")
    private String photosRoot;

    @Autowired
    private PhotoDAO photoDAO;

    @Override
    public String getPictureUrl(int userId, String pictureId) {
        if (pictureId != null) {
            return photosUrl + userId + Constants.Pathes.PHOTOS + pictureId;
        }
        return null;
    }

    @Override
    public List<String> getExistingPictures(int userId) {
        File[] listOfFiles = new File(photosRoot + userId + Constants.Pathes.PHOTOS).listFiles();
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

    @Override
    @Transactional
    public String savePicture(int userId, MultipartFile uploadPicture) {
        final Photo photo = new Photo();
        final String extension = FilenameUtils.getExtension(uploadPicture.getOriginalFilename()).toLowerCase();
        photo.setUserId(userId);
        String photoId = UserUtils.generatePhotoName(uploadPicture.getOriginalFilename());
        photo.setPhotoId(photoId);
        try {
            File file = new File(photosRoot + userId + Constants.Pathes.PHOTOS + photoId);
            file.getParentFile().mkdirs();
            uploadPicture.transferTo(file);
            File miniFile = new File(photosRoot + userId + Constants.Pathes.MINIPHOTOS + photoId);
            miniFile.getParentFile().mkdirs();
            BufferedImage miniature = UserUtils.
                    resizeImage(ImageIO.read(file), Constants.PHOTO_MIN_HEIGHT);
            ImageIO.write(miniature, extension, miniFile);
            photoDAO.create(photo);
        } catch (IOException e) {
            Logger.getLogger(PhotoServiceImpl.class.getName()).
                    log(Level.WARNING, "Uploading photo", e);
        }
        return null;
    }
    
    @Override
    @Transactional
    public void updatePhoto (Photo photo){
        photoDAO.update(photo);
    }
    
    @Override
    @Transactional
    public Photo findById(String photoId){
        return photoDAO.getById(photoId);
    }

    @Override
    @Transactional
    public void deletePicture(int userId, String photoId) {
        new File(photosRoot + userId + Constants.Pathes.PHOTOS + photoId).delete();
        new File(photosRoot + userId + Constants.Pathes.MINIPHOTOS + photoId).delete();
        photoDAO.deleteById(photoId);
    }

    @Override
    @Transactional
    public List<Photo> getAllUserPhoto(int userId) {
        return photoDAO.getPhotosByUserId(userId);
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
