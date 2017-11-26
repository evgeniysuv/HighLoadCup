package com.esuvorov.service;

import com.esuvorov.model.User;
import com.esuvorov.repository.UserRepository;
import com.esuvorov.service.exceptions.InvalidDataException;
import com.esuvorov.service.exceptions.UserAlreadyExistsException;
import com.esuvorov.service.exceptions.UserNotFoundException;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Long.parseLong;
import static java.lang.String.valueOf;

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

    public User updateUser(long id, JSONObject newUser) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            throw new UserNotFoundException(id);

        user.setFirstName(notNull(newUser.get("first_name")));
        user.setLastName(notNull(newUser.get("last_name")));
        user.setEmail(notNull(newUser.get("email")));
        user.setBirthDate(parseLong(notNull(newUser.get("birth_date"))));
        return userRepository.save(user);
    }

    private String notNull(Object jsonParam) {
        if (jsonParam == null)
            throw new InvalidDataException();
        return valueOf(jsonParam);
    }

    public User createUser(JSONObject newUser) {
        long userId = parseLong(notNull(newUser.get("id")));
        User user = userRepository.findById(userId).orElse(null);
        if (user != null)
            throw new UserAlreadyExistsException(userId);

        user = new User();
        user.setId(userId);
        user.setFirstName(notNull(newUser.get("first_name")));
        user.setLastName(notNull(newUser.get("last_name")));
        user.setEmail(notNull(newUser.get("email")));
        user.setBirthDate(parseLong(notNull(newUser.get("birth_date"))));
        user.setGender(notNull(newUser.get("gender")));

        return userRepository.save(user);
    }
}
