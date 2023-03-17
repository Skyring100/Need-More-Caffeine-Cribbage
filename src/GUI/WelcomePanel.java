package src.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Creating a welcome screen to get a username and assigning a static frame which just override by different panels.
 * * Sorry that we couldn't finish it on time but we gave our best
 */

public class WelcomePanel extends JPanel implements ActionListener {

    public static JFrame frame = new JFrame();
    private final JLabel title_label;
    private final JLabel team_name_title;
    private final JLabel border_label;
    private final JButton submitButton;
    private final TextField textField;
    private final TextField textField1;
    public static String playerName;
//    private final GUI gui;
    //getters and setters

    //constructor for rendering the screen layout
    public WelcomePanel() {
//        this.gui = gui;
        // rendering the image
        ImageIcon imageIcon = new ImageIcon("jack-hamilton-9SewS6lowEU-unsplash.jpg");
        int height = 1000;
        int width = 1500;
        Image backgroundImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
        imageIcon = new ImageIcon(backgroundImage);
        JLayeredPane layeredPane = new JLayeredPane(); // initializing layerPane
        // initializing all variables
        JLabel imageLabel = new JLabel("", imageIcon, JLabel.CENTER); //initializing label
        JLabel text_field_label = new JLabel(); // initializing a sub label1 which will stay on top of the label
        title_label = new JLabel(); // initializing label2
        team_name_title = new JLabel();  // initializing label3
        border_label = new JLabel();  // initializing label4
        submitButton = new JButton("Submit"); // initializing
        textField1 = new TextField();
        textField = new TextField();
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField.setText("");
            }
        });
        // setting the parameters for the frame
        this.setForeground(new Color(0x7f0000));// setting the title of the frame
        this.setLayout(new BorderLayout()); // using the borderLayout

        layeredPane.setBounds(0,0, width, height); // setting the parameter of the pane

        // using a label to add an image to it
        imageLabel.setBounds(0,0, width, height);

        createTitleLabel();

        createTeamTitle();

        createBorderLabel();

        inputTextField();

        userNameTextField();

        text_field_label.setBounds(400,300,600,600); //its dimensions
        text_field_label.add(textField,BorderLayout.CENTER); // adding a text field to a label1
        text_field_label.add(textField1, BorderLayout.CENTER);

        createSubmitButton();
        text_field_label.add(submitButton); // adding a button to the label
        // adding all layers to the layered pane
        layeredPane.add(border_label,BorderLayout.CENTER);
        layeredPane.add(team_name_title,BorderLayout.CENTER);
        layeredPane.add(title_label,BorderLayout.CENTER);
        layeredPane.add(text_field_label,BorderLayout.CENTER);
        layeredPane.add(imageLabel,JLayeredPane.DEFAULT_LAYER);
        this.add(layeredPane); // adding layered pane to the Frame
        createMenuBar();
        this.setVisible(true); // rendering the frame
    }

    /**
     * creating a menu bar which assigned a background, menus and other attributes that make it appear good on the screen
     */

    private void createMenuBar(){
        JMenuBar menuBar = new JMenuBar(); // creating a menu bar.
        menuBar.setBackground(Color.darkGray);
        menuBar.setBorderPainted(false);
        JMenu helpMenu = new JMenu("Help"); // setting a help menu which provides navigation instructions about game and rules
        JMenu infoMenu = new JMenu("Information"); // setting info menu which shows information
        helpMenu.setForeground(new Color(0x7f0000));
        infoMenu.setForeground(new Color(0x7f0000));
        JMenuItem creator_info = new JMenuItem("Creator's INFO"); // give the information about the creator of the game
        JMenuItem Game_info = new JMenuItem("Game's information");//  give information about game (history)
        infoMenu.add(creator_info);
        infoMenu.add(Game_info);
        menuBar.add(infoMenu);
        menuBar.add(helpMenu);
        this.add(menuBar, BorderLayout.NORTH);
    }

    /**
     * creating a submit button which has a listener which change the screen and get the name from the user.
     */
    private void createSubmitButton(){
        submitButton.addActionListener(this); // adding an action listener to a button
        submitButton.setBounds(225,200,100,40); // setting its parameter
        submitButton.setBackground(Color.LIGHT_GRAY); // setting its color
        submitButton.setFont(new Font("MV Boil",Font.ITALIC,18)); // customizing font of the button text
        submitButton.setForeground(Color.BLACK); // setting a color to the text
        submitButton.setBackground(new Color(0x7F0000)); // setting a color to the text
    }
    /**
     * Creating an overridden method to listen for an action such as when button clicked
     * @param e the event to be processed
     */

    @Override
    public void actionPerformed(ActionEvent e) {

        // getting players name from the text field
        playerName = textField.getText();
        System.out.println(playerName); // printing it out in the terminal
        if (!playerName.strip().equals("")) {
            submitButton.setEnabled(false); // setting enable option to false
            submitButton.setBackground(Color.lightGray); // changing the color state of the button
            this.setVisible(false);

            frame.getContentPane().remove(this);
            frame.setContentPane(new GamePanel());

        }

    }

    /**
     * create title label
     */
    private void createTitleLabel(){
        String s = "C(♣)RIBBAGE BY \n"; // setting a title of the game as a string
        title_label.setText(s); // setting it to the label2
        title_label.setBounds(400,100,500,50); // setting the bounds of the label2
        title_label.setForeground(new Color(0x7f0000)); // setting the color of the text
        title_label.setFont(new Font("Arial Bold Italic", Font.ITALIC,40)); // doing font customization
        title_label.setHorizontalAlignment(JLabel.CENTER); // setting the horizontal parameter of the text
        title_label.setVerticalAlignment(JLabel.TOP); // setting the vertical parameter of the text
    }

    /**
     * create a team title
     */
    private void createTeamTitle(){
        String s1 =  "TEAM C(♣)AFFEINE"; // Title string
        team_name_title.setText(s1); // setting a title of the game
        team_name_title.setBounds(400,180,500,70); // setting the parameter of the label
        team_name_title.setForeground(new Color(0x7f0000)); // assigning black color
//        label3.setBackground(new Color(0,150,0,50));
        team_name_title.setFont(new Font("Arial Bold Italic", Font.ITALIC,40)); // customizing font
        team_name_title.setHorizontalAlignment(JLabel.CENTER); // setting horizontal restriction
        team_name_title.setVerticalAlignment(JLabel.TOP); // setting vertical restriction
    }

    /**
     * creating a border between the title
     */
    private void createBorderLabel(){
        String s3 =  "♣♦♥♠♣♦♥♠♣♦♥♠♣♦♥♠♣♦♥♠♣♦♥♠♣♦♥♠♣♦♥♠♣♠♣♦♥♠♠♣♦♥"; // setting the border between two title
        border_label.setText(s3); // setting the text to the label
        border_label.setBounds(350,150,600,30); // setting the parameter of the label
        border_label.setForeground(new Color(0x0A0506)); // setting the text color
        border_label.setFont(new Font("Arial Bold Italic", Font.ITALIC,20)); // customizing font
        border_label.setHorizontalAlignment(JLabel.CENTER); // setting horizontal restriction
        border_label.setVerticalAlignment(JLabel.TOP);// setting vertical restriction
    }

    /**
     * creating a text_field to get the name from user
     */

    private void inputTextField(){
        textField.setText("Player 1"); // creating a text box and assigning it to a default text
        textField.setBounds(275,100,125,30); // setting the parameters
        textField.setFont(new Font("MV Boil",Font.BOLD,20)); //customizing font
        textField.setForeground(Color.BLACK); // setting the text color
        textField.setBackground((Color.lightGray)); // setting the background color
        textField.setCursor(new Cursor(Cursor.TEXT_CURSOR)); // setting a cursor for the text bar

    }

    /**
     * creating a text field for "enter name" to show that the name goes here
     */

    private void userNameTextField(){
        textField1.setText("Enter Name:");
        textField1.setBounds(150,100,175,30); //sets the position
        textField1.setFont(new Font("MV Boil",Font.BOLD,20));
        textField1.setForeground(Color.BLACK); // setting the text color
        textField1.setBackground((Color.lightGray)); // setting the background color
        textField1.setEditable(false); //makes it so the user cannot change the text
        textField1.setEnabled(false);
    }

    public static void main(String[] args) {

        frame.setLayout(new BorderLayout());
        frame.setContentPane(new WelcomePanel());
        frame.setSize(1500,1000);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}