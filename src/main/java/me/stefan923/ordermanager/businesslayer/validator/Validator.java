package me.stefan923.ordermanager.businesslayer.validator;

public interface Validator<E> {

    boolean validate(E e);

}
