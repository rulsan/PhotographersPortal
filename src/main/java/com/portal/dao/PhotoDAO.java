package com.portal.dao;

import com.portal.domain.Photo;
import java.util.List;

/**
 ** DAO interface for Photo
 *
 * @author ruslan 
 */
public interface PhotoDAO {
    
    void create(Photo photo);
    
    Photo getById(String photoId);
    
    void update(Photo photo);
    
    void delete(Photo photo);
    
    void deleteById(String photoId);
    
    List<Photo> getPhotosByUserId(Integer userId);
    
    List<Photo> getPhotosByTags(String tags);
    
}
