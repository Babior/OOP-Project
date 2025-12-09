import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Swing-based GUI for the Climate Change Quiz.
 * Uses a QuizSession to get questions and submit answers.
 */
public class QuizGui extends JFrame {

    private QuizSession quizSession;
    private Question currentQuestion;

    // UI components
    private JLabel headerLabel;         // "Question X of Y"
    private JLabel metaLabel;           // "Difficulty / Topic"
    private JTextArea questionArea;     // Question text
    private JLabel instructionsLabel;   // Extra info per question type
    private JTextField answerField;     // User answer input
    private JButton submitButton;
    private JButton nextButton;
    private JLabel feedbackLabel;       // "Correct!" or "Incorrect."
    private JTextArea explanationArea;  // Explanation after answering

    public QuizGui(QuizSession quizSession) {
        this.quizSession = quizSession;

        setTitle("Climate Change Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null); // center on screen

        initComponents();
        layoutComponents();
        registerListeners();

        // Load first question
        showNextQuestion();

        setVisible(true);
    }

    private void initComponents() {
        headerLabel = new JLabel("Climate Change Quiz");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        metaLabel = new JLabel("");

        questionArea = new JTextArea(4, 50);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setEditable(false);

        instructionsLabel = new JLabel("Enter your answer and press Submit.");

        answerField = new JTextField(25);

        submitButton = new JButton("Submit");
        nextButton = new JButton("Next Question");
        nextButton.setEnabled(false); // disabled until user submits

        feedbackLabel = new JLabel(" ");
        feedbackLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        explanationArea = new JTextArea(5, 50);
        explanationArea.setLineWrap(true);
        explanationArea.setWrapStyleWord(true);
        explanationArea.setEditable(false);
    }

    private void layoutComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top panel: header + meta info
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1));
        topPanel.add(headerLabel);
        topPanel.add(metaLabel);

        // Center panel: question + instructions + answer field
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(5, 5));

        JScrollPane questionScroll = new JScrollPane(questionArea);
        centerPanel.add(questionScroll, BorderLayout.NORTH);
        centerPanel.add(instructionsLabel, BorderLayout.CENTER);

        JPanel answerPanel = new JPanel();
        answerPanel.add(new JLabel("Your answer: "));
        answerPanel.add(answerField);
        answerPanel.add(submitButton);

        centerPanel.add(answerPanel, BorderLayout.SOUTH);

        // Bottom panel: feedback, explanation, next button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout(5, 5));

        bottomPanel.add(feedbackLabel, BorderLayout.NORTH);
        JScrollPane explanationScroll = new JScrollPane(explanationArea);
        bottomPanel.add(explanationScroll, BorderLayout.CENTER);

        JPanel nextPanel = new JPanel();
        nextPanel.add(nextButton);
        bottomPanel.add(nextPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private void registerListeners() {
        // Submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        // Next Question button
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextQuestion();
            }
        });

        // Press Enter in the answer field to submit
        answerField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
    }

    private void handleSubmit() {
        if (currentQuestion == null) {
            return;
        }

        String userAnswer = answerField.getText().trim();
        if (userAnswer.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter an answer before submitting.",
                    "No Answer",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean correct = quizSession.submitAnswer(userAnswer);

        if (correct) {
            feedbackLabel.setText("✅ Correct!");
        } else {
            feedbackLabel.setText("❌ Incorrect.");
        }

        explanationArea.setText(currentQuestion.getExplanation());

        // After submitting, disable submit and enable next
        submitButton.setEnabled(false);
        answerField.setEnabled(false);
        nextButton.setEnabled(true);
    }

    private void showNextQuestion() {
        if (!quizSession.hasMoreQuestions()) {
            // No more questions: show summary and close or disable
            String summary = quizSession.getSummaryReport();
            JOptionPane.showMessageDialog(this,
                    summary,
                    "Quiz Summary",
                    JOptionPane.INFORMATION_MESSAGE);

            // Disable controls
            submitButton.setEnabled(false);
            nextButton.setEnabled(false);
            answerField.setEnabled(false);
            instructionsLabel.setText("Quiz finished.");
            return;
        }

        currentQuestion = quizSession.nextQuestion();

        if (currentQuestion == null) {
            // Fallback: no question was returned
            JOptionPane.showMessageDialog(this,
                    "No more questions available.",
                    "End of Quiz",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Update header and meta info
        int questionNumber = quizSession.getQuestionsAsked() + 1;
        headerLabel.setText("Climate Change Quiz");
        metaLabel.setText("Question " + questionNumber + " of "
                + quizSession.getNumQuestionsToAsk()
                + " | Difficulty: " + currentQuestion.getDifficultyLevel()
                + " | Topic: " + currentQuestion.getQuestionTopic());

        // Set question text
        questionArea.setText(currentQuestion.getQuestionText());

        // Set instructions based on question type
        if (currentQuestion instanceof MultipleChoiceQuestion) {
            instructionsLabel.setText("For MCQ, enter the option letter (A, B, C, ...) or number (1, 2, 3, ...).");
        } else if (currentQuestion instanceof TrueFalseQuestion) {
            instructionsLabel.setText("For True/False, enter T or F (or True/False).");
        } else if (currentQuestion instanceof FillInTheBlankQuestion) {
            instructionsLabel.setText("Type your answer text and press Submit.");
        } else {
            instructionsLabel.setText("Enter your answer and press Submit.");
        }

        // Clear previous input and feedback
        answerField.setText("");
        feedbackLabel.setText(" ");
        explanationArea.setText("");

        // Enable submit, disable next until user answers
        submitButton.setEnabled(true);
        answerField.setEnabled(true);
        nextButton.setEnabled(false);

        // Focus input field
        answerField.requestFocusInWindow();
    }
}
