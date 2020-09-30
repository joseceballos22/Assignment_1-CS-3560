package Code;

import javax.swing.plaf.synth.SynthMenuBarUI;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.StrictMath.round;

/**
 * File: VotingService.java
 * Goal: To Simulate The Voting Process
 * */

public class VotingService
{

    //Data Fields
    private ArrayList<Question> questions; //List of all the questions this voting service object has
    private ArrayList<Student> students; //List of all the students this voting service object has

    //Keeps Track of Answers to each question made by students
    // (key == Question ID), value == List Of all Students And their Answers Selected For that Question
    private HashMap<String, ArrayList<StudentAndAnswer>> answersToQuestions;

    //Allows us to keep track of the current Question we are on
    private int currentQuestionCounter;

    //Constructor
    public VotingService()
    {
        //Initializing Data Fields
        this.questions = new ArrayList<>();
        this.students = new ArrayList<>();
        this.answersToQuestions = new HashMap<>();
        this.currentQuestionCounter = -1; //Initially -1 Since No Question s
    }

    /**
     * Adds a Question to the Voting Service
     * */
    public void addQuestion(Question question)
    {
        if(this.questions.size() == 0)
            this.currentQuestionCounter = 0; //Start at the beginning
        this.questions.add(question);
    }

    /**
     * Adds a student to the Voting Service
     * */
    public void addStudent(Student student)
    {
        this.students.add(student);
    }

    /**
     * Allows a Student to Answer the Current Question
     * @param student The Student Trying to Answer the Current Question (MUST BE PART OF THE VOTING System to Answer)
     * @param answers The Answers the Student thinks for that question
     * */
    public boolean answerCurrentQuestion(Student student, ArrayList<String> answers)
    {
        //Ensuring that we at least have one question Or that our counter has not passed the amount of questions asked
        if(questions.size() == 0 || currentQuestionCounter >= questions.size())
        {
            System.out.println("Please Add A Question First");
            return false;
        }

        //Ensuring the Student is Part of the Voting System
        boolean partOfSystem = false; //Initially Going to assume the student is not part of the system

        for(int i =0; i < students.size(); i++)
        {
            if(student.getId() == students.get(i).getId())
            {
                partOfSystem = true; //The Student is Part of the System
            }
        }
        if(partOfSystem == false)
        {
            System.out.println("Student Is Not Part of Voting System");
            return false; //Cant add Answers Since Student Not Part Of System
        }

        /**
         * Saving the Answer the Student has provided for the current Question
         * - But First Checks if that Student has Already Answered the Question
         * - Where the Key is the Questions's Id
         * - And the Answers to that question are added to the ArrayList
         * */

        //First Have To Initialize the ArrayList if it hasn't been initialized in the HashMap
        this.answersToQuestions.putIfAbsent(this.questions.get(this.currentQuestionCounter).getId(),new ArrayList<StudentAndAnswer>());


        /**Checking if the student has already answered the question */
        for(int i = 0; i < this.answersToQuestions.get(questions.get(currentQuestionCounter).getId()).size(); i++)
        {
            //The Student Has Already Answered the Question
            if(this.answersToQuestions.get(questions.get(currentQuestionCounter).getId()).get(i).getStudentId().equals(student.getId()))
            {
                //Removing the old answer from the ArrayList of StudentAndAnswer Since The Student Already Answered
                this.answersToQuestions.get(questions.get(currentQuestionCounter).getId()).remove(i);
                break; //Breaking out of the loop
            }

        }

        StudentAndAnswer answersToQuestion = new StudentAndAnswer(student.getId() ,answers);

        //Adding the StudentID And Answers That Student Made to its ArrayList At the specified Question
        this.answersToQuestions.get(this.questions.get(this.currentQuestionCounter).getId()).add(answersToQuestion);

        return true; //Added Answers to Current Question Since Student is Part of System
    }

