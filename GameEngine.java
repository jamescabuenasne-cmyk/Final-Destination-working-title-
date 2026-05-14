import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;

public class GameEngine {

        JFrame window;
        Container con;
        JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel;
        JLabel titleNameLabel;
        Font titleFont = new Font("Georgia", Font.PLAIN, 30);
        Font normalFont = new Font("Georgia", Font.PLAIN, 25);

        Scanner sc = new Scanner(System.in);
 
        GameObject currentScene;
 
        Scene scene1, scene2, scene3, scene4, scene5, scene6;
 

    public void startGame() {
        //To the next person who reads this, I am sorry~~
        //I know this is a mess, but I will clean it up eventually, I promise.

        //the game window...
        window = new JFrame("Final Destination!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);
        con = window.getContentPane();

        con.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.black);
        

        //Panels for the title and start button...
        titleNamePanel = new JPanel();
        titleNamePanel.setBackground(Color.black);
        titleNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleNameLabel = new JLabel("Final Destination");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);
        titleNamePanel.add(titleNameLabel);

        Component spacer = Box.createRigidArea(new Dimension(0, 150));

        //Start button panel...
        startButtonPanel = new JPanel();
        startButtonPanel.setBackground(Color.black);
        startButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButtonPanel.setOpaque(false);
        //button deets
        JButton startButton = new JButton("START");
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(true);
        startButton.setBorder(BorderFactory.createLineBorder(Color.red, 2, true));
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.setPreferredSize(new Dimension(200, 60));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                createScenes();
                currentScene = scene1;
                openGameWindow();
            }
        });

        startButtonPanel.add(startButton);
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(titleNamePanel);
        centerPanel.add(spacer);
        centerPanel.add(startButtonPanel);
        centerPanel.add(Box.createVerticalGlue());

        con.add(centerPanel, BorderLayout.CENTER);
        window.setVisible(true);
        //do not put any GUI stuff below this line...
        //---------------------------------------------------------------

        //public void titleNameScreen() {
           // System.out.println("");
        //}

        createScenes();
 
        while (true) {
 
            System.out.println("Welcome to Final Destination!! (Working title)");
            System.out.println("Main Menu");
            System.out.println("A. Start");
            System.out.println("B. Quit");
            System.out.print("Choice: ");
 
            String mainChoice = sc.nextLine().toUpperCase();
 
            if (mainChoice.equals("A")) {
 
                currentScene = scene1;
                boolean gameActive = true;
 
                while (gameActive) {
 
                    currentScene.displayScene();
 
                    System.out.print("Choice: ");
 
                    String input = sc.nextLine().toUpperCase();
 
                    int index = input.charAt(0) - 'A';
 
                    Scene tempScene = (Scene) currentScene;
 
                    if (index >= 0 && index < tempScene.getChoices().size()) {
 
                        gameActive = processChoice(
                                tempScene.getChoices().get(index)
                        );
 
                    } else {
 
                        System.out.println("Invalid choice.");
 
                    }
                }
 
            } else if (mainChoice.equals("B")) {
 
                System.out.println("Thanks for playing!");
                System.exit(0);
 
            } else {
 
                System.out.println("Invalid choice.");
 
            }
        }
    }

    public void openGameWindow() {

        JFrame gameWindow = new JFrame("Final Destination - Game");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(800, 600);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.getContentPane().setBackground(Color.black);
        gameWindow.setLayout(new BorderLayout());

        // Scene text area at the top
        mainTextPanel = new JPanel();
        mainTextPanel.setBackground(Color.black);
        mainTextPanel.setLayout(new BorderLayout());
        mainTextPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 20, 60));

        JLabel sceneText = new JLabel("You feel your usual path will kill you...");
        sceneText.setForeground(Color.white);
        sceneText.setFont(normalFont);
        sceneText.setHorizontalAlignment(SwingConstants.CENTER);
        mainTextPanel.add(sceneText, BorderLayout.CENTER);

        JButton startButton = new JButton("START");
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(true);
        startButton.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);

        // Choice buttons area at the bottom
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new BoxLayout(choiceButtonPanel, BoxLayout.Y_AXIS));
        choiceButtonPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 40, 60));


        String[] choices = {"A. Take the same path", "B. Take a different path"};
        for (String choice : choices) {
        JButton btn = new JButton(choice);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(true);
        btn.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        btn.setForeground(Color.white);
        btn.setFont(normalFont);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(400, 40));
        choiceButtonPanel.add(btn);
        choiceButtonPanel.add(Box.createRigidArea(new Dimension(0, 8)));
    }

    gameWindow.add(mainTextPanel, BorderLayout.CENTER);
    gameWindow.add(choiceButtonPanel, BorderLayout.SOUTH);
    gameWindow.setVisible(true);
    }


 
    public boolean processChoice(Choice choice) {
 
        if (choice.isGameOver()) {
 
            System.out.println("\n" + choice.getDeathMessage());
            System.out.println("\nGame Over!\n");
 
            return false;
 
        }
 
        if (((Scene) currentScene).getId() == 6) {
 
            if (choice.getDescription().equals("B")) {
 
                System.out.println("\nThe house explodes out of nowhere...");
 
            }
 
            System.out.println("Returning to main menu...");
 
            return false;
 
        }
 
        if (choice.getNextScene() == 1) currentScene = scene1;
        else if (choice.getNextScene() == 2) currentScene = scene2;
        else if (choice.getNextScene() == 3) currentScene = scene3;
        else if (choice.getNextScene() == 4) currentScene = scene4;
        else if (choice.getNextScene() == 5) currentScene = scene5;
        else if (choice.getNextScene() == 6) currentScene = scene6;
 
        return true;
    }
 
    public void createScenes() {
        // Scene 1
        scene1 = new Scene(
                1,
                "You feel your usual path will kill you..."
        );
 
        scene1.addChoice(
                new Choice(
                        "Same Path",
                        0,
                        true,
                        "Aircon falls on your head."
                )
        );
 
        scene1.addChoice(
                new Choice(
                        "Different Path",
                        2,
                        false,
                        null
                )
        );
 
      
        // Scene 2
        scene2 = new Scene(
                2,
                "A dog blocks your way..."
        );
 
        scene2.addChoice(
                new Choice(
                        "Feed Biscuit",
                        0,
                        true,
                        "Dog got aggressive."
                )
        );
 
        scene2.addChoice(
                new Choice(
                        "Pick Object",
                        3,
                        false,
                        null
                )
        );
 

        // Scene 3
        scene3 = new Scene(
                3,
                "You are lost, phone dying..."
        );
 
        scene3.addChoice(
                new Choice(
                        "Talk to man",
                        4,
                        false,
                        null
                )
        );
 
        scene3.addChoice(
                new Choice(
                        "Enter alley",
                        0,
                        true,
                        "Robber kills you."
                )
        );
 
        scene3.addChoice(
                new Choice(
                        "Wait",
                        0,
                        true,
                        "Dogs find you."
                )
        );
 
        scene3.addChoice(
                new Choice(
                        "Sing song",
                        0,
                        true,
                        "Man stabs you."
                )
        );
 
        // Scene 4
        scene4 = new Scene(
                4,
                "The man misleads you..."
        );
 
        scene4.addChoice(
                new Choice(
                        "Run",
                        0,
                        true,
                        "You fall."
                )
        );
 
        scene4.addChoice(
                new Choice(
                        "Shout",
                        0,
                        true,
                        "Dogs hear you."
                )
        );
 
        scene4.addChoice(
                new Choice(
                        "Climb gate",
                        0,
                        true,
                        "You fall."
                )
        );
 
        scene4.addChoice(
                new Choice(
                        "Confront man",
                        5,
                        false,
                        null
                )
        );
 
        // Scene 5
        scene5 = new Scene(
                5,
                "You are close to home..."
        );
 
        scene5.addChoice(
                new Choice(
                        "Run home",
                        0,
                        true,
                        "Hit by car."
                )
        );
 
        scene5.addChoice(
                new Choice(
                        "Walk safely",
                        6,
                        false,
                        null
                )
        );
 
        // Scene 6
        scene6 = new Scene(
                6,
                "...\nEverything goes silent.\nThe world fades to black."
        );
 
        scene6.addChoice(
                new Choice(
                        "A",
                        0,
                        false,
                        null
                )
        );
 
        scene6.addChoice(
                new Choice(
                        "B",
                        0,
                        false,
                        "Explosion ending"
                )
        );
    }
}