package com.GUI;


import com.SokobanGrid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SKBWindow extends JFrame {
    private JTextArea textArea1;
    private JButton btnStartGame;
    private JButton btnResetGame;

    private ImageManager imgMan;
    private SKBCanvas canvas;

    private SokobanGrid grid;

    public SKBWindow(ImageManager imgMan, SokobanGrid grid) {
        this.imgMan = imgMan;
        this.grid = grid;

        this.setTitle("Sokoban");
        this.setSize(800, 640);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout());

        JPanel jp1 = new JPanel();

        btnStartGame = new JButton("Start Game");
        btnStartGame.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            System.out.println("Start game, do stuff");
        });

        btnResetGame = new JButton("Reset Game");
        btnResetGame.addActionListener((ActionEvent e) -> { // add a lambda function to take care of callback
            System.out.println("Reset game, do stuff");
        });

        //btnStartGame

        jp1.add(btnStartGame);
        jp1.add(btnResetGame);

        this.add(jp1);

        jp1.setLocation(0, 0);

        //c.gridx = 1;
        //c.gridy = 1;
        //c.weightx = 1.0;
        //c.weighty = 1.0;
        //c.fill = GridBagConstraints.BOTH;

        textArea1 = new JTextArea();
        textArea1.setText("helloworld");
        //jp1.add(textArea1);
        //jp3.add(textArea1, c);

        this.canvas = new SKBCanvas(imgMan, grid);
        this.add(canvas);

        //this.pack();
        this.setVisible(true);
    }
}