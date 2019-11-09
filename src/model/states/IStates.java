package model.states;

import java.io.Serializable;

/**
 * @author ricardo
 */

public interface IStates extends Serializable{
    IStates start();
    IStates drawTopCard();
    IStates drawNextCard(int dado);
    IStates loadGame();
    IStates saveGame();
    IStates finish();
    IStates archerAttack();
    IStates executeArcherAttack(int op, int dado);
    IStates boilingWaterAttack();
    IStates executeBoilingWaterAttack(int op, int dado);
    IStates closeCombat();
    IStates tunnelMove();
    IStates rallyTroops(int dado);
    IStates supplyRaid(int dado);
    IStates sabotage(int dado);
    IStates coupure(int dado);
    IStates checkGameOver();    
    IStates automaticTunnelMoveForward();
    IStates automaticTunnelMoveBack();
    IStates tunnelMoveForwardWithActionPoint();
    IStates tunnelMoveBackWithActionPoint();
    IStates buyActionPoint();
    IStates buyActionPointMorale();
    IStates buyActionPointSupply();
    IStates executeCloseCombat(int op, int dado);
    IStates mainMenu();
    IStates victory();
}