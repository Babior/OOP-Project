import javax.swing.*;

/**
 * GUI entry point for the Climate Change Quiz.
 * Uses Swing (QuizGUI) instead of the console.
 */
public class GuiMain {

    public static void main(String[] args) {

        // Ask for user's name with a dialog
        String name = JOptionPane.showInputDialog(
                null,
                "Enter your name:",
                "Welcome to the Climate Change Quiz",
                JOptionPane.QUESTION_MESSAGE
        );

        if (name == null || name.trim().isEmpty()) {
            // User canceled or empty name
            JOptionPane.showMessageDialog(
                    null,
                    "No name entered. Exiting quiz.",
                    "Exit",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        name = name.trim();

        // Ask how many questions
        String numInput = JOptionPane.showInputDialog(
                null,
                "How many questions would you like to answer?",
                "Number of Questions",
                JOptionPane.QUESTION_MESSAGE
        );

        int numQuestionsToAsk = 10; // default
        if (numInput != null) {
            try {
                int value = Integer.parseInt(numInput.trim());
                if (value > 0) {
                    numQuestionsToAsk = value;
                }
            } catch (NumberFormatException e) {
                // keep default
            }
        }

        User user = new User(name);
        QuestionBank questionBank = new QuestionBank();
        questionBank.loadDefaultQuestionsGhanaFocused(); // Winfred's work

        if (questionBank.getAllQuestions().isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "No questions available in the QuestionBank. Exiting quiz.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        QuizSession quizSession = new QuizSession(user, questionBank, numQuestionsToAsk);

        // Create GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuizGui(quizSession);
            }
        });
    }
}
