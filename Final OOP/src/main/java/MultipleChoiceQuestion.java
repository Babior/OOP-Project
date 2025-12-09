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
        if (userAnswer == null | userAnswer.isEmpty()) {
            return false;
        }
        int index;
        String answer = userAnswer.toUpperCase();

        switch (answer) {
            case "A":
                index = 0;
                break;
            case "B":
                index = 1;
                break;
            case "C":
                index = 2;
                break;

            case "D":
                index = 3;
                break;
            default:
                return false;
        }
        return false;
    }
    }