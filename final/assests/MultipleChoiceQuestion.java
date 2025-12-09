import java.util.List;

public class MultipleChoiceQuestion extends Question {

    private List<String> options;
    private int correctOptionIndex; // Index of correct option in the list

    public MultipleChoiceQuestion(String questionText,  Difficulty difficulty,  String explanation,  Topic topic,  List<String> options, int correctOptionIndex) {
        super(questionText, difficulty, explanation, topic);
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        // Convert userAnswer (A/B/C/D) to index and compare
        String cleanAnswer = userAnswer.trim().toUpperCase();
        
        if (cleanAnswer.length() == 1 && cleanAnswer.charAt(0) >= 'A' && cleanAnswer.charAt(0) <= 'D') {
            int userIndex = cleanAnswer.charAt(0) - 'A';
            return userIndex == correctOptionIndex;
        }
        return false; // Invalid input
    }
}