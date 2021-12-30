package com.portal.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import com.portal.controller.IndexController;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {
	
	@Mock
    private Model model;
	
    @InjectMocks
    private IndexController indexController;
    
    @Test
    public void testIndexController() {
        assertEquals("index", indexController.printWelcome(model));
        verify(model).addAttribute(eq("welcome"), anyString());
        verify(model).addAttribute(eq("maintext"), anyString());
    }
}
