package model.states;

import model.gameplay.GamePlay;

/**
 * @author ricardo
 */

public class AwaitOptionSelection extends StateAdapter{
    
    public AwaitOptionSelection(GamePlay gameplay) {
        super(gameplay);       
    }
           
    @Override
    public IStates archerAttack(){         
        return new AwaitTrackSelectionForArcherAttack(getGamePlay());  
    }
    
    @Override
    public IStates boilingWaterAttack(){
        if(!getGamePlay().isBoilingWatterAttack() && (getGamePlay().getLadderPosition() == 1 || getGamePlay().getRamPosition() == 1 || getGamePlay().getTowerPosition() == 1))
            return new AwaitTrackSelectionForBoilingWaterAttack(getGamePlay());  
        else{
            getGamePlay().setErrorflag(true);
            return new AwaitOptionSelection(getGamePlay());
        }
    }
    
    @Override
     public IStates drawNextCard(int dado){
        int turn, dayStatus; 
        getGamePlay().setErrorflag(false);
        if(getGamePlay().getActions() == 0){ 
            getGamePlay().setBuyFlag(false);
            getGamePlay().setBoilingWatterAttack(false);
            getGamePlay().setOneFreeMoveOnlyErrorflag(false);
            if(getGamePlay().getTunnelPosition() == 3)
                if(dado == 1){
                    getGamePlay().setTunnelPosition(0);
                    getGamePlay().setRaidedSupplies(0);  
                    if(getGamePlay().getMorale() > 0)
                        getGamePlay().setMorale(getGamePlay().getMorale() - 1);  
                }
            if((getGamePlay().getLadderPosition() == 0 &&  getGamePlay().getRamPosition() == 0) ||  (getGamePlay().getLadderPosition() == 0 && getGamePlay().getTowerPosition() == 0) || (getGamePlay().getRamPosition() == 0 && getGamePlay().getTowerPosition() == 0))
                return new GameOver(getGamePlay());
            if(getGamePlay().getMorale() == 0 || getGamePlay().getWallStrength()== 0 || getGamePlay().getSupplies() == 0)
                return new GameOver(getGamePlay());
            turn = getGamePlay().getTurn() + 1;
            
            getGamePlay().dayEventsReset();
            getGamePlay().setTurn(turn);
            if(turn == 7){
                if(getGamePlay().getTunnelPosition() == 3){
                    getGamePlay().setTunnelPosition(0);
                    getGamePlay().setRaidedSupplies(0);  
                    if(getGamePlay().getMorale() > 0)
                        getGamePlay().setMorale(getGamePlay().getMorale() - 1);                   
                }   
                if(getGamePlay().getSupplies() > 0)
                    getGamePlay().setSupplies(getGamePlay().getSupplies() - 1);
                if(getGamePlay().getTunnelPosition() < 3){
                    getGamePlay().setTunnelPosition(0);                    
                    getGamePlay().setSupplies(getGamePlay().getSupplies() + getGamePlay().getRaidedSupplies());
                    if(getGamePlay().getSupplies() > 4)
                        getGamePlay().setSupplies(4);                        
                }                   
                getGamePlay().setTurn(0);
                getGamePlay().randomizeCardList();
                dayStatus = getGamePlay().getDayStatus() + 1;
                getGamePlay().setDayStatus(dayStatus);
                if(dayStatus == 4)
                    return new Victory(getGamePlay());
            } 
            getGamePlay().setCurrentCard();
            return new AwaitTopCard(getGamePlay());
        }
        else
            return new AwaitOptionSelection(getGamePlay()); 
    }
       
    @Override
    public IStates closeCombat(){ 
        if(getGamePlay().getLadderPosition() == 0 || getGamePlay().getRamPosition() == 0 || getGamePlay().getTowerPosition() == 0)
            return new AwaitEnemySelectionForCloseCombat(getGamePlay()); 
        else{
            getGamePlay().setErrorflag(true);
            return new AwaitOptionSelection(getGamePlay());       
        }
    }    
    
    @Override
    public IStates tunnelMove(){
        return new AwaitForTunnelActionSelection(getGamePlay());         
    }
    
    @Override
    public IStates supplyRaid(int dado){
        if(getGamePlay().getTunnelPosition() != 3){
            getGamePlay().setErrorflag(true);
            return new AwaitOptionSelection(getGamePlay());
        }
        else{
            getGamePlay().raidSupplies(dado); 
            getGamePlay().setActions(getGamePlay().getActions()-1);
            return new AwaitOptionSelection(getGamePlay());            
        }           
    }
    
    @Override
    public IStates sabotage(int dado){
        int dadoBonus = dado + getGamePlay().getSabotageBonus();
        if(getGamePlay().getTunnelPosition() != 3){
            getGamePlay().setErrorflag(true);
            return new AwaitOptionSelection(getGamePlay());
        }
        else{
            getGamePlay().sabotageAction(dadoBonus);
            getGamePlay().setActions(getGamePlay().getActions()-1);
            return new AwaitOptionSelection(getGamePlay());           
        } 
    }
    
    @Override
    public IStates coupure(int dado){
        int dadoBonus = dado + getGamePlay().getCoupureBonus();
        getGamePlay().coupureAction(dadoBonus);
        getGamePlay().setActions(getGamePlay().getActions()-1);
        return new AwaitOptionSelection(getGamePlay());      
    }
    
    @Override
    public IStates saveGame(){
//tratado nos interfaces
        return new AwaitOptionSelection(getGamePlay());         
    }
       
    @Override
    public IStates rallyTroops(int dado){
        int dadoBonus = dado + getGamePlay().getMoraleAction();
        getGamePlay().rallyTroops(dadoBonus);  
        getGamePlay().setActions(getGamePlay().getActions()-1);
        return new AwaitOptionSelection(getGamePlay());         
    }
    
    @Override
    public IStates checkGameOver(){
        if(getGamePlay().checkEnemyPositionForImmediateGameOver())
            return new GameOver(getGamePlay());
        if(getGamePlay().checkStatusCardForImmediateLoss())
            return new GameOver(getGamePlay());
        return new AwaitOptionSelection(getGamePlay());   
    }   
    
    @Override
    public IStates finish(){          
        return new GameOver(getGamePlay());              
    }
    
    @Override
    public IStates mainMenu(){          
        return new AwaitBeginning(getGamePlay());              
    }
    
    @Override
     public IStates buyActionPoint() {
        if(getGamePlay().isBuyFlag())
            return new AwaitOptionSelection(getGamePlay()); 
        else
            return new AwaitPaymentSelectionForActionPoint(getGamePlay());
    }
}
