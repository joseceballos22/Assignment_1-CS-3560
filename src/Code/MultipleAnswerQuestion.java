package Code;

import java.util.HashSet;

/**
 * File: MultipleAnswerQuestion.java
 * Goal: A Class that allows multiple Answers to a Question
 * */

public class MultipleAnswerQuestion extends Question
{
    private HashSet<String> answers; //Answers to the Question

    //Constructor
    public MultipleAnswerQuestion(String question)
    {
        super(question);
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
     * Returns a HashSet of all the Answers to this Question
     */
    @Override
    public HashSet<String> getAnswers() {
        return this.answers;
    }
}
