// File: Main.java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();

        User user = new User(name);
        QuestionBank questionBank = new QuestionBank();
        questionBank.loadDefaultQuestionsGhanaFocused(); // later

        int numQuestionsToAsk = 10; // you can change this

        QuizSession quizSession = new QuizSession(user, questionBank, numQuestionsToAsk);

        ConsoleUI consoleUI = new ConsoleUI(quizSession);
        consoleUI.run();

        scanner.close();
    }
}
