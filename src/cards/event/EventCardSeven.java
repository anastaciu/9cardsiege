
package cards.event;


/**
 *
 * @author ricardo
 */
public class EventCardSeven extends EventCard{
    
    public EventCardSeven(){
        cardLocation = "/ui/gui/Imagens/card_7.png";
        name = "Card #7";
        description = "Day 1\nActions: 2\nDETERMINED ENEMY: -1 to Attacks on Ram\nEnemy Advancement: Ram\nDay 2\nActions: 2\nIRON SHIELDS: -1 to Atacks on Tower\nEnemy Advancement: Tower\nDay 3\nActions: 3\nFAITH: +1 Attacks on Ram, Ladder and Morale actions\nEnemy Advancement: All\n";
    }    
    
    @Override
    public int getActionsNumber(int dayStatus){
        if(dayStatus == 1)
            return 2;
        if(dayStatus == 2)
            return 2;
        if(dayStatus == 3)
            return 3; 
        return -1;
    }
   
    
}
