package entity;


import java.util.Objects;

/**
 *
 * @author Zokerkill
 */

public class Student {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;

      
    public Student(){
    }

    public Student(Long id, String name, String surname, String email, String phone){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }
}