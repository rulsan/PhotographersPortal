package com.portal.dao;

import java.util.List;

import com.portal.IntegrationTest;
import com.portal.domain.User;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;


public class UserDAOTest extends IntegrationTest {

    @Autowired
    private UserDAO userDAO;

    @Before
    public void setUpClass() {
        initializeData();
    }

    @Test
    public void testGetById() {
        final User user = userDAO.getById(1);
        assertEquals(Integer.valueOf(1), user.getId());
        assertEquals("test@test.com", user.getEmail());
    }
    
    @Test
    public void testGetByInvalidId() {
        final User user = userDAO.getById(25);
        assertNull(user);
    }

    @Test
    public void testGetByEmail(){
        final User user = userDAO.getByEmail("test@test.com");
        assertEquals(Integer.valueOf(1), user.getId());
    }

    @Test
    public void testGetByInvalidEmail() {
        final User user = userDAO.getByEmail("invalid@invalid.invalid");
        assertNull(user);
    }

    @Test
    public void testGetByLogin(){
        final User user = userDAO.getByLogin("testLogin");
        assertEquals(Integer.valueOf(1), user.getId());
    }

    @Test
    public void testGetByInvalidLogin() {
        final User user = userDAO.getByLogin("invalidLogin");
        assertNull(user);
    }

    @Test
    public void testCreate() {
    	final User user = new User();
    	user.setEmail("testThird@test.com");
    	userDAO.create(user);
    	assertEquals(Integer.valueOf(3), user.getId());
    	assertEquals("testThird@test.com", userDAO.getById(3).getEmail());
    }
    
    @Test
    public void testUpdate() {
    	final User user = userDAO.getById(1);
    	user.setLogin("testLoginChange");
    	userDAO.update(user);
    	assertEquals(Integer.valueOf(1), user.getId());
    	assertEquals("test@test.com", user.getEmail());
    	assertEquals("testLoginChange", user.getLogin());
    }
    
    @Test
    public void testDelete() {
    	final User user = userDAO.getById(1);
    	userDAO.delete(user);
    	final User userNew = userDAO.getById(1);
    	assertNull(userNew);
    }
    
    @Test
    public void testDeleteByID() {
    	userDAO.deleteById(1);
    	assertNull(userDAO.getById(1));
    }    
    
    @Test
    public void testGetAllUsers() {
        final List<User> users = userDAO.getAllUsers();
        assertEquals(2, users.size());
        assertEquals("test@test.com", users.get(0).getEmail());
        assertEquals("testSecond@test.com", users.get(1).getEmail());
    }

    @Test
    public void testGetByEmailAndPass() {
        final User user = userDAO.getById(1);
        assertEquals(user.getEmail(), "test@test.com");
        assertEquals(user.getPassword(), "test");
        assertFalse((user.getEmail().equals("notexistemail@test.com") && (user.getPassword().equals("notexistpass"))));
    }

    @Test
    public void testGetUsers() {
        final List<User> usersOnFirstPage = userDAO.getUsers(0);
        assertEquals(2, usersOnFirstPage.size());
        final List<User> usersOnSecondPage = userDAO.getUsers(1);
        assertEquals(0, usersOnSecondPage.size());
    }

    @Test
    public void testSearchUsersByLogin() {
        final List<User> searchedUsers = userDAO.searchUsersByLogin("test", 0);
        assertEquals(2, searchedUsers.size());
        final List<User> noSearchedUsers = userDAO.searchUsersByLogin("noTest", 0);
        assertEquals(0, noSearchedUsers.size());
    }
}
