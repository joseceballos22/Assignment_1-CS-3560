package Code;
import java.util.HashMap;
import java.util.HashSet;

/**
 * File: Question.java
 * What This Class Will Do:
 *      - Has General Methods and Properties that a question class will need
 * */

abstract public class Question
{
    //Data Fields
    private String question; //question to ask the user
    private HashMap<String, String> choices; //Used to store the choices of the user Key, Value

    //Constructors
    public Question(String question)
    {
        //Using Already Defined Constructor
        this(question, new HashMap<>());
    }

    public Question(String question, HashMap<String, String> choices)
    {
        this.question = question;
        this.choices = choices;
    }

    /**
     * Adds a choice to the question
     * @param letter is the Letter the user chooses when selecting their Answer
     * @param descriptionOfLetter is the description of the letter the user selected
     * e.g.     addChoice( "A", "The Sun is Blue");
     *          addChoice( "B", "The Sun is Yellow");
     * */
    public void addChoice(String letter, String descriptionOfLetter)
    {
        //Making all Keys By Default UpperCase
        String upperKey = letter.toUpperCase();
        this.choices.put(upperKey, descriptionOfLetter);
    }

    /**
     * Adds a Answer to this Question
     * @param answer Answer to Add
     * */
    public abstract void addAnswer(String answer);

    /**
     * Returns a HashSet of all the Answers to this Question
     * */
    public abstract HashSet<String> getAnswers();

}
