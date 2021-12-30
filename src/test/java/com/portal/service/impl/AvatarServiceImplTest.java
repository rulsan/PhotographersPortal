package com.portal.service.impl;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Photo Service Implementation
 *
 * @author SkillsUpPracticeTeam
 */
@RunWith(MockitoJUnitRunner.class)
public class AvatarServiceImplTest {

    @InjectMocks
    AvatarServiceImpl testingInstance;

    @Test
    public void testNullAvatar() {
        testingInstance.setPhotosUrl("/photo/");
        String url = testingInstance.getPictureUrl(1, null);
        Assert.assertEquals("/photo/defaultAvatar.jpg", url);
    }

    @Test
    public void testNotNullAvatar() {
        testingInstance.setPhotosUrl("/photo/");
        String url = testingInstance.getPictureUrl(1, "notNull");
        Assert.assertEquals("/photo/1/avatars/notNull", url);
    }

    @Test
    public void testNotEmptyAvatarDirectory()  throws IOException {
        String photosDirectory = createEmptyPhotosDirectory();
        File avatarDirectoryForUser = new File(photosDirectory+"/1/avatars/");
        avatarDirectoryForUser.mkdirs();
        File photoOne = new File(avatarDirectoryForUser, "a.jpg");
        File photoTwo = new File(avatarDirectoryForUser, "b.png");
        photoOne.createNewFile();
        photoTwo.createNewFile();

        assertEquals(2, testingInstance.getExistingPictures(1).size());
    }

    @Test
    public void testEmptyAvatarDirectory() throws IOException {
        String photosDirectory = createEmptyPhotosDirectory();
        File emptyAvatarDirectoryForUser = new File(photosDirectory+"/1/avatars/");
        emptyAvatarDirectoryForUser.mkdirs();

        assertEquals(0, testingInstance.getExistingPictures(1).size());
    }

    @Test
    public void testUserSaveFirstAvatar() throws IOException {
        String photosDirectory = createEmptyPhotosDirectory();
        File avatarDirectoryForCurrentUser = new File(photosDirectory+"/1/avatars");
        MockMultipartFile firstAvatar = new MockMultipartFile("photo.JPG", "photo.JPG", null, new byte[]{0});
        MockMultipartFile secondAvatar = new MockMultipartFile("photo.PNG", "photo.PNG", null, new byte[]{1});

        testingInstance.savePicture(1, firstAvatar);
        assertTrue(avatarDirectoryForCurrentUser.exists());
        assertEquals(1, avatarDirectoryForCurrentUser.list().length);
        assertTrue(avatarDirectoryForCurrentUser.list()[0].endsWith(".jpg"));

        testingInstance.savePicture(1, secondAvatar);

        assertTrue(avatarDirectoryForCurrentUser.exists());
        assertEquals(2, avatarDirectoryForCurrentUser.list().length);
        assertTrue(avatarDirectoryForCurrentUser.list()[0].endsWith(".png") ||
                avatarDirectoryForCurrentUser.list()[1].endsWith(".png"));
    }

    @AfterClass
    public static void clearPhotosDirecotry() throws IOException {
        String directoryToUseForTest = System.getProperty("user.home")+"/PhotoPortal/";
        File directory = new File(directoryToUseForTest);
        FileUtils.deleteDirectory(directory);
    }

    private String createEmptyPhotosDirectory() throws IOException {
        String directoryToUseForTest = System.getProperty("user.home")+"/PhotoPortal/";
        File directory = new File(directoryToUseForTest);
        FileUtils.deleteDirectory(directory);
        assertTrue(directory.mkdirs());
        testingInstance.setPhotosRoot(directoryToUseForTest);
        testingInstance.setPhotosUrl("/photo/");
        return directoryToUseForTest;
    }
}
