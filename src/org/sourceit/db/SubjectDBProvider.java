package org.sourceit.db;

import org.sourceit.entities.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum  SubjectDBProvider {

    INSTANCE;

    private Connection connection;

    SubjectDBProvider() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_applicant", "root", "Cesare1986");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Class not found: com.mysql.jdbc.Driver " + e);
            throw new RuntimeException("Class not found: com.mysql.jdbc.Driver");
        }
    }

    public Subject getSubject(long subjectId) throws Exception {
        PreparedStatement preparedStatement = null;
        Subject subject = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM subject WHERE subject_id=?");
            preparedStatement.setInt(1, (int) subjectId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                subject = new Subject();
                subject.setId(resultSet.getInt("subject_id"));
                subject.setSubjectName(resultSet.getString("subject_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return subject;
    }

    public List<Subject> getSubjects() throws Exception {

        Statement statement = null;
        List<Subject> subjects = new ArrayList<>();

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM subject" );
            Subject subject = null;
            while (resultSet.next()) {
                subject = new Subject();
                subject.setSubjectName(resultSet.getString("subject_name"));
                subject.setId(resultSet.getInt("subject_id"));
                subjects.add(subject);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }

        return subjects;
    }

    public void saveSubject(Subject subject) throws Exception {
        PreparedStatement preparedStatement = null;

        try {
            if (subject.getId() == -1) {
                System.out.println("new subject");
                preparedStatement = connection.prepareStatement("INSERT INTO subject (subject_name)" +
                       " VALUES (?) ");

                preparedStatement.setString(1, subject.getSubjectName());

            } else {
                System.out.println("update subject");
                preparedStatement = connection.prepareStatement("UPDATE subject SET subject_name=?" +
                       " WHERE subject_id=?");

                preparedStatement.setString(1, subject.getSubjectName());
                preparedStatement.setInt(2, (int) subject.getId());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public void deleteSubject(long subjectId) throws Exception {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM subject WHERE subject_id=?");

            preparedStatement.setInt(1, (int) subjectId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
}
