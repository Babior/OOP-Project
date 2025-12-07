import java.util.List;
public class User {
    private String name;
    private int totalQuestionsAnswered;
    private int totalCorrectAnswers;
    private Difficulty currentDifficulty;

    public User(String name) {
        this.name = name;
        this.totalQuestionsAnswered = 0;
        this.totalCorrectAnswers = 0;
        this.currentDifficulty = Difficulty.EASY;
    }

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

    public void recordAnswer(boolean correct) {
        totalQuestionsAnswered++;
        if (correct) {
            totalCorrectAnswers++;
        }
    }

    public double getAccuracy() {
        if (totalQuestionsAnswered == 0) {
            return 0.0;
        }
        return (double) totalCorrectAnswers / totalQuestionsAnswered;
    }

    // Adaptive difficulty based on recent answers
    public void updateDifficulty(List<Boolean> recentAnswers) {
        // TODO: Implement the real rule later.
        // For now, do nothing so it compiles and runs.
    }

    // Optional helper if you want later
    private void increaseDifficulty() {
        if (currentDifficulty == Difficulty.EASY) {
            currentDifficulty = Difficulty.MEDIUM;
        } else if (currentDifficulty == Difficulty.MEDIUM) {
            currentDifficulty = Difficulty.HARD;
        }
    }

    private void decreaseDifficulty() {
        if (currentDifficulty == Difficulty.HARD) {
            currentDifficulty = Difficulty.MEDIUM;
        } else if (currentDifficulty == Difficulty.MEDIUM) {
            currentDifficulty = Difficulty.EASY;
        }
    }
}
