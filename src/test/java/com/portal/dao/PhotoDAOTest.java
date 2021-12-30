package com.portal.dao;

import com.portal.IntegrationTest;
import com.portal.domain.Photo;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 *
 * @author UserXP
 */
public class PhotoDAOTest extends IntegrationTest {

    @Autowired
    private PhotoDAO photoDAO;

    @Before
    public void setUpClass() {
        initializeData();
    }

    @Test
    public void testCreate() {
        final Photo photo = new Photo();
        photo.setPhotoId("4");
        photo.setUserId(2);
        photo.setDescription("ThirdDescription");
        photoDAO.create(photo);
        assertEquals("ThirdDescription", photoDAO.getById("4").getDescription());
    }

    @Test
    public void testGetPhotoById() {
        final Photo photo = photoDAO.getById("1");
        assertEquals(1, photo.getUserId());
        assertEquals("testtags", photo.getTags());
    }

    @Test
    public void testGetByInvalidId() {
        final Photo photo = photoDAO.getById("8");
        assertNull(photo);
    }

    @Test
    public void testUpdate() {
        final Photo photo = photoDAO.getById("1");
        photo.setTags("testTagsChange");
        photoDAO.update(photo);
        assertEquals(1, photo.getUserId());
        assertEquals("testdescription", photo.getDescription());
        assertEquals("testTagsChange", photo.getTags());
    }

    @Test
    public void testDelete() {
        final Photo photo = photoDAO.getById("1");
        photoDAO.delete(photo);
        final Photo photoNew = photoDAO.getById("1");
        assertNull(photoNew);
    }

    @Test
    public void testDeleteById() {
        photoDAO.deleteById("1");
        assertNull(photoDAO.getById("1"));
    }

    @Test
    public void testGetPhotosByUserId() {
        final List<Photo> photos = photoDAO.getPhotosByUserId(1);
        assertEquals(2, photos.size());
        assertEquals("testdescription", photos.get(0).getDescription());
        assertEquals("testSecondDescription", photos.get(1).getDescription());
    }

    @Test
    public void testGetPhotosByTags() {
        final List<Photo> photos = photoDAO.getPhotosByTags("testPickTag");
        assertEquals(2, photos.size());
        assertEquals("testSecondDescription", photos.get(0).getDescription());
        assertEquals("testThirdDescription", photos.get(1).getDescription());
    }
}
