package me.stefan923.ordermanager.businesslayer;

import me.stefan923.ordermanager.businesslayer.validator.Validator;
import me.stefan923.ordermanager.dao.AbstractDAO;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class AbstractBLL<T> {

    private final List<Validator<T>> validators;
    private AbstractDAO<T> dao;

    public AbstractBLL(List<Validator<T>> validators, AbstractDAO<T> dao) {
        this.validators = validators;
        this.dao = dao;
    }

    public List<T> findAll() {
        return dao.findAll();
    }

    public T findById(int id) {
        return dao.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public T insert(T client) {
        return dao.insert(client);
    }

    public T update(T client) {
        return dao.update(client);
    }

    public boolean delete(int id) {
        return dao.delete(id);
    }

}
