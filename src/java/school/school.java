/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school;

import entity.Lesson;
import entity.SchoolHistory;
import entity.Student;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Zokerkill
 */
public class school {
    
    public static void main(String[] args) {
        
        Lesson lesson1 = new Lesson(1L, "Hajusrakenduste alused", "Java", "Juri Melnikov", 200);
        Student student1 = new Student(1L, "Vladislav", "Rodionov", "vladisla29@mail.ru", "56389267");
        
        Calendar c = new GregorianCalendar();
        
        SchoolHistory schoolHistory1 = new SchoolHistory(1L, lesson1, student1, c.getTime(), null);
        
        System.out.println(lesson1.toString());
        System.out.println(student1.toString());
        System.out.println(schoolHistory1.toString());
        
        c.add(Calendar.DATE,2);
        schoolHistory1.setStudentOnLesson(c.getTime());
        System.out.println(schoolHistory1.toString());
    }
}
