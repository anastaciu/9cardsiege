package model.states;

import model.gameplay.GamePlay;

/**
 * @author ricardo
 */

public class StateAdapter implements IStates{

    private final GamePlay gameplay;
    
    public StateAdapter(GamePlay gameplay){
        this.gameplay = gameplay;
    }
    
    public GamePlay getGamePlay(){
        return gameplay;
    }
    
    @Override
    public IStates start() {
        return this;
    }

    @Override
    public IStates finish() {
        return this;
    }

    @Override
    public IStates drawTopCard() {
        return this;
    }
    
    @Override
    public IStates drawNextCard(int dado) {
        return this;
    }
    
    @Override
    public IStates archerAttack() {
        return this;
    }

    @Override
    public IStates executeArcherAttack(int op, int dado) {
        return this;
    }
    
    @Override
    public IStates boilingWaterAttack() {
        return this;
    }

    @Override
    public IStates executeBoilingWaterAttack(int op, int dado){
        return this;
    }
    
    @Override
    public IStates closeCombat() {
        return this;
    }

    @Override
    public IStates tunnelMove() {
        return this;
    }

    @Override
    public IStates rallyTroops(int dado) {  
        return this;
    }

    @Override
    public IStates supplyRaid(int dado) {
        return this;
    }

    @Override
    public IStates sabotage(int dado) {
        return this;
    }

    @Override
    public IStates coupure(int dado) {
        return this;
    }

    @Override
    public IStates saveGame() {
        return this;
    }

    @Override
    public IStates checkGameOver() {
         return this;
    }

    @Override
    public IStates mainMenu() {
        return this;
    }   

    @Override
    public IStates automaticTunnelMoveForward() {
        return this;
    }

    @Override
    public IStates automaticTunnelMoveBack() {
        return this;
    }

    @Override
    public IStates tunnelMoveForwardWithActionPoint() {
        return this;
    }

    @Override
    public IStates tunnelMoveBackWithActionPoint() {
        return this;
    }

    @Override
    public IStates buyActionPoint() {
        return this;
    }

    @Override
    public IStates buyActionPointMorale() {
        return this;
    }

    @Override
    public IStates buyActionPointSupply() {
        return this;
    }

    @Override
    public IStates executeCloseCombat(int op, int dado) {
        return this;
    }

    @Override
    public IStates victory() {
        return this;
    }

    @Override
    public IStates loadGame() {
        return this;
    }
}
