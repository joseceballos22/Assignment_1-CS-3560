package Code;

import java.util.UUID;

/**
 * File: Student.java
 * Goal: To define a Student class
 * */

public class Student {

    private String id; //Unique Id that every Student has
    private String firstName; //First Name of the Student
    private String lastName; //Last Name of the Student

    //Constructors
    public Student()
    {
        this("", "");
    }

    public Student(String firstName)
    {
        this(firstName, "");
    }
    public Student(String firstName,String lastName)
    {
        this.id = UUID.randomUUID().toString(); //Creating a Random UUID For this Specific Student
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId()
    {
        return this.id;
    }

    @Override
    public String toString()
    {
        String answer = "";
        answer += this.firstName + " " + this.lastName;
        return answer;
    }


}
