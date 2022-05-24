package ru.learnUp.lesson23.hibernate.exceptions;

public class NotEnoughBooksException extends RuntimeException{

    public NotEnoughBooksException(String str) {
        super(str);
    }
}
