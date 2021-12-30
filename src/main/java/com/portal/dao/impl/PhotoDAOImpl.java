package com.portal.dao.impl;

import com.portal.dao.PhotoDAO;
import com.portal.domain.Photo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * Implementation DAO interface for Photo
 * 
 * @author ruslan
 */
@Repository
public class PhotoDAOImpl implements PhotoDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Photo photo) {
        em.persist(photo);
    }

    @Override
    public Photo getById(String photoId) {
        return em.find(Photo.class, photoId);
    }

    @Override
    public void update(Photo photo) {
        em.merge(photo);
    }

    @Override
    public void delete(Photo photo) {
        em.remove(photo);
    }

    @Override
    public void deleteById(String photoId) {
        em.remove(em.find(Photo.class, photoId));
    }

    @Override
    public List<Photo> getPhotosByUserId(Integer userId) {
        return em.createQuery("select x from com.portal.domain.Photo x where x.userId = :searchId ")
                .setParameter("searchId", userId)
                .getResultList();
    }
    
    @Override
    public List<Photo> getPhotosByTags(String tags) {
        return em.createQuery("select x from com.portal.domain.Photo x where x.tags like :searchTags ")
                .setParameter("searchTags", "%"+tags+"%")
                .getResultList();   
    }
}
