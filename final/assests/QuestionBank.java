import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

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
            // Fallback: return any question
            if (questions.isEmpty()) {
                return null;
            }
            Random random = new Random();
            int index = random.nextInt(questions.size());
            return questions.get(index);
        }
        Random random = new Random();
        int index = random.nextInt(filtered.size());
        return filtered.get(index);
    }

    public void loadDefaultQuestionsGhanaFocused() {
        // EASY Questions
        addQuestion(new TrueFalseQuestion(
            "Trees reduce carbon dioxide in the atmosphere.",
            Difficulty.EASY,
            "Trees absorb CO2 during photosynthesis and store carbon.",
            Topic.BASICS_CLIMATE_CONCEPTS,
            true
        ));
        
        addQuestion(new MultipleChoiceQuestion(
            "What is the main greenhouse gas causing climate change?",
            Difficulty.EASY,
            "Carbon dioxide is the primary greenhouse gas from human activities.",
            Topic.BASICS_CLIMATE_CONCEPTS,
            Arrays.asList("Carbon Dioxide", "Oxygen", "Nitrogen", "Helium"),
            0
        ));
        
        addQuestion(new FillInTheBlankQuestion(
            "Ghana experiences a _____ season that is hot and dusty.",
            Difficulty.EASY,
            "The Harmattan season brings dry, dusty winds from the Sahara.",
            Topic.GHANA_CLIMATE_IMPACTS,
            "harmattan"
        ));
        
        addQuestion(new TrueFalseQuestion(
            "Plastic pollution harms ocean ecosystems.",
            Difficulty.EASY,
            "Plastic breaks down into microplastics that harm marine life.",
            Topic.BASICS_CLIMATE_CONCEPTS,
            true
        ));
        
        // MEDIUM Questions
        addQuestion(new MultipleChoiceQuestion(
            "Which region in Ghana is most prone to flooding?",
            Difficulty.MEDIUM,
            "Accra's coastal location and poor drainage make it flood-prone.",
            Topic.GHANA_CLIMATE_IMPACTS,
            Arrays.asList("Greater Accra Region", "Northern Region", "Ashanti Region", "Volta Region"),
            0
        ));
        
        addQuestion(new TrueFalseQuestion(
            "Ghana has submitted a Nationally Determined Contribution (NDC) to the UNFCCC.",
            Difficulty.MEDIUM,
            "Ghana submitted its updated NDC in 2021, pledging emission reductions.",
            Topic.GHANA_CLIMATE_POLICY,
            true
        ));
        
        addQuestion(new MultipleChoiceQuestion(
            "What percentage of Ghana's energy comes from renewable sources?",
            Difficulty.MEDIUM,
            "As of 2023, renewable energy accounts for about 42% of Ghana's energy mix.",
            Topic.GHANA_CLIMATE_POLICY,
            Arrays.asList("Around 42%", "Around 15%", "Around 60%", "Around 25%"),
            0
        ));
        
        // HARD Questions
        addQuestion(new MultipleChoiceQuestion(
            "Which sector is the largest contributor to Ghana's greenhouse gas emissions?",
            Difficulty.HARD,
            "Agriculture, particularly livestock and fertilizer use, is Ghana's largest emissions sector.",
            Topic.GHANA_CLIMATE_IMPACTS,
            Arrays.asList("Agriculture", "Energy", "Transport", "Waste"),
            0
        ));
        
        addQuestion(new FillInTheBlankQuestion(
            "Ghana aims to reduce emissions by _____% by 2030 compared to business-as-usual.",
            Difficulty.HARD,
            "Ghana's updated NDC targets a 64% reduction in emissions by 2030.",
            Topic.GHANA_CLIMATE_POLICY,
            "64"
        ));
        
        addQuestion(new TrueFalseQuestion(
            "Northern Ghana is experiencing increased desertification due to climate change.",
            Difficulty.HARD,
            "Climate change has intensified desertification in northern Ghana, affecting agriculture.",
            Topic.GHANA_CLIMATE_IMPACTS,
            true
        ));
    }
}