package entity;


import java.util.Objects;

/**
 *
 * @author Zokerkill
 */

public class Lesson {
    private Long id;
    private String lessonName;
    private String theme;
    private String teacher;
    private Integer hours;

       
    public Lesson(){
    }

    public Lesson(Long id, String lessonName, String theme, String teacher, Integer hours){
        this.id = id;
        this.lessonName = lessonName;
        this.theme = theme;
        this.teacher = teacher;
        this.hours = hours;
    }

    public void setLessonName(String nextLine) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setTheme(String nextLine) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setTeacher(String nextLine) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setHours(String nextLine) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getLessonName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}