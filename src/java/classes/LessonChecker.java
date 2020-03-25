/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import entity.SchoolHistory;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Zokerkill
 */
public class LessonChecker {
    public boolean returnSchoolHistory(List<SchoolHistory> schoolHistories){
        try{
            System.out.println("-----Отметить ученика-----");
            SchoolHistory schoolHistory = new SchoolHistory();
            Scanner scanner = new Scanner(System.in);
            int countSchoolHistories = schoolHistories.size();
            for (int i = 0; i < countSchoolHistories; i++){
                SchoolHistory history = schoolHistories.get(i);
                System.out.println(i+1+". "+history.getStudent().getName()+" "+history.getStudent().getSurname()+": "+history.getLesson().getLessonName());
            }
                System.out.println("Выберите номер строки с нужным уроком: ");
                System.out.println("Чтобы ничего не делать наберите - 1");
                int numHistory = scanner.nextInt();
                if(numHistory < 0) return false;
                schoolHistory = schoolHistories.get(numHistory-1);
                schoolHistories.remove(schoolHistory);
                Calendar c = new GregorianCalendar();
                schoolHistory.setStudentOnLesson(c.getTime());
        return true;
    }catch(Exception e){
        return false;
        
        }       
    }
}
