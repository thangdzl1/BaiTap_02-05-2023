package repository;

import config.MysqlConfig;
import model.TaskModel;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskRepository {
    public TaskModel findTaskById(int id){
        Connection connection = null;
        TaskModel taskModel = new TaskModel();
        try {
            connection = MysqlConfig.getConnection();
            String sql = "SELECT *FROM tasks t WHERE t.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            //Lấy giá trị của cột chỉ định lưu vào đối tượng
            if(resultSet.next()) {
                taskModel.setId(resultSet.getInt("id"));
                taskModel.setName(resultSet.getString("name"));
                taskModel.setStart_date(resultSet.getString("start_date"));
                taskModel.setEnd_date(resultSet.getString("end_date"));
                taskModel.setJob_id(resultSet.getInt("job_id"));
                taskModel.setUser_id(resultSet.getInt("user_id"));
                taskModel.setStatus_id(resultSet.getInt("status_id"));
            }

        }catch (Exception e){
            System.out.println("Error findTaskById : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối findTaskById : " + e.getMessage());
                }
            }
        }return taskModel;
    }
}
