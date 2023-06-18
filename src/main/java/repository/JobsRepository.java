package repository;

import config.MysqlConfig;
import model.JobsModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobsRepository {
    public JobsModel findJobsById(int id){
        Connection connection = null;
        JobsModel jobsModel = new JobsModel();
        try {
            connection = MysqlConfig.getConnection();
            String sql = "SELECT * From jobs j WHERE j.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            //Lấy giá trị của cột chỉ định lưu vào đối tượng
            if(resultSet.next()) {
                jobsModel.setName(resultSet.getString("name"));
                jobsModel.setStart_date(resultSet.getString("start_date"));
                jobsModel.setEnd_date(resultSet.getString("end_date"));
            }

        }catch (Exception e){
            System.out.println("Error findJobsById : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối findJobsById : " + e.getMessage());
                }
            }
        }return jobsModel;
    }
}
