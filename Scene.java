import java.util.ArrayList;
 
public class Scene extends GameObject {

    private int id;
    private String story;
    private ArrayList<Choice> choices = new ArrayList<>();
 
    public Scene(int id, String story){
        this.id = id;
        this.story = story;
    }
 
    public int getId(){
        return id;
    }
 
    public ArrayList<Choice> getChoices(){
        return choices;
    }
 
    public void addChoice(Choice choice){
        choices.add(choice);
    }
 
    @Override
    public void displayScene(){
        System.out.println("\n" + story);
        char option = 'A';
 
        for(Choice c : choices){
            System.out.println(option + ". " + c.getDescription());
            option++;
        }
    }
}

// Code before
// public class Scene {
 
//     int id;
//     String story;
//     Choice choiceA;
//     Choice choiceB;
 
//     public Scene(int id, String story, Choice choiceA, Choice choiceB) {
//         this.id = id;
//         this.story = story;
//         this.choiceA = choiceA;
//         this.choiceB = choiceB;
//     }
 
//     public void displayScene() {
//         System.out.println("\n" + story);
//         System.out.println("A. " + choiceA.text);
//         System.out.println("B. " + choiceB.text);
//     }
// }