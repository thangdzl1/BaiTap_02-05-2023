package repository;

import config.MysqlConfig;
import model.RoleModel;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    public List<RoleModel> getAllRole(){
        Connection connection = null;
        List<RoleModel> roleModels = new ArrayList<>();
        try {
            String sql = "SELECT *FROM roles r ";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt qua từng dòng dữ liệu
                RoleModel roleModel = new RoleModel();
                //Lấy giá trị của cột chỉ định lưu vào đối tượng
                roleModel.setRole_id(resultSet.getInt("id"));
                roleModel.setRole_name(resultSet.getString("name"));
                roleModel.setRole_desc(resultSet.getString("description"));
                roleModels.add(roleModel);
            }
        }catch (Exception e){
            System.out.println("Error getAllRole : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối getAllRole : " + e.getMessage());
                }
            }
        }return roleModels;
    }
}
