package kusnierz.Frame;

import kusnierz.Model.Result;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class ResultingFrame extends JFrame implements ActionListener {
    private final JTextField name;
    private final int result;
    private final JButton confirmButton;

    public ResultingFrame(int result) {
        setSize(300, 300);
        setLayout(new GridLayout(3, 1));
        this.result = result;
        JLabel text = new JLabel("Set your name");
        add(text);
        name = new JTextField();
        add(name);
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(this);
        add(confirmButton);
        setLocationRelativeTo(null);
        setVisible(true);
    }


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
