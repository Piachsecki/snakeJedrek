package com.platformy_programistyczne.Frame;

import com.platformy_programistyczne.Model.Result;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * JFrame to set a name of the player
 * that it will be written to the file
 * with player's result current game
 */
public class SetResultFrame extends JFrame implements ActionListener {
    private JTextField name;
    private int result;
    private JButton confirmButton;
    private JLabel text;
    public SetResultFrame(int result) {
        setSize(300, 300);
        setLayout(new GridLayout(3, 1));
        this.result = result;
        text = new JLabel("Set your name");
        add(text);
        name = new JTextField();
        add(name);
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(this);
        add(confirmButton);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * action performed method to check if
     * button is pressed then make a new
     * result object and write it to the file
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == confirmButton) {
            var newResult = new Result(result, name.getText());
            try {
                newResult.saveResult();
                dispose();
                System.exit(0);   // Exit the application
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
