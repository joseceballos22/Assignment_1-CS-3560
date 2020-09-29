package Code;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * File: MultipleAnswerQuestion.java
 * Goal: A Class that allows multiple Answers to a Question
 * */

public class MultipleAnswerQuestion extends Question
{
    private ArrayList<String> answers; //Answers to the Question

    //Constructor
    public MultipleAnswerQuestion(String question ,boolean isSingleAnswered)
    {
        super(question,isSingleAnswered);
        this.answers = new ArrayList<>(); //Initializing the HashSet
    }

    /**
     * Adds a Answer to this Question
     * @param answer Answer to Add
     */
    @Override
    public void addAnswer(String answer) {
        this.answers.add(answer);
    }

    /**
     * Returns a ArrayList of all the Answers to this Question
     */
    @Override
    public ArrayList<String> getAnswers() {
        return this.answers;
    }
}
