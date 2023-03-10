package src.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static src.GUI.GUI.showGame;


public class WelcomePanel extends JPanel implements ActionListener {
    private int height = 1000;
    private int width = 1500;
    // initializing all variables
    private JLabel imageLabel, textFieldLabel, titleLabel, teamNameTitle, borderLabel, newGameLabel;
    private ImageIcon imageIcon;
    private Image backgroundImage;
    private JButton startButton;
    private TextField textField;
    public static String playerName;
    private JLayeredPane layeredPane;
    //getters and setters

    //constructor for rendering the screen layout
    public WelcomePanel() {
        // rendering the image
        imageIcon = new ImageIcon("jack-hamilton-9SewS6lowEU-unsplash.jpg");
        backgroundImage = imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_AREA_AVERAGING);
        imageIcon = new ImageIcon(backgroundImage);
        layeredPane = new JLayeredPane(); // initializing layerPane
        imageLabel = new JLabel("", imageIcon,JLabel.CENTER); //initializing label
        textFieldLabel = new JLabel(); // initializing a sub label1 which will stay on top of the label
        titleLabel = new JLabel(); // initializing label2
        teamNameTitle = new JLabel();  // initializing label3
        borderLabel = new JLabel();  // initializing label4
        startButton = new JButton("Start"); // initializing
        textField = new TextField();
        newGameLabel = new JLabel();

        // setting the parameters for the frame
        this.setForeground(new Color(0x7f0000));// setting the title of the frame
        this.setLayout(new BorderLayout()); // using the borderLayout
        // --------------------------------------LayerPane--------------------------------------------------------------
        layeredPane.setBounds(0,0,width,height); // setting the parameter of the pane

        // ----------------------------------label--------------------------------------------------------------------
        // using a label to add an image to it
        imageLabel.setBounds(0,0,width,height);

        // ----------------------------------label2---------------------------------------------------------------------

        String s = "C(???)RIBBAGE BY \n"; // setting a title of the game as a string
        titleLabel.setText(s); // setting it to the label2
        titleLabel.setBounds(400,100,500,50); // setting the bounds of the label2
        titleLabel.setForeground(new Color(0x7f0000)); // setting the color of the text
        titleLabel.setFont(new Font("Arial Bold Italic", Font.ITALIC,40)); // doing font customization
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // setting the horizontal parameter of the text
        titleLabel.setVerticalAlignment(JLabel.TOP); // setting the vertical parameter of the text
//        label2.setBackground(new Color(0,150,0,50));

        //-----------------------------------------Label3---------------------------------------------------------------

        String s1 =  "TEAM C(???)AFFEINE"; // Title string
        teamNameTitle.setText(s1); // setting a title of the game
        teamNameTitle.setBounds(400,180,500,70); // setting the parameter of the label
        teamNameTitle.setForeground(new Color(0x7f0000)); // assigning black color
//        label3.setBackground(new Color(0,150,0,50));
        teamNameTitle.setFont(new Font("Arial Bold Italic", Font.ITALIC,40)); // customizing font
        teamNameTitle.setHorizontalAlignment(JLabel.CENTER); // setting horizontal restriction
        teamNameTitle.setVerticalAlignment(JLabel.TOP); // setting vertical restriction

        //-----------------------------------------Label4---------------------------------------------------------------
        String s3 =  "??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????"; // setting the border between two title
        borderLabel.setText(s3); // setting the text to the label
        borderLabel.setBounds(350,150,600,30); // setting the parameter of the label
        borderLabel.setForeground(new Color(0x0A0506)); // setting the text color
        borderLabel.setFont(new Font("Arial Bold Italic", Font.ITALIC,20)); // customizing font
        borderLabel.setHorizontalAlignment(JLabel.CENTER); // setting horizontal restriction
        borderLabel.setVerticalAlignment(JLabel.TOP);// setting vertical restriction

