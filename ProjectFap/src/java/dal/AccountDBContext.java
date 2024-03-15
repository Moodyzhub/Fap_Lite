/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Account;
import entity.Student;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fatvv
 */
public class AccountDBContext extends DBContext {

    public Account getByUsernamePassword(String username, String password) {
        try {
            String sql = "SELECT username,password FROM Account\n"
                    + " WHERE username = ? AND password = ?";
            //Create a PreparedStatement object to execute parameterized SQL queries
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setUsername(username);
                account.setPassword(password);
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getIdStudentByAcc(String username) {
        try {
            int idByAcc;
            String sql = "SELECT s.sid \n"
                    + "FROM Student s\n"
                    + "JOIN Account a ON s.username = a.username\n"
                    + "WHERE a.username = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                return idByAcc = rs.getInt("sid");
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;

    }

    public int getIdLecturerByAcc(String username) {
        try {
            int idByAcc;
            String sql = "SELECT l.lecid "
                    + "FROM Lecturer l "
                    + "JOIN Account a ON l.username = a.username "
                    + "WHERE a.username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                return idByAcc = rs.getInt("lecid");
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;

    }

    

}
