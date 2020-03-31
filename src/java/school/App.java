/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school;

import classes.HistoryChecker;
import classes.LessonChecker;
import classes.LessonCreator;
import classes.SchoolHistoryCreator;
import classes.StudentCreator;
import entity.Lesson;
import entity.SchoolHistory;
import entity.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Zokerkill
 */
public class App {
    private List<Lesson> lessons = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    public void run(){
       String repeat = "r";
       Scanner scanner = new Scanner(System.in);
       int task;
       do{
           System.out.println("Выберите действие:");
           System.out.println("0 - выход из программы");
           System.out.println("1 - добавить урок в базу данных");
           System.out.println("2 - добавить нового ученика");
           System.out.println("3 - проверить посещаемость ученика");
           System.out.println("4 - отметить ученика на уроке");
           System.out.println("5 - история работы школы");
           
           task = scanner.nextInt();
           switch (task) {
               case 0:
                   repeat = "q";
                   break;
               case 1:
                   LessonCreator lessonCreator = new LessonCreator();
                   lessons.add(lessonCreator.returnNewLesson());
                   break;
               case 2:
                   StudentCreator studentCreator = new StudentCreator();
                   students.add(studentCreator.returnNewStudent());
               case 3:
                   SchoolHistoryCreator schoolHistoryCreator = new SchoolHistoryCreator();
                   schoolHistories.add(schoolHistoryCreator.returnNewSchoolHistory(lessons, students));
                   break;
               case 4:
                   LessonChecker lessonChecker = new LessonChecker();
                   if(lessonChecker.returnSchoolHistory(schoolHistories)){
                       System.out.println("Ученик отмечен на уроке");
                   }else{
                       System.out.println("Не удалось отметить ученика");
                   }
                   break;
               case 5:
                   HistoryChecker historyChecker = new HistoryChecker();
                   historyChecker.printListWhoWasOnLesson(schoolHistories);
                   break;
               default:
                   System.out.println("Выберите одно из действий!");
               
           }
       }while("r".equals(repeat));
    }
    
    private List<SchoolHistory> schoolHistories = new ArrayList<>();
    
}
