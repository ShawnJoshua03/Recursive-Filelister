import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class RecursiveLister extends JFrame {

    private JTextArea textArea;

    public RecursiveLister() {
        super("Recursive Lister");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        add(createControlPanel(), BorderLayout.NORTH);
        add(createScrollPane(), BorderLayout.CENTER);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();

        JButton startButton = new JButton("Start");
        startButton.addActionListener(this::handleStart);
        controlPanel.add(startButton);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        controlPanel.add(quitButton);

        return controlPanel;
    }

    private JScrollPane createScrollPane() {
        textArea = new JTextArea();
        textArea.setEditable(false);
        return new JScrollPane(textArea);
    }

    private void handleStart(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            textArea.setText("");
            File directory = chooser.getSelectedFile();
            traverseDirectory(directory);
        }
    }

    private void traverseDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    traverseDirectory(file);
                } else {
                    textArea.append(file.getPath() + "\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecursiveLister frame = new RecursiveLister();
            frame.setVisible(true);
        });
    }
}
