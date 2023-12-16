
// Create a Text Editor in Java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Task5 extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu;
    private JMenuItem openItem, saveItem, closeItem, cutItem, copyItem, pasteItem, printItem, saveAndSubmitItem;

    public Task5() {
        // Set up the frame
        setTitle("Text Editor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        textArea = new JTextArea();
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");

        // File menu items
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        closeItem = new JMenuItem("Close");
        printItem = new JMenuItem("Print");
        saveAndSubmitItem = new JMenuItem("Save and Submit");

        // Edit menu items
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");

        // Add action listeners
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        closeItem.addActionListener(this);
        printItem.addActionListener(this);
        saveAndSubmitItem.addActionListener(this);
        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);

        // Add items to menus
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(closeItem);
        fileMenu.add(printItem);
        fileMenu.add(saveAndSubmitItem);

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        // Set up the frame layout
        setJMenuBar(menuBar);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new Task5();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openItem) {
            openFile();
        } else if (e.getSource() == saveItem) {
            saveFile();
        } else if (e.getSource() == closeItem) {
            closeFile();
        } else if (e.getSource() == printItem) {
            printText();
        } else if (e.getSource() == saveAndSubmitItem) {
            saveAndSubmit();
        } else if (e.getSource() == cutItem) {
            textArea.cut();
        } else if (e.getSource() == copyItem) {
            textArea.copy();
        } else if (e.getSource() == pasteItem) {
            textArea.paste();
        }
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeFile() {
        textArea.setText("");
    }

    private void printText() {
        try {
            textArea.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveAndSubmit() {
        saveFile();
        closeFile();
    }
}
