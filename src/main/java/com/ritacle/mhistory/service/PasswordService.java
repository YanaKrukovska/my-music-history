package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.InputError;

import java.util.List;

public interface PasswordService {

    String encodePassword(String password);

    List<InputError> validatePasswordsInput(String password, String confirmationPassword);

    boolean comparePasswordAndConfirmationPassword(String password, String confirmationPassword);

    boolean compareRawAndEncodedPassword(String raw, String encoded);
}