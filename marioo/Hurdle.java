import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hurdle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hurdle extends Actor {
    public Hurdle() {
        //setImage("ground.png");
        GreenfootImage platform = new GreenfootImage("brick.png");
        GreenfootImage image = new GreenfootImage(40, platform.getHeight());
        int w = platform.getWidth();
        for(int offset=0; offset<600; offset+=w)
        {
            image.drawImage(platform, offset, 0);
        }
        setImage(image);
    }
}
