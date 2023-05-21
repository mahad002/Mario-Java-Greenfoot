import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World {
    private Mario mario;
    private Scoreboard score;
    private int numCoins;
    private int coinsCollected;
    String playerName = "";

    public MyWorld() {
        this(0);
    }

    public MyWorld(int coinsCollected) {
        super(600, 400, 1); // create a world with a size of 600x400 pixels and a cell size of 1 pixel
        this.coinsCollected = coinsCollected;
        prepare(); // prepare the world by adding objects to it
        while(playerName.equals("")){
            playerName = Greenfoot.ask("Please enter your name: ");
        }
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

        // Add a scoreboard to the top left of the screen
        Scoreboard score = new Scoreboard();
        addObject(score, 100, 30);
        
        // Add some coins on top of the hurdles
        numCoins = 4;
        int coinSpacing = 60;
        int coinY = hurdleY - hurdleSpacing;
        Coin[][] coins = new Coin[numHurdles][numCoins];
        for (int i = 0; i < numHurdles; i++) {
            for (int j = 0; j < numCoins; j++) {
                coins[i][j] = new Coin(score);
                addObject(coins[i][j], getWidth() - hurdleSpacing * i - coinSpacing * j, coinY);
            }
        }
    }

    public void act() {
       if (coinsCollected == numCoins*8) {
            // Create a new instance of MyWorld and set it as the new world for Mario
            int x = score.getScore();
            NextWorld nextWorld = new NextWorld(x,numCoins);
            //nextWorld.addObject(mario, getWidth() / 2, getHeight() / 2);
            Greenfoot.setWorld(nextWorld);
        }
    
        if (Greenfoot.isKeyDown("escape")) {
            Greenfoot.stop(); // stop the simulation if the user presses the Escape key
        }
    }

    public void addCoin() {
        coinsCollected++;
    }
}
