public class Choice {
    private String description;
    private int nextScene;
    private boolean gameOver;
    private String deathMessage;
    
    public Choice(String description, int nextScene, boolean gameOver, String deathMessage) {
        this.description = description;
        this.nextScene = nextScene;
        this.gameOver = gameOver;
        this.deathMessage = deathMessage;
    }
 
    //Getters for Choice
    public String getDescription(){
        return description;
     }
    public int getNextScene() {
        return nextScene;
    }
    public boolean isGameOver() {
        return gameOver;
    }
    public String getDeathMessage() {
        return deathMessage;
    }
}