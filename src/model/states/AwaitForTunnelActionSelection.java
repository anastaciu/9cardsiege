package model.states;

import model.gameplay.GamePlay;

/**
 * @author ricardo
 */

public class AwaitForTunnelActionSelection extends StateAdapter{
      
    public AwaitForTunnelActionSelection(GamePlay gameplay) {
        super(gameplay);
    }
    
    @Override
    public IStates automaticTunnelMoveForward(){
        if(getGamePlay().isOneFreeMoveOnlyErrorflag())
            return new AwaitOptionSelection(getGamePlay());
        if(getGamePlay().getTunnelPosition() > 0 && getGamePlay().getTunnelPosition() < 3){
            getGamePlay().setTunnelPosition(getGamePlay().getTunnelPosition() + 1); 
            getGamePlay().setOneFreeMoveOnlyErrorflag(true);
            return new AwaitOptionSelection(getGamePlay());
        }
        getGamePlay().setErrorflag(true);        
        return new AwaitOptionSelection(getGamePlay());
    }
    
    @Override
    public IStates automaticTunnelMoveBack(){
        if(getGamePlay().isOneFreeMoveOnlyErrorflag())
            return new AwaitOptionSelection(getGamePlay());
        if(getGamePlay().getTunnelPosition() > 0 && getGamePlay().getTunnelPosition() < 3){
            getGamePlay().setTunnelPosition(getGamePlay().getTunnelPosition() - 1);
            getGamePlay().setOneFreeMoveOnlyErrorflag(true);
            return new AwaitOptionSelection(getGamePlay());
        }       
        getGamePlay().setErrorflag(true); 
        return new AwaitOptionSelection(getGamePlay());
    }
    
    @Override
    public IStates tunnelMoveForwardWithActionPoint(){
        if(getGamePlay().getTunnelPosition() == 0)            
            getGamePlay().setTunnelPosition(1);
        else if (getGamePlay().getTunnelPosition() == 1 || getGamePlay().getTunnelPosition() == 2)
            getGamePlay().setTunnelPosition(3);
        else{
            getGamePlay().setErrorflag(true);
            return new AwaitOptionSelection(getGamePlay());
        }
        getGamePlay().setActions(getGamePlay().getActions() - 1);
        return new AwaitOptionSelection(getGamePlay());
    }
    
    @Override
    public IStates tunnelMoveBackWithActionPoint(){
        if(getGamePlay().getTunnelPosition() == 3)            
            getGamePlay().setTunnelPosition(2);
        else if (getGamePlay().getTunnelPosition() == 1 || getGamePlay().getTunnelPosition() == 2)
            getGamePlay().setTunnelPosition(0);
        else{
            getGamePlay().setErrorflag(true);
            return new AwaitOptionSelection(getGamePlay());
        }
        getGamePlay().setActions(getGamePlay().getActions() - 1);
        return new AwaitOptionSelection(getGamePlay());
    }
}
