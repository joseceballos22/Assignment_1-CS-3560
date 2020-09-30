package Code;

import java.util.ArrayList;
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

        MultipleAnswerQuestion q_2 = new MultipleAnswerQuestion("Multiple Answers May Apply: Which of the Following Are Countries ?", false);
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

        MultipleAnswerQuestion q_4 = new MultipleAnswerQuestion("Multiple Answers May Apply: Which Of the Following are NOT Colors ?", false);
        q_4.addChoice("A", "Blue");
        q_4.addChoice("B", "Pink");
        q_4.addChoice("C", "Water");
        q_4.addChoice("D", "Yellow");
        q_4.addAnswer("C"); //Setting Answer

        /**Creating Students*/

        Student s_1 = new Student("John");
        Student s_2 = new Student("Jake", "Smith");
        Student s_3 = new Student("Sarah", "Johnson");
        Student s_4 = new Student("Ben", "Dover");
        Student s_5 = new Student("Bryan");
        Student s_6 = new Student("Sophie");
        Student s_7 = new Student("Jessica");
        Student s_8 = new Student("Jimmy");
        Student s_9 = new Student("Bill");
        Student s_10 = new Student("Karen");

        /**Configuring Questions For VotingService Object*/

        VotingService iVote = new VotingService();

        //Adding Questions to Voting System
        iVote.addQuestion(q_1);
        iVote.addQuestion(q_2);
        iVote.addQuestion(q_3);
        iVote.addQuestion(q_4);

        //Adding Students To Voting System
        iVote.addStudent(s_1);
        iVote.addStudent(s_2);
        iVote.addStudent(s_3);
        iVote.addStudent(s_4);
        iVote.addStudent(s_5);
        iVote.addStudent(s_6);
        iVote.addStudent(s_7);
        iVote.addStudent(s_8);
        iVote.addStudent(s_9);
        iVote.addStudent(s_10);


        /**Randomly Selecting Answers for Each Student on Each Question And Printing the Stats  */

        Random rand = new Random();

        for(int i = 0; i < iVote.numOfQuestions(); i++)
        {
            //Printing the Question
            System.out.println("Question (" + (i+1) + ")");
            System.out.println(iVote.getCurrentQuestion());

            //Each Student Will Select an Answer for each Question
            for(Student s : iVote.getStudents())
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
                //List of all the Answers the Student Will Answer for this question
                ArrayList<String> answersFromStudent = new ArrayList<>();

                //Used to Select the Answer for this specific student
                Random rand_2 = new Random();

                //Getting A ArrayList of all valid Answers for the currentQuestion
                ArrayList<String> choicesKeys = iVote.getCurrentQuestion().getChoiceKeys();

                //Randomly Choosing amountToPick Times an Answer for this question and for this student
                for(int k = 0; k < amountToPick; k++)
                {
                    //Randomly Selecting an Answer from the Choices

                    //Getting a Random Integer
                    int randInt = rand_2.nextInt(choicesKeys.size());

                    String randomAnswer = choicesKeys.get(randInt);

                    //Adding an Answer to the Answer ArrayList
                    answersFromStudent.add(randomAnswer);
                }
                iVote.answerCurrentQuestion(s, answersFromStudent); //Answering the Question
                System.out.println("Student: " + s + " Has Just Answered Question (" + (i+1) + ")");
            }

            //Print the Stats
            iVote.getCurrentStats(String.valueOf(i+1));

            //Go to the Next Question
            iVote.nextQuestion();
        }


        /**
         * Testing Certain Edge Cases
         *  - Multiple Submissions To Same Question From Same Student
         *  - A Student Forgets to Answer Question
         * */

        VotingService test = new VotingService();
        SingleAnswerQuestion q_test = new SingleAnswerQuestion("Is California Known for its Sunny Weather ?",true);

        /**Creating a Test Question **/
        q_test.addChoice("True", "");
        q_test.addChoice("False", "");
        q_test.addAnswer("True");

        /**Creating a Test Student*/
        Student s_test = new Student("Tester");

        /**Adding the Test questions and Students to the Voting Service*/
        test.addQuestion(q_test);
        test.addStudent(s_test);

        ArrayList<String> testAnswer = new ArrayList<String>();
        testAnswer.add("False");

        ArrayList<String> otherAnswer = new ArrayList<>();
        otherAnswer.add("True");


        System.out.println("-----------------------------TESTING EDGE CASES-----------------------------");

        test.answerCurrentQuestion(s_test,testAnswer); //Answering the Question

        System.out.println("TEST QUESTION: " + test.getCurrentQuestion());
        test.getCurrentStats("FIRST ANSWER");


        System.out.println("-----------------------------STUDENT ANSWERING AGAIN-----------------------------");
        test.answerCurrentQuestion(s_test,otherAnswer); //Answering the Question Again
        test.getCurrentStats("SECOND ANSWER");


    }

}












