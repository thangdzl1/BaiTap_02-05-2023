package service;

import model.RoleModel;
import model.UserModel;
import repository.RoleRepository;
import repository.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    private RoleRepository roleRepository = new RoleRepository();

    public List<UserModel> getAllUser(){
        return userRepository.findAllUser();
    }
    public boolean addUser(String email, String password, String fullname, int role_id) {
        return userRepository.addUser(email, password, fullname, role_id);
    }
    public boolean deleteUser(int id){
        return userRepository.deleteUserById(id);
    }
    public List<RoleModel> getAllModels() {
        return roleRepository.getAllRole();
    }
    public boolean adjustUser(String email, String password, String fullname, int role_id, int id){
        return userRepository.adjustUser(email, password, fullname, role_id, id);
    }
    public UserModel findById(int id){
        return userRepository.findUserById(id);
    }
}
