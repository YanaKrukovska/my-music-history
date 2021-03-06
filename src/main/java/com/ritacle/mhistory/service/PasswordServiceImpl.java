package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.InputError;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PasswordServiceImpl implements PasswordService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PasswordServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public List<InputError> validatePasswordsInput(String password, String confirmationPassword) {
        List<InputError> errors = new LinkedList<>();
        if (StringUtils.isAllBlank(password)) {
            errors.add(new InputError("password","Password can't be empty"));
        }
        if (StringUtils.isAllBlank(confirmationPassword)) {
            errors.add(new InputError("confirmationPassword", "Confirmation password can't be empty"));
        }
        return errors;
    }

    @Override
    public boolean comparePasswordAndConfirmationPassword(String password, String confirmationPassword) {
        return password.equals(confirmationPassword);
    }

    @Override
    public boolean compareRawAndEncodedPassword(String raw, String encoded) {
        return bCryptPasswordEncoder.matches(raw, encoded);
    }
}
