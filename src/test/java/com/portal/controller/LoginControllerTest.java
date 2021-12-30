package com.portal.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.eq;

/**
 * @author : Alex
 *         Date: 10.12.13
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    private Model model;

    @InjectMocks
    private LoginController loginController;

    @Test
    public void testSignin() {
        assertEquals("login", loginController.signin(model));
    }

    @Test
    public void testFail() {
        assertEquals("login", loginController.fail(model));
        verify(model).addAttribute(eq("fail"), anyString());
    }
}
