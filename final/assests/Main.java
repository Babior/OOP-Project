// File: Main.java
public class Main {
    public static void main(String[] args) {
        // Launch the GUI version
        javax.swing.SwingUtilities.invokeLater(() -> {
            new ClimateQuizGUI();
        });
    }
}