/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import java.util.Objects;
/**
 *
 * @author Zokerkill
 */
public class SchoolHistory {
    private Long id;
    private Lesson lesson;
    private Student student;
    private Date lessonDate;
    private Date studentOnLesson;


    public SchoolHistory(){
    }
    
    public SchoolHistory(Long id, Lesson lesson, Student student, Date lessonDate, Date studentOnLesson){
        this.id = id;
        this.lesson = lesson;
        this.student = student;
        this.lessonDate = lessonDate;
        this.studentOnLesson = studentOnLesson;
    }

    public void setStudentOnLesson(Date time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}