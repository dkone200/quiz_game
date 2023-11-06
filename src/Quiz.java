import java.awt.event.*;
import java.awt.*;
import javax.swing.*;



public class Quiz implements ActionListener {

    String[] questions = {
                            "Which one is the biggest continent in the world?",
                            "Which one is the fastest animal on earth?",
                            "What is the chemical formula of water?",
                            "Who discovered America?",
                            "Which civilization had pyramids?",
                            "Who discovered gravity?",
                            "Who was Picasso?",
                            "What is light?",
                            "What is sound?",
                            "What is the speed of light?"
                         };
    String[][] options = {
                            {"Africa", "Asia", "Zimbabwe", "Europe"},
                            {"Cheetah", "Eagle", "Human", "Sloth"},
                            { "H20","CO2", "NaCl", "C21H30O2"},
                            { "Magellan","Augustus", "Shakespeare", "Columbus"},
                            {"Romans","Greeks","Egyptians","Vikings"},
                            {"Newton","Einstein","Aristotle","Da Vinci"},
                            {"Musician","Spanish King","Architect","Painter"},
                            {"Chemical reaction","Electro-magnetic wave","Heat","Radiation"},
                            {"A pressure wave","Energy","Noise","Air"},
                            {"0.229792 km/s","2.99792 km/s","299792.458 km/s","infinity km/s"}

                         };
    char [] answers = {
                        'B',
                        'B',
                        'A',
                        'D',
                        'C',
                        'A',
                        'D',
                        'B',
                        'A',
                        'C'
                      };

    char guess;
    char answer;
    int index;
    int correct_guesses = 0;
    int total_questions = questions.length;
    int results;
    int seconds=10;

    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JTextArea textarea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    JLabel time_label = new JLabel();
    JLabel seconds_left = new JLabel();
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();
    JTextField answer_result = new JTextField();

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            seconds_left.setText(String.valueOf(seconds));
            if (seconds<=0){
                displayAnswer();
            }

        }
    });


    public Quiz(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,650);
        frame.getContentPane().setBackground(new Color(50,50,82));
        frame.setLayout(null);
        frame.setResizable(false);

        textField.setBounds(0,0,650,50);
        textField.setBackground(new Color(25,25,25));
        textField.setForeground(new Color(100,50,168));
        textField.setFont(new Font("Arial",Font.BOLD,30));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment((JTextField.CENTER));
        textField.setEditable(false);

        textarea.setBounds(0,50,650,50);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setBackground(new Color(25,25,25));
        textarea.setForeground(new Color(255,250,250));
        textarea.setFont(new Font("Arial",Font.BOLD,25));
        textarea.setBorder(BorderFactory.createBevelBorder(1));
        textarea.setEditable(false);

        buttonA.setBounds(0,100,100,100);
        buttonA.setFont(new Font("Arial",Font.BOLD,35));
        buttonA.setFocusable(false);
        buttonA.addActionListener( this);
        buttonA.setText("A");

        buttonB.setBounds(0,200,100,100);
        buttonB.setFont(new Font("Arial",Font.BOLD,35));
        buttonB.setFocusable(false);
        buttonB.addActionListener( this);
        buttonB.setText("B");

        buttonC.setBounds(0,300,100,100);
        buttonC.setFont(new Font("Arial",Font.BOLD,35));
        buttonC.setFocusable(false);
        buttonC.addActionListener( this);
        buttonC.setText("C");

        buttonD.setBounds(0,400,100,100);
        buttonD.setFont(new Font("Arial",Font.BOLD,35));
        buttonD.setFocusable(false);
        buttonD.addActionListener( this);
        buttonD.setText("D");

        answer_labelA.setBounds(125,100,500,100);
        answer_labelA.setBackground(new Color(50,50,50));
        answer_labelA.setForeground(new Color(255,250,250));
        answer_labelA.setFont(new Font("Arial",Font.PLAIN,35));

        answer_labelB.setBounds(125,200,500,100);
        answer_labelB.setBackground(new Color(50,50,50));
        answer_labelB.setForeground(new Color(255,250,250));
        answer_labelB.setFont(new Font("Arial",Font.PLAIN,35));

        answer_labelC.setBounds(125,300,500,100);
        answer_labelC.setBackground(new Color(50,50,50));
        answer_labelC.setForeground(new Color(255,250,250));
        answer_labelC.setFont(new Font("Arial",Font.PLAIN,35));

        answer_labelD.setBounds(125,400,500,100);
        answer_labelD.setBackground(new Color(50,50,50));
        answer_labelD.setForeground(new Color(255,250,250));
        answer_labelD.setFont(new Font("Arial",Font.PLAIN,35));

        seconds_left.setBounds(535,510,100,100);
        seconds_left.setBackground(new Color(25,25,25));
        seconds_left.setForeground(new Color(255,0,0));
        seconds_left.setFont(new Font("Ink Free",Font.BOLD,60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));

        time_label.setBounds(535,475,100,25);
        time_label.setBackground(new Color(50,50,50));
        time_label.setForeground(new Color(255,0,0));
        time_label.setFont(new Font("Arial", Font.BOLD,16));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("You Have...");

        number_right.setBounds(225,225,200,100);
        number_right.setBackground(new Color(25,25,25));
        number_right.setForeground(new Color(238,232,170));
        number_right.setFont(new Font("Ink Free",Font.BOLD,50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        percentage.setBounds(225,325,200,100);
        percentage.setBackground(new Color(25,25,25));
        percentage.setForeground(new Color(238,232,170));
        percentage.setFont(new Font("Ink Free",Font.BOLD,50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

        answer_result.setBounds(340, 280, 250, 150);
        answer_result.setBackground(new Color(50,50,82));
        answer_result.setForeground(new Color(255,0,0));
        answer_result.setFont(new Font("Ink Free",Font.BOLD,30));
        answer_result.setBorder(BorderFactory.createBevelBorder(1));
        answer_result.setHorizontalAlignment(JTextField.CENTER);
        answer_result.setEditable(false);


        frame.add(time_label);
        frame.add(seconds_left);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textarea);
        frame.add(textField);
        frame.setVisible(true);


        nextQuestion();

    }

    public void nextQuestion(){

        if(index>=total_questions) {
            results();
        }
        else {
            textField.setText("Question "+(index+1));
            textarea.setText(questions[index]);
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);
            timer.start();
        }

    }

    public void actionPerformed(ActionEvent e){

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(e.getSource()==buttonA) {
            answer= 'A';
            if (answer ==answers[index]){
                correct_guesses++;
            }
        }
        if(e.getSource()==buttonB) {
            answer= 'B';
            if (answer ==answers[index]){
                correct_guesses++;
            }
        }
        if(e.getSource()==buttonC) {
            answer= 'C';
            if (answer ==answers[index]){
                correct_guesses++;
            }
        }
        if(e.getSource()==buttonD) {
            answer= 'D';
            if (answer ==answers[index]){
                correct_guesses++;
            }
        }


        displayAnswer();

    }

   public void displayCorrect(){
        Timer correct = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                buttonA.setEnabled(false);
                buttonB.setEnabled(false);
                buttonC.setEnabled(false);
                buttonD.setEnabled(false);


                if (answer==answers[index]){
                    answer_result.setText("Correct Answer!");
                }else {

                    answer_result.setText("Wrong Answer!");
                }

            }

        });

        frame.add(answer_result);
        correct.start();
        correct.setRepeats(false);

    }

    public void removeAnswer(){
        Timer remove = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(answer_result);
            }
        });
        remove.start();
        remove.setRepeats(false);
    }

    public void displayAnswer(){

        displayCorrect();
        removeAnswer();


        timer.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (answers[index] != 'A'){
            answer_labelA.setForeground(new Color(255,0,0));
        }
        if (answers[index] != 'B'){
            answer_labelB.setForeground(new Color(255,0,0));
        }
        if (answers[index] != 'C'){
            answer_labelC.setForeground(new Color(255,0,0));
        }if (answers[index] != 'D'){
            answer_labelD.setForeground(new Color(255,0,0));
        }



        Timer pause = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer_labelA.setForeground(new Color(255,250,250));
                answer_labelB.setForeground(new Color(255,250,250));
                answer_labelC.setForeground(new Color(255,250,250));
                answer_labelD.setForeground(new Color(255,250,250));

                answer = ' ';
                seconds=10;
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();

            }
        });
        pause.setRepeats(false);
        pause.start();

    }

    public void results(){

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        results = (int)((correct_guesses/(double)total_questions)*100);

        textField.setText("RESULTS!");
        textarea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        number_right.setText(correct_guesses+"/"+total_questions);
        percentage.setText(results+"%");

        frame.add(percentage);
        frame.add(number_right);



    }
}
