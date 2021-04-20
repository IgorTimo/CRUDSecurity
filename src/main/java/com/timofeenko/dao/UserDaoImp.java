package com.timofeenko.dao;

import com.timofeenko.model.Role;
import com.timofeenko.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
//        return entityManager.createQuery("from User", User.class).getResultList();
        return entityManager.createQuery("select u from User u left join fetch u.roles", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUserById(int id) {
        entityManager.createQuery("delete User where id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public User getUserByEmail(String email) {
        return entityManager.createQuery("SELECT u from User u WHERE u.email = :email", User.class).setParameter("email", email).getSingleResult();
    }



}
