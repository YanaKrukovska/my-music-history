package com.ritacle.mhistory.rest;

import com.ritacle.mhistory.persistence.model.Response;
import com.ritacle.mhistory.persistence.model.User;
import com.ritacle.mhistory.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserRestService {

    private final Logger logger = LoggerFactory.getLogger(UserRestService.class);

    private final UserService userService;

    @Autowired
    public UserRestService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{mail}")
    public User getUser(@PathVariable String mail) {
        logger.debug("User GET mail={} ", mail);
        return userService.findUserByMailIgnoreCase(mail);
    }

    @PostMapping("/add")
    @ResponseBody
    public Response<User> addUser(@RequestBody User user) {
        logger.debug("User PUT:{}", user.toString());
        return userService.save(user);
    }

}
