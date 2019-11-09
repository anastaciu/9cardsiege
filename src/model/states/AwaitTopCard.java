package model.states;

import model.gameplay.GamePlay;

/**
 * @author ricardo
 */

public class AwaitTopCard extends StateAdapter{
    
    public AwaitTopCard(GamePlay gameplay) {
        super(gameplay);
    }
    
    @Override
    public IStates drawTopCard(){       
        getGamePlay().setCurrentCard();
        getGamePlay().enemyCardAdvancementCheck();
        getGamePlay().enemyCardAdvancement();        
        getGamePlay().dayEvents(getGamePlay().getDado());
        if(getGamePlay().isTowerDown() && getGamePlay().getTowerPosition() == 4)
            getGamePlay().setTowerPosition(4);
        if(getGamePlay().getLadderPosition() == 0 || getGamePlay().getRamPosition() == 0 || getGamePlay().getTowerPosition() == 0)
            if(getGamePlay().getMorale() > 0)
                getGamePlay().setMorale(getGamePlay().getMorale() - 1);
        getGamePlay().setCardActions();
        if((getGamePlay().getLadderPosition() == 0 && getGamePlay().getRamPosition() == 0) || (getGamePlay().getRamPosition() == 0 && getGamePlay().getTowerPosition() == 0) || (getGamePlay().getLadderPosition() == 0 && getGamePlay().getTowerPosition() == 0))
            return new AwaitEnemySelectionForCloseCombat(getGamePlay());
        return new AwaitOptionSelection(getGamePlay());          
    }    
}
