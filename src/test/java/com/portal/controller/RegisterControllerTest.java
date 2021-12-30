package com.portal.controller;

import com.portal.domain.User;
import com.portal.dto.RegisterUserDTO;
import com.portal.service.UserService;
import com.portal.util.exception.EmailAlreadyOccupiedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Alex
 *         Date: 05.12.13
 */

@RunWith(MockitoJUnitRunner.class)
public class RegisterControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private ModelMap model;

    @Mock
    private BindingResult result;

    @Mock
    private RegisterUserDTO registerUserDTO;

    @Mock
    private User user;

    @InjectMocks
    private RegisterController registerController;

    @Test
    public void testSighnup() {
        assertEquals("registration", registerController.signup(model));
    }

    @Test
    public void testSighnupOk() throws EmailAlreadyOccupiedException {
        when(registerUserDTO.getLogin()).thenReturn("test");
        when(registerUserDTO.getEmail()).thenReturn("test@test.test");
        when(registerUserDTO.getPass()).thenReturn("1234");
        when(registerUserDTO.getConfirmPass()).thenReturn("1234");
        when(result.hasErrors()).thenReturn(false);
        assertEquals("registration-success", registerController.processSignup(registerUserDTO, result));
    }

    @Test
    public void testSighnupFail() throws EmailAlreadyOccupiedException{
        when(registerUserDTO.getLogin()).thenReturn("test");
        when(registerUserDTO.getEmail()).thenReturn("test@test.test");
        when(registerUserDTO.getPass()).thenReturn("1234");
        when(registerUserDTO.getConfirmPass()).thenReturn("4321");
        when(result.hasErrors()).thenReturn(true);
        assertEquals("registration", registerController.processSignup(registerUserDTO, result));
    }
}
