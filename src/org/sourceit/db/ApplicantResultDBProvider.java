package org.sourceit.db;

import org.sourceit.entities.ApplicantResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum ApplicantResultDBProvider {

    INSTANCE;

    private Connection connection;

    ApplicantResultDBProvider() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_applicant", "root", "Cesare1986");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Class not found: com.mysql.jdbc.Driver " + e);
            throw new RuntimeException("Class not found: com.mysql.jdbc.Driver");
        }
    }

    public ApplicantResult getApplicantResult(long applicantResultId) throws Exception {
        PreparedStatement preparedStatement = null;
        ApplicantResult applicantResult = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM applicantResult WHERE applicantResult_id=?");
            preparedStatement.setInt(1, (int) applicantResultId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                applicantResult = new ApplicantResult();
                applicantResult.setId(resultSet.getInt("applicant_result_id"));
                applicantResult.setApplicantId(resultSet.getInt("applicant_id"));
                applicantResult.setSubjectId(resultSet.getInt("subject_id"));
                applicantResult.setMark(resultSet.getInt("mark"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return applicantResult;
    }

    public List<ApplicantResult> getApplicantResults() throws Exception {

        Statement statement = null;
        List<ApplicantResult> applicantResults = new ArrayList<>();

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM applicant_result" +
                    " JOIN (applicant, subject) ON applicant_result.APPLICANT_ID=applicant.APPLICANT_ID " +
                    "AND " +
                    "applicant_result.SUBJECT_ID=subject.SUBJECT_ID" );
            ApplicantResult applicantResult = null;

            while (resultSet.next()) {

                applicantResult = new ApplicantResult();
                applicantResult.setApplicantId(resultSet.getInt("applicant_id"));
                applicantResult.setSubjectId(resultSet.getInt("subject_id"));
                applicantResult.setMark(resultSet.getInt("mark"));
                applicantResult.setId(resultSet.getInt("applicant_result_id"));
                applicantResults.add(applicantResult);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }

        return applicantResults;
    }

    public void saveApplicantResult(ApplicantResult applicantResult) throws Exception {
        PreparedStatement preparedStatement = null;

        try {
            if (applicantResult.getId() == -1) {
                System.out.println("new applicant result");
                preparedStatement = connection.prepareStatement("INSERT INTO applicant_result (applicant_id, subject_id," +
                        " mark) VALUES (?, ?, ?) ");

                preparedStatement.setInt(1, (int) (long) applicantResult.getApplicantId());
                preparedStatement.setInt(2, (int) (long) applicantResult.getSubjectId());
                preparedStatement.setInt(3, applicantResult.getMark());

            } else {
                System.out.println("update applicant");
                preparedStatement = connection.prepareStatement("UPDATE applicant_result SET fapplicant_id=?, subject_id=?," +
                        " mark=?  WHERE applicant_result_id=?");

                preparedStatement.setInt(1, (int) (long) applicantResult.getApplicantId());
                preparedStatement.setInt(2, (int) (long) applicantResult.getSubjectId());
                preparedStatement.setInt(3, applicantResult.getMark());
                preparedStatement.setInt(4, (int) (long) applicantResult.getId());
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

    public void deleteApplicantResult(long applicantResultId) throws Exception {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM applicant_result WHERE applicant_result_id=?");

            preparedStatement.setInt(1, (int) applicantResultId);
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

