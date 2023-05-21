import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NextWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NextWorld extends World
{
    private Mario mario;
    private Scoreboard score;
    private int numCoins;
    private int coinsCollected;
    private int lives = 3; // the number of lives Mario has
    
    public NextWorld(int s, int n)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        score = new Scoreboard(s);
        numCoins = n;
        prepare();
    }
    
    private void prepare() {
        // Add Mario to the center of the world
        mario = new Mario(score);
        addObject(mario, getWidth() / 2, getHeight() / 2);

        // Add the ground at the bottom of the world
        Ground ground = new Ground();
        addObject(ground, getWidth() / 2, getHeight() - ground.getImage().getHeight() / 2);

        // Add a row of hurdles on the right side of the world
        int numHurdles = 8;
        int hurdleSpacing = 120;
        int hurdleY = getHeight() - ground.getImage().getHeight() - hurdleSpacing;
        for (int i = 0; i < numHurdles; i++) {
            Hurdle hurdle = new Hurdle();
            addObject(hurdle, getWidth() - (hurdle.getImage().getWidth() / 2) - i * hurdleSpacing, hurdleY);
        }
        
        Enemy enemy = new Enemy();
        addObject(enemy, enemy.getImage().getWidth(), getHeight()-50);
        
        // Add a scoreboard to the top left of the screen
        //Scoreboard score1 = new Scoreboard();
        addObject(score, 120, 50);
        //score1.setScore(score.getScore());
        
        // Add some coins on top of the hurdles
        numCoins = 4;
        int coinSpacing = 60;
        int coinY = hurdleY - hurdleSpacing;
        for (int i = 0; i < numHurdles; i++) {
            for (int j = 0; j < numCoins; j++) {
                Coin1 coin = new Coin1(score);
                addObject(coin, getWidth() - hurdleSpacing * i - coinSpacing * j, coinY);
            }
        }
    }

    public void act() {
       if (coinsCollected == numCoins*8) {
            // Create a new instance of MyWorld and set it as the new world for Mario
            YouWon nextNextWorld = new YouWon();
            Greenfoot.setWorld(nextNextWorld);
        }
        
        if (lives == 0) {
            // Display a game over message and stop the simulation
            //showText("Game Over", getWidth() / 2, getHeight() / 2);
            //Greenfoot.stop();
        }
    
        if (Greenfoot.isKeyDown("escape")) {
            Greenfoot.stop(); // stop the simulation if the user presses the Escape key
        }
    }

    public void addCoin() {
        coinsCollected++;
    }
    
    public void loseLife() {
        lives--;
        if (lives >= 0) {
            // Display the updated number of lives on the scoreboard
            score.setLives(lives);
        }
    }
}
