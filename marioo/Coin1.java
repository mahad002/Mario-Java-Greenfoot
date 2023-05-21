import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Coin1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Coin1 extends Actor
{
    private GreenfootImage coinImage;
    private boolean isCollected;
    Scoreboard score;
    
    public Coin1(Scoreboard score)
    {
        coinImage = new GreenfootImage("FireFlower.PNG");
        setImage(coinImage);
        isCollected = false;
        this.score = score;
    }
    
    public void act() 
    {
        checkCollision();
    }
    
    public void checkCollision()
    {
        if (!isCollected && getOneIntersectingObject(Mario.class) != null) {
            NextWorld myWorld = (NextWorld)getWorld();
            myWorld.addCoin();
            isCollected = true;
            getWorld().removeObject(this);
            score.addScore(10);
        }
    }
}
