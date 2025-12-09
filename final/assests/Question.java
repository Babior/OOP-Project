/**
 * Abstract class for all types of quiz questions.
 * Subclasses like MultipleChoiceQuestion, TrueFalseQuestion, and
 * FillInTheBlankQuestion will inherit from this class and provide their own
 * implementation of the checkAnswer() method.
 */
public abstract class Question {
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
