package com.portal.service.impl;

import com.portal.dao.UserDAO;
import com.portal.domain.User;
import com.portal.util.exception.EmailAlreadyOccupiedException;
import com.portal.util.exception.LoginAlreadyOccupiedException;
import com.portal.util.exception.UserUpdateFailedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * User: Alex
 * Date: 09.12.13
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private User user;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testFindById() {
        when(userDAO.getById(1)).thenReturn(user);
        assertEquals(user, userService.findById(1));
    }

    @Test
    public void testFindByInvalidId() {
        when(userDAO.getById(33)).thenReturn(null);
        assertNull(userService.findById(33));
    }

    @Test
    public void testRegister() throws EmailAlreadyOccupiedException, LoginAlreadyOccupiedException {
        when(user.getPassword()).thenReturn("test");
        userService.register(user);
        verify(user).setPassword(anyString());
        verify(userDAO).create(user);
    }

    @Test(expected = EmailAlreadyOccupiedException.class)
    public void testEmailAlreadyOccupiedException() throws EmailAlreadyOccupiedException, LoginAlreadyOccupiedException, UserUpdateFailedException {
        when(userDAO.getByEmail(user.getEmail())).thenReturn(user);
        userService.register(user);
        userService.updateUser(user);
    }

    @Test(expected = LoginAlreadyOccupiedException.class)
    public void testLoginAlreadyOccupiedException() throws EmailAlreadyOccupiedException, LoginAlreadyOccupiedException, UserUpdateFailedException {
        when(userDAO.getByLogin(user.getLogin())).thenReturn(user);
        userService.register(user);
        userService.updateUser(user);
    }
}
