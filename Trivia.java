import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Trivia {
    private List<String> questions = new ArrayList<>();
    private List<Integer> answers = new ArrayList<>();

    public Trivia() {
        loadFiles();
    }

    private void loadFiles() {
        try {
            Scanner qScanner = new Scanner(new File("questions.txt"));
            while (qScanner.hasNextLine()) {
                String line = qScanner.nextLine();
                if (!line.startsWith("[source")) questions.add(line);
            }
            Scanner aScanner = new Scanner(new File("answers.txt"));
            while (aScanner.hasNextInt()) {
                answers.add(aScanner.nextInt());
            }
        } catch (Exception e) {
            System.err.println("Trivia files not found. Using defaults.");
        }
    }

    public boolean askQuestion() {
        if (questions.isEmpty()) return true; 
        int idx = new Random().nextInt(questions.size() / 5) * 5;
        
        String q = questions.get(idx);
        String[] options = {questions.get(idx+1), questions.get(idx+2), questions.get(idx+3), questions.get(idx+4)};
        
        int response = JOptionPane.showOptionDialog(null, q, "Trivia Hazard!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        return (response + 1) == answers.get(idx / 5);
    }
}
