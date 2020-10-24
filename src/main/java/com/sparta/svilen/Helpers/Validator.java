package com.sparta.svilen.Helpers;

public class Validator {
    //TODO: Incorporate into system
    private static boolean isEmailValid(String email){
        return email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");
    }
}
