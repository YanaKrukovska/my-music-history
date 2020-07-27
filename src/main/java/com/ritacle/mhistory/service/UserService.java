package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.InputError;
import com.ritacle.mhistory.persistence.model.Response;
import com.ritacle.mhistory.persistence.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User findUserByMailIgnoreCase(String mail);

    User findUserByUserNameIgnoreCase(String username);

    Response<User> save(User user);

    List<InputError> validateUser(User user);

    User getUser(Long id);
}
