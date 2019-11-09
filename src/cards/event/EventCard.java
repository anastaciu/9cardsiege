package cards.event;

import java.io.Serializable;



/**
 * @author ricardo
 */

public abstract class EventCard implements Serializable{
    protected String name;
    protected String description;  
    String cardLocation;
    
    public EventCard(){             
    }
  
    public EventCard getCard(){
        return this;
    }            
    
    public String getCardLocation(){
        return cardLocation;
    }
    
    public abstract int getActionsNumber(int dayStatus);
            
    @Override
    public String toString(){
        return "\nThe " + name + " was drawn...\n" + description;
    }     
}
