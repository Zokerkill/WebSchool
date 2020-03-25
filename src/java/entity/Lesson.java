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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.lessonName);
        hash = 79 * hash + Objects.hashCode(this.theme);
        hash = 79 * hash + Objects.hashCode(this.teacher);
        hash = 79 * hash + Objects.hashCode(this.hours);
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
        final Lesson other = (Lesson) obj;
        if (!Objects.equals(this.lessonName, other.lessonName)) {
            return false;
        }
        if (!Objects.equals(this.theme, other.theme)) {
            return false;
        }
        if (!Objects.equals(this.teacher, other.teacher)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.hours, other.hours)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lesson{" + "id=" + id + ", lessonName=" + lessonName + ", theme=" + theme + ", teacher=" + teacher + ", hours=" + hours + '}';
    }

}