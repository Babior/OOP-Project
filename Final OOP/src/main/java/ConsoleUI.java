// File: ConsoleUI.java
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private QuizSession quizSession;
    private Scanner scanner;

    public ConsoleUI(QuizSession quizSession) {
        this.quizSession = quizSession;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        showWelcome();

        while (quizSession.hasMoreQuestions()) {
            Question question = quizSession.nextQuestion();
            if (question == null) {
                System.out.println("No more questions available.");
                break;
            }

            displayQuestion(question);
            String userAnswer = readAnswerFor(question);

            boolean correct = quizSession.submitAnswer(userAnswer);

            if (correct) {
                System.out.println(" Correct!");
            } else {
                System.out.println(" Incorrect.");
            }

            System.out.println("Explanation: " + question.getExplanation());
        }

        showSummary();
    }

    private void showWelcome() {
        System.out.println(" Welcome to the Climate Change Quiz!!! ");
        System.out.println("Answer the questions to test your knowledge.");
        System.out.println();
    }

    private void showSummary() {
        System.out.println();
        System.out.println(" Quiz Finished ");
        System.out.println(quizSession.getSummaryReport());
    }

    private void displayQuestion(Question question) {
        System.out.println("Question:");
        System.out.println(question.getQuestionText());

        if (question instanceof MultipleChoiceQuestion) {
            MultipleChoiceQuestion mcq = (MultipleChoiceQuestion) question;
            List<String> options = mcq.getOptions();
            char label = 'A';
            for (String option : options) {
                System.out.println(label + ") " + option);
                label++;
            }
        } else if (question instanceof TrueFalseQuestion) {
            System.out.println("Enter T (True) or F (False).");
        } else if (question instanceof FillInTheBlankQuestion) {
            System.out.println("Type your answer and press Enter.");
        }
    }

    private String readAnswerFor(Question question) {
        System.out.print("Your answer: ");
        String input = scanner.nextLine().trim();
        return input;
    }
}
