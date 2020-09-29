package Code;

import java.util.HashSet;

/**
 * File: SingleAnswerQuestion.java
 * Goal: A Class that allows Only One Answer to a Question
 * */

public class SingleAnswerQuestion extends Question{

    private HashSet<String> answer; //ONLY ONE ANSWER
    private int counter = 0; //Used to Ensure Only One Answer is Valid for the given Question
    //Constructor
    public SingleAnswerQuestion(String question)
    {
        super(question);
    }

    /**
     * Adds a Answer to this Question
     *
     * @param answer Answer to Add
     */
    @Override
    public void addAnswer(String answer) {
        if(counter == 0)
        {
            counter++;
            this.answer.add(answer);
        }
        //Else DON'T Add it this question only excepts One Answer
    }

    /**
     * Returns a HashSet of all the Answers to this Question
     */
    @Override
    public HashSet<String> getAnswers() {
        return this.answer;
    }
}
