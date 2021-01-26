package com.gyawalibros.Service;

import com.gyawalibros.Repository.UserRepository;
import com.gyawalibros.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationService {

    @Autowired
    UserRepository userRepository;

    public void activeUser(User user, boolean active) {
        user.setActive(active);
        userRepository.save(user);
    }
}
