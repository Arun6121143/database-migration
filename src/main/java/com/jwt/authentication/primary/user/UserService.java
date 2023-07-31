package com.jwt.authentication.primary.user;

import com.jwt.authentication.primary.book.Book;
import com.jwt.authentication.primary.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

   @Autowired
   UserRepository userRepository;

   @Autowired
   BookRepository bookRepository;

    public User saveUser(User user) throws UserExistWithSameEmail {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser!=null){
            throw new UserExistWithSameEmail("A user is already registered with same Email Id");
        }
        user.setUserBookList(new ArrayList<>());
        return userRepository.save(user);
    }

    public List<User> getAllUsersList() throws NoUserInTheDatabaseException {
        if(userRepository.findAll().isEmpty()){
            throw new NoUserInTheDatabaseException("no users added in the database yet");
        }
        return userRepository.findAll();
    }

    public User findById(int userId) throws UserNotFoundWithIdException {
        if(userRepository.findById(userId)==null){
            throw new UserNotFoundWithIdException("user with this userId"+userId+" doesn't exists");
        }
        return userRepository.findById(userId);
    }

    public List<Book> findUserBooks(int userId) throws UserNotFoundWithIdException {
        if(userRepository.findById(userId)==null){
            throw new UserNotFoundWithIdException("user with this userId"+userId+" doesn't exists");
        }
        return userRepository.findById(userId).getUserBookList();
    }
}