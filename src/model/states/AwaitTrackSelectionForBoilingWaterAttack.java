package model.states;

import model.gameplay.GamePlay;

/*
 * @author ricardo
 */

public class AwaitTrackSelectionForBoilingWaterAttack extends StateAdapter{   
    public AwaitTrackSelectionForBoilingWaterAttack(GamePlay gameplay) {
        super(gameplay);              
    }
    
    @Override
    public  IStates executeBoilingWaterAttack(int op, int dado){       
        int bonusLadder, bonusRam, bonusTower;
        if(dado == 1)
            getGamePlay().setMorale(getGamePlay().getMorale() - 1 );
        bonusLadder = dado + getGamePlay().getAllAttacks() + getGamePlay().getLadderAttack()+getGamePlay().getCircleAttacks();
        bonusRam = dado + getGamePlay().getAllAttacks() + getGamePlay().getRamAttack() + getGamePlay().getCircleAttacks();
        bonusTower = dado + getGamePlay().getAllAttacks() + getGamePlay().getTowerAttack()+getGamePlay().getCircleAttacks();       
        if(op == 1 && bonusLadder >= getGamePlay().getLadderStrength() && getGamePlay().getLadderPosition() == 1){
            getGamePlay().setLadderPosition(getGamePlay().getLadderPosition() + 1);
            getGamePlay().setBoilingWatterAttack(true);
        }          
        else if(op == 2 && bonusRam >= getGamePlay().getRamStrength() && getGamePlay().getRamPosition() == 1){
            getGamePlay().setRamPosition(getGamePlay().getRamPosition() + 1);
            getGamePlay().setBoilingWatterAttack(true);
        } 
        else if(op == 3 && bonusTower >= getGamePlay().getTowerStrength() && getGamePlay().getTowerPosition() == 1){
            getGamePlay().setTowerPosition(getGamePlay().getTowerPosition() + 1);
            getGamePlay().setBoilingWatterAttack(true);
        }
        else{
            getGamePlay().setErrorflag(true);
            return new AwaitOptionSelection(getGamePlay());
        }             
        getGamePlay().setActions(getGamePlay().getActions() - 1);        
        return new AwaitOptionSelection(getGamePlay());              
    }    
}
