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
        if  (userAnswer == null || correctAnswer == null) {
            return false;
        }
            String user_answer = userAnswer.trim().toLowerCase();
            String correct_answer = correctAnswer.trim().toLowerCase();
            return userAnswer.equals(correct_answer);
        }
}
