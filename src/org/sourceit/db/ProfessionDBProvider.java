package org.sourceit.db;

import org.sourceit.entities.Profession;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum  ProfessionDBProvider {

    INSTANCE;

    private Connection connection;

    ProfessionDBProvider() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_applicant", "root", "Cesare1986");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Class not found: com.mysql.jdbc.Driver " + e);
            throw new RuntimeException("Class not found: com.mysql.jdbc.Driver");
        }
    }

    public Profession getProfession(long professionId) throws Exception {
        PreparedStatement preparedStatement = null;
        Profession profession = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM professoin WHERE profession_id=?");
            preparedStatement.setInt(1, (int) professionId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                profession = new Profession();
                profession.setId(resultSet.getInt("profession_id"));
                profession.setProfessionName(resultSet.getString("profession_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return profession;
    }

    public List<Profession> getProfessions() throws Exception {

        Statement statement = null;
        List<Profession> professions = new ArrayList<>();

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM profession");

            Profession profession = null;
            while (resultSet.next()) {
                profession = new Profession();
                profession.setProfessionName(resultSet.getString("profession_name"));
                profession.setId(resultSet.getInt("profession_id"));
                professions.add(profession);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }

        return professions;
    }

    public void saveProfession(Profession profession) throws Exception {
        PreparedStatement preparedStatement = null;

        try {
            if (profession.getId() == -1) {
                System.out.println("new profession");
                preparedStatement = connection.prepareStatement("INSERT INTO profession (profession_name)" +
                        " VALUES (?) ");

                preparedStatement.setString(1, profession.getProfessionName());


            } else {
                System.out.println("update profession");
                preparedStatement = connection.prepareStatement("UPDATE profession SET profession_name=? " +
                        " WHERE profession_id=?");

                preparedStatement.setString(1, profession.getProfessionName());
                preparedStatement.setInt(2, (int) profession.getId());
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

    public void deleteProfession(long professionId) throws Exception {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM profession WHERE applicant_id=?");

            preparedStatement.setInt(1, (int) professionId);
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
