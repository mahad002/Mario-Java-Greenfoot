import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Scoreboard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Scoreboard extends Actor {
    private static int score;
    private static int lives;
    
    public Scoreboard() {
        score = 0;
        //lives = 3; 
        updateImage();
    }
    public Scoreboard(int s) {
        score = s;
        //lives = 3;
        updateImage();
    }
    
    public static int getScore(){return score;}
    public void setScore(int s) {score = s;}
    
    public static int getLives(){return lives;}
    public void setLives(int s) {lives = s;}

    public void addScore(int points) {
        score += points;
        updateImage();
    }

    private void updateImage() {
        GreenfootImage image = new GreenfootImage("Score: " + score, 30, Color.WHITE, new Color(0, 0, 0, 0));
        setImage(image);
    }
}
