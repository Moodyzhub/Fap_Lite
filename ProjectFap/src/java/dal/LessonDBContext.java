/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Lesson;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;

/**
 *
 * @author Fatvv
 */
public class LessonDBContext extends DBContext<Lesson>{
    public ArrayList<Lesson> getLessonOfLecturer(int lid, Date from, Date to) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        try {
            String sql = "SELECT  \n"
                    + "	les.leid,les.isAttended,les.date,\n"
                    + "	g.gid,g.gname,su.subid,su.suname,\n"
                    + "	t.tid,t.tname,\n"
                    + "	r.rid,r.rname,\n"
                    + "	l.lid,l.lname\n"
                    + "FROM Lesson les INNER JOIN StudentGroup g ON g.gid = les.gid\n"
                    + "				 INNER JOIN [Subject] su ON su.subid = g.subid\n"
                    + "				 INNER JOIN TimeSlot t ON t.tid = les.tid\n"
                    + "				 INNER JOIN Room r ON r.rid = les.rid\n"
                    + "				 INNER JOIN Lecturer l ON l.lid = les.lid\n"
                    + "WHERE les.lid=? AND les.[date] >=? AND les.[date]<=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lesson les = new Lesson();
                StudentGroup g = new StudentGroup();
                Subject su = new Subject();
                TimeSlot slot = new TimeSlot();
                Room room = new Room();
                Lecturer lec = new Lecturer();

                les.setId(rs.getInt("leid"));
                les.setAttended(rs.getBoolean("isAttended"));
                les.setDate(rs.getDate("date"));

                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                su.setId(rs.getInt("subid"));
                su.setName(rs.getString("suname"));
                g.setSubject(su);

                les.setGroup(g);

                slot.setId(rs.getInt("tid"));
                slot.setName(rs.getString("tname"));
                les.setSlot(slot);

                room.setId(rs.getInt("rid"));
                room.setName(rs.getString("rname"));
                les.setRoom(room);

                lec.setId(rs.getInt("lid"));
                lec.setName(rs.getString("lname"));
                les.setLecturer(lec);

                lessons.add(les);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessonDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lessons;
    }
    
    
    
    
    

    @Override
    public ArrayList<Lesson> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Lesson entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Lesson entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Lesson entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Lesson get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
