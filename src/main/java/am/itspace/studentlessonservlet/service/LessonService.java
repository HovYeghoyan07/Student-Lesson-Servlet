package am.itspace.studentlessonservlet.service;

import am.itspace.studentlessonservlet.db.DbConnectionProvider;
import am.itspace.studentlessonservlet.model.Lesson;
import am.itspace.studentlessonservlet.util.DateUtil;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class LessonService {
    private Connection connection = DbConnectionProvider.getInstance().getConnection();
    private UserService userService = new UserService();

    public void add(Lesson lesson) {

        try{
            String sql = "INSERT INTO lesson(name,duration,lecturer_name,price,user_id) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,lesson.getName());
            preparedStatement.setString(2,DateUtil.fromDateToSqlString(lesson.getDuration()));
            preparedStatement.setString(3,lesson.getLecturerName());
            preparedStatement.setDouble(4,lesson.getPrice());
            preparedStatement.setInt(5,lesson.getUser().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Lesson> getAllLessons() {
        List<Lesson> result = new ArrayList<>();
        String sql = "SELECT * FROM lesson";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt("id"));
                lesson.setName(resultSet.getString("name"));
                lesson.setDuration(resultSet.getDate("duration"));
                lesson.setLecturerName(resultSet.getString("lecturer_name"));
                lesson.setPrice(resultSet.getDouble("price"));
                lesson.setUser(userService.getUserById(resultSet.getInt("user_id")));
                result.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Lesson getLessonById(int id) {
        String sql = "SELECT * FROM lesson WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt("id"));
                lesson.setName(resultSet.getString("name"));
                lesson.setDuration(resultSet.getDate("duration"));
                lesson.setLecturerName(resultSet.getString("lecturer_name"));
                lesson.setPrice(resultSet.getDouble("price"));
                return lesson;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteLesson(int lessonId) {
        String sql = "DELETE FROM lesson WHERE id = " + lessonId;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Lesson lesson) {
        String sql = """
                UPDATE lesson SET name = '%s', duration = '%s', lecturer_name = '%s',
                price = '%f' WHERE id = %d
                """.formatted(lesson.getName(),
                DateUtil.fromDateToSqlString(lesson.getDuration())
                ,lesson.getLecturerName(),
                lesson.getPrice(), lesson.getId());


        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Lesson> getLessonsByUserId(int userId) {
        List<Lesson> lessons = new ArrayList<>();
        try {
            String sql = "SELECT * FROM lesson WHERE user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt("id"));
                lesson.setName(resultSet.getString("name"));
                lesson.setLecturerName(resultSet.getString("lecturer_name"));
                lesson.setPrice(resultSet.getDouble("price"));
                lesson.setDuration(resultSet.getDate("duration"));
                lesson.setUser(userService.getUserById(resultSet.getInt("user_id")));
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    public Lesson getLessonByName(String name) {

        try {
            String sql = "SELECT * FROM lesson WHERE name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Lesson lesson = Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .lecturerName(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .duration(resultSet.getDate("duration"))
                        .user(userService.getUserById(resultSet.getInt("user_id")))
                        .build();
                return lesson;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
