import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Projectile extends Actor {
    
    private int speed = 5;
    private int direction;
    
    public Projectile(int direction) {
        this.direction = direction;
    }
    
    public void act() {
        move(speed * direction);
        checkCollision();
    }
    
    public void checkCollision() {
        Actor projectile = getOneIntersectingObject(Projectile.class);
        
        if (projectile != null) {
            getWorld().removeObject(projectile);

            Mario mario = (Mario) getOneIntersectingObject(Mario.class);
            if (mario != null) {
                ((NextWorld) getWorld()).loseLife();
            }
        }
    }

}
