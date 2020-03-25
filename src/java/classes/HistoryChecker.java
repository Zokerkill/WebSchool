/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import entity.SchoolHistory;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Zokerkill
 */
public class HistoryChecker {
    public void printListWhoWasOnLesson(List<SchoolHistory> schoolHistories){
        System.out.println("-----Список учеников присутсвоваших на уроке-----");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int countHistories = schoolHistories.size();
        for(int i=0; i<countHistories; i++){
            SchoolHistory h = schoolHistories.get(i);
            if(h.getStudentOnLesson()==null){
                System.out.println(i + 1 + ". "+h.getLesson().getLessonName()+". Присутствует на:"+ h.getStudent().getName()+h.getStudent().getSurname()+". Присутствовал на:"+ sdfDate.format(h.getLessonDate()));
                
            }
        }
    }
}
