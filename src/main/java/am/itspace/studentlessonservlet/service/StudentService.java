package am.itspace.studentlessonservlet.service;

import am.itspace.studentlessonservlet.db.DbConnectionProvider;
import am.itspace.studentlessonservlet.model.Lesson;
import am.itspace.studentlessonservlet.model.Student;
import am.itspace.studentlessonservlet.util.DateUtil;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private Connection connection = DbConnectionProvider.getInstance().getConnection();
    private LessonService lessonService = new LessonService();


    public void add(Student student) {

        try{
            String sql = "INSERT INTO student(name,surname,email,age,lesson_id) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setInt(5, student.getLesson().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Student> getAllStudents() {
        List<Student> result = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setEmail(resultSet.getString("email"));
                student.setAge(resultSet.getInt("age"));
                student.setLesson(lessonService.getLessonById(resultSet.getInt("lesson_id")));
                result.add(student);
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        }
        return result;
    }

    public Student getStudentById(int id) {
        String sql = "SELECT * FROM student WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setEmail(resultSet.getString("email"));
                student.setAge(resultSet.getInt("age"));
                student.setLesson(lessonService.getLessonById(resultSet.getInt("lesson_id")));
                return student;
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM student WHERE id = " + studentId;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Student student) {
        try {
            String sql = "UPDATE student SET name = ? , surname = ? , email = ? , age = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setInt(5, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
