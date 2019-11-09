
package cards.event;

/**
 *
 * @author ricardo
 */
public class EventCardThree extends EventCard{
    
    public EventCardThree(){
        cardLocation = "/ui/gui/Imagens/card_3.png";
        name = "Card #3";
        description = "Day 1\nActions: 2\nSUPPLIES SPOILED: Supplies -1\nEnemy Advancement: Ladder\nDay 2\nActions: 2\nBAD WEATHER: Only Raid and Sabotage\nEnemy Advancement: None\nDay 3\nActions: 2\nBOILING OIL: Attack +2 in Position 1\nEnemy Advancement: Ladder, Ram\n";
    }
           
    @Override
    public int getActionsNumber(int dayStatus){
        if(dayStatus == 1)
            return 2;
        if(dayStatus == 2)
            return 2;
        if(dayStatus == 3)
            return 2; 
        return -1;
    }            
      
}
