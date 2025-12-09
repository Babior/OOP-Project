public class FillInTheBlankQuestion extends Question {
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
        // Trim and lowercase both for case-insensitive comparison
        String userAnswerClean = userAnswer.trim().toLowerCase();
        String correctAnswerClean = correctAnswer.trim().toLowerCase();
        return userAnswerClean.equals(correctAnswerClean);
    }
}