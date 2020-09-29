package Code;
import java.util.*;

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

    private String id; //Each Question will have a Unique ID

    private boolean isSingleAnswer; //Determines if the question is SingleAnswered Type

    //Constructors
    public Question(String question, boolean isSingleAnswer)
    {
        //Using Already Defined Constructor
        this(question, new HashMap<>());
        this.id = UUID.randomUUID().toString();
        this.isSingleAnswer = isSingleAnswer;
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
     * Returns A ArrayList of Keys Of the Choices Used
     * So that the User can Answer the Questions
     * */
    public ArrayList<String> getChoiceKeys()
    {
        ArrayList<String> answer = new ArrayList<>();
        Set<String> keys = this.choices.keySet();
        for(String key: keys)
        {
            answer.add(key);
        }
        return answer;
    }


    /**
     * Returns the Id Of the Given Question
     * */
    public String getId()
    {
        return this.id;
    }

    //Returns if its a single answered or not
    public boolean getIsSingleAnswered()
    {
        return this.isSingleAnswer;
    }

    //Returns the number Of Choices for the question
    public int numOfChoices()
    {
        return this.choices.size();
    }



    /**Abstract Methods*/

    /**
     * Adds a Answer to this Question
     * @param answer Answer to Add
     * */
    public abstract void addAnswer(String answer);

    /**
     * Returns a ArrayList of all the Answers to this Question
     * */
    public abstract ArrayList<String> getAnswers();





    @Override
    public String toString()
    {
        String answer = this.question;

        answer += "\n";

        for(String key : this.choices.keySet())
        {
            answer += key + " : ";
            answer += this.choices.get(key) + "\n";
        }

        return answer;
    }


}












