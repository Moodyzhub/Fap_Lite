/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Fatvv
 */
public class StudentGroup {
    private int stdg_id;
    private String stdg_name;
    private Subject subject;

    public int getStdg_id() {
        return stdg_id;
    }

    public void setStdg_id(int stdg_id) {
        this.stdg_id = stdg_id;
    }

    public String getStdg_name() {
        return stdg_name;
    }

    public void setStdg_name(String stdg_name) {
        this.stdg_name = stdg_name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
}
