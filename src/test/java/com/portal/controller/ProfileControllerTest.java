package com.portal.controller;

import com.portal.domain.User;
import com.portal.dto.ChangePassUserDTO;
import com.portal.dto.ProfileUserDTO;
import com.portal.service.AvatarService;
import com.portal.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author : Alex
 *         Date: 10.12.13
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private AvatarService avatarService;


    @Mock
    private ChangePassUserDTO changePassUserDTO;

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private User user;

    @Mock
    private ProfileUserDTO profileUserDTO;

    @Mock
    private Model model;

    @Mock
    private ModelMap map;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private ProfileController profileController;

    @Test
    public void testFillOwnProfile() {
        when(userService.getCurrentUser()).thenReturn(user);
        when(avatarService.getPictureUrl(1, "test.jpg")).thenReturn("/var/photo/1/avatars/test.jpg");
        assertEquals("profile", profileController.fillOwnProfile(model));
    }

    @Test
    public void testFillProfile() {
        when(userService.findById(1)).thenReturn(user);
        when(avatarService.getPictureUrl(1, "test.jpg")).thenReturn("/var/photo/1/avatars/test.jpg");
        assertEquals("profile", profileController.fillProfile(model, 1));
    }

    @Test
    public void testShowProfileList() {
        assertEquals("list", profileController.showProfileList(map));
        verify(map).put(eq("profileList"), anyList());
    }

    @Test
    public void testUpdateAvatarUrl() {
        when(userService.getCurrentUser()).thenReturn(user);
        profileController.updateAvatarUrl("image.jpg");
        verify(userService).updateAvatarId(user, "image.jpg");
    }

    @Test
    public void testSearchUsers(){
        profileController.searchUsers("", 0);
        verify(userService).getUsers(0);
    }

    @Test
    public void testSearchUsersByLogin(){
        profileController.searchUsers("test", 0);
        verify(userService).searchUsersByLogin("test", 0);
    }

    @Test
    public void testSaveAvatarInvalidSize() {
        when(multipartFile.isEmpty()).thenReturn(true);
        assertEquals("update-user", profileController.saveAvatar(multipartFile, model));
        verify(model).addAttribute(eq("uploadError"), anyString());
    }

    @Test
    public void testSaveAvatarInvalidType() {
        when(multipartFile.getOriginalFilename()).thenReturn("test.bmp");
        assertEquals("update-user", profileController.saveAvatar(multipartFile, model));
        verify(model).addAttribute(eq("uploadError"), anyString());
    }

    @Test
    public void testSaveAvatarSuccess() {
        when(userService.getCurrentUser()).thenReturn(user);
        when(user.getId()).thenReturn(1);
        when(multipartFile.getOriginalFilename()).thenReturn("test.gif");
        assertEquals("redirect:/profile", profileController.saveAvatar(multipartFile, model));
        verify(avatarService).savePicture(1, multipartFile);
    }

    @Test
    public void testEditProfile() {
        when(userService.getCurrentUser()).thenReturn(user);
        assertEquals("update-user", profileController.editProfile(model));
        verify(model).addAttribute(eq("username"),anyString());
        verify(model).addAttribute(eq("memberSince"),anyString());
        verify(model).addAttribute(eq("email"),anyString());
        verify(model).addAttribute(eq("avatarURL"),anyString());
    }

    @Test
    public void testUserNotFound() {
        when(userService.getCurrentUser()).thenReturn(null);
        assertEquals("redirect:/profile", profileController.editProfile(model));
        verify(model).addAttribute("username", "Пользователь не найден");
    }

    @Test
    public void testEditProfileAction() {
        when(userService.getCurrentUser()).thenReturn(user);
        when(profileUserDTO.getVisibleEmail()).thenReturn(true);
        when(profileUserDTO.getCamera()).thenReturn("testCamera");
        when(profileUserDTO.getContacts()).thenReturn("testContacts");
        assertEquals("redirect:/profile", profileController.editProfileAction(profileUserDTO, model));
        verify(user).setVisibleEmail(true);
        verify(user).setCamera("testCamera");
        verify(user).setContacts("testContacts");
        verify(userService).updateUser(user);
    }
}
