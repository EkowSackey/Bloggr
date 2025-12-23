package services;

import DAO.UserRepository;

public class UserService {
    UserRepository userRepository;
    public UserService(UserRepository repo){
        userRepository = repo;
    }

//    public void registerUser(){
//        userRepository.createUser();
//    }
//
//    public void login(){
//        userRepository.findById();
////        check if passwords match. return token.
//    }
//
//    public void viewProfile(){
//        userRepository.findByUsername();
////        show details
//    }
//
//    public void updateProfile(){
//        userRepository.findByUsername();
////        create update fields and send back
//        userRepository.updateUser();
//    }
}
