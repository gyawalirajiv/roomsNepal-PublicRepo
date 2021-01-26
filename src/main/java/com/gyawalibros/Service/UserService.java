package com.gyawalibros.Service;

import com.gyawalibros.Repository.RoleRepository;
import com.gyawalibros.Repository.UserRepository;
import com.gyawalibros.domain.Role;
import com.gyawalibros.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public List<User> getAllUsers() {
        List<User> userList = (List<User>) userRepository.findAll();
        return userList;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void addUser(User user) {
        Role role = roleRepository.findOne(new Long(2));
        user.getRoles().add(role);

        Date currentDate = Calendar.getInstance().getTime();
        user.setCreatedDate(currentDate);

        user.setPhotoUrl("http://res.cloudinary.com/ddlkvnblt/image/upload/v1522924364/profile_default.jpg");

        user.setActive(false);

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = getUserByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }

        return new UserDetailsImpl(user);
    }

    public User getUser(Long userIdLong) {
        User user = userRepository.findOne(userIdLong);
        return user;
    }

    public void updateUser(User user) {
        User userPreviousState = userRepository.findOne(user.getId());
        user.setEmail(userPreviousState.getEmail());
        user.setPassword(userPreviousState.getPassword());
        user.setVerifiedNumber(userPreviousState.isVerifiedNumber());
        user.setPhotoUrl(userPreviousState.getPhotoUrl());
        user.setCreatedDate(userPreviousState.getCreatedDate());
        user.setModfiedDate(userPreviousState.getModfiedDate());
        user.setActive(userPreviousState.isActive());
        user.setRoles(userPreviousState.getRoles());
        userRepository.save(user);
    }

    public void deactivate(User user) {
        user.setActive(false);
        userRepository.save(user);
    }

    public void deleteUser(Long userIdLong) {
        userRepository.delete(userIdLong);
    }

    public void updateProfilePic(UserDetailsImpl userDetails, String fileURL) {
        User user = userRepository.findOne(userDetails.getUser().getId());
        user.setPhotoUrl(fileURL);
        userRepository.save(user);
    }

    public boolean checkEmail(User user) {
        User foundUser = userRepository.findByEmail(user.getEmail());
        return foundUser != null ? true : false;
    }

    public User findByConfirmationToken(String confirmationToken) {
        return userRepository.findByConfirmationToken(confirmationToken);
    }

    public void changePassword(UserDetailsImpl userDetails, String newPassword) {
        User user = userDetails.getUser();
        user.setPassword(newPassword);
        userRepository.save(user);
    }
}
