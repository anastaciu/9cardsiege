
package cards.event;


/**
 *
 * @author ricardo
 */
public class EventCardFour extends EventCard{
    
    public EventCardFour(){
        cardLocation = "/ui/gui/imagens/card_4.png";
        name = "Card #4";
        description = "Day 1\nActions: 2\nDEATH OF A LEADER: Morale -1\nEnemy Advancement: Ladder, Tower\nDay 2\nActions: 2\nGATE FORTIFIED: +1 Attack on Ram\nEnemy Advancement: Ladder, Ram\nDay 3\nActions: 3\nFLAMING ARROWS: +1 Attack on Siege Tower\nEnemy Advancement: Tower\n";
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
