package src.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WelcomeFrame extends JFrame implements ActionListener {
    private int height = 1000;          // height of the cribbage game screen
    private int width = 1500;               // width of the cribbage game screen

    // initializing all variables
    JLabel label, label1,label2, label3, label4;
    ImageIcon image;
    Image image1;
    JButton button;
    TextField textField;
    public static String playerName;
    JLayeredPane layeredPane;
    //getters and setters

    //constructor for rendering the screen layout
    public WelcomeFrame() {
        // rendering the image
        image = new ImageIcon("jack-hamilton-9SewS6lowEU-unsplash.jpg");
        image1 = image.getImage().getScaledInstance(width,height,Image.SCALE_AREA_AVERAGING);
        image = new ImageIcon(image1);
        layeredPane = new JLayeredPane(); // initializing layerPane
        label = new JLabel("",image,JLabel.CENTER); //initializing label
        label1 = new JLabel(); // initializing a sub label1 which will stay on top of the label
        label2 = new JLabel(); // initializing label2
        label3 = new JLabel();  // initializing label3
        label4 = new JLabel();  // initializing label4
        button = new JButton("Submit"); // initializing
        textField= new TextField();

        // setting the parameters for the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exits when user clicks on 'x' button
        this.setSize(width,height); //set the given size to the frame
        this.setTitle("Cribbage By Caffeine");
        this.setForeground(new Color(0x7f0000));// setting the title of the frame
        this.setLayout(new BorderLayout()); // using the borderLayout
        // --------------------------------------LayerPane--------------------------------------------------------------
        layeredPane.setBounds(0,0,width,height); // setting the parameter of the pane

        // ----------------------------------label--------------------------------------------------------------------
        // using a label to add an image to it
        label.setBounds(0,0,width,height);

        // ----------------------------------label2---------------------------------------------------------------------

        String s = "C(♣)RIBBAGE BY \n"; // setting a title of the game as a string
        label2.setText(s); // setting it to the label2
        label2.setBounds(400,100,500,50); // setting the bounds of the label2
        label2.setForeground(new Color(0x7f0000)); // setting the color of the text
        label2.setFont(new Font("Arial Bold Italic", Font.ITALIC,40)); // doing font customization
        label2.setHorizontalAlignment(JLabel.CENTER); // setting the horizontal parameter of the text
        label2.setVerticalAlignment(JLabel.TOP); // setting the vertical parameter of the text
//        label2.setBackground(new Color(0,150,0,50));

        //-----------------------------------------Label3---------------------------------------------------------------

        String s1 =  "TEAM C(♣)AFFEINE"; // Title string
        label3.setText(s1); // setting a title of the game
        label3.setBounds(400,180,500,70); // setting the parameter of the label
        label3.setForeground(new Color(0x7f0000)); // assigning black color
//        label3.setBackground(new Color(0,150,0,50));
        label3.setFont(new Font("Arial Bold Italic", Font.ITALIC,40)); // customizing font
        label3.setHorizontalAlignment(JLabel.CENTER); // setting horizontal restriction
        label3.setVerticalAlignment(JLabel.TOP); // setting vertical restriction

        //-----------------------------------------Label4---------------------------------------------------------------
        String s3 =  "♣♦♥♠♣♦♥♠♣♦♥♠♣♦♥♠♣♦♥♠♣♦♥♠♣♦♥♠♣♦♥♠♣♠♣♦♥♠♠♣♦♥"; // setting the border between two title
        label4.setText(s3); // setting the text to the label
        label4.setBounds(350,150,600,30); // setting the parameter of the label
        label4.setForeground(new Color(0x0A0506)); // setting the text color
        label4.setFont(new Font("Arial Bold Italic", Font.ITALIC,20)); // customizing font
        label4.setHorizontalAlignment(JLabel.CENTER); // setting horizontal restriction
        label4.setVerticalAlignment(JLabel.TOP);// setting vertical restriction

        //-----------------------------------------Text field-----------------------------------------------------------
            // Creating a text field
        textField.setText("USERNAME :-    Player 1"); // creating a text box and assigning it to a default text
        textField.setBounds(100,100,350,30); // setting the parameters
        textField.setFont(new Font("MV Boil",Font.BOLD,20)); //customizing font
        textField.setForeground(Color.BLACK); // setting the text color
        textField.setBackground((Color.lightGray)); // setting the background color
        textField.setCursor(new Cursor(Cursor.TEXT_CURSOR)); // setting a cursor for the text bar

        //---------------------------------label1----------------------------------------------------------------------

        label1.setBounds(400,300,600,600); //its dimensions
        label1.add(textField,BorderLayout.CENTER); // adding a text field to a label1

        //-----------------------------------------button---------------------------------------------------------------

        button.addActionListener(this); // adding an action listener to a button
        button.setBounds(225,200,100,40); // setting its parameter
        button.setBackground(Color.LIGHT_GRAY); // setting its color
        button.setFont(new Font("MV Boil",Font.ITALIC,18)); // customizing font of the button text
        button.setForeground(Color.BLACK); // setting a color to the text
        button.setBackground(new Color(0x7F0000)); // setting a color to the text



        label1.add(button); // adding a button to the label

        // adding all layers to the layered pane
        layeredPane.add(label4,BorderLayout.CENTER);
        layeredPane.add(label3,BorderLayout.CENTER);
        layeredPane.add(label2,BorderLayout.CENTER);
        layeredPane.add(label1,BorderLayout.CENTER);
        layeredPane.add(label,JLayeredPane.DEFAULT_LAYER);
        this.add(layeredPane); // adding layered pane to the Frame

        // --------------------------------------------menu bar --------------------------------------------------------
        JMenuBar menuBar = new JMenuBar(); // creating a menu bar
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
        this.setJMenuBar(menuBar);


//        layeredPane.setLayout(new BorderLayout());
//        label1.setLayout(new BorderLayout());
//        label2.setLayout(new BorderLayout());
//        label3.setLayout(new BorderLayout());
//        label4.setLayout(new BorderLayout());
//        label.setLayout(new BorderLayout());

        this.setLocationRelativeTo(null);//opens the window at center
        this.setResizable(false); // disabling the resizable function
        this.setVisible(true); // rendering the frame
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
            button.setEnabled(false); // setting enable option to false
            button.setBackground(Color.lightGray); // changing the color state of the button

        }

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
