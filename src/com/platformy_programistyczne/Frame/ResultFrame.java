package com.platformy_programistyczne.Frame;

import com.platformy_programistyczne.Model.ResultReader;

import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * JFrame to display all results in two columns
 * player's name and result
 */
public class ResultFrame extends JFrame {
    private JTextArea resultTextAre;
    private JPanel panel1;

    public ResultFrame() throws FileNotFoundException {
        super("Results Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setTextArea1();
        add(resultTextAre);
        setVisible(true);
    }

    /**
     * Method to set JTextArea content
     * At first it is creating a new instance
     * of an object StringBuilder with column names
     * and then fulfilling it by read from the file
     * result objects
     * @throws FileNotFoundException
     */
    public void setTextArea1() throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name\t" + "Result\t\n");
        var results = ResultReader.getAllResult();
        for (var result : results) {
            stringBuilder.append(result.getName() + "\t" + result.getResult() + "\t\n");
        }
        resultTextAre.append(stringBuilder.toString());
    }
}