    /**
     * Goes to the Next Question Even if Some Students Didn't Answer
     * */
    public boolean nextQuestion()
    {
        if(questions.size() == 0 || currentQuestionCounter >= this.questions.size())
        {
            System.out.println("Failed To Move to the Next Question");
            return false;
        }

        //Else
        this.currentQuestionCounter++;

        return true;
    }

    /**Returns the CurrentQuestion*/
    public Question getCurrentQuestion()
    {
        if (this.questions.size() > 0)
            return this.questions.get(this.currentQuestionCounter);
        //ELSE
        return null;
    }

    /**
     * Returns an ArrayList Of all the Students in the Voting System
     * */
    public ArrayList<Student> getStudents()
    {
        return this.students;
    }


    /**
     * Prints the Stats of the Current Question
     * - First It Prints the Question and the Answer or Answers
     * - Then It Displays the Number of students that selected each answer With Their Percentages
     * */
    public void getCurrentStats(String questionNum)
    {
        if(this.questions.size() == 0 || this.currentQuestionCounter >= this.questions.size())
            return; //INVALID
        //ELSE

        //Printing the Stats of the Current Question
        this.getStats(this.questions.get(this.currentQuestionCounter) , questionNum);
    }
    /**
     * Private Helper method
     * Prints the Stats of the Question Specified
     * - First It Prints the Question and the Answer or Answers
     * - Then It Displays the Number of students that selected each answer With Their Percentages
     * @param question Question to get the stats from
     * */
    private void getStats(Question question, String questionNum)
    {
        System.out.println("===========================================");
        //Printing the Question
        System.out.println("Stats For Question (" + questionNum + ")" + ":");
        //System.out.println(question);
        System.out.println("Answer: " + question.getAnswers());

        ArrayList<String> answers = new ArrayList<>();

        //Getting The Answers Or Answer to the Questions
        /**To Fix Bug Of True / False Making all answers UpperCase*/
        for(String element : question.getAnswers())
        {
            answers.add(element.toUpperCase()); //Has to Be UpperCase Since Making all Keys UpperCase
        }


        int numOfStudents = this.answersToQuestions.get(question.getId()).size(); //Getting the Number of Students that answered this question
        int correct = 0; //Number of Students that answered Correctly

        //Processing the Data for this question
        for(int i = 0; i < this.answersToQuestions.get(question.getId()).size(); i++)
        {
            //Getting a Student and its answers to this question (StudentAndAnswer Object)
            StudentAndAnswer temp = this.answersToQuestions.get(question.getId()).get(i);

            //Checking if this student answered correctly by checking if its answer contains everything from the answer list
            if(answers.containsAll(temp.getAnswers()) && temp.getAnswers().containsAll(answers))
            {
                correct++; //Incrementing the Stat
            }
        }


        int incorrect = numOfStudents - correct;

        double correctPercent = (Double.valueOf(correct) / Double.valueOf(numOfStudents)) * 100;
        double incorrectPercent = 100 - correctPercent;

        System.out.printf(correct + " Students Answered Correctly: %.2f ", correctPercent);
        System.out.println("%");
        System.out.printf(incorrect + " Students Answered Incorrectly: %.2f ", incorrectPercent);
        System.out.println("%");
        System.out.println("===========================================");
    }


    public int numOfQuestions()
    {
        return this.questions.size();
    }

    public int numOfStudents()
    {
        return this.students.size();
    }



    /**
     * Helper Private Class
     * That Combines the Student Id and the answers made to a given question
     * */
    private class StudentAndAnswer {
        private String id; //id of the Student
        private ArrayList<String> answers; //Answers made by the student to this given Question

        public StudentAndAnswer(String id, ArrayList<String> answers) {
            this.id = id;
            this.answers = new ArrayList<>();
            //Making the answers all uppercase
            for(String a: answers)
            {
                this.answers.add(a.toUpperCase());
            }
        }

        public String getStudentId() {
            return this.id;
        }

        public ArrayList<String> getAnswers() {
            return this.answers;
        }

    }
}
