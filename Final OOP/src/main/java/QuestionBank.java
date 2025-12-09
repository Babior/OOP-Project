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
        // create MultipleChoiceQuestion / TrueFalseQuestion / FillInTheBlankQuestion
        // objects and add them here with addQuestion(...)

        // ========================================================
        // SECTION 1: MULTIPLE-CHOICE QUESTIONS (15)
        // ========================================================

        // ---------- From Ghana NDC (GHANA_POLICY) ----------
        {
            List<String> o = new ArrayList<>();
            o.add("12%");
            o.add("5%");
            o.add("20%");
            o.add("45%");
            addQuestion(new MultipleChoiceQuestion(
                    "1. What is Ghana’s UNCONDITIONAL NDC emission reduction target by 2030?",
                    Difficulty.MEDIUM,
                    "The unconditional target is about 12%.",
                    Topic.GHANA_POLICY,
                    o, 0
            ));
        }

        {
            List<String> o = new ArrayList<>();
            o.add("19 mitigation and 9 adaptation actions");
            o.add("2 mitigation and 1 adaptation action");
            o.add("No listed actions");
            o.add("Only adaptation actions");
            addQuestion(new MultipleChoiceQuestion(
                    "2. How many mitigation and adaptation actions are included in Ghana’s NDC?",
                    Difficulty.HARD,
                    "Ghana's NDC outlines 19 mitigation actions and 9 adaptation actions.",
                    Topic.GHANA_POLICY,
                    o, 0
            ));
        }

        // ---------- From Ghana Adaptation Strategy (GHANA_POLICY) ----------
        {
            List<String> o = new ArrayList<>();
            o.add("Agriculture, water, and coastal zones");
            o.add("Banking and telecommunications");
            o.add("Mining and aviation");
            o.add("Fashion and entertainment");
            addQuestion(new MultipleChoiceQuestion(
                    "3. Which sectors are identified as most climate-vulnerable in Ghana’s adaptation strategy?",
                    Difficulty.MEDIUM,
                    "Agriculture, water resources, and coastal zones are heavily impacted.",
                    Topic.GHANA_POLICY,
                    o, 0
            ));
        }

        {
            List<String> o = new ArrayList<>();
            o.add("Provide early warning systems");
            o.add("Ban all irrigation");
            o.add("Increase fossil fuel dependence");
            o.add("Stop monitoring climate hazards");
            addQuestion(new MultipleChoiceQuestion(
                    "4. Which action is a KEY part of Ghana’s adaptation strategy?",
                    Difficulty.EASY,
                    "Early warning systems help communities prepare for hazards.",
                    Topic.GHANA_POLICY,
                    o, 0
            ));
        }

        // ---------- From GHG Inventory (GHANA_POLICY) ----------
        {
            List<String> o = new ArrayList<>();
            o.add("AFOLU");
            o.add("Banking");
            o.add("Tourism");
            o.add("Telecommunications");
            addQuestion(new MultipleChoiceQuestion(
                    "5. Which sector is a major source of emissions in Ghana’s GHG Inventory?",
                    Difficulty.MEDIUM,
                    "AFOLU (Agriculture, Forestry and Land Use) is significant.",
                    Topic.GHANA_POLICY,
                    o, 0
            ));
        }

        {
            List<String> o = new ArrayList<>();
            o.add("Methane");
            o.add("Helium");
            o.add("Argon");
            o.add("Xenon");
            addQuestion(new MultipleChoiceQuestion(
                    "6. Which gas is heavily emitted from livestock activities in Ghana?",
                    Difficulty.EASY,
                    "Livestock emit methane through enteric fermentation.",
                    Topic.GHANA_POLICY,
                    o, 0
            ));
        }

        // ---------- Climate Risk Profile (GHANA_IMPACTS) ----------
        {
            List<String> o = new ArrayList<>();
            o.add("Northern Ghana");
            o.add("Upper atmosphere");
            o.add("Deep ocean");
            o.add("Sahara Desert");
            addQuestion(new MultipleChoiceQuestion(
                    "7. Which region faces the strongest drought and heat risks in Ghana?",
                    Difficulty.EASY,
                    "Northern Ghana experiences severe drought conditions.",
                    Topic.GHANA_IMPACTS,
                    o, 0
            ));
        }

        {
            List<String> o = new ArrayList<>();
            o.add("Rainfall variability");
            o.add("Volcanic activity");
            o.add("Snowstorms");
            o.add("Glacier melting within Ghana");
            addQuestion(new MultipleChoiceQuestion(
                    "8. Which hazard is expected to worsen under climate projections in Ghana?",
                    Difficulty.MEDIUM,
                    "Rainfall variability strongly affects farming and water supply.",
                    Topic.GHANA_IMPACTS,
                    o, 0
            ));
        }

        // ---------- Coastal Erosion (GHANA_IMPACTS) ----------
        {
            List<String> o = new ArrayList<>();
            o.add("Keta");
            o.add("Kumasi");
            o.add("Tamale");
            o.add("Bolgatanga");
            addQuestion(new MultipleChoiceQuestion(
                    "9. Which Ghanaian town is heavily impacted by coastal erosion?",
                    Difficulty.EASY,
                    "Keta experiences severe coastline loss.",
                    Topic.GHANA_IMPACTS,
                    o, 0
            ));
        }

        {
            List<String> o = new ArrayList<>();
            o.add("Homes and schools destroyed");
            o.add("Icebergs forming");
            o.add("Snowfall increasing");
            o.add("Coral reefs expanding");
            addQuestion(new MultipleChoiceQuestion(
                    "10. Which REAL impact of sea-level rise is reported along Ghana’s coast?",
                    Difficulty.MEDIUM,
                    "Homes and infrastructure are being destroyed.",
                    Topic.GHANA_IMPACTS,
                    o, 0
            ));
        }

        // ---------- Ghana Energy Statistics (GHANA_POLICY) ----------
        {
            List<String> o = new ArrayList<>();
            o.add("Akosombo Dam");
            o.add("Hoover Dam");
            o.add("Three Gorges Dam");
            o.add("Jirapa Dam");
            addQuestion(new MultipleChoiceQuestion(
                    "11. Which is Ghana’s largest hydropower station?",
                    Difficulty.EASY,
                    "Akosombo Dam provides major hydroelectric power.",
                    Topic.GHANA_POLICY,
                    o, 0
            ));
        }

        {
            List<String> o = new ArrayList<>();
            o.add("Solar");
            o.add("Coal");
            o.add("Diesel");
            o.add("Crude oil");
            addQuestion(new MultipleChoiceQuestion(
                    "12. Which renewable energy source is rapidly expanding in Ghana?",
                    Difficulty.EASY,
                    "Solar energy adoption is increasing across Ghana.",
                    Topic.GHANA_POLICY,
                    o, 0
            ));
        }

        {
            List<String> o = new ArrayList<>();
            o.add("Industry");
            o.add("Fashion");
            o.add("Aviation");
            o.add("Comedy sector");
            addQuestion(new MultipleChoiceQuestion(
                    "13. Which sector consumes the most electricity in Ghana?",
                    Difficulty.MEDIUM,
                    "Industrial operations use a large share of national electricity.",
                    Topic.GHANA_POLICY,
                    o, 0
            ));
        }

        // ---------- NASA Climate Basics (GLOBAL_BASICS) ----------
        {
            List<String> o = new ArrayList<>();
            o.add("CO₂ traps heat in the atmosphere");
            o.add("CO₂ cools the planet");
            o.add("CO₂ blocks sunlight completely");
            o.add("CO₂ is unrelated to warming");
            addQuestion(new MultipleChoiceQuestion(
                    "14. What is the role of CO₂ in global warming?",
                    Difficulty.MEDIUM,
                    "CO₂ traps heat, increasing Earth’s temperature.",
                    Topic.GLOBAL_BASICS,
                    o, 0
            ));
        }

        {
            List<String> o = new ArrayList<>();
            o.add("Long-term average weather over decades");
            o.add("A single rainfall event");
            o.add("Yesterday’s temperature");
            o.add("A weekly weather forecast");
            addQuestion(new MultipleChoiceQuestion(
                    "15. What is climate?",
                    Difficulty.EASY,
                    "Climate reflects long-term weather conditions.",
                    Topic.GLOBAL_BASICS,
                    o, 0
            ));
        }


        // ========================================================
        // SECTION 2: TRUE OR FALSE QUESTIONS (15)
        // ========================================================

        // ---------- Ghana NDC ----------
        addQuestion(new TrueFalseQuestion(
                "16. Ghana’s NDC includes both mitigation and adaptation actions.",
                Difficulty.EASY,
                "True — the NDC includes efforts to reduce emissions and increase resilience.",
                Topic.GHANA_POLICY,
                true
        ));

        addQuestion(new TrueFalseQuestion(
                "17. Ghana can meet its conditional NDC targets without international support.",
                Difficulty.HARD,
                "False — conditional targets require significant financial and technological support.",
                Topic.GHANA_POLICY,
                false
        ));

        // ---------- Adaptation Strategy ----------
        addQuestion(new TrueFalseQuestion(
                "18. Agriculture is one of the most climate-vulnerable sectors in Ghana.",
                Difficulty.EASY,
                "True — rainfall variability and drought strongly affect farming.",
                Topic.GHANA_POLICY,
                true
        ));

        addQuestion(new TrueFalseQuestion(
                "19. Early warning systems are NOT part of Ghana’s national adaptation plans.",
                Difficulty.MEDIUM,
                "False — early warning systems are essential adaptation measures.",
                Topic.GHANA_POLICY,
                false
        ));

        // ---------- GHG Inventory ----------
        addQuestion(new TrueFalseQuestion(
                "20. Livestock emissions contribute significantly to methane emissions in Ghana.",
                Difficulty.MEDIUM,
                "True — enteric fermentation from cattle emits methane.",
                Topic.GHANA_POLICY,
                true
        ));

        addQuestion(new TrueFalseQuestion(
                "21. Ghana's GHG inventory ignores carbon removals from forests.",
                Difficulty.EASY,
                "False — removals are included in national inventories.",
                Topic.GHANA_POLICY,
                false
        ));

        // ---------- Climate Risk Profile ----------
        addQuestion(new TrueFalseQuestion(
                "22. Average temperatures in Ghana are projected to continue rising.",
                Difficulty.EASY,
                "True — climate models show consistent warming.",
                Topic.GHANA_IMPACTS,
                true
        ));

        addQuestion(new TrueFalseQuestion(
                "23. Northern Ghana is projected to experience more heat waves.",
                Difficulty.MEDIUM,
                "True — temperatures are rising fastest in northern areas.",
                Topic.GHANA_IMPACTS,
                true
        ));

        // ---------- Coastal Erosion ----------
        addQuestion(new TrueFalseQuestion(
                "24. Sea-level rise has destroyed homes and schools along sections of Ghana’s coastline.",
                Difficulty.MEDIUM,
                "True — communities like Keta have suffered major losses.",
                Topic.GHANA_IMPACTS,
                true
        ));

        addQuestion(new TrueFalseQuestion(
                "25. Coastal erosion has been completely solved in Ghana.",
                Difficulty.EASY,
                "False — erosion remains a major threat.",
                Topic.GHANA_IMPACTS,
                false
        ));

        // ---------- Ghana Energy Statistics ----------
        addQuestion(new TrueFalseQuestion(
                "26. Ghana generates electricity from both hydropower and thermal sources.",
                Difficulty.EASY,
                "True — hydro and natural gas are major contributors.",
                Topic.GHANA_POLICY,
                true
        ));

        addQuestion(new TrueFalseQuestion(
                "27. Solar mini-grids help electrify remote Ghanaian communities.",
                Difficulty.MEDIUM,
                "True — they support rural energy access.",
                Topic.GHANA_POLICY,
                true
        ));

        // ---------- NASA Climate Basics ----------
        addQuestion(new TrueFalseQuestion(
                "28. CO₂ is the main gas responsible for trapping heat in the atmosphere.",
                Difficulty.MEDIUM,
                "True — CO₂ is the most significant long-lived greenhouse gas.",
                Topic.GLOBAL_BASICS,
                true
        ));

        addQuestion(new TrueFalseQuestion(
                "29. Weather refers to long-term patterns, while climate refers to day-to-day conditions.",
                Difficulty.EASY,
                "False — the definitions are reversed.",
                Topic.GLOBAL_BASICS,
                false
        ));

        addQuestion(new TrueFalseQuestion(
                "30. Climate change contributes to more intense rainfall events in West Africa.",
                Difficulty.MEDIUM,
                "True — increased rainfall extremes are widely reported.",
                Topic.AFRICA_REGIONAL,
                true
        ));


        // ========================================================
        // SECTION 3: FILL-IN-THE-BLANK QUESTIONS (15)
        // ========================================================

        // ---------- Ghana NDC ----------
        addQuestion(new FillInTheBlankQuestion(
                "31. Ghana’s climate pledge under the Paris Agreement is called its ______.",
                Difficulty.EASY,
                "It is called the Nationally Determined Contribution (NDC).",
                Topic.GHANA_POLICY,
                "nationally determined contribution"
        ));

        addQuestion(new FillInTheBlankQuestion(
                "32. One focus of Ghana’s NDC is expanding ______ energy to reduce emissions.",
                Difficulty.MEDIUM,
                "Renewable energy is essential for Ghana’s mitigation strategy.",
                Topic.GHANA_POLICY,
                "renewable"
        ));

        // ---------- Adaptation Strategy ----------
        addQuestion(new FillInTheBlankQuestion(
                "33. Reducing the negative effects of climate impacts is called climate ______.",
                Difficulty.EASY,
                "This refers to adaptation.",
                Topic.GHANA_POLICY,
                "adaptation"
        ));

        addQuestion(new FillInTheBlankQuestion(
                "34. Flooding in Accra is worsened by poor ______ systems.",
                Difficulty.MEDIUM,
                "Poor drainage increases flood severity.",
                Topic.GHANA_POLICY,
                "drainage"
        ));

        // ---------- GHG Inventory ----------
        addQuestion(new FillInTheBlankQuestion(
                "35. Greenhouse gas emissions are measured in ______-equivalent.",
                Difficulty.MEDIUM,
                "‘CO₂-equivalent’ is the standard metric.",
                Topic.GLOBAL_BASICS,
                "co2-equivalent"
        ));

        addQuestion(new FillInTheBlankQuestion(
                "36. Deforestation reduces the land’s carbon ______ capacity.",
                Difficulty.HARD,
                "This refers to carbon sequestration.",
                Topic.GHANA_POLICY,
                "sequestration"
        ));

        // ---------- Climate Risk Profile ----------
        addQuestion(new FillInTheBlankQuestion(
                "37. Greater temperature extremes increase climate ______ for vulnerable communities.",
                Difficulty.MEDIUM,
                "The missing word is ‘risk’.",
                Topic.GHANA_IMPACTS,
                "risk"
        ));

        addQuestion(new FillInTheBlankQuestion(
                "38. Irregular rainfall patterns in Ghana are linked to climate ______.",
                Difficulty.MEDIUM,
                "The correct term is ‘variability’.",
                Topic.GHANA_IMPACTS,
                "variability"
        ));

        // ---------- Coastal Erosion ----------
        addQuestion(new FillInTheBlankQuestion(
                "39. Many households along the Volta Region have been forced to ______ due to sea-level rise.",
                Difficulty.MEDIUM,
                "The correct answer is ‘relocate’.",
                Topic.GHANA_IMPACTS,
                "relocate"
        ));

        addQuestion(new FillInTheBlankQuestion(
                "40. The gradual loss of shoreline due to wave action and rising seas is called coastal ______.",
                Difficulty.EASY,
                "The correct term is erosion.",
                Topic.GHANA_IMPACTS,
                "erosion"
        ));

        // ---------- Ghana Energy Statistics ----------
        addQuestion(new FillInTheBlankQuestion(
                "41. The Akosombo Dam generates significant ______ power for Ghana.",
                Difficulty.EASY,
                "It generates hydropower.",
                Topic.GHANA_POLICY,
                "hydro"
        ));

        addQuestion(new FillInTheBlankQuestion(
                "42. A key strategy for reducing electricity waste is improving energy ______.",
                Difficulty.MEDIUM,
                "Energy efficiency is vital for reducing unnecessary energy use.",
                Topic.GHANA_POLICY,
                "efficiency"
        ));

        // ---------- NASA Climate Basics ----------
        addQuestion(new FillInTheBlankQuestion(
                "43. The long-term warming of Earth due to greenhouse gases is known as global ______.",
                Difficulty.EASY,
                "The missing word is 'warming'.",
                Topic.GLOBAL_BASICS,
                "warming"
        ));

        addQuestion(new FillInTheBlankQuestion(
                "44. The gases that trap heat in the atmosphere are known as ______ gases.",
                Difficulty.MEDIUM,
                "The correct term is 'greenhouse gases'.",
                Topic.GLOBAL_BASICS,
                "greenhouse"
        ));

        addQuestion(new FillInTheBlankQuestion(
                "45. A long-term shift in temperatures and weather patterns is called climate ______.",
                Difficulty.EASY,
                "The word is 'change'.",
                Topic.GLOBAL_BASICS,
                "change"
        ));
    }

    }
}