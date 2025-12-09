import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ClimateQuizGUI extends JFrame {
    private QuizSession quizSession;
    private User currentUser;
    
    // UI Colors
    private final Color PRIMARY_COLOR = new Color(46, 125, 50); // Dark Green
    private final Color SECONDARY_COLOR = new Color(139, 195, 74); // Light Green
    private final Color ACCENT_COLOR = new Color(244, 67, 54); // Red for incorrect
    private final Color CORRECT_COLOR = new Color(76, 175, 80); // Green for correct
    private final Color BACKGROUND_COLOR = new Color(240, 255, 240); // Very light green
    private final Color CARD_COLOR = Color.WHITE;
    private final Color TEXT_COLOR = new Color(33, 33, 33); // Dark gray
    
    // UI Components
    private JPanel mainPanel;
    private CardLayout cardLayout;
    
    // Welcome Screen
    private JPanel welcomePanel;
    private JTextField nameField;
    
    // Quiz Screen
    private JPanel quizPanel;
    private JLabel questionLabel;
    private JPanel optionsPanel;
    private JTextField answerField;
    private JButton submitButton;
    private JLabel feedbackLabel;
    private JLabel difficultyLabel;
    private JLabel scoreLabel;
    private JLabel questionNumberLabel;
    private JTextArea explanationArea;
    private JProgressBar progressBar;
    
    // Results Screen
    private JPanel resultsPanel;
    private JTextArea resultsArea;
    private JButton restartButton;
    
    // For True/False questions
    private ButtonGroup trueFalseGroup;
    private JRadioButton trueButton;
    private JRadioButton falseButton;
    
    public ClimateQuizGUI() {
        super("🌍 Climate Change Quiz - Ghana Focus");
        initializeComponents();
        setupUI();
        showWelcomeScreen();
        setVisible(true);
    }
    
    private void initializeComponents() {
        // Set up the main panel with CardLayout for screen switching
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(BACKGROUND_COLOR);
        
        // Create the three screens
        createWelcomeScreen();
        createQuizScreen();
        createResultsScreen();
        
        // Add screens to main panel
        mainPanel.add(welcomePanel, "WELCOME");
        mainPanel.add(quizPanel, "QUIZ");
        mainPanel.add(resultsPanel, "RESULTS");
        
        // Set up the main window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null); // Center on screen
        setResizable(false);
        add(mainPanel);
    }
    
    private void setupUI() {
        // Set custom look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void createWelcomeScreen() {
        welcomePanel = new JPanel(new BorderLayout(30, 30));
        welcomePanel.setBackground(BACKGROUND_COLOR);
        
        // Header with gradient effect simulation
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        
        JLabel titleLabel = new JLabel("🌍 CLIMATE CHANGE QUIZ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel subtitleLabel = new JLabel("Test Your Knowledge About Climate Change in Ghana", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitleLabel.setForeground(new Color(240, 240, 240));
        
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(subtitleLabel, BorderLayout.SOUTH);
        
        // Center content panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));
        
        // Name input card
        JPanel nameCard = createCardPanel();
        nameCard.setLayout(new GridLayout(3, 1, 10, 10));
        
        JLabel namePrompt = new JLabel("Enter Your Name", SwingConstants.CENTER);
        namePrompt.setFont(new Font("Segoe UI", Font.BOLD, 20));
        namePrompt.setForeground(PRIMARY_COLOR);
        
        nameField = new JTextField();
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        nameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SECONDARY_COLOR, 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        
        JButton startButton = createStyledButton("🚀 Start Quiz", PRIMARY_COLOR);
        startButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.addActionListener(e -> startQuiz());
        
        // Press Enter to start
        nameField.addActionListener(e -> startQuiz());
        
        buttonPanel.add(startButton);
        
        nameCard.add(namePrompt);
        nameCard.add(nameField);
        nameCard.add(buttonPanel);
        
        // Instructions card
        JPanel instructionCard = createCardPanel();
        instructionCard.setLayout(new BorderLayout());
        
        JLabel instructionTitle = new JLabel("📋 Instructions", SwingConstants.CENTER);
        instructionTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        instructionTitle.setForeground(PRIMARY_COLOR);
        instructionTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        JTextArea instructions = new JTextArea(
            "• Answer 10 questions on climate change in Ghana\n" +
            "• Difficulty adapts based on your performance\n" +
            "• Multiple Choice: Select from A, B, C, or D\n" +
            "• True/False: Choose True or False\n" +
            "• Fill in Blank: Type your answer\n" +
            "• Get detailed explanations after each question\n" +
            "• Track your progress with the progress bar\n" +
            "• Aim for high accuracy to reach higher difficulty levels!"
        );
        instructions.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        instructions.setEditable(false);
        instructions.setOpaque(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setForeground(TEXT_COLOR);
        
        instructionCard.add(instructionTitle, BorderLayout.NORTH);
        instructionCard.add(instructions, BorderLayout.CENTER);
        
        // Add cards to center panel
        centerPanel.add(nameCard);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(instructionCard);
        
        // Footer
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setOpaque(false);
        JLabel footerLabel = new JLabel("🌱 Learn • Adapt • Protect", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        footerLabel.setForeground(new Color(100, 100, 100));
        footerPanel.add(footerLabel);
        
        // Add components to welcome panel
        welcomePanel.add(headerPanel, BorderLayout.NORTH);
        welcomePanel.add(centerPanel, BorderLayout.CENTER);
        welcomePanel.add(footerPanel, BorderLayout.SOUTH);
    }
    
    private void createQuizScreen() {
        quizPanel = new JPanel(new BorderLayout(15, 15));
        quizPanel.setBackground(BACKGROUND_COLOR);
        
        // Top panel with progress info
        JPanel topPanel = createCardPanel();
        topPanel.setLayout(new GridLayout(1, 5, 10, 10));
        
        // Progress bar
        progressBar = new JProgressBar(0, 10);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setForeground(SECONDARY_COLOR);
        progressBar.setBackground(new Color(230, 230, 230));
        progressBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Info labels
        questionNumberLabel = createInfoLabel("Question: 1/10");
        difficultyLabel = createInfoLabel("Difficulty: EASY");
        scoreLabel = createInfoLabel("Score: 0%");
        
        // Quit button
        JButton quitButton = createStyledButton("🚪 Quit", ACCENT_COLOR);
        quitButton.addActionListener(e -> returnToWelcome());
        
        topPanel.add(progressBar);
        topPanel.add(questionNumberLabel);
        topPanel.add(difficultyLabel);
        topPanel.add(scoreLabel);
        topPanel.add(quitButton);
        
        // Main question area
        JPanel questionArea = createCardPanel();
        questionArea.setLayout(new BorderLayout(20, 20));
        
        // Question label with nice styling
        JPanel questionHeader = new JPanel(new BorderLayout());
        questionHeader.setOpaque(false);
        
        JLabel questionTag = new JLabel("❓ QUESTION");
        questionTag.setFont(new Font("Segoe UI", Font.BOLD, 16));
        questionTag.setForeground(PRIMARY_COLOR);
        questionTag.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        questionLabel = new JLabel("Question will appear here...");
        questionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        questionLabel.setForeground(TEXT_COLOR);
        
        questionHeader.add(questionTag, BorderLayout.NORTH);
        questionHeader.add(questionLabel, BorderLayout.CENTER);
        
        // Options panel
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setOpaque(false);
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Input area (for fill-in questions)
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setOpaque(false);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        answerField = new JTextField();
        answerField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        answerField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SECONDARY_COLOR, 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        submitButton = createStyledButton("📤 Submit Answer", PRIMARY_COLOR);
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        submitButton.addActionListener(e -> submitAnswer());
        
        // Press Enter to submit
        answerField.addActionListener(e -> submitAnswer());
        
        inputPanel.add(new JLabel("Your answer:"), BorderLayout.WEST);
        inputPanel.add(answerField, BorderLayout.CENTER);
        inputPanel.add(submitButton, BorderLayout.EAST);
        
        questionArea.add(questionHeader, BorderLayout.NORTH);
        questionArea.add(optionsPanel, BorderLayout.CENTER);
        questionArea.add(inputPanel, BorderLayout.SOUTH);
        
        // Right panel for feedback and explanation
        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));
        rightPanel.setOpaque(false);
        
        // Feedback panel
        JPanel feedbackPanel = createCardPanel();
        feedbackPanel.setLayout(new BorderLayout());
        
        feedbackLabel = new JLabel("", SwingConstants.CENTER);
        feedbackLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        feedbackLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        feedbackPanel.add(feedbackLabel, BorderLayout.CENTER);
        
        // Explanation panel
        JPanel explanationPanel = createCardPanel();
        explanationPanel.setLayout(new BorderLayout());
        
        JLabel explanationTitle = new JLabel("💡 Explanation", SwingConstants.CENTER);
        explanationTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        explanationTitle.setForeground(PRIMARY_COLOR);
        explanationTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        explanationArea = new JTextArea();
        explanationArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        explanationArea.setLineWrap(true);
        explanationArea.setWrapStyleWord(true);
        explanationArea.setEditable(false);
        explanationArea.setBackground(CARD_COLOR);
        explanationArea.setForeground(TEXT_COLOR);
        
        JScrollPane explanationScroll = new JScrollPane(explanationArea);
        explanationScroll.setBorder(null);
        
        explanationPanel.add(explanationTitle, BorderLayout.NORTH);
        explanationPanel.add(explanationScroll, BorderLayout.CENTER);
        
        rightPanel.add(feedbackPanel, BorderLayout.NORTH);
        rightPanel.add(explanationPanel, BorderLayout.CENTER);
        
        // Add all panels to main quiz panel
        quizPanel.add(topPanel, BorderLayout.NORTH);
        quizPanel.add(questionArea, BorderLayout.CENTER);
        quizPanel.add(rightPanel, BorderLayout.EAST);
        
        // Add padding
        quizPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }
    
    private void createResultsScreen() {
        resultsPanel = new JPanel(new BorderLayout(30, 30));
        resultsPanel.setBackground(BACKGROUND_COLOR);
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        
        JLabel resultsTitle = new JLabel("🏆 QUIZ RESULTS", SwingConstants.CENTER);
        resultsTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
        resultsTitle.setForeground(Color.WHITE);
        
        headerPanel.add(resultsTitle, BorderLayout.CENTER);
        
        // Results content
        JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));
        
        // Results card
        JPanel resultsCard = createCardPanel();
        resultsCard.setLayout(new BorderLayout());
        
        resultsArea = new JTextArea();
        resultsArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        resultsArea.setEditable(false);
        resultsArea.setOpaque(false);
        resultsArea.setLineWrap(true);
        resultsArea.setWrapStyleWord(true);
        resultsArea.setForeground(TEXT_COLOR);
        
        JScrollPane resultsScroll = new JScrollPane(resultsArea);
        resultsScroll.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        resultsScroll.getViewport().setOpaque(false);
        
        resultsCard.add(resultsScroll, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        
        restartButton = createStyledButton("🔄 Take Another Quiz", PRIMARY_COLOR);
        restartButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        restartButton.setPreferredSize(new Dimension(250, 60));
        restartButton.addActionListener(e -> returnToWelcome());
        
        JButton exitButton = createStyledButton("🚪 Exit", ACCENT_COLOR);
        exitButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        exitButton.setPreferredSize(new Dimension(150, 60));
        exitButton.addActionListener(e -> System.exit(0));
        
        buttonPanel.add(restartButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(exitButton);
        
        contentPanel.add(resultsCard, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Footer
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setOpaque(false);
        JLabel footerLabel = new JLabel("Thank you for learning about climate change!", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        footerLabel.setForeground(new Color(100, 100, 100));
        footerPanel.add(footerLabel);
        
        resultsPanel.add(headerPanel, BorderLayout.NORTH);
        resultsPanel.add(contentPanel, BorderLayout.CENTER);
        resultsPanel.add(footerPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createCardPanel() {
        JPanel card = new JPanel();
        card.setBackground(CARD_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        return card;
    }
    
    private JLabel createInfoLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(TEXT_COLOR);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return label;
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.darker());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
            }
        });
        
        return button;
    }
    
    private void showWelcomeScreen() {
        cardLayout.show(mainPanel, "WELCOME");
        nameField.setText("");
        nameField.requestFocus();
    }
    
    private void startQuiz() {
        String userName = nameField.getText().trim();
        if (userName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your name!", "Input Required", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Create user and quiz session
        currentUser = new User(userName);
        QuestionBank questionBank = new QuestionBank();
        questionBank.loadDefaultQuestionsGhanaFocused();
        
        quizSession = new QuizSession(currentUser, questionBank, 10);
        
        // Show quiz screen
        cardLayout.show(mainPanel, "QUIZ");
        
        // Display first question
        showNextQuestion();
    }
    
    private void showNextQuestion() {
        if (!quizSession.hasMoreQuestions()) {
            showResults();
            return;
        }
        
        // Get next question from QuizSession
        Question question = quizSession.nextQuestion();
        if (question == null) {
            JOptionPane.showMessageDialog(this, "No more questions available.", "Quiz Complete", 
                JOptionPane.INFORMATION_MESSAGE);
            showResults();
            return;
        }
        
        // Update UI with question
        questionLabel.setText("<html><div style='width:500px; padding:10px;'>" + 
                             question.getQuestionText() + "</div></html>");
        
        // Clear previous options
        optionsPanel.removeAll();
        answerField.setText("");
        feedbackLabel.setText("");
        explanationArea.setText("");
        
        // Show appropriate input based on question type
        if (question instanceof MultipleChoiceQuestion) {
            showMultipleChoice((MultipleChoiceQuestion) question);
        } else if (question instanceof TrueFalseQuestion) {
            showTrueFalse((TrueFalseQuestion) question);
        } else if (question instanceof FillInTheBlankQuestion) {
            showFillInTheBlank((FillInTheBlankQuestion) question);
        }
        
        // Update progress indicators
        updateProgressIndicators();
        
        // Refresh the UI
        optionsPanel.revalidate();
        optionsPanel.repaint();
        
        if (answerField.isVisible()) {
            answerField.requestFocus();
        }
    }
    
    private void showMultipleChoice(MultipleChoiceQuestion mcq) {
        answerField.setVisible(false);
        submitButton.setVisible(false);
        
        List<String> options = mcq.getOptions();
        ButtonGroup group = new ButtonGroup();
        
        char optionLetter = 'A';
        for (String option : options) {
            JRadioButton radioButton = new JRadioButton("<html><b>" + optionLetter + ")</b> " + option + "</html>");
            radioButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            radioButton.setActionCommand(String.valueOf(optionLetter));
            radioButton.setOpaque(false);
            radioButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            // Style the radio button
            radioButton.setIcon(new ImageIcon(createCircleIcon(false)));
            radioButton.setSelectedIcon(new ImageIcon(createCircleIcon(true)));
            
            group.add(radioButton);
            optionsPanel.add(radioButton);
            optionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            
            optionLetter++;
        }
        
        // Add submit button for MCQs
        JButton mcqSubmit = createStyledButton("📤 Submit Answer", PRIMARY_COLOR);
        mcqSubmit.setFont(new Font("Segoe UI", Font.BOLD, 16));
        mcqSubmit.addActionListener(e -> {
            String selected = group.getSelection() != null ? group.getSelection().getActionCommand() : "";
            if (selected.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select an answer!", "Selection Required", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            processAnswer(selected);
        });
        
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        optionsPanel.add(mcqSubmit);
    }
    
    private void showTrueFalse(TrueFalseQuestion tfq) {
        answerField.setVisible(false);
        submitButton.setVisible(false);
        
        optionsPanel.removeAll();
        
        // Create True/False radio buttons
        trueFalseGroup = new ButtonGroup();
        
        JPanel tfPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        tfPanel.setOpaque(false);
        
        trueButton = new JRadioButton("<html><b>✓ TRUE</b></html>");
        styleTrueFalseButton(trueButton, CORRECT_COLOR);
        trueButton.setActionCommand("T");
        
        falseButton = new JRadioButton("<html><b>✗ FALSE</b></html>");
        styleTrueFalseButton(falseButton, ACCENT_COLOR);
        falseButton.setActionCommand("F");
        
        trueFalseGroup.add(trueButton);
        trueFalseGroup.add(falseButton);
        
        tfPanel.add(trueButton);
        tfPanel.add(falseButton);
        
        optionsPanel.add(tfPanel);
        
        // Add submit button
        JButton tfSubmit = createStyledButton("📤 Submit Answer", PRIMARY_COLOR);
        tfSubmit.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tfSubmit.addActionListener(e -> {
            String selected = trueFalseGroup.getSelection() != null ? 
                trueFalseGroup.getSelection().getActionCommand() : "";
            if (selected.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select True or False!", "Selection Required", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            processAnswer(selected);
        });
        
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        optionsPanel.add(tfSubmit);
    }
    
    private void styleTrueFalseButton(JRadioButton button, Color color) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
        button.setOpaque(true);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
        
        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.darker());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
            }
        });
    }
    
    private void showFillInTheBlank(FillInTheBlankQuestion fib) {
        answerField.setVisible(true);
        submitButton.setVisible(true);
        answerField.setText("");
        answerField.setToolTipText("Type your answer (case insensitive)");
        
        // Clear options panel
        optionsPanel.removeAll();
        JLabel hintLabel = new JLabel("Type your answer in the box below:");
        hintLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        hintLabel.setForeground(new Color(100, 100, 100));
        optionsPanel.add(hintLabel);
    }
    
    private Image createCircleIcon(boolean selected) {
        int size = 20;
        java.awt.image.BufferedImage image = new java.awt.image.BufferedImage(
            size, size, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (selected) {
            g2d.setColor(PRIMARY_COLOR);
            g2d.fillOval(2, 2, size-4, size-4);
            g2d.setColor(Color.WHITE);
            g2d.fillOval(6, 6, size-12, size-12);
        } else {
            g2d.setColor(new Color(200, 200, 200));
            g2d.drawOval(2, 2, size-4, size-4);
        }
        
        g2d.dispose();
        return image;
    }
    
    private void updateProgressIndicators() {
        if (quizSession == null || currentUser == null) return;
        
        int asked = quizSession.getQuestionsAsked();
        int total = quizSession.getNumQuestionsToAsk();
        double accuracy = currentUser.getAccuracy() * 100;
        
        questionNumberLabel.setText(String.format("Question: %d/%d", asked + 1, total));
        difficultyLabel.setText(String.format("Difficulty: %s", currentUser.getCurrentDifficulty()));
        scoreLabel.setText(String.format("Score: %.1f%%", accuracy));
        progressBar.setValue(asked + 1);
    }
    
    private void submitAnswer() {
        processAnswer(answerField.getText().trim());
    }
    
    private void processAnswer(String answer) {
        if (answer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an answer!", "Input Required", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Submit answer to QuizSession
        boolean correct = quizSession.submitAnswer(answer);
        
        // Show feedback with animation
        Question currentQ = quizSession.getCurrentQuestion();
        if (correct) {
            feedbackLabel.setText("✅ CORRECT!");
            feedbackLabel.setForeground(CORRECT_COLOR);
            feedbackPanelAnimation(CORRECT_COLOR);
        } else {
            feedbackLabel.setText("❌ INCORRECT");
            feedbackLabel.setForeground(ACCENT_COLOR);
            feedbackPanelAnimation(ACCENT_COLOR);
        }
        
        // Show explanation
        explanationArea.setText("Correct answer: " + getCorrectAnswerText(currentQ) + 
                               "\n\n" + currentQ.getExplanation());
        
        // Wait 2.5 seconds, then show next question
        Timer timer = new Timer(2500, e -> {
            showNextQuestion();
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    private void feedbackPanelAnimation(Color color) {
        Component parent = feedbackLabel.getParent();
        if (parent != null) {
            parent.setBackground(color.brighter());
            Timer timer = new Timer(100, null);
            timer.addActionListener(new ActionListener() {
                int count = 0;
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (count++ >= 10) {
                        parent.setBackground(CARD_COLOR);
                        timer.stop();
                    }
                }
            });
            timer.start();
        }
    }
    
    private String getCorrectAnswerText(Question question) {
        if (question instanceof MultipleChoiceQuestion) {
            MultipleChoiceQuestion mcq = (MultipleChoiceQuestion) question;
            List<String> options = mcq.getOptions();
            int index = mcq.getCorrectOptionIndex();
            char letter = (char) ('A' + index);
            return letter + ") " + options.get(index);
        } else if (question instanceof TrueFalseQuestion) {
            TrueFalseQuestion tfq = (TrueFalseQuestion) question;
            return tfq.getCorrectAnswer() ? "True" : "False";
        } else if (question instanceof FillInTheBlankQuestion) {
            FillInTheBlankQuestion fib = (FillInTheBlankQuestion) question;
            return "\"" + fib.getCorrectAnswer() + "\"";
        }
        return "";
    }
    
    private void showResults() {
        if (quizSession == null) return;
        
        // Get summary from QuizSession
        String summary = quizSession.getSummaryReport();
        
        // Format results nicely
        String formattedResults = "🎯 YOUR QUIZ RESULTS\n\n" +
                                 "================================\n" +
                                 summary.replace("\n", "\n") +
                                 "\n================================\n\n" +
                                 getPerformanceMessage(currentUser.getAccuracy() * 100);
        
        resultsArea.setText(formattedResults);
        
        // Show results screen
        cardLayout.show(mainPanel, "RESULTS");
    }
    
    private String getPerformanceMessage(double accuracy) {
        if (accuracy >= 90) {
            return "🌟 EXCELLENT! You're a climate change expert!";
        } else if (accuracy >= 75) {
            return "👍 GREAT JOB! You have strong climate knowledge!";
        } else if (accuracy >= 60) {
            return "📚 GOOD EFFORT! Keep learning about climate change!";
        } else {
            return "🌱 KEEP LEARNING! Every bit of knowledge helps protect our planet!";
        }
    }
    
    private void returnToWelcome() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to quit? Your current progress will be lost.",
            "Confirm Quit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            showWelcomeScreen();
        }
    }
    
    // Main method to run the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ClimateQuizGUI();
        });
    }
}