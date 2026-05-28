import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Quiz extends JFrame implements ActionListener {

    JLabel qno, question;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup grp;
    JButton next, prev, submit, lifeline;

    DoublyLinkedList qList;
    Node curr;
    int qCount = 0;
    String username;
    boolean lifelineUsed = false;

    Quiz(String username) {
        this.username = username;

        qList = new DoublyLinkedList();
        loadQuestions();
        curr = qList.head;

        setBounds(50, 0, 1440, 850);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel header = new JPanel();
        header.setBounds(0, 0, 1440, 100);
        header.setBackground(new Color(30, 144, 255));
        add(header);

        JLabel title = new JLabel("Online Quiz Portal");
        title.setFont(new Font("Tahoma", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        header.add(title);

        qno = new JLabel();
        qno.setBounds(100, 150, 50, 30);
        qno.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(qno);

        question = new JLabel();
        question.setBounds(150, 150, 900, 30);
        question.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(question);

        opt1 = new JRadioButton();
        opt1.setBounds(170, 220, 700, 30);
        opt1.setBackground(Color.WHITE);
        opt1.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt1);

        opt2 = new JRadioButton();
        opt2.setBounds(170, 260, 700, 30);
        opt2.setBackground(Color.WHITE);
        opt2.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt2);

        opt3 = new JRadioButton();
        opt3.setBounds(170, 300, 700, 30);
        opt3.setBackground(Color.WHITE);
        opt3.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt3);

        opt4 = new JRadioButton();
        opt4.setBounds(170, 340, 700, 30);
        opt4.setBackground(Color.WHITE);
        opt4.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt4);

        grp = new ButtonGroup();
        grp.add(opt1);
        grp.add(opt2);
        grp.add(opt3);
        grp.add(opt4);

        lifeline = new JButton("50-50 Lifeline");
        lifeline.setBounds(1150, 370, 200, 40);
        lifeline.setFont(new Font("Tahoma", Font.BOLD, 18));
        lifeline.setBackground(new Color(255, 140, 0));
        lifeline.setForeground(Color.WHITE);
        lifeline.addActionListener(this);
        add(lifeline);

        prev = new JButton("Previous");
        prev.setBounds(900, 450, 200, 40);
        prev.setFont(new Font("Tahoma", Font.PLAIN, 22));
        prev.setBackground(new Color(30, 144, 255));
        prev.setForeground(Color.WHITE);
        prev.addActionListener(this);
        prev.setEnabled(false);
        add(prev);

        next = new JButton("Next");
        next.setBounds(1150, 450, 200, 40);
        next.setFont(new Font("Tahoma", Font.PLAIN, 22));
        next.setBackground(new Color(30, 144, 255));
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        submit = new JButton("Submit");
        submit.setBounds(1150, 530, 200, 40);
        submit.setFont(new Font("Tahoma", Font.PLAIN, 22));
        submit.setBackground(new Color(30, 144, 255));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setEnabled(false);
        add(submit);

        if (curr != null) {
            showQuestion(curr.data);
        }

        setVisible(true);
    }

    void loadQuestions() {
        File f = new File("questions.txt");
        boolean loaded = false;

        if (f.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length == 6) {
                        String[] opts = { parts[1], parts[2], parts[3], parts[4] };
                        qList.add(new Question(parts[0], opts, parts[5]));
                    }
                }
                br.close();
                if (qList.size > 0)
                    loaded = true;
            } catch (Exception e) {
                System.out.println("could not read file: " + e.getMessage());
            }
        }

        if (!loaded) {
            JOptionPane.showMessageDialog(this, "questions.txt not found! Loading default questions.");

            qList.add(new Question(
                "In a Doubly Linked List, each node contains?",
                new String[]{"Data and Next only", "Data and Prev only", "Data, Next and Prev", "Only Data"},
                "Data, Next and Prev"));

            qList.add(new Question(
                "Which principle does a Queue follow?",
                new String[]{"LIFO", "FIFO", "FILO", "LILO"},
                "FIFO"));

            qList.add(new Question(
                "Which method is used to insert an element in a Stack?",
                new String[]{"pop()", "push()", "peek()", "poll()"},
                "push()"));

            qList.add(new Question(
                "In a Binary Search Tree the left child is always?",
                new String[]{"Greater than root", "Less than root", "Equal to root", "Can be anything"},
                "Less than root"));

            qList.add(new Question(
                "Which keyword is used to inherit a class in Java?",
                new String[]{"this", "super", "extends", "implements"},
                "extends"));
        }
    }

    void showQuestion(Question q) {
        qno.setText((qCount + 1) + ". ");
        question.setText(q.text);

        // make all options visible again in case lifeline hid some on a previous question
        opt1.setVisible(true); opt1.setEnabled(true);
        opt2.setVisible(true); opt2.setEnabled(true);
        opt3.setVisible(true); opt3.setEnabled(true);
        opt4.setVisible(true); opt4.setEnabled(true);

        opt1.setText(q.options[0]);
        opt1.setActionCommand(q.options[0]);
        opt2.setText(q.options[1]);
        opt2.setActionCommand(q.options[1]);
        opt3.setText(q.options[2]);
        opt3.setActionCommand(q.options[2]);
        opt4.setText(q.options[3]);
        opt4.setActionCommand(q.options[3]);

        grp.clearSelection();

        // restore answer if user already answered this question before
        if (!q.userAns.equals("")) {
            if (q.userAns.equals(q.options[0])) opt1.setSelected(true);
            else if (q.userAns.equals(q.options[1])) opt2.setSelected(true);
            else if (q.userAns.equals(q.options[2])) opt3.setSelected(true);
            else if (q.userAns.equals(q.options[3])) opt4.setSelected(true);
        }
    }

    void saveAns() {
        if (grp.getSelection() != null) {
            curr.data.userAns = grp.getSelection().getActionCommand();
        }
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == next) {
            saveAns();
            if (curr.next != null) {
                curr = curr.next;
                qCount++;
                showQuestion(curr.data);
                prev.setEnabled(true);
            }
            if (curr.next == null) {
                next.setEnabled(false);
                submit.setEnabled(true);
            }

        } else if (ae.getSource() == prev) {
            saveAns();
            if (curr.prev != null) {
                curr = curr.prev;
                qCount--;
                showQuestion(curr.data);
                next.setEnabled(true);
                submit.setEnabled(false);
            }
            if (curr.prev == null) {
                prev.setEnabled(false);
            }

        } else if (ae.getSource() == lifeline) {
            if (!lifelineUsed) {
                lifelineUsed = true;
                lifeline.setEnabled(false);

                String ans = curr.data.correctAns;
                int removed = 0;

                // hide 2 wrong options
                if (!opt1.getText().equals(ans) && removed < 2) { opt1.setVisible(false); removed++; }
                if (!opt2.getText().equals(ans) && removed < 2) { opt2.setVisible(false); removed++; }
                if (!opt3.getText().equals(ans) && removed < 2) { opt3.setVisible(false); removed++; }
                if (!opt4.getText().equals(ans) && removed < 2) { opt4.setVisible(false); removed++; }
            }

        } else if (ae.getSource() == submit) {
            saveAns();
            calcScore();
        }
    }

    void calcScore() {
        int score = 0;
        Node temp = qList.head;
        while (temp != null) {
            if (temp.data.userAns != null && temp.data.userAns.equals(temp.data.correctAns)) {
                score += 10;
            }
            temp = temp.next;
        }
        setVisible(false);
        new Score(username, score);
    }
}
