package dal;

import entity.Account;
import entity.Lecturer;
import entity.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AccountDBContext extends DBContext {

    
    public Account getAccount(String username, String password) {
        try {
            Account account = new Account();
            String sql = "SELECT a.username, a.password, s.id AS studentID, l.id AS lecturerID "
           + "FROM Account a "
           + "LEFT JOIN Student s ON a.student_id = s.id "
           + "LEFT JOIN Lecturer l ON a.lecturer_id = l.id "
           + "WHERE a.username = ? AND a.password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                account.setUsername(rs.getString("Username"));
                account.setPassword(rs.getString("Password"));
                
                if (rs.getString("studentID") != null) {
                    Student student = new Student();
                    student.setId(rs.getString("studentID"));
                    account.setStudent(student);
                }
                
                if(rs.getObject("lecturerID") != null){
                    Lecturer lecturer = new Lecturer();
                    lecturer.setId(rs.getInt("lecturerID"));
                    account.setLecturer(lecturer);
                }
                
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
