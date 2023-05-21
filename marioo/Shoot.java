import greenfoot.*;

public class Shoot extends Actor {
    private int speed = 5;
    Scoreboard score;
    
    public Shoot(Scoreboard s){
        score = s;
    }

    public void act() {
        setLocation(getX(), getY() + speed);
        if (getY() > getWorld().getHeight() - 1) {
            getWorld().removeObject(this);
            
        }
    }
}