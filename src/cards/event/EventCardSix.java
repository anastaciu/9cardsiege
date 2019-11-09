
package cards.event;


/**
 *
 * @author ricardo
 */
public class EventCardSix extends EventCard{
    
    public EventCardSix(){
        cardLocation = "/ui/gui/Imagens/card_6.png";
        name = "Card #6";
        description = "Day 1\nActions: 3\nCOVER OF DARKNESS: +1 to Raid and Sabotage\nEnemy Advancement: Slowest Enemy\nDay 2\nActions: 3\nENEMY FATIGUE: +1 to Coupure, Raid and Sabotage\nEnemy Advancement: Ladder\nDay 3\nActions: 3\nRALLY: +1 Attacks on Close Combat or Circles\nEnemy Advancement: Ram, Tower\n";
    }        
     
    
    
    @Override
    public int getActionsNumber(int dayStatus){
        if(dayStatus == 1)
            return 3;
        if(dayStatus == 2)
            return 3;
        if(dayStatus == 3)
            return 3; 
        return -1;
    }
 
}
