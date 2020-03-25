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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(Date lessonDate) {
        this.lessonDate = lessonDate;
    }

    public Date getStudentOnLesson() {
        return studentOnLesson;
    }

    public void setStudentOnLesson(Date studentOnLesson) {
        this.studentOnLesson = studentOnLesson;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.lesson);
        hash = 79 * hash + Objects.hashCode(this.student);
        hash = 79 * hash + Objects.hashCode(this.lessonDate);
        hash = 79 * hash + Objects.hashCode(this.studentOnLesson);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SchoolHistory other = (SchoolHistory) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.lesson, other.lesson)) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.lessonDate, other.lessonDate)) {
            return false;
        }
        if (!Objects.equals(this.studentOnLesson, other.studentOnLesson)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SchoolHistory{" + "id=" + id + ", lesson=" + lesson + ", student=" + student + ", lessonDate=" + lessonDate + ", studentOnLesson=" + studentOnLesson + '}';
    }

 
}