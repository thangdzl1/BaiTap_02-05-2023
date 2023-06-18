package repository;

import config.MysqlConfig;
import model.TaskModel;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    public List<TaskModel> findTaskByUserId(int id){
        Connection connection = null;
        List<TaskModel> list = new ArrayList<>();
        try {
            connection = MysqlConfig.getConnection();
            String sql = "SELECT * From tasks t WHERE t.user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            //Lấy giá trị của cột chỉ định lưu vào đối tượng
            while(resultSet.next()) {
                TaskModel taskModel = new TaskModel();
                taskModel.setId(resultSet.getInt("id"));
                taskModel.setName(resultSet.getString("name"));
                taskModel.setStart_date(resultSet.getString("start_date"));
                taskModel.setEnd_date(resultSet.getString("end_date"));
                taskModel.setJob_id(resultSet.getInt("job_id"));
                taskModel.setUser_id(resultSet.getInt("user_id"));
                taskModel.setStatus_id(resultSet.getInt("status_id"));
                list.add(taskModel);
            }

        }catch (Exception e){
            System.out.println("Error findTaskByUserId : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối findTaskByUserId : " + e.getMessage());
                }
            }
        }return list;
    }
}
