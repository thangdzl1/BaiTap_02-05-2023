package repository;

import config.MysqlConfig;
import model.StatusModel;
import model.TaskModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusRepository {
    public StatusModel findStatusById(int id){
        Connection connection = null;
        StatusModel statusModel = new StatusModel();
        try {
            connection = MysqlConfig.getConnection();
            String sql = "SELECT * FROM status s WHERE s.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            //Lấy giá trị của cột chỉ định lưu vào đối tượng
            if(resultSet.next()) {
                statusModel.setId(Integer.parseInt(resultSet.getString("id")));
                statusModel.setName(resultSet.getString("name"));
            }

        }catch (Exception e){
            System.out.println("Error findStatusById : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối findStatusById : " + e.getMessage());
                }
            }
        }return statusModel;
    }
}
