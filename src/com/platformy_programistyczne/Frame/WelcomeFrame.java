package com.platformy_programistyczne.Frame;

import com.platformy_programistyczne.Frame.ResultFrame;
import com.platformy_programistyczne.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;


public class WelcomeFrame extends JFrame implements ActionListener {
    private JButton checkResultsBox;
    private JButton startGameButton;

    public WelcomeFrame() {
        super("Welcome Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(2,1));
        setLocationRelativeTo(null);

        checkResultsBox.addActionListener(this);
        startGameButton.addActionListener(this);
        add(startGameButton);
        add(checkResultsBox);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == checkResultsBox) {
            try {
                dispose();
                new ResultFrame().setVisible(true);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
        else if (source == startGameButton) {
            dispose();
            new Snake().setVisible(true);
        }
    }
}
