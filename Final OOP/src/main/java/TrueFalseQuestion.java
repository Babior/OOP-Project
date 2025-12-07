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
        // TODO: Accept T/F or True/False (case-insensitive) and compare
        // For now, always return false so it compiles.
        return false;
    }
}
