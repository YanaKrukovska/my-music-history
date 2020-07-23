package com.ritacle.mhistory.service;

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
            return new Response<>(null, new LinkedList<>(Collections.singleton("User can't be null")));
        }
        if (findUserByMailIgnoreCase(user.getMail()) != null) {
            return new Response<>(user, new LinkedList<>(Collections.singleton("Mail is already used by another user")));
        }
        if (findUserByUserNameIgnoreCase(user.getUserName()) != null) {
            return new Response<>(user, new LinkedList<>(Collections.singleton("Username is already used by another user")));
        }

        List<String> errors = validateUser(user);
        if (!errors.isEmpty()) {
            return new Response<>(user, errors);
        }

        return new Response<>(userRepository.save(user), errors);
    }

    private List<String> validateUser(User user) {
        List<String> errors = new LinkedList<>();
        if (StringUtils.isAllBlank(user.getUserName())) {
            errors.add("Username can't be empty");
        }
        if (StringUtils.isAllBlank(user.getGender())) {
            errors.add("Gender can't be empty");
        }
        if (StringUtils.isAllBlank(user.getNickName())) {
            errors.add("Nickname can't be empty");
        }
        if (user.getBirthDate() == null) {
            errors.add("Birth date must be chosen");
        }
        return errors;
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getOne(id);
    }
}
