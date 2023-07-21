package com.raheeb.fullstackbackend.exception;

public class UserNotFoundException extends RuntimeException{

        public UserNotFoundException(Long id){
            super("Could notfound the user with id "+ id);
        }
    }


