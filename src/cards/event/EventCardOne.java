
package cards.event;

/*
 * @author ricardo
 */

public class EventCardOne extends EventCard{
    
    public EventCardOne(){
        cardLocation = "/ui/gui/Imagens/card_1.png";
        name = "Card #1";
        description = "Day 1\nActions: 3\nTREBUCHET ATTACK\nDay 2\nActions: 2\nTREBUCHET ATTACK\nDay 3\nActions: 1\nTREBUCHET ATTACK\n";
    }   
        
    @Override
    public int getActionsNumber(int dayStatus){
        if(dayStatus == 1)
            return 3;
        if(dayStatus == 2)
            return 2;
        if(dayStatus == 3)
            return 1; 
        return -1;
    }
       
}

