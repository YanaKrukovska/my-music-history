package com.ritacle.mhistory.service;

import java.util.List;

public interface PasswordService {

    String encodePassword(String password);

    List<String> validatePasswordsInput(String password, String confirmationPassword);

    boolean comparePasswordAndConfirmationPassword(String password, String confirmationPassword);

    boolean compareRawAndEncodedPassword(String raw, String encoded);
}