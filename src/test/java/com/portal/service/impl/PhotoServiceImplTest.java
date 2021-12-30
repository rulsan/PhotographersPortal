package com.portal.service.impl;

import com.portal.dao.PhotoDAO;
import com.portal.domain.Photo;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Photo Service Test
 */
@RunWith(MockitoJUnitRunner.class)
public class PhotoServiceImplTest {

    @Mock
    private PhotoDAO photoDAO;

    @Mock
    private Photo photo;

    @InjectMocks
    private PhotoServiceImpl testingInstance;

    @Test
    public void testNullPhoto() {
        testingInstance.setPhotosUrl("/photo/");
        Assert.assertNull(testingInstance.getPictureUrl(1, null));
    }

    @Test
    public void testNotNullPhoto() {
        testingInstance.setPhotosUrl("/photo/");
        String url = testingInstance.getPictureUrl(1, "notNull");
        Assert.assertEquals("/photo/1/photos/notNull", url);
    }

    @Test
    public void testNotEmptyPhotoDirectoryAndDeletePicture()  throws IOException  {
        String photosDirectory = createEmptyPhotosDirectory();
        File photoDirectoryForUser = new File(photosDirectory + "/1/photos/");
        photoDirectoryForUser.mkdirs();
        File photoOne = new File(photoDirectoryForUser, "a.jpg");
        File photoTwo = new File(photoDirectoryForUser, "b.png");
        photoOne.createNewFile();
        photoTwo.createNewFile();

        assertEquals(2, testingInstance.getExistingPictures(1).size());

        testingInstance.deletePicture(1, "a.jpg");
        assertEquals(1, testingInstance.getExistingPictures(1).size());
        testingInstance.deletePicture(1, "b.jpg");
        assertEquals(1, testingInstance.getExistingPictures(1).size());
        testingInstance.deletePicture(1, "b.png");
        assertEquals(0, testingInstance.getExistingPictures(1).size());
    }

    @Test
    public void testEmptyPhotoDirectory() throws IOException {
        String photosDirectory = createEmptyPhotosDirectory();
        File emptyAvatarDirectoryForUser = new File(photosDirectory + "/1/photos/");
        emptyAvatarDirectoryForUser.mkdirs();

        assertEquals(0, testingInstance.getExistingPictures(1).size());
    }

    @Test
    public void testUserSavePhoto() throws IOException {
        String photosDirectory = createEmptyPhotosDirectory();
        File photoDirectory = new File(photosDirectory + "/1/photos");
        File miniPhotoDirectory = new File(photosDirectory + "/1/miniphotos");

        File testPhotoFile = new File(photosDirectory + "/1/testing/testphoto.jpg");
        testPhotoFile.getParentFile().mkdirs();
        BufferedImage image = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2D = image.createGraphics();
        g2D.drawImage(image, 0, 0, 300, 300, null);
        ImageIO.write(image, "jpg", testPhotoFile);

        MockMultipartFile testPhoto = new MockMultipartFile("photo.JPG", "photo.jpg", null, new FileInputStream(testPhotoFile));
        MockMultipartFile testPhoto2 = new MockMultipartFile("photo2.JPEG", "photo2.jpeg", null, new FileInputStream(testPhotoFile));

        testingInstance.savePicture(1, testPhoto);
        assertTrue(photoDirectory.exists());
        assertTrue(miniPhotoDirectory.exists());
        assertEquals(1, photoDirectory.list().length);
        assertEquals(1, miniPhotoDirectory.list().length);
        assertTrue(photoDirectory.list()[0].endsWith(".jpg"));
        assertTrue(miniPhotoDirectory.list()[0].endsWith(".jpg"));

        testingInstance.savePicture(1, testPhoto2);
        assertTrue(photoDirectory.exists());
        assertTrue(miniPhotoDirectory.exists());
        assertEquals(2, photoDirectory.list().length);
        assertEquals(2, miniPhotoDirectory.list().length);
        assertTrue(photoDirectory.list()[0].endsWith(".jpeg") ||
                photoDirectory.list()[1].endsWith(".jpeg"));
        assertTrue(miniPhotoDirectory.list()[0].endsWith(".jpeg") ||
                miniPhotoDirectory.list()[1].endsWith(".jpeg"));
    }
    
    @Test
    public void testFindById() {
        when(photoDAO.getById("1")).thenReturn(photo);
        assertEquals(photo, testingInstance.findById("1"));
    }

    @Test
    public void testFindByInvalidId() {
        when(photoDAO.getById("18")).thenReturn(null);
        assertNull(testingInstance.findById("18"));
    }

    @AfterClass
    public static void clearPhotosDirecotry() throws IOException {
        String directoryToUseForTest = System.getProperty("user.home") + "/PhotoPortal/";
        File directory = new File(directoryToUseForTest);
        FileUtils.deleteDirectory(directory);
    }

    private String createEmptyPhotosDirectory() throws IOException {
        String directoryToUseForTest = System.getProperty("user.home") + "/PhotoPortal/";
        File directory = new File(directoryToUseForTest);
        FileUtils.deleteDirectory(directory);
        assertTrue(directory.mkdirs());
        testingInstance.setPhotosRoot(directoryToUseForTest);
        testingInstance.setPhotosUrl("/photo/");
        return directoryToUseForTest;
    }
}
