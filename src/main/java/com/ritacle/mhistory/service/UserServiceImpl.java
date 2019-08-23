package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.User;
import com.ritacle.mhistory.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

   @Override
    public User findUserByMailIgnoreCase(String mail) {
        return repository.findUserByMailIgnoreCase(mail);
    }

    @Override
    public User save(User user) {

        User persistedUser = findUserByMailIgnoreCase(user.getMail());

        if (persistedUser == null) {
            persistedUser = repository.save(user);
        }

        return persistedUser;
    }

    @Override
    public User getUser(Long id) {
        return repository.getOne(id);
    }
}
