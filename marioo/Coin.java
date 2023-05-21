import greenfoot.*; 

public class Coin extends Actor
{
    private GreenfootImage coinImage;
    private boolean isCollected;
    Scoreboard score;
    
    public Coin(Scoreboard score)
    {
        coinImage = new GreenfootImage("bullet pickup.png");
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
            MyWorld myWorld = (MyWorld)getWorld();
            myWorld.addCoin();
            isCollected = true;
            getWorld().removeObject(this);
            score.addScore(10);
        }
    }
}
