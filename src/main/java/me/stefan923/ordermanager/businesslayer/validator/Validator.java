package me.stefan923.ordermanager.businesslayer.validator;

public interface Validator<E> {

    /**
     * Check if the input data meets certain conditions.
     *
     * @param e - the object whose data is to be verified.
     * @return true it the object meets the conditions, false otherwise.
     */
    boolean validate(E e);

}
