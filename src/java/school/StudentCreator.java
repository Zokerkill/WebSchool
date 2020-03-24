/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school;

import entity.Student;
import java.util.Scanner;

/**
 *
 * @author Zokerkill
 */
class StudentCreator {

    public Student returnNewStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----Добавление нового ученика-----");
        Student student = new Student();
        System.out.println("Имя ученика:");
        student.setName(scanner.nextLine());
        System.out.println("Фамилия ученика:");
        student.setSurname(scanner.nextLine());
        System.out.println("Эмайл ученика:");
        student.setEmail(scanner.nextLine());
        System.out.println("Номер ученика:");
        student.setPhone(scanner.nextLine());
        return student;
    }
    
}
