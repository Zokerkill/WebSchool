/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Zokerkill
 */
public class SchoolHistoryCreator {
    public SchoolHistory returnNewSchoolHistory(List<Lesson> lessons, List<Student> students){
        System.out.println("-----Назначение урока ученику-----");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Список уроков:");
        int countLessons = lessons.size();
        for(int i = 0; i<countLessons; i++){
            System.out.println(i+1+". "+lessons.get(i).getLessonName());
        }
        System.out.println("Список учеников:");
        int countStudents = students.size();
        for(int i = 0; i<countStudents; i++){
            System.out.println(i+1+". "+students.get(i).getName()+" "+students.get(i).getSurname());
        }
        System.out.println("Выберите номер урока:");
        int numberLesson = scanner.nextInt();
        Lesson lesson = lessons.get(numberLesson-1);
        System.out.println("Выберите номер ученика:");
        int numberStudent = scanner.nextInt();
        Student student = students.get(numberStudent-1);
        Calendar c = new GregorianCalendar();
        SchoolHistory schoolHistory = new SchoolHistory(null, lesson, student, c.getTime(), null);
        return schoolHistory;
        
    }
    
}
