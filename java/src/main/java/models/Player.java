package models;

public class Player {
    private final String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public int getScore() {return score;}
    public void incrementScore() {this.score+=1;}
    public String getName() {return name;}
}
