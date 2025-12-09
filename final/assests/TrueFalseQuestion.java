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
        // Accept T/F or True/False (case-insensitive)
        String cleanAnswer = userAnswer.trim().toLowerCase();
        
        if (cleanAnswer.equals("t") || cleanAnswer.equals("true")) {
            return correctAnswer == true;
        } else if (cleanAnswer.equals("f") || cleanAnswer.equals("false")) {
            return correctAnswer == false;
        }
        return false; // Invalid input
    }
}