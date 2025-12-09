public class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion(String questionText, Difficulty difficulty, String explanation, Topic topic, boolean correctAnswer) {
        super(questionText, difficulty, explanation, topic);
        this.correctAnswer = correctAnswer;
    }

    public boolean getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        if (userAnswer == null) {
            return false;
        }
        // This is a variable to store the value of users input
        boolean BooleanUser;
        userAnswer = userAnswer.toLowerCase();
        if (userAnswer.equals("t") || userAnswer.equals("true")) {
            BooleanUser = true;
        } else if (userAnswer.equals("f") || userAnswer.equals("false")) {
            BooleanUser = false;
        } else {
            BooleanUser = false;
        }

        return BooleanUser == correctAnswer;
    }
    }
