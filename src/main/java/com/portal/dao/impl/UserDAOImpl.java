package com.portal.dao.impl;

import com.portal.dao.UserDAO;
import com.portal.domain.User;
import com.portal.util.Constants;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation DAO interface for User
 *
 * @author SkillsUpPracticeTeam
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User getById(Integer id) {
        return em.find(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        try {
            return (User) em.createQuery("select u from com.portal.domain.User u where email = :email").
                    setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User getByLogin(String login) {
        try {
            return (User) em.createQuery("select u from com.portal.domain.User u where login = :login").
                    setParameter("login", login).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void create(User user) {
        em.persist(user);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public void delete(User user) {
        em.remove(user);
    }

    @Override
    public void deleteById(Integer id) {
        em.remove(em.find(User.class, id));
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery(Constants.Queryes.GETUSERS).getResultList();
    }

    @Override
    public List<User> getUsers(int page) {
        return em.createQuery(Constants.Queryes.GETUSERS).setMaxResults(Constants.PICKS_USERS)
                .setFirstResult(page * Constants.PICKS_USERS).getResultList();
    }

    @Override
    public List<User> searchUsersByLogin(String login, int page) {
        return em.createQuery("SELECT u FROM com.portal.domain.User u WHERE u.login LIKE :searchSymbol")
            .setParameter("searchSymbol", "%"+login+"%")
            .setMaxResults(Constants.PICKS_USERS)
            .setFirstResult(page * Constants.PICKS_USERS)
            .getResultList();
    }
}
