package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findUserByMailIgnoreCase(String mail);

   User save (User user);

   User getUser (Long id);
}
