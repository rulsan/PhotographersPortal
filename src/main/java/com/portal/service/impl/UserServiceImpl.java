package com.portal.service.impl;

import com.portal.dao.UserDAO;
import com.portal.domain.User;
import com.portal.domain.UserRole;
import com.portal.service.UserService;
import com.portal.util.UserUtils;
import com.portal.util.exception.EmailAlreadyOccupiedException;
import com.portal.util.exception.LoginAlreadyOccupiedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Alex Date: 09.12.13
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    /**
     * Find user by ID
     *
     * @param id to find user
     * @return user if found, null otherwise
     */
    @Transactional
    public User findById(Integer id) {
        return userDAO.getById(id);
    }

    /**
     * Register user
     *
     * @param user to register
     */
    @Transactional
    public void register(final User user) throws EmailAlreadyOccupiedException, LoginAlreadyOccupiedException {
        if (userDAO.getByLogin(user.getLogin()) != null) {
            throw new LoginAlreadyOccupiedException("Имя уже занято");
        }
        if (userDAO.getByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyOccupiedException("Пользователь уже существует с этим E-mail!");
        }
        String encodedPassword = UserUtils.md5(user.getPassword());
        user.setRole(UserRole.ROLE_USER);
        user.setPassword(encodedPassword);
        userDAO.create(user);
    }

    /**
     * Update user
     *
     * @param user to update
     */
    @Transactional
    public void updateUser(User user) {
        userDAO.update(user);
    }

    /**
     * Update avatarId
     *
     * @param user to update
     * @param avatarId field that's updating
     */
    @Transactional
    public void updateAvatarId(User user, String avatarId) {
        user.setAvatarId(avatarId);
        userDAO.update(user);
    }

    /**
     * Obtain currently logged in user
     *
     * @return currently logged in user
     */
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        final String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userDAO.getByEmail(email);
    }

    /**
     * Get List of Users
     *
     * @return List of Users
     */
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    /**
     * Get List of Users in some page number page begins from index 1, 2, 3, 4
     * ...
     *
     * @return List of Users
     */
    @Transactional
    public List<User> getUsers(int page) {
        return userDAO.getUsers(page);
    }

    /**
     * Search users by login
     *
     * @param login to search
     * @return list of users
     */
    @Transactional
    public List<User> searchUsersByLogin(String login, int page) {
        return userDAO.searchUsersByLogin(login, page);
    }
}
