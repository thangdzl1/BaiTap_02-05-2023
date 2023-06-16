package repository;

import config.MysqlConfig;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public List<UserModel> findUserByEmailAndPassword(String email, String password){
        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();
        try {
            String sql = "SELECT *FROM users u where u.email = ? AND u.password = ?";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            statement.setString(1,email);
            statement.setString(2,password);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt qua từng dòng dữ liệu
                UserModel userModel = new UserModel();
                //Lấy giá trị của cột chỉ định lưu vào đối tượng
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setRole_id(resultSet.getInt("role_id"));

                userModelList.add(userModel);
            }
        }catch (Exception e){
            System.out.println("Error findUserByEmailAndPassword : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối findUserByEmailAndPassword : " + e.getMessage());
                }
            }
        }return userModelList;
    }
    public List<UserModel> findAllUser(){
        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();
        try {
            String sql = "SELECT *FROM users u ";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt qua từng dòng dữ liệu
                UserModel userModel = new UserModel();
                //Lấy giá trị của cột chỉ định lưu vào đối tượng
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setRole_id(resultSet.getInt("role_id"));

                userModelList.add(userModel);
            }
        }catch (Exception e){
            System.out.println("Error findAllUser : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối findAllUser : " + e.getMessage());
                }
            }
        }return userModelList;

    }
    public boolean deleteUserById(int id){
        Connection connection = null;
        boolean isSucess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "DELETE FROM users u WHERE u.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            isSucess = statement.executeUpdate()>0;

        }catch (Exception e){
            System.out.println("Error deleteUserById : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối deleteUserById : " + e.getMessage());
                }
            }
        }return isSucess;
    }
    public boolean addUser(String email, String password, String fullname, int role_id){
        Connection connection = null;
        boolean isSucess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "insert into users(email,password,fullname,role_id) values(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,email);
            statement.setString(2,password);
            statement.setString(3,fullname);
            statement.setInt(4,role_id);
            isSucess = statement.executeUpdate()>0;

        }catch (Exception e){
            System.out.println("Error addUser : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối addUser : " + e.getMessage());
                }
            }
        }return isSucess;
    }
    public boolean adjustUser(String email, String password, String fullname, int role_id, int id){
        Connection connection = null;
        boolean isSucess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "UPDATE users u SET email = ?  , password = ? , fullname = ? , role_id = ? where u.id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,email);
            statement.setString(2,password);
            statement.setString(3,fullname);
            statement.setInt(4,role_id);
            statement.setInt(5,id);

            isSucess = statement.executeUpdate()>0;

        }catch (Exception e){
            System.out.println("Error adjustUser : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối adjustUser : " + e.getMessage());
                }
            }
        }return isSucess;
    }
    public UserModel findUserById(int id){
        Connection connection = null;
        UserModel userModel = new UserModel();
        try {
            connection = MysqlConfig.getConnection();
            String sql = "SELECT *FROM users u WHERE u.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            //Lấy giá trị của cột chỉ định lưu vào đối tượng
            if(resultSet.next()) {
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setPassword(resultSet.getString("password"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setRole_id(resultSet.getInt("role_id"));
            }

        }catch (Exception e){
            System.out.println("Error findUserById : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối findUserById : " + e.getMessage());
                }
            }
        }return userModel;
    }
}
