package ru.learnUp.lesson23.hibernate.exceptions;

public class NameAlreadyExists extends RuntimeException{
    public NameAlreadyExists(String str) {
        super(str);
    }
}
