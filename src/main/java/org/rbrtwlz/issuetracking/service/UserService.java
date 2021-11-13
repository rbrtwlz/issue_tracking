package org.rbrtwlz.issuetracking.service;

import org.rbrtwlz.issuetracking.entity.Author;
import org.rbrtwlz.issuetracking.entity.User;
import org.rbrtwlz.issuetracking.form.UserForm;
import org.rbrtwlz.issuetracking.repository.AuthorRepository;
import org.rbrtwlz.issuetracking.repository.UserRepository;
import org.rbrtwlz.issuetracking.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
public class UserService {

    private UserRepository userRepository;
    private AuthorRepository authorRepository;
    @Autowired
    public UserService(UserRepository userRepository, AuthorRepository authorRepository){
        this.userRepository = userRepository;
        this.authorRepository = authorRepository;
    }

    public User getSupportUser(){
        List<User> supportUsers = this.userRepository.getSupportUsers();
        Random randomGenerator = new Random();
        int idx = randomGenerator.nextInt(supportUsers.size());
        return supportUsers.get(idx);

    }

    public void createNewUser(UserForm userForm, UserRole userRole){

        User user = new User();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        user.setEmail(userForm.getEmail());
        String encodedPassword = passwordEncoder.encode(userForm.getPassword());
        user.setPassword(encodedPassword);
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setRole(userRole);
        Author author = new Author(userForm.getEmail(), userRole==UserRole.SUPPORT);
        authorRepository.save(author);
        user.setAuthor(author);
        userRepository.save(user);

    }
}
