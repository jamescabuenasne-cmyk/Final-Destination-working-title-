import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class GameEngine {

        JFrame window;
        Container con;
        JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel;
        JLabel titleNameLabel;
        Font titleFont = new Font("Courier New", Font.PLAIN, 20);
        Font normalFont = new Font("Courier New", Font.PLAIN, 25);

        Scanner sc = new Scanner(System.in);
 
        GameObject currentScene;
 
        Scene scene1, scene2, scene3, scene4, scene5, scene6;
 
    public void startGame() {
        
        //the game window...
        window = new JFrame("Final Destination!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setSize(800, 600);
        window.setLayout(null);
        window.setLocationRelativeTo(null);
        con = window.getContentPane();

        //Panels for the title and start button...
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("Final Destination");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);
        titleNamePanel.add(titleNameLabel);

        con.add(titleNamePanel);


        window.setVisible(true);
        //do not put any GUI stuff below this line...
        //---------------------------------------------------------------
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