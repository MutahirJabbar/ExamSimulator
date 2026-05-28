import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rules extends JFrame implements ActionListener {

    String name;
    JButton start, back;

    Rules(String name) {
        this.name = name;

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Welcome " + name + "!");
        heading.setBounds(50, 20, 700, 30);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 28));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);

        JLabel rulesLabel = new JLabel();
        rulesLabel.setBounds(20, 90, 700, 350);
        rulesLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rulesLabel.setText(
            "<html>" +
            "1. This exam uses a Doubly Linked List to store questions.<br><br>" +
            "2. You can go back and forward between questions using Prev/Next.<br><br>" +
            "3. If questions.txt is not found, default questions will be used.<br><br>" +
            "4. Your score will be saved to history.txt after submission.<br><br>" +
            "5. Each correct answer gives 10 marks.<br><br>" +
            "</html>"
        );
        add(rulesLabel);

        back = new JButton("Back");
        back.setBounds(250, 500, 100, 30);
        back.setBackground(new Color(30, 144, 254));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        start = new JButton("Start Exam");
        start.setBounds(400, 500, 120, 30);
        start.setBackground(new Color(30, 144, 254));
        start.setForeground(Color.WHITE);
        start.addActionListener(this);
        add(start);

        setSize(800, 650);
        setLocation(350, 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == start) {
            setVisible(false);
            new Quiz(name);
        } else {
            setVisible(false);
            new Login();
        }
    }
}
