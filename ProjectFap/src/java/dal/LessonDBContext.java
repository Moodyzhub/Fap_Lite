/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.DBContext;
import entity.Lecturer;
import entity.Lesson;
import entity.Room;
import java.util.ArrayList;
import java.sql.*;
import entity.StudentGroup;
import entity.Subject;
import entity.TimeSlot;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fatvv
 */
public class LessonDBContext extends DBContext {

    public ArrayList<Lesson> getLecturerLesson(int lecid, Date from, Date to) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        try {
            String sql = "SELECT  \n"
                    + "	les.leid, les.isAttended, les.date,\n"
                    + "	g.gid, g.gname, su.subid, su.subname,\n"
                    + "	t.tid, t.tname,\n"
                    + "	r.rid, r.rname,\n"
                    + "	l.lecid, l.lecname\n"
                    + "FROM \n"
                    + "	Lesson les INNER JOIN StudentGroup g ON g.gid = les.gid\n"
                    + "	INNER JOIN [Subject] su ON su.subid = g.subid\n"
                    + "	INNER JOIN TimeSlot t ON t.tid = les.tid\n"
                    + "	INNER JOIN Room r ON r.rid = les.rid\n"
                    + "	INNER JOIN Lecturer l ON l.lecid = les.lecid\n"
                    + "WHERE \n"
                    + "	les.lecid=? AND les.[date] >=? AND les.[date]<=?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lecid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lesson les = new Lesson();
                StudentGroup sg = new StudentGroup();
                Subject sub = new Subject();
                TimeSlot slot = new TimeSlot();
                Room room = new Room();
                Lecturer lec = new Lecturer();
                //set each fields of each Object then save it to that Object
                les.setId(rs.getInt("leid"));
                les.setAttended(rs.getBoolean("isAttended"));
                les.setDate(rs.getDate("date"));

                sg.setStdg_id(rs.getInt("gid"));
                sg.setStdg_name(rs.getString("gname"));
                sub.setSub_id(rs.getInt("subid"));
                sub.setSub_name(rs.getString("subname"));
                sg.setSubject(sub);
                //then save that Object to Lesson as fields
                les.setGroup(sg);

                slot.setTime_id(rs.getInt("tid"));
                slot.setTime_name(rs.getString("tname"));
                les.setSlot(slot);

                room.setRoom_id(rs.getInt("rid"));
                room.setRoom_name(rs.getString("rname"));
                les.setRoom(room);

                lec.setLec_id(rs.getInt("lecid"));
                lec.setLec_name(rs.getString("lecname"));
                les.setLecturer(lec);
                //add each object to be a list 
                lessons.add(les);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessonDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lessons;
    }

    public ArrayList<Lesson> getStudentLesson(int stuid, Date from, Date to) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        try {
            String sql = "SELECT  \n"
                    + "    les.leid, les.isAttended, les.date,\n"
                    + "    s.sid, s.sname,\n"
                    + "    g.gid, g.gname, su.subid, su.subname,\n"
                    + "    t.tid, t.tname,\n"
                    + "    r.rid, r.rname,\n"
                    + "    l.lecid, l.lecname\n"
                    + "FROM \n"
                    + "    Lesson les \n"
                    + "    INNER JOIN StudentGroup g ON g.gid = les.gid\n"
                    + "    INNER JOIN Subject su ON su.subid = g.subid\n"
                    + "    INNER JOIN TimeSlot t ON t.tid = les.tid\n"
                    + "    INNER JOIN Room r ON r.rid = les.rid\n"
                    + "    INNER JOIN Lecturer l ON l.lecid = les.lecid\n"
                    + "    INNER JOIN Enrollment e ON e.gid = g.gid\n"
                    + "    INNER JOIN Student s ON s.sid = e.sid\n"
                    +"WHERE s.sid=? AND les.[date] >=? AND les.[date]<=?";
            

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, stuid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                //Object fields of Lesson.
                Lesson les = new Lesson();
                StudentGroup sg = new StudentGroup();
                Subject sub = new Subject();
                TimeSlot slot = new TimeSlot();
                Room room = new Room();
                Lecturer lec = new Lecturer();
                les.setId(rs.getInt("leid"));
                les.setAttended(rs.getBoolean("isAttended"));
                les.setDate(rs.getDate("date"));

                sg.setStdg_id(rs.getInt("gid"));
                sg.setStdg_name(rs.getString("gname"));
                sub.setSub_id(rs.getInt("subid"));
                sub.setSub_name(rs.getString("subname"));
                sg.setSubject(sub);
                //then save that Object to Lesson as fields
                les.setGroup(sg);

                slot.setTime_id(rs.getInt("tid"));
                slot.setTime_name(rs.getString("tname"));
                les.setSlot(slot);

                room.setRoom_id(rs.getInt("rid"));
                room.setRoom_name(rs.getString("rname"));
                les.setRoom(room);

                lec.setLec_id(rs.getInt("lecid"));
                lec.setLec_name(rs.getString("lecname"));
                les.setLecturer(lec);
                //add each object to be a list 
                lessons.add(les);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessonDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lessons;
    }

    

}
