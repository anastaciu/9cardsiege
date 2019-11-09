package model;

import java.io.Serializable;
import model.gameplay.GamePlay;
import model.states.*;

/*
 * @author ricardo
 */

public class Game implements Serializable{
    private GamePlay gamePlay;
    private IStates state;
    
    public Game(){
        gamePlay = new GamePlay();
        state = new AwaitBeginning(gamePlay);
    }

    public GamePlay getGamePlay(){
        return gamePlay;
    }

    public void setGamePlay(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
    }

    public IStates getState() {
        return state;
    }

    public void setState(IStates state) {
        this.state = state;
    }    
    
    public void newGame(){
        
    }
    
//    
//    //--------------------- Methods that trigger events/actions in the finite state machine  -----------------------
//   
    
    public void start(){
        setState(getState().start());
    }
    
    public void loadGame(){
        setState(getState().loadGame());
    }
    
    public void saveGame(){
        setState(getState().saveGame());
    }
    
    public void drawTopCard(){
        setState(getState().drawTopCard());
    }
    
    public void drawNextCard(int dado){
        setState(getState().drawNextCard(dado));
    }

    public void archerAttack(){    
        setState(getState().archerAttack());
    }
    
    public void executeArcherAttack(int op, int dado){    
        setState(getState().executeArcherAttack(op, dado));
    }
    
    public void  boilingWaterAttack(){
        setState(getState().boilingWaterAttack());
    }
    
    public void  executeBoilingWaterAttack(int op, int dado){
        setState(getState().executeBoilingWaterAttack(op, dado));
    }
    
    public void closeCombat(){
        setState(getState().closeCombat());
    }
    
    public void tunnelMove(){
        setState(getState().tunnelMove());
    }
        
    public void finish(){
        setState(getState().finish());
    }
    
    public void rallyTroops(int dado){
        setState(getState().rallyTroops(dado));
    }
    
    public void supplyRaid(int dado){
        setState(getState().supplyRaid(dado));
    }
    
    public void sabotage(int dado){
        setState(getState().sabotage(dado));
    }
    
    public void coupure(int dado){
        setState(getState().coupure(dado));
    }
    
    public void mainMenu(){
        setState(getState().mainMenu());
    }    

    public void automaticTunnelMoveForward() {
        setState(getState().automaticTunnelMoveForward());
    }

    public void automaticTunnelMoveBack() {
        setState(getState().automaticTunnelMoveBack());
    }

    public void tunnelMoveForwardWithActionPoint() {
        setState(getState().tunnelMoveForwardWithActionPoint());
    }

    public void tunnelMoveBackWithActionPoint() {
       setState(getState().tunnelMoveBackWithActionPoint());
    }
    
    public void buyActionPoint() {
       setState(getState().buyActionPoint());       
    }
    
    public void buyActionPointMorale(){
       setState(getState().buyActionPointMorale());       
    }
    
    public void buyActionPointSupply(){
       setState(getState().buyActionPointSupply());       
    }
    
    public void checkGameOver(){
       setState(getState().checkGameOver());       
    }
    public void executeCloseCombat(int op, int dado){
       setState(getState().executeCloseCombat(op, dado));       
    }
    
    public void victory(){
       setState(getState().victory());       
    }
    
}
