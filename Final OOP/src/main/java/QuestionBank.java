import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionBank {

    private List<Question> questions;

    public QuestionBank() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        if (question != null) {
            questions.add(question);
        }
    }

    public List<Question> getAllQuestions() {
        // Defensive copy
        return new ArrayList<>(questions);
    }

    public List<Question> getQuestionsByDifficulty(Difficulty difficulty) {
        List<Question> result = new ArrayList<>();
        for (Question q : questions) {
            if (q.getDifficultyLevel() == difficulty) {
                result.add(q);
            }
        }
        return result;
    }

    public List<Question> getQuestionsByTopic(Topic topic) {
        List<Question> result = new ArrayList<>();
        for (Question q : questions) {
            if (q.getQuestionTopic() == topic) {
                result.add(q);
            }
        }
        return result;
    }

    public Question getRandomQuestion(Difficulty difficulty) {
        List<Question> filtered = getQuestionsByDifficulty(difficulty);
        if (filtered.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(filtered.size());
        return filtered.get(index);
    }

    public void loadDefaultQuestionsGhanaFocused() {
        // TODO: ':
        // create MultipleChoiceQuestion / TrueFalseQuestion / FillInTheBlankQuestion
        // objects and add them here with addQuestion(...)
    }
}