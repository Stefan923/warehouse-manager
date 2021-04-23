package me.stefan923.ordermanager.bll;

import me.stefan923.ordermanager.bll.validator.Validator;
import me.stefan923.ordermanager.dao.AbstractDAO;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class AbstractBLL<T> {

    protected final AbstractDAO<T> dao;

    private final List<Validator<T>> validators;

    public AbstractBLL(List<Validator<T>> validators, AbstractDAO<T> dao) {
        this.validators = validators;
        this.dao = dao;
    }

    /**
     * Returns the data associated with a class type (T).
     *
     * @return a list of elements of type T.
     */
    public List<T> findAll() {
        return dao.findAll();
    }

    /**
     * Returns the data associated with a class type (T) and an id.
     *
     * @param id - the identifier of the element whose data is to be retrieved.
     * @return an object of type Optional<T>.
     */
    public T findById(int id) {
        return dao.findById(id).orElseThrow(NoSuchElementException::new);
    }

    /**
     * Saves the data associated with a class type (T). This method attempts to save an object.
     *
     * @param t - the object whose data is to be saved.
     * @return the object (as Optional<T>), if object's data could be saved, empty Optional<t> otherwise.
     */
    public T insert(T t) {
        validators.forEach(validator -> validator.validate(t));
        return dao.insert(t);
    }

    /**
     * Saves the data associated with a class type (T). This method attempts to save an object that already exists
     * into the data access object.
     *
     * @param t - the object whose data is to be saved.
     * @return the object (as Optional<T>), if object's data could be saved, empty Optional<t> otherwise.
     */
    public T update(T t) {
        validators.forEach(validator -> validator.validate(t));
        return dao.update(t);
    }

    /**
     * Deletes the data associated with a class type (T) and an id.
     *
     * @param id - the identifier of the object whose data is to be deleted.
     * @return true if delete was successfull, false otherwise.
     */
    public boolean delete(int id) {
        return dao.delete(id);
    }

}
