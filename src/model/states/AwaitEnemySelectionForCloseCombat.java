
package model.states;

import model.gameplay.GamePlay;

/**
 * @author ricardo
 */

public class AwaitEnemySelectionForCloseCombat extends StateAdapter{
    
    public AwaitEnemySelectionForCloseCombat(GamePlay gameplay) {
        super(gameplay);
    }
    
    @Override
    public IStates executeCloseCombat(int op, int dado){
        if(dado <= 4)          
            getGamePlay().setActions(getGamePlay().getActions() - 1);
        else if(op == 1 && getGamePlay().getLadderPosition() == 0 && dado > 4){ 
            getGamePlay().setLadderPosition(getGamePlay().getLadderPosition() + 1);
            getGamePlay().setActions(getGamePlay().getActions() - 1);
        }        
        else if(op == 2 && getGamePlay().getRamPosition() == 0 && dado > 4){ 
            getGamePlay().setRamPosition(getGamePlay().getRamPosition() + 1);
            getGamePlay().setActions(getGamePlay().getActions() - 1);
        }
        else if(op == 3 && getGamePlay().getTowerPosition() == 0 && dado > 4){ 
            getGamePlay().setTowerPosition(getGamePlay().getTowerPosition() + 1);
            getGamePlay().setActions(getGamePlay().getActions() - 1);
        }
        else
            getGamePlay().setErrorflag(true); 
        return new AwaitOptionSelection(getGamePlay());            
    }   
}
