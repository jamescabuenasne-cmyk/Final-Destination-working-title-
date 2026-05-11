import java.util.ArrayList;

public class Scene {
 
    int id;
    String story;
   ArrayList<Choice> choices = new ArrayList<>();
  
    public Scene(int id, String story) {
        this.id = id;
        this.story = story;
    }
    
    public void addChoice(Choice choice){
        choices.add(choice);
    }

    public void displayScene() {
        System.out.println("\n" + story);
        
        char option = 'A';
        for(Choice c : choices){
            System.out.println(option + ". " + c.getDescription());
            option++;
        }
    }
}