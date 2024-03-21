package domain;

import javax.swing.*;

public class Panels {

    public static JPanel wordInputPanel() {
        JPanel wordInfoPanel = new JPanel();

        // Add labels and text fields for inputs
        JLabel wordLabel = new JLabel("Word: ");
        JTextField wordTextField = new JTextField(10);

        JLabel definitionLabel = new JLabel("Defintion: ");
        JTextField definitionTextField = new JTextField(10);

        JLabel partOfSpeechLabel = new JLabel("Part of speech: ");
        JTextField partOfSpeechTextField = new JTextField(10);

        JLabel exampleLabel = new JLabel("Example sentence: ");
        JTextField exampleTextField = new JTextField(10);

        // adding to panel
        wordInfoPanel.add(wordLabel);
        wordInfoPanel.add(wordTextField);
        wordInfoPanel.add(definitionLabel);
        wordInfoPanel.add(definitionTextField);
        wordInfoPanel.add(partOfSpeechLabel);
        wordInfoPanel.add(partOfSpeechTextField);
        wordInfoPanel.add(exampleLabel);
        wordInfoPanel.add(exampleTextField);

        return wordInfoPanel;

    }


}
