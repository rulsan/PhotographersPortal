package com.portal.service;

import com.portal.domain.User;
import com.portal.util.exception.EmailAlreadyOccupiedException;
import com.portal.util.exception.LoginAlreadyOccupiedException;

import java.util.List;

/**
 * User Service
 *
 * @author Alex
 * Date: 09.12.13
 */
public interface UserService {

    /**
     * Find user by ID
     *
     * @param id to find user
     * @return user if found, null otherwise
     */
    User findById(Integer id);

    /**
     * Registration User
     * @param user User to registration
     * @throws  EmailAlreadyOccupiedException
     * @throws LoginAlreadyOccupiedException 
     */
    void register(final User user) throws EmailAlreadyOccupiedException, LoginAlreadyOccupiedException;

    /**
     * Update User
     * @param user User to update registration
     */
    void updateUser(User user);

    /**
     * Update avatarId
     *
     * @param user to update
     * @param avatarId field that's updating
     */
    void updateAvatarId(User user, String avatarId);

    /**
     * Get current User
     */
    User getCurrentUser();

    /**
     * @return all users
     */
    List<User> getAllUsers();

    /**
     * @return some users
     */
    List<User> getUsers(int page);

    /**
     * @return List of search Users
     */
    List<User> searchUsersByLogin(String login, int page);
}
