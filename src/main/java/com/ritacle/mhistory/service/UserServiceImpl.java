package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Country;
import com.ritacle.mhistory.persistence.model.InputError;
import com.ritacle.mhistory.persistence.model.Response;
import com.ritacle.mhistory.persistence.model.User;
import com.ritacle.mhistory.persistence.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordService passwordService;

    @Autowired
    CountryService countryService;

    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserByMailIgnoreCase(String mail) {
        return userRepository.findUserByMailIgnoreCase(mail);
    }

    @Override
    public User findUserByUserNameIgnoreCase(String username) {
        return userRepository.findUserByUserNameIgnoreCase(username);
    }

    @Override
    public Response<User> save(User user) {

        if (user == null) {
            return new Response<>(null, new LinkedList<>(Collections.singleton(new InputError("user", "User can't be null"))));
        }
        if (findUserByMailIgnoreCase(user.getMail()) != null) {
            return new Response<>(user, new LinkedList<>(Collections.singleton(new InputError("mail", "Mail is already used by another user"))));
        }
        if (findUserByUserNameIgnoreCase(user.getUserName()) != null) {
            return new Response<>(user, new LinkedList<>(Collections.singleton(new InputError("username", "Username is already used by another user"))));
        }

        List<InputError> errors = validateUser(user);
        if (!errors.isEmpty()) {
            return new Response<>(user, errors);
        }

        if (countryService.findCountryByCountryCodeIgnoreCase(user.getCountry().getCountryCode()) == null) {
            Response<Country> countryErrors = countryService.save(user.getCountry());
            if (!countryErrors.isOkay()) {
                return new Response<>(user, countryErrors.getErrors());
            }
        }

        List<InputError> passwordErrors = passwordService.validatePasswordsInput(user.getPassword(), user.getConfirmationPassword());
        if (!passwordErrors.isEmpty()) {
            return new Response<>(user, errors);
        }

        if (!passwordService.comparePasswordAndConfirmationPassword(user.getPassword(), user.getConfirmationPassword())) {
            return new Response<>(user, new LinkedList<>(Collections.singleton(new InputError("confirmationPassword", "Passwords do not match"))));
        }

        user.setPassword(passwordService.encodePassword(user.getPassword()));
        return new Response<>(userRepository.save(user), errors);
    }

    @Override
    public List<InputError> validateUser(User user) {
        List<InputError> errors = new LinkedList<>();
        if (StringUtils.isAllBlank(user.getUserName())) {
            errors.add(new InputError("userName", "Username can't be empty"));
        }
        if (StringUtils.isAllBlank(user.getGender())) {
            errors.add(new InputError("gender", "Gender can't be empty"));
        }
        if (StringUtils.isAllBlank(user.getNickName())) {
            errors.add(new InputError("nickName", "Nickname can't be empty"));
        }
        if (user.getBirthDate() == null) {
            errors.add(new InputError("birthDate", "Birth date must be chosen"));
        }
        return errors;
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getOne(id);
    }
}
