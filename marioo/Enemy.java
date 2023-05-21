import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor {
    private int shootDelay = 50; // the number of act cycles between shots
    private int shootCounter = 0; // the current number of act cycles since the last shot
    private int speed = 2;
    private int direction = 1; // 1 = right, -1 = left

    public void act() {
        shootCounter++;
        if (shootCounter >= shootDelay) {
            shoot();
            shootCounter = 0;
        }
        // Move horizontally
        setLocation(getX() + speed * direction, getY());

        // Check for edge of screen
        if (getX() <= 0 || getX() >= getWorld().getWidth() - 1) {
            // Change direction
            direction *= -1;
        }

        // Check for collision with Mario
        // checkCollision();
    }

    private void shoot() {
        Projectile projectile = new Projectile(direction);
        getWorld().addObject(projectile, getX(), getY());
    }

    public void checkCollision() {
        Actor projectile = getOneIntersectingObject(Projectile.class);
        if (projectile != null) {
            getWorld().removeObject(projectile);
            ((NextWorld) getWorld()).loseLife();
        }
        if (getX() < getWorld().getWidth()-5) {
            getWorld().removeObject(projectile);
            
        }
    }
}