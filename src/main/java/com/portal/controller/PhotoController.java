package com.portal.controller;

import com.portal.domain.Photo;
import com.portal.dto.UpdatePhotoDTO;
import com.portal.service.PhotoService;
import com.portal.service.UserService;
import com.portal.util.Constants;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Photo Controller.
 */
@Controller
@RequestMapping("/myPhoto")
public class PhotoController {

    @Autowired
    private UserService userService;
    
    @Autowired
    @Qualifier("PhotoService")
    private PhotoService photoService;

    /**
     * @return view with user photos
     */
    @RequestMapping
    public String showMyPhotos(ModelMap map) {
        final int userId = userService.getCurrentUser().getId();
        List<Photo> photoList = photoService.getAllUserPhoto(userId);
        map.put(Constants.Models.LIST_OF_PHOTOS, photoList);
        UpdatePhotoDTO updatePhotoDTO = new UpdatePhotoDTO();
        map.put("updatePhotoDTO", updatePhotoDTO);
        return Constants.Views.MYPHOTO;
    }

    /**
     * Saving photos
     *
     * @param model to populate
     * @param newPhoto array of photos to load
     */
    @RequestMapping(value = {"/save"}, method = RequestMethod.POST)
    public String saveNewPhotos(Model model, @RequestParam("newPhotos[]") MultipartFile[] newPhoto) {

        final int userId = userService.getCurrentUser().getId();
        String newPhotoExtension;
        for (int i = 0; i < newPhoto.length; i++) {
            newPhotoExtension = FilenameUtils.getExtension(newPhoto[i].getOriginalFilename());
            if (newPhoto[i].isEmpty() || newPhoto[i].getSize() > 10000000) {
                model.addAttribute(Constants.Models.PHOTO_UPLOADERROR_KEY, Constants.Models.PHOTO_UPLOADERROR_OVERSIZE_MESSAGE);
                return Constants.Views.MYPHOTO;
            } else if (!Constants.VALID_AVATAR_EXTENSIONS.contains(newPhotoExtension.toLowerCase())) {
                model.addAttribute(Constants.Models.PHOTO_UPLOADERROR_KEY, Constants.Models.PHOTO_UPLOADERROR_EXTENSION_MESSAGE);
                return Constants.Views.MYPHOTO;
            } else {
                photoService.savePicture(userId, newPhoto[i]);
            }
        }
        return Constants.Views.REDIRECT + Constants.Views.MYPHOTO;
    }

    @RequestMapping(value = {"/updatePhoto"}, method = RequestMethod.POST)
    public String updatePhoto(final UpdatePhotoDTO updatePhotoDTO) {
        Photo photo = photoService.findById(updatePhotoDTO.getPhotoId());
        if(photo!= null){
        photo.setDescription(updatePhotoDTO.getDescription());
        photo.setTags(updatePhotoDTO.getTags());
        photoService.updatePhoto(photo);
        return Constants.Views.REDIRECT + Constants.Views.MYPHOTO;
        }else{
           return Constants.Views.REDIRECT + Constants.Views.MYPHOTO; 
        }
    }
    
    @RequestMapping(value = {"/deletePhoto"}, method = RequestMethod.POST)
    public String deletePhoto(final UpdatePhotoDTO updatePhotoDTO) {
        Photo photo = photoService.findById(updatePhotoDTO.getPhotoId());
        if(photo!=null){
        photoService.deletePicture(photo.getUserId(), photo.getPhotoId());
        return Constants.Views.REDIRECT + Constants.Views.MYPHOTO;
        }else{
            return Constants.Views.REDIRECT + Constants.Views.MYPHOTO;
        }
    }
}
