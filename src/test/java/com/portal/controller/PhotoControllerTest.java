package com.portal.controller;

import com.portal.domain.Photo;
import com.portal.dto.UpdatePhotoDTO;
import com.portal.service.PhotoService;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author ruslan
 */
@RunWith(MockitoJUnitRunner.class)
public class PhotoControllerTest {
    
    @Mock
    private PhotoService photoService;
    
    @Mock
    private Photo photo;
    
    @Mock
    private UpdatePhotoDTO updatePhotoDTO;
    
    @InjectMocks
    private PhotoController photoController;

    @Test
    public void testUpdatePhoto() {
        when(photoService.findById("1")).thenReturn(photo);
        when(updatePhotoDTO.getPhotoId()).thenReturn("1");
        when(updatePhotoDTO.getDescription()).thenReturn("testdescription");
        when(updatePhotoDTO.getTags()).thenReturn("testtags");
        assertEquals("redirect:/myPhoto", photoController.updatePhoto(updatePhotoDTO));
        verify(photo).setDescription("testdescription");
        verify(photo).setTags("testtags");
        verify(photoService).updatePhoto(photo);

    }
}