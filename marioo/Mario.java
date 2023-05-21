import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mario extends Actor {
    public int lives = 3;
    private final int MOVE_SPEED = 5; // the speed at which Mario moves
    private final double JUMP_STRENGTH = 10; // the strength of Mario's jump
    private double ySpeed = 0; // the vertical speed of Mario
    Scoreboard score;
    
    public Mario(Scoreboard s){
        score = s;
    }

    public void act() {
        handleMovement(); // handle Mario's movement
        handleJump(); // handle Mario's jump
        checkCollision(); // check for collision with the ground
        checkHurdleCollision(); // check for collision with a hurdle
    }

    private void handleMovement() {
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - MOVE_SPEED, getY()); // move Mario left
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + MOVE_SPEED, getY()); // move Mario right
        }
    }

    private void handleJump() {
        if (Greenfoot.isKeyDown("Up")) {
            if (isOnGround()) {
                ySpeed = -JUMP_STRENGTH;
            }
            Hurdle hurdle = (Hurdle) getOneIntersectingObject(Hurdle.class);
            if (hurdle != null) {
                ySpeed = -JUMP_STRENGTH;
                setLocation(getX(), hurdle.getY() - hurdle.getImage().getHeight() / 2 - getImage().getHeight() / 2);
            }
        }
        int newY = (int) (getY() + ySpeed);
        int groundHeight = 0;
        Actor ground = getOneObjectAtOffset(0, getImage().getHeight() / 2, Ground.class);
        if (ground != null) {
            groundHeight = ground.getImage().getHeight() / 2;
        }
        if (newY >= getWorld().getHeight() - groundHeight) {
            setLocation(getX(), getWorld().getHeight() - groundHeight);
            ySpeed = 0;
        } else {
            setLocation(getX(), newY);
            ySpeed += 0.5;
        }
    }



    private boolean isOnGround() {
        Actor ground = getOneObjectAtOffset(0, getImage().getHeight()/2, Ground.class); // get the ground object
        return ground != null; // return true if Mario is intersecting with the ground object
    }

    private void checkCollision() {
        Ground ground = (Ground) getOneIntersectingObject(Ground.class); // check for collision with ground
        if (ground != null) {
            int marioTop = getY() - getImage().getHeight() / 2; // get Mario's top y-coordinate
            int groundBottom = ground.getY() + ground.getImage().getHeight() / 2; // get the ground's bottom y-coordinate
            if (marioTop <= groundBottom) { // check if Mario is above the ground
                ySpeed = 0; // reset the vertical speed

            }
        }
        Actor projectile = getOneIntersectingObject(Projectile.class);
        if (projectile != null) {
            getWorld().removeObject(projectile);
            loseLife();
        }
    }

    public void loseLife() {
        lives--;
        score.setLives(lives);
        if (lives == 0) {
            GameOver go = new GameOver();
            Greenfoot.setWorld(go);
            //Greenfoot.stop();
        }
    }
    
    private void checkHurdleCollision() {
        Hurdle hurdle = (Hurdle) getOneIntersectingObject(Hurdle.class); // check for collision with hurdles
        if (hurdle != null) {
            int marioRight = getX() + getImage().getWidth() / 2; // get Mario's right x-coordinate
            int hurdleLeft = hurdle.getX() - hurdle.getImage().getWidth() / 2; // get the hurdle's left x-coordinate
            int marioTop = getY() - (getImage().getWidth() / 2); // get Mario's right x-coordinate
            int hurdleBottom = hurdle.getY() + hurdle.getImage().getWidth() / 2; // get the hurdle's left x-coordinate
            if (marioRight >= hurdleLeft && marioTop > hurdleBottom) { // check if Mario is to the right of the hurdle
                //setLocation(hurdleLeft - getImage().getWidth() / 2, getY()); // move Mario to the left of the hurdle
                ySpeed = 0; // reset the vertical speed
            }
            if (marioTop <= hurdleBottom) { // check if Mario is above the ground
                ySpeed = 0; // reset the vertical speed
            }
        }
    }
}

