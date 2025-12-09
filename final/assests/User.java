
import java.util.List;

/**
 * Represents a user taking the quiz
 * OOP Goals: Encapsulation, used in composition by QuizSession
 */
public class User {
    // Private fields for encapsulation
    private String name;
    private int totalQuestionsAnswered;
    private int totalCorrectAnswers;
    private Difficulty currentDifficulty;
    
    /**
     * Constructor to create a new user
     * @param name The user's name
     */
    public User(String name) {
        this.name = name;
        this.totalQuestionsAnswered = 0;
        this.totalCorrectAnswers = 0;
        this.currentDifficulty = Difficulty.EASY;
    }
    
    // Getters (no setters as per requirements)
    public String getName() {
        return name;
    }
    
    public int getTotalQuestionsAnswered() {
        return totalQuestionsAnswered;
    }
    
    public int getTotalCorrectAnswers() {
        return totalCorrectAnswers;
    }
    
    public Difficulty getCurrentDifficulty() {
        return currentDifficulty;
    }
    
    /**
     * Records an answer and updates statistics
     * @param correct Whether the answer was correct
     */
    public void recordAnswer(boolean correct) {
        totalQuestionsAnswered++;
        if (correct) {
            totalCorrectAnswers++;
        }
    }
    
    /**
     * Calculates the user's accuracy
     * @return Accuracy as a double (percentage between 0-1)
     */
    public double getAccuracy() {
        if (totalQuestionsAnswered == 0) {
            return 0.0;
        }
        return (double) totalCorrectAnswers / totalQuestionsAnswered;
    }
    
    /**
     * Updates difficulty based on recent performance
     * @param recentAnswers List of recent answer correctness
     */
    public void updateDifficulty(List<Boolean> recentAnswers) {
        if (recentAnswers.size() < 3) {
            return; // Need at least 3 answers to evaluate
        }
        
        // Calculate accuracy for recent answers
        int recentCorrect = 0;
        for (boolean correct : recentAnswers) {
            if (correct) recentCorrect++;
        }
        double recentAccuracy = (double) recentCorrect / recentAnswers.size();
        
        // Apply adaptive difficulty rules
        if (recentAccuracy >= 0.8) { // 80% or higher
            increaseDifficulty();
        } else if (recentAccuracy <= 0.4) { // 40% or lower
            decreaseDifficulty();
        }
        // Otherwise, keep same difficulty
    }
    
    /**
     * Helper method to increase difficulty level
     */
    private void increaseDifficulty() {
        switch (currentDifficulty) {
            case EASY:
                currentDifficulty = Difficulty.MEDIUM;
                break;
            case MEDIUM:
                currentDifficulty = Difficulty.HARD;
                break;
            case HARD:
                // Already at hardest level, stay at HARD
                break;
        }
    }
    
    /**
     * Helper method to decrease difficulty level
     */
    private void decreaseDifficulty() {
        switch (currentDifficulty) {
            case HARD:
                currentDifficulty = Difficulty.MEDIUM;
                break;
            case MEDIUM:
                currentDifficulty = Difficulty.EASY;
                break;
            case EASY:
                // Already at easiest level, stay at EASY
                break;
        }
    }
}