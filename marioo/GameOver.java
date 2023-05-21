import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends World
{

    /**
     * Constructor for objects of class GameOver.
     * 
     */
    public GameOver()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        showText("Press enter to restart", getWidth() / 2, getHeight() -100);
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("enter")) {
            MyWorld nextNextWorld = new MyWorld();
            Greenfoot.setWorld(nextNextWorld);
        }
    }
}
