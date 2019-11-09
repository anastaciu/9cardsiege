
package cards.event;

/**
 * @author ricardo
 */

public class EventCardTwo extends EventCard{
        
    public EventCardTwo(){
        cardLocation = "/ui/gui/Imagens/card_2.png";
        name = "Card #2";
        description = "Day 1\nActions: 2\nILLNESS: Morale -1 Supplies -1\nEnemy Advancement: Tower\nDay 2\nActions: 2\nGUARDS DISTRACTED: Sabotage +1 Morale +1\nEnemy Advancement: Slowest Units\nDay 3\nActions: 1\nTREBUCHET ATTACK\n";
    }
              
    @Override
    public int getActionsNumber(int dayStatus){
        if(dayStatus == 1)
            return 2;
        if(dayStatus == 2)
            return 2;
        if(dayStatus == 3)
            return 1; 
        return -1;
    }   

}
