package com.ritacle.mhistory.persistence.repository;

import com.ritacle.mhistory.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {


    User findUserByMailIgnoreCase(String mail);

}
