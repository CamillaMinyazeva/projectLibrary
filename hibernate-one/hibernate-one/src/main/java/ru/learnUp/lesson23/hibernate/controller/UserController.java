package ru.learnUp.lesson23.hibernate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.learnUp.lesson23.hibernate.dao.repository.RoleRepository;
import ru.learnUp.lesson23.hibernate.dao.services.UserService;
import ru.learnUp.lesson23.hibernate.view.UserView;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserView mapping;
    private final RoleRepository roleRepository;

    public UserController(UserService userService, RoleRepository roleRepository, UserView mapping) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.mapping = mapping;
    }

    @PostMapping
    public Boolean createUser(@RequestBody UserView view) {
        userService.create(mapping.mapFromView(view, roleRepository));
        return true;
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public List<UserView> getUsers() {
        return mapping.mapToView(userService.findAll());
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{userId}")
    public UserView getUser(@PathVariable("userId") Long userId) {
        return mapping.mapToView(userService.findById(userId));
    }

}
