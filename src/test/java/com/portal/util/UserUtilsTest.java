package com.portal.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.portal.domain.User;

import com.portal.dto.UserListItemDTO;
import org.junit.Test;

import com.portal.util.UserUtils;

import org.springframework.ui.Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;

public class UserUtilsTest {

    @Test
    public void testMd5() {
        String pass = "test";
        assertEquals("098f6bcd4621d373cade4e832627b4f6", UserUtils.md5(pass));
    }

    @Test
    public void testProfileFilling() {
        User user = mock(User.class);
        Model model = mock(Model.class);
        when(user.getEmail()).thenReturn("test@test.test");
        when(user.getLogin()).thenReturn("test");
        when(user.getVisibleEmail()).thenReturn(true);
        UserUtils.profileFilling(model, user);
        verify(model).addAttribute("email", "test@test.test");
        verify(model).addAttribute("username", "test");
        verify(model).addAttribute(eq("lastDate"), anyString());
        verify(model).addAttribute(eq("memberSince"), anyString());
        verify(model).addAttribute(eq("devices"), anyString());
        verify(model).addAttribute(eq("contacts"), anyString());
    }

    @Test
    public void testGetDisplayedList(){
        final List<User> listOfUsers = Collections.emptyList();
        final List<UserListItemDTO> updateList = Collections.emptyList();
        assertEquals(updateList, UserUtils.getDisplayedList(listOfUsers));
    }

    @Test
    public void testResizeImage(){
        BufferedImage image = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2D = image.createGraphics();
        g2D.drawImage(image, 0, 0, 300, 300, null);
        assertEquals(150, UserUtils.resizeImage(image, 150).getWidth());
        assertEquals(150, UserUtils.resizeImage(image, 150).getHeight());
    }
}
