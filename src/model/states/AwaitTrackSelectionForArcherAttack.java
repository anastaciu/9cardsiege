package model.states;

import model.gameplay.GamePlay;

/*
 * @author ricardo
 */

public class AwaitTrackSelectionForArcherAttack extends StateAdapter{
    
    public AwaitTrackSelectionForArcherAttack(GamePlay gameplay) {
        super(gameplay);
    }
    
    @Override
    public  IStates executeArcherAttack(int op, int dado){ 
        int bonusLadder, bonusRam, bonusTower;
        bonusLadder = dado + getGamePlay().getAllAttacks() + getGamePlay().getLadderAttack();
        if(getGamePlay().getLadderPosition() == 1)
            bonusLadder += getGamePlay().getCircleAttacks();
        bonusRam = dado + getGamePlay().getAllAttacks() + getGamePlay().getRamAttack();
            if(getGamePlay().getRamPosition() == 1)
                bonusRam += getGamePlay().getCircleAttacks();
        bonusTower = dado + getGamePlay().getAllAttacks() + getGamePlay().getTowerAttack();
            if(getGamePlay().getTowerPosition() == 1)
                bonusTower += getGamePlay().getCircleAttacks();
        if(op == 1 && bonusLadder > getGamePlay().getLadderStrength() && getGamePlay().getLadderPosition() < 4){
            getGamePlay().setLadderPosition(getGamePlay().getLadderPosition() + 1);
            getGamePlay().buildPlayCardsOutput();   
        }
        else if(op == 2 && bonusRam > getGamePlay().getRamStrength() && getGamePlay().getRamPosition() < 4){
            getGamePlay().setRamPosition(getGamePlay().getRamPosition() + 1);
            getGamePlay().buildPlayCardsOutput();    
        } 
        else if(op == 3 && bonusTower > getGamePlay().getTowerStrength() && getGamePlay().getTowerPosition() < 4){
            getGamePlay().setTowerPosition(getGamePlay().getTowerPosition() + 1);
            getGamePlay().buildPlayCardsOutput(); 
        }
        getGamePlay().setActions(getGamePlay().getActions() - 1);
        return new AwaitOptionSelection(getGamePlay());  
    }
}