        //-----------------------------------------Label5---------------------------------------------------------------
        newGameLabel.setText("Press Start for New Game"); // creating a text box and assigning it to a default text
        newGameLabel.setBounds(400,300,550,250); // setting the parameters
        newGameLabel.setForeground(new Color(0x2538c3)); // setting the text color
        newGameLabel.setFont(new Font("Arial Bold",Font.BOLD,30)); //customizing font
        newGameLabel.setHorizontalAlignment(JLabel.CENTER); // setting horizontal restriction
        newGameLabel.setVerticalAlignment(JLabel.CENTER);// setting vertical restriction
        //-----------------------------------------Text field-----------------------------------------------------------
           /* // Creating a text field
        textField.setText("USERNAME :-    Player 1"); // creating a text box and assigning it to a default text
        textField.setBounds(100,100,350,30); // setting the parameters
        textField.setFont(new Font("MV Boil",Font.BOLD,20)); //customizing font
        textField.setForeground(Color.BLACK); // setting the text color
        textField.setBackground((Color.lightGray)); // setting the background color
        textField.setCursor(new Cursor(Cursor.TEXT_CURSOR)); // setting a cursor for the text bar
            */

        //---------------------------------label1----------------------------------------------------------------------

        textFieldLabel.setBounds(400,300,600,600); //its dimensions
        textFieldLabel.add(textField,BorderLayout.CENTER); // adding a text field to a label1

        //-----------------------------------------button---------------------------------------------------------------

        startButton.addActionListener(this); // adding an action listener to a button
        startButton.setBounds(225,200,100,40); // setting its parameter
        startButton.setBackground(Color.LIGHT_GRAY); // setting its color
        startButton.setFont(new Font("MV Boil",Font.ITALIC,18)); // customizing font of the button text
        startButton.setForeground(Color.BLACK); // setting a color to the text
        startButton.setBackground(new Color(0x7F0000)); // setting a color to the text



        textFieldLabel.add(startButton); // adding a button to the label

        // adding all layers to the layered pane
        layeredPane.add(borderLabel,BorderLayout.CENTER);
        layeredPane.add(teamNameTitle,BorderLayout.CENTER);
        layeredPane.add(titleLabel,BorderLayout.CENTER);
        layeredPane.add(newGameLabel, BorderLayout.CENTER);
        layeredPane.add(textFieldLabel,BorderLayout.CENTER);
        layeredPane.add(imageLabel,JLayeredPane.DEFAULT_LAYER);
        this.add(layeredPane); // adding layered pane to the Frame

        // --------------------------------------------menu bar --------------------------------------------------------
        JMenuBar menuBar = new JMenuBar(); // creating a menu bar.
        menuBar.setBackground(Color.darkGray);
        menuBar.setBorderPainted(false);



        JMenu helpMenu = new JMenu("Help"); // setting a help menu which provides navigation instructions about game and rules
        JMenu infoMenu = new JMenu("Information"); // setting info menu which shows information
        helpMenu.setForeground(new Color(0x7f0000));
        helpMenu.addActionListener(e -> {
            startButton.setToolTipText("submit to start the game");
            textFieldLabel.setToolTipText("enter your name to give the player identity");
        });
        infoMenu.setForeground(new Color(0x7f0000));
        JMenuItem creator_info = new JMenuItem("Creator's INFO"); // give the information about the creator of the game
        JMenuItem Game_info = new JMenuItem("Game's information");//  give information about game (history)
        infoMenu.add(creator_info);
        infoMenu.add(Game_info);
        menuBar.add(infoMenu);
        menuBar.add(helpMenu);
        this.add(menuBar, BorderLayout.NORTH);


//        layeredPane.setLayout(new BorderLayout());
//        label1.setLayout(new BorderLayout());
//        label2.setLayout(new BorderLayout());
//        label3.setLayout(new BorderLayout());
//        label4.setLayout(new BorderLayout());
//        label.setLayout(new BorderLayout());

        this.setVisible(true); // rendering the frame
    }

    /**
     * Creating an overridden method to listen for an action such as when button clicked
     * @param e the event to be processed
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        showGame();
    }

    /**
     * setting height of the frame
     * @param height of frame
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * setting width of the frame
     * @param width of frame
     */
    public void setWidth(int width) {
        this.width = width;
    }


}
