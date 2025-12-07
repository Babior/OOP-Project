// File: QuizSession.java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuizSession {

    private User user;
    private QuestionBank questionBank;
    private int numQuestionsToAsk;
    private int questionsAsked;
    private Question currentQuestion;
    private List<Boolean> recentAnswers;
    private Set<Question> askedQuestions;

    public QuizSession(User user, QuestionBank questionBank, int numQuestionsToAsk) {
        this.user = user;
        this.questionBank = questionBank;
        this.numQuestionsToAsk = numQuestionsToAsk;
        this.questionsAsked = 0;
        this.recentAnswers = new ArrayList<>();
        this.askedQuestions = new HashSet<>();
    }

    public User getUser() {
        return user;
    }

    public int getQuestionsAsked() {
        return questionsAsked;
    }

    public int getNumQuestionsToAsk() {
        return numQuestionsToAsk;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public boolean hasMoreQuestions() {
        return questionsAsked < numQuestionsToAsk;
    }

    public Question nextQuestion() {
        if (!hasMoreQuestions()) {
            currentQuestion = null;
            return null;
        }

        Difficulty difficulty = user.getCurrentDifficulty();
        Question candidate = null;

        int maxTries = 20;
        int tries = 0;

        while (tries < maxTries) {
            candidate = questionBank.getRandomQuestion(difficulty);
            if (candidate == null) {
                break;
            }
            if (!askedQuestions.contains(candidate)) {
                break;
            }
            tries++;
        }

        currentQuestion = candidate;
        return currentQuestion;
    }

    public boolean submitAnswer(String userAnswer) {
        if (currentQuestion == null) {
            return false;
        }

        boolean correct = currentQuestion.checkAnswer(userAnswer);

        user.recordAnswer(correct);
        questionsAsked++;

        recentAnswers.add(correct);
        if (recentAnswers.size() > 3) {
            recentAnswers.remove(0);
        }

        if (recentAnswers.size() == 3) {
            user.updateDifficulty(recentAnswers);
        }

        askedQuestions.add(currentQuestion);
        return correct;
    }

    public String getSummaryReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Quiz Summary for ").append(user.getName()).append("\n");
        sb.append("Total questions answered: ").append(user.getTotalQuestionsAnswered()).append("\n");
        sb.append("Total correct: ").append(user.getTotalCorrectAnswers()).append("\n");
        sb.append("Accuracy: ").append(String.format("%.2f", user.getAccuracy() * 100)).append("%\n");
        sb.append("Final difficulty: ").append(user.getCurrentDifficulty()).append("\n");
        return sb.toString();
    }
}
