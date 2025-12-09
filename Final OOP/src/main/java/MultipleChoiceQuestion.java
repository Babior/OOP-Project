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
        // TODO: Convert userAnswer (A/B/C/D or 1/2/3/4) to index and compare
        int index = 0;
        if (userAnswer == null || userAnswer.isEmpty()) {
            return false;
            char letter = Character.toUpperCase(userAnswer.charAt(0));
            index = letter - 'A';
        }
        if (index < 0 || index > 3) {
            return false;
        }
        return index == correctOptionIndex;
        }
    }