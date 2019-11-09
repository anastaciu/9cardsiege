package cards.event;


/**
 * @author ricardo
 */

public class EventCardFive extends EventCard{
    
    public EventCardFive(){
        cardLocation = "/ui/gui/Imagens/card_5.png";      
        name = "Card #5";       
        description = "Day 1\nActions: 3\nVALLEY OF ARROWS: +1 All Attacks\nEnemy Advancement: Ram\nDay 2\nActions: 2\nCOLLAPSED: Tower removed from game if on starting space\nEnemy Advancement: Ladder, Ram\nDay 3\nActions: 2\nREPAIRED TREBUCHET: +1 Trebuchet +1 Attacks on Close Combat\nEnemy Advancement: Ladder\n";
        
    }              
    
    @Override
    public int getActionsNumber(int dayStatus){
        if(dayStatus == 1)
            return 3;
        if(dayStatus == 2)
            return 2;
        if(dayStatus == 3)
            return 2; 
        return -1;
    }    





}
