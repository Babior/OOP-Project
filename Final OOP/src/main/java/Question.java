public abstract class Question {         // This class is to serve as the basis for all question-related classes
    private String questionText;
    private Difficulty difficultyLevel;
    private String explanation;
    private Topic questionTopic;

    public Question(String questionText, Difficulty difficultyLevel, String explanation, Topic questionTopic) {
        this.questionText = questionText;
        this.difficultyLevel = difficultyLevel;
        this.explanation = explanation;
        this.questionTopic = questionTopic;
    }

    public String getQuestionText() {
        return questionText;
    }

    public Difficulty getDifficultyLevel() {
        return difficultyLevel;
    }

    public String getExplanation() {
        return explanation;
    }

    public Topic getQuestionTopic() {
        return questionTopic;
    }

    // Each question will have to check it's answer
    public abstract boolean checkAnswer(String userAnswer);
}
