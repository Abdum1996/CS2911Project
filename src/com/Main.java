package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class Main {
    private JFrame frame;
    private JTextArea textArea1;
    private JButton btnStartGame;
    private JButton btnResetGame;


    public Main() {
        frame = new JFrame("MainGUI");
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jp1 = new JPanel();

        btnStartGame = new JButton("Start Game");
        btnStartGame.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            System.out.println("Start game, do stuff");
        });
        btnResetGame = new JButton("Reset Game");
        btnResetGame.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            System.out.println("Reset game, do stuff");
        });

        jp1.add(btnStartGame);
        jp1.add(btnResetGame);

        frame.add(jp1);


        jp1.setLayout(new GridBagLayout());

        //c.gridx = 1;
        //c.gridy = 1;
        //c.weightx = 1.0;
        //c.weighty = 1.0;
        //c.fill = GridBagConstraints.BOTH;

        textArea1 = new JTextArea();
        textArea1.setText("helloworld");
        jp1.add(textArea1);
        //jp3.add(textArea1, c);

        //frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        Main gui = new Main();
    }
}
