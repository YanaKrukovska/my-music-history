package com.ritacle.mhistory.persistence.repository;

import com.ritacle.mhistory.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {

    User findUserById(Long id);

    User findUserByMailIgnoreCase(String mail);

    User findUserByUserNameIgnoreCase(String username);
}
