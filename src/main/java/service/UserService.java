package service;

import model.*;
import repository.*;

import java.util.List;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    private RoleRepository roleRepository = new RoleRepository();
    private StatusRepository statusRepository = new StatusRepository();
    private JobsRepository jobsRepository = new JobsRepository();
    private TaskRepository taskRepository = new TaskRepository();

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
    public StatusModel findStatusById(int statusId){
        return statusRepository.findStatusById(statusId);
    }
    public JobsModel findJobsById(int jobId){
        return jobsRepository.findJobsById(jobId);
    }
    public List<TaskModel> findTaskByUserId(int userId){
        return taskRepository.findTaskByUserId(userId);
    }
}
