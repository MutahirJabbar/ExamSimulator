import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Score extends JFrame implements ActionListener {

    Score(String name, int score) {
        saveToHistory(name, score);

        setBounds(400, 150, 750, 550);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel msg = new JLabel("Thank you " + name + " for playing!");
        msg.setBounds(45, 30, 700, 30);
        msg.setFont(new Font("Tahoma", Font.PLAIN, 26));
        add(msg);

        JLabel scoreLabel = new JLabel("Your Score: " + score);
        scoreLabel.setBounds(300, 200, 300, 30);
        scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
        scoreLabel.setForeground(new Color(30, 144, 255));
        add(scoreLabel);

        JButton again = new JButton("Play Again");
        again.setBounds(380, 270, 120, 30);
        again.setBackground(new Color(30, 144, 255));
        again.setForeground(Color.WHITE);
        again.addActionListener(this);
        add(again);

        setVisible(true);
    }

    void saveToHistory(String name, int score) {
        try {
            FileWriter fw = new FileWriter("history.txt", true);
            fw.write("Name: " + name + " | Score: " + score + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving score: " + e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Login();
    }
}
