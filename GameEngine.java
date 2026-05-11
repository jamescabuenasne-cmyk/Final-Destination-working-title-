import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameEngine {
 
    JFrame window;
    Container con;
    JPanel titleNamePanel, startButtonPanel;
    JLabel titleNameLabel;
    Font titleFont = new Font("Comic Sans MS", Font.PLAIN, 50);
    JButton startButton;

    Scanner sc = new Scanner(System.in);
 
    Scene currentScene;
    Scene scene1, scene2, scene3, scene4, scene5, scene6;
    
    public void startGame() {
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        con = window.getContentPane();
        window.setTitle("Final Destination!! (Working title)");

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("Final Destination!!");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.blue);

        startButton = new JButton("Start");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        

        startButtonPanel.add(startButton);
        titleNamePanel.add(titleNameLabel);
        con.add(titleNamePanel);
        con.add(startButtonPanel);

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

               
                    if (index >= 0 && index < currentScene.choices.size()) { 
                        gameActive = processChoice(currentScene.choices.get(index));
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
            System.out.println("Game Over");
            return false;
        }

 
        if (currentScene.id == 6) {   
            if (choice.getDescription().equals("B")) {
                System.out.println("\n The house explodes out of nowhere...");
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
 
        //  Scene 1
        scene1 = new Scene(1, "You feel your usual path will kill you...");
        scene1.addChoice(new Choice("Same Path", 0, true, "Aircon falls on your head."));
        scene1.addChoice(new Choice("Different Path", 2, false, null));
 
        //  Scene 2
        scene2 = new Scene(2, "A dog blocks your way...");
        scene2.addChoice(new Choice("Feed Biscuit", 0, true, "Dog got aggressive."));
        scene2.addChoice(new Choice("Pick Object", 3, false, null));
 
        //  Scene 3 
        scene3 = new Scene(3, "You are lost, phone dying...");
        scene3.addChoice(new Choice("Talk to man", 4, false, null));
        scene3.addChoice(new Choice("Enter alley", 0, true, "Robber kills you."));
        scene3.addChoice(new Choice("Wait", 0, true, "Dogs find you."));
        scene3.addChoice(new Choice("Sing song", 0, true, "Man stabs you."));
 
        //  Scene 4
        scene4 = new Scene(4, "The man misleads you...");
        scene4.addChoice(new Choice("Run", 0, true, "You fall."));
        scene4.addChoice(new Choice("Shout", 0, true, "Dogs hear you."));
        scene4.addChoice(new Choice("Climb gate", 0, true, "You fall."));
        scene4.addChoice(new Choice("Confront man", 5, false, null));
 
        //  Scene 5 (pre-final)
        scene5 = new Scene(5, "You are close to home...");
        scene5.addChoice(new Choice("Run home", 0, true, "Hit by car."));
        scene5.addChoice(new Choice("Walk safely", 6, false, null));

        // //Scene 6 (finale)
        scene6 = new Scene(6, "...\nEverything goes silent.\nThe world fades to black.");
        scene6.addChoice(new Choice("A", 0, false, null));
        scene6.addChoice(new Choice("B", 0, false, "Explosion ending"));

       // NOTE:
       // Scenes are currently summarized for testing purposes.
       // Full narrative scenes will be added in future updates.
       // - SEBLOS
        window.setVisible(true);
    }
}
 
