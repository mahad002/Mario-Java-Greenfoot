import java.util.ArrayList;
import greenfoot.Actor;

public class Group<T extends Actor> extends Actor {
    private ArrayList<T> members = new ArrayList<T>();
    
    public Group() {
    }
    
    public void add(T actor) {
        members.add(actor);
        getWorld().addObject(actor, 0, 0);
    }
    
    public boolean remove(T actor) {
        if (members.remove(actor)) {
            getWorld().removeObject(actor);
            return true;
        }
        return false;
    }
    
    public ArrayList<T> getMembers() {
        return members;
    }
    
    public void act() {
    }
}
