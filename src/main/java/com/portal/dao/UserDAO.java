package com.portal.dao;

import com.portal.domain.User;

import java.util.List;

/**
 * DAO interface for User
 *
 * @author SkillsUpPracticeTeam
 */
public interface UserDAO {

    /**
     * Get User by his id
     *
     * @param id identifier
     */
    User getById(Integer id);

    /**
     * Get User by his email
     *
     * @param email identifier
     */
    User getByEmail(String email);

    /**
     * Get User by his Login
     *
     * @param login identifier
     */
    User getByLogin(String login);

    /**
     * Create new User
     *
     * @param user
     */
    void create(User user);

    /**
     * Update User parameters
     *
     * @param user
     */
    void update(User user);

    /**
     * Delete User
     *
     * @param user
     */
    void delete(User user);

    /**
     * Delete User by his Id
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * Get List of Users
     */
    List<User> getAllUsers();

    /**
     * Get List of Users in some page
     */
    List<User> getUsers(int page);

    /**
     * Get List of search Users
     */
    List<User> searchUsersByLogin(String login, int page);
}
