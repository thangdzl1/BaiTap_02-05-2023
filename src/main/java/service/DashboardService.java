package service;

import model.UserModel;
import repository.UserRepository;

import java.util.List;

public class DashboardService {
    private UserRepository userRepository = new UserRepository();
    public List<UserModel> getAllUser(){
        return userRepository.findAll();
    }
}
