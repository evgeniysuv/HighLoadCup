package com.esuvorov.service;

import com.esuvorov.model.Location;
import com.esuvorov.model.User;
import com.esuvorov.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Value("${json.config.folder}")
    private String jsonDataConfigFolder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<User> getUserByLastName(String lastName) {
        return userRepository.findUsersByLastName(lastName);
    }


    public List<Location> getVisitsByUser(long id) {

        return null;
    }
}
