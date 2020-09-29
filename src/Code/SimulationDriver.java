package Code;

import java.util.Random;

/**
 * File: SimulationDriver.java
 * Due: 9/29/20
 * What the Class Will Do:
 *      - Creating the questions and configuring the answers
 *      - Configure the questions for the IVote Service
 *      - Randomly Generate a Number of students and the answers of those Students
 *      - Submit all the students answers to IVote Service
 *      - Call the IVote Service Output function to display the Result
 * */


public class SimulationDriver {

    public static void main(String[] args)
    {
        /**Creating Questions */
        SingleAnswerQuestion q_1 = new SingleAnswerQuestion("What is the Color of the Sun ? ", true);
        q_1.addChoice("A", "The Sun's Color is Yellow");
        q_1.addChoice("B", "The Sun's Color is Pink");
        q_1.addChoice("C", "The Sun's Color is Blue");
        q_1.addAnswer("A"); //Setting Answer

        MultipleAnswerQuestion q_2 = new MultipleAnswerQuestion("Multple Answers May Apply: Which of the Following Are Countries ?", false);
        q_2.addChoice("A", "California");
        q_2.addChoice("B", "China");
        q_2.addChoice("C", "Russia");
        q_2.addChoice("D", "New Mexico");
        q_2.addChoice("E", "Alaska");
        q_2.addAnswer("B"); //Setting Answer
        q_2.addAnswer("C"); //Setting Answer

        SingleAnswerQuestion q_3 = new SingleAnswerQuestion("(True Or False): The United States is in Europe ?", true);
        q_3.addChoice("True", "");
        q_3.addChoice("False", "");
        q_3.addAnswer("False");

        /**Creating Students*/

        Student s_1 = new Student("John");
        Student s_2 = new Student("Jake", "Smith");
        Student s_3 = new Student("Sarah", "Johnson");
        Student s_4 = new Student("Ben", "Dover");

        /**Configuring Questions For VotingService Object*/

        VotingService iVote = new VotingService();

        //Adding Questions to Voting System
        iVote.addQuestion(q_1);
        iVote.addQuestion(q_2);
        iVote.addQuestion(q_3);

        //Adding Students To Voting System
        iVote.addStudent(s_1);
        iVote.addStudent(s_2);
        iVote.addStudent(s_3);
        iVote.addStudent(s_4);

        /**Randomly Selecting Answers for Each Student on Each Question And Printing the Stats  */

        Random rand = new Random();

        for(int i = 0; i < iVote.numOfQuestions(); i++)
        {
            //Printing the Question
            System.out.println("Question (" + i + ")");
            System.out.println(iVote.getCurrentQuestion());

            //Selecting A Answer for each Question
            for(int j = 0; j < iVote.numOfStudents(); j++)
            {
                //How many answers to pick for this question
                int amountToPick = 0;
                //If this is true then Its a SingleAnswered Question
                if(iVote.getCurrentQuestion().getIsSingleAnswered())
                    amountToPick = 1; //Since Only One Answer Allowed
                //Else its A MultipleAnswerQuestion
                else
                {
                    int numOfChoices = iVote.getCurrentQuestion().numOfChoices();
                    amountToPick = rand.nextInt(numOfChoices + 1);
                }
                //Randomly Choosing amountToPick Times an Answer for this question and for this student
                for(int k = 0; k < amountToPick; k++)
                {
                    iVote.answerCurrentQuestion()
                }
            }

            //Print the Stats
            iVote.getCurrentStats();

            //Go to the Next Question
            iVote.nextQuestion();
        }

    }

}
