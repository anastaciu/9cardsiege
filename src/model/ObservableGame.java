
package model;

import cards.event.EventCard;
import java.util.Observable;
import model.gameplay.GamePlay;
import model.states.IStates;


/** 
 * @author Ricardo
 *
 */

public class ObservableGame extends Observable
{
    Game game;
    
    public ObservableGame(){
        game = new Game();
    }

    public Game getGame(){
        return game;
    }

    public void setGame(Game game){        
        this.game = game;        
        setChanged();
        notifyObservers();
    }   
    
    public GamePlay getGameData() {
        return game.getGamePlay();
    }

    public IStates getState(){
        return game.getState();
    }        
    
     // Methods retrieve data from the game
    
    public EventCard getCurrentCard(){
        return game.getGamePlay().getCurrentCard();
    }
    
//    public int getNumPlayers()
//    {
//        return game.getNumPlayers();
//    }
//    
//    public Player getCurrentPlayer() 
//    {
//        return game.getCurrentPlayer();
//    }
//
//    public Player getNotCurrentPlayer() 
//    {
//        return game.getNotCurrentPlayer();
//    }
//    
//    public Player getPlayer1()
//    {
//        return game.getPlayer1();
//    }
//
//    public Player getPlayer2()
//    {
//        return game.getPlayer2();
//    }
//
//    public Token getToken(int line, int column) 
//    {
//        return game.getToken(line, column);
//    }
//    
//    public String grelhaToString()
//    {
//        return game.gridToString();
//    }
//
//    public int getNumCurrentPlayer()
//    {
//        return game.getNumCurrentPlayer();
//    }
//
//    public String getCurrentPlayerName()
//    {
//        return game.getCurrentPlayerName();
//    }
//    
//    public boolean isOver() 
//    {
//        return game.isOver();
//    }
//    
//    public boolean hasWon(Player player) 
//    {
//        return game.hasWon(player);
//    }
    
    // Methods that are intended to be used by the user interfaces and that are delegated in the current state of the finite state machine 
    
    public void start(){
        game.start();
        setChanged();
        notifyObservers();
    }
    
    public void loadGame(){
        game.loadGame();
        setChanged();
        notifyObservers();
    }
    
    public void saveGame(){
        game.saveGame();
        setChanged();
        notifyObservers();
    }
    
    public void drawTopCard(){
        game.drawTopCard();
        setChanged();
        notifyObservers();
    }
    
    public void drawNextCard(int dado){
        game.drawNextCard(dado);
        setChanged();
        notifyObservers();
    }

    public void archerAttack(){    
        game.archerAttack();
        setChanged();
        notifyObservers();
    }
    
    public void executeArcherAttack(int op, int dado){    
        game.executeArcherAttack(op, dado);
        setChanged();
        notifyObservers();
    }
    
    public void  boilingWaterAttack(){
        game.boilingWaterAttack();
        setChanged();
        notifyObservers();
    }
    
    public void  executeBoilingWaterAttack(int op, int dado){
        game.executeBoilingWaterAttack(op, dado);
        setChanged();
        notifyObservers();
    }
    
    public void closeCombat(){
        game.closeCombat();
        setChanged();
        notifyObservers();
    }
    
    public void tunnelMove(){
        game.tunnelMove();
        setChanged();
        notifyObservers();
    }
        
    public void finish(){
        game.finish();
        setChanged();
        notifyObservers();
    }
    
    public void rallyTroops(int dado){
        game.rallyTroops(dado);
        setChanged();
        notifyObservers();
    }
    
    public void supplyRaid(int dado){
        game.supplyRaid(dado);
        setChanged();
        notifyObservers();
    }
    
    public void sabotage(int dado){
        game.sabotage(dado);
        setChanged();
        notifyObservers();
    }
    
    public void coupure(int dado){
        game.coupure(dado);
        setChanged();
        notifyObservers();
    }
    
    public void mainMenu(){
        game.mainMenu();
        setChanged();
        notifyObservers();
    }    

    public void automaticTunnelMoveForward() {
        game.automaticTunnelMoveForward();
        setChanged();
        notifyObservers();
    }

    public void automaticTunnelMoveBack() {
        game.automaticTunnelMoveBack();
        setChanged();
        notifyObservers();
    }

    public void tunnelMoveForwardWithActionPoint() {
        game.tunnelMoveForwardWithActionPoint();
        setChanged();
        notifyObservers();
    }

    public void tunnelMoveBackWithActionPoint() {
        game.tunnelMoveBackWithActionPoint();
        setChanged();
        notifyObservers();
    }
    
    public void buyActionPoint() {
        game.buyActionPoint();
        setChanged();
        notifyObservers();
    }
    
    public void buyActionPointMorale(){
        game.buyActionPointMorale();
        setChanged();
        notifyObservers();       
    }
    
    public void buyActionPointSupply(){
        game.buyActionPointSupply(); 
        setChanged();
        notifyObservers();
    }
    
    public void checkGameOver(){
        game.checkGameOver(); 
        setChanged();
        notifyObservers();
    }
    public void executeCloseCombat(int op, int dado){
        game.executeCloseCombat(op, dado);  
        setChanged();
        notifyObservers();
    }
    
    public void victory(){
        game.victory(); 
        setChanged();
        notifyObservers();
    }

}
