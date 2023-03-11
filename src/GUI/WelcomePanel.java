package src.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WelcomePanel extends JPanel implements ActionListener {
    //private int height = 1000;
    //private int width = 1000;
    // initializing all variables
    private JLabel image_label, textfield_label, title_label, team_name_label, border_label;
    private ImageIcon background_image;
    private Image image;
    private JButton submit_button;
    private TextField textbox;
    public static String playerName;
    private JLayeredPane layeredPane;
    //getters and setters

    //constructor for rendering the screen layout
    public WelcomePanel(GUI g) {
        // rendering the image
        int width = g.getWidth();
        int height = g.getHeight();
        background_image = new ImageIcon("jack-hamilton-9SewS6lowEU-unsplash.jpg"); // getting the image
        image = background_image.getImage().getScaledInstance(width,height,Image.SCALE_AREA_AVERAGING);
        background_image = new ImageIcon(image);
        layeredPane = new JLayeredPane(); // initializing layerPane
        image_label = new JLabel("", background_image,JLabel.CENTER); //initializing label
        textfield_label = new JLabel(); // initializing a sub label1 which will stay on top of the label
        title_label = new JLabel(); // initializing label2
        team_name_label = new JLabel();  // initializing label3
        border_label = new JLabel();  // initializing label4
        submit_button = new JButton("Submit"); // initializing
        textbox = new TextField();

        // setting the parameters for the frame
        this.setForeground(new Color(0x7f0000));// setting the title of the frame
        this.setLayout(new BorderLayout()); // using the borderLayout
        // --------------------------------------LayerPane--------------------------------------------------------------
        layeredPane.setBounds(0,0,width,height); // setting the parameter of the pane

        // ----------------------------------label--------------------------------------------------------------------
        // using a label to add an image to it
        image_label.setBounds(0,0,width,height);
        image_label.setOpaque(true);

        // ----------------------------------label2---------------------------------------------------------------------

        String s = "C(♣)RIBBAGE BY \n"; // setting a title of the game as a string
        title_label.setText(s); // setting it to the label2
        title_label.setBounds(400,100,500,50); // setting the bounds of the label2
        title_label.setForeground(new Color(0x7f0000)); // setting the color of the text
        title_label.setFont(new Font("Arial Bold Italic", Font.ITALIC,40)); // doing font customization
        title_label.setHorizontalAlignment(JLabel.CENTER); // setting the horizontal parameter of the text
        title_label.setVerticalAlignment(JLabel.TOP); // setting the vertical parameter of the text
        title_label.setBackground(new Color(0,150,0,50));

        //-----------------------------------------Label3---------------------------------------------------------------

        String s1 =  "TEAM C(♣)AFFEINE"; // Title string
        team_name_label.setText(s1); // setting a title of the game
        team_name_label.setBounds(400,180,500,70); // setting the parameter of the label
        team_name_label.setForeground(new Color(0x7f0000)); // assigning black color
//        label3.setBackground(new Color(0,150,0,50));
        team_name_label.setFont(new Font("Arial Bold Italic", Font.ITALIC,40)); // customizing font
        team_name_label.setHorizontalAlignment(JLabel.CENTER); // setting horizontal restriction
        team_name_label.setVerticalAlignment(JLabel.TOP); // setting vertical restriction

        //-----------------------------------------Label4---------------------------------------------------------------
        String s3 =  "♣♦♥♠♣♦♥♠♣♦♥♠♣♦♥♠♣♦♥♠♣♦♥♠♣♦♥♠♣♦♥♠♣♠♣♦♥♠♠♣♦♥"; // setting the border between two title
        border_label.setText(s3); // setting the text to the label
        border_label.setBounds(350,150,600,30); // setting the parameter of the label
        border_label.setForeground(new Color(0x0A0506)); // setting the text color
        border_label.setFont(new Font("Arial Bold Italic", Font.ITALIC,20)); // customizing font
        border_label.setHorizontalAlignment(JLabel.CENTER); // setting horizontal restriction
        border_label.setVerticalAlignment(JLabel.TOP);// setting vertical restriction

        //-----------------------------------------Text field-----------------------------------------------------------
            // Creating a text field
        textbox.setText("USERNAME :-    Player 1"); // creating a text box and assigning it to a default text
        textbox.setBounds(100,100,350,30); // setting the parameters
        textbox.setFont(new Font("MV Boil",Font.BOLD,20)); //customizing font
        textbox.setForeground(Color.BLACK); // setting the text color
        textbox.setBackground((Color.lightGray)); // setting the background color
        textbox.setCursor(new Cursor(Cursor.TEXT_CURSOR)); // setting a cursor for the text bar

        //---------------------------------label1----------------------------------------------------------------------

        textfield_label.setBounds(400,300,600,600); //its dimensions
        textfield_label.add(textbox,BorderLayout.CENTER); // adding a text field to a label1

        //-----------------------------------------button---------------------------------------------------------------

        submit_button.addActionListener(this); // adding an action listener to a button
        submit_button.setBounds(225,200,100,40); // setting its parameter
        submit_button.setBackground(Color.LIGHT_GRAY); // setting its color
        submit_button.setFont(new Font("MV Boil",Font.ITALIC,18)); // customizing font of the button text
        submit_button.setForeground(Color.BLACK); // setting a color to the text
        submit_button.setBackground(new Color(0x7F0000)); // setting a color to the text



        textfield_label.add(submit_button); // adding a button to the label

        // adding all layers to the layered pane
        layeredPane.add(border_label,BorderLayout.CENTER);
        layeredPane.add(team_name_label,BorderLayout.CENTER);
        layeredPane.add(title_label,BorderLayout.CENTER);
        layeredPane.add(textfield_label,BorderLayout.CENTER);
        layeredPane.add(image_label,JLayeredPane.DEFAULT_LAYER);
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
        //this.setJMenuBar(menuBar);


        this.setVisible(true); // rendering the frame
    }

    /**
     * Creating an overridden method to listen for an action such as when button clicked
     * @param e the event to be processed
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        // getting players name from the text field
        playerName = textbox.getText();
        System.out.println(playerName); // printing it out in the terminal
        if (!playerName.strip().equals("")) {
            submit_button.setEnabled(false); // setting enable option to false
            submit_button.setBackground(Color.lightGray); // changing the color state of the button

        }

    }

    /**
     * setting height of the frame
     * @param height of frame
     */
//    public void setHeight(int height) {
//        this.height = height;
//    }
    /**
     * setting width of the frame
     * @param width of frame
     */
//    public void setWidth(int width) {
//        this.width = width;
//    }
}
