public class FillInTheBlankQuestion extends Question{
    private String correctAnswer;

    public FillInTheBlankQuestion(String questionText,
                                  Difficulty difficulty,
                                  String explanation,
                                  Topic topic,
                                  String correctAnswer) {
        super(questionText, difficulty, explanation, topic);
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        // TODO: Trim + lowercase both and compare
        // For now, always return false so it compiles.
        return false;
    }
}
