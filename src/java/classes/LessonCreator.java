/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import entity.Lesson;
import java.util.Scanner;

/**
 *
 * @author Zokerkill
 */
public class LessonCreator {

    public Lesson returnNewLesson() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----Добавление нового урока-----");
        Lesson lesson = new Lesson();
        System.out.println("Название урока:");
        lesson.setLessonName(scanner.nextLine());
        System.out.println("Название темы:");
        lesson.setTheme(scanner.nextLine());
        System.out.println("Учитель урока:");
        lesson.setTeacher(scanner.nextLine());
        System.out.println("Количество часов:");
        lesson.setHours(scanner.nextInt());
        return lesson;
    }   
    
}
