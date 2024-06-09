package com.platformy_programistyczne.Frame;

import com.platformy_programistyczne.Model.ResultReader;

import javax.swing.*;
import java.io.FileNotFoundException;


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
