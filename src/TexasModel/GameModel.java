/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2015
 *
 * Name: Jiayu Huang
 * Date: April 7, 2015
 * Time: 5:30:00 PM
 *
 * Project: csci205
 * Package: CS205Final.TexasModel
 * File: GameModel
 * Description:
 * The basic game logic class for Texas Poker
 * ****************************************
 */
package TexasModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A Main Model Class for Texas Holdem Game
 *
 * @author huangjiayu
 */
/**
 * ReadMe: How to use this model To start the game: Use constructor to new a
 * Game To add player: Use addPlayer method To process the game: First: let
 * player choose what to do, Then Call method: getPlayerChoice. Model will do
 * anything else for you
 *
 * How it works: Get player choice will get current player's choice and modify
 * needed information then, it will automatically process to next player. While
 * all players did something, It will automatically process to next round. When
 * the game ends, It will automatically Checkwin and ends game.
 *
 * @author huangjiayu
 */
public class GameModel {

    private Deck theDeck;
    private ArrayList<Player> players;

    public LinkedList<Player> getPlayerthisRound() {
        return playerthisRound;
    }
    private LinkedList<Player> playerinGame;
    private LinkedList<Player> playerthisRound;
    private double moneypool;
    private boolean isBlind;
    private boolean isTurnhand;
    private boolean isRiverhand;
    private boolean isEnd;
    private ArrayList<Card> poolcards;
    public double callAmount;
    private Player currentPlayer;
    private boolean isFlop;

    /**
     * This is a constructor for GameModel, It will construct the game model
     * with several players. While AI is finished, we can change it to AI
     *
     * @param moneypool
     * @param numplayer
     */
    public GameModel(double moneypool, int numplayer) {
        this.theDeck = new Deck();
        this.players = new ArrayList<>();
        this.moneypool = moneypool;
        this.poolcards = new ArrayList<>();
        this.isBlind = true; //If the Game is in Blind Stage(without three card
        //shown
        this.isTurnhand = false;//If the Game is in Turn Hand Stage
        this.isRiverhand = false; //If the Game is in RiverHand Stage
        this.isFlop = false;
        this.isEnd = false;
        this.poolcards.add(this.theDeck.drawRandomCard());
        this.poolcards.add(this.theDeck.drawRandomCard());
        this.poolcards.add(this.theDeck.drawRandomCard());
        this.callAmount = 0;
        for (int i = 0; i < numplayer; i++) {
            this.players.add(new Player());
        }

        this.playerinGame = new LinkedList<>();
        this.playerinGame.addAll(players);// The Player that is still in this game
        this.playerthisRound = new LinkedList<>();
        this.playerthisRound.addAll(playerinGame);//The Player left in this ROUND That is a player moves one by one system
        this.currentPlayer = this.playerthisRound.pop();
    }

    /**
     * It will construct a game with several Players in an arraylist
     *
     * @param moneypool
     * @param play
     */
    public GameModel(double moneypool, ArrayList<Player> play) {
        this.theDeck = new Deck();
        this.players = play;
        this.moneypool = moneypool;
        this.poolcards = new ArrayList<Card>();
        this.isBlind = true; //If the Game is in Blind Stage(without three card
        //shown
        this.isTurnhand = false;//If the Game is in Turn Hand Stage
        this.isRiverhand = false; //If the Game is in RiverHand Stage
        this.isFlop = false;
        this.isEnd = false;
        this.poolcards.add(this.theDeck.drawRandomCard());
        this.poolcards.add(this.theDeck.drawRandomCard());
        this.poolcards.add(this.theDeck.drawRandomCard());
        this.callAmount = 0; //Initialize the Call Amount =50, So that it assures
        //the Game will have some money. We might change it to 0.
        this.playerinGame = new LinkedList<Player>();
        this.playerinGame.addAll(players);// The Player that is still in this game
        this.playerthisRound = new LinkedList<Player>();
        this.playerthisRound.addAll(playerinGame);//The Player left in this ROUND That is a player moves one by one system
        this.currentPlayer = this.playerthisRound.pop();
    }

    /**
     * Another Constructor of GameModel to Help the GameModelTest & For Future
     * Use, if networking is needed
     *
     * @param moneypool
     * @throws SixCardHandException
     */
    public GameModel(double moneypool) throws SixCardHandException {
        this.theDeck = new Deck();
        this.players = new ArrayList<Player>();
        this.players.add(new Player("new Player"));
        this.players.add(new Player("new Playe1"));
        this.moneypool = moneypool;
        this.poolcards = new ArrayList<Card>();
        this.isBlind = true; //If the Game is in Blind Stage(without three card
        //shown
        this.isTurnhand = false;//If the Game is in Turn Hand Stage
        this.isRiverhand = false; //If the Game is in RiverHand Stage
        this.isFlop = false;
        this.isEnd = false;
        this.poolcards.add(this.theDeck.drawRandomCard());
        this.poolcards.add(this.theDeck.drawRandomCard());
        this.poolcards.add(this.theDeck.drawRandomCard());
        this.callAmount = 0; //Initialize the Call Amount =50, So that it assures
        //the Game will have some money. We might change it to 0.
        this.playerinGame = new LinkedList<Player>();
        this.playerinGame.addAll(players);// The Player that is still in this game
        this.playerthisRound = new LinkedList<Player>();
        this.playerthisRound.addAll(playerinGame);//The Player left in this ROUND That is a player moves one by one system
        this.currentPlayer = this.playerthisRound.pop();
        this.giveCards();
    }

    /**
     * This is a method to make every player in this Game has two cards
     *
     * @throws SixCardHandException
     */
    public void giveCards() throws SixCardHandException {
        for (Player p : this.playerinGame) {
            p.setHand(this.theDeck);
        }
    }

    /**
     * Returns the Current Player making decision
     *
     * @return Player
     */
    public Player getCurrentPlayer() {

        return currentPlayer;
    }

    /**
     * This is the method to return players in this game
     *
     * @return
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * To see if the game is in flop round
     *
     * @return
     */
    public boolean isIsFlop() {
        return isFlop;
    }

    /**
     * This is the Listener in the Model to make sure the game moves on turn by
     * turn. It is a very IMPORTANT method. The game steps forward with this
     *
     * @throws NoMoneyException
     * @throws SixCardHandException
     */
    public void getPlayerChoice() throws NoMoneyException, SixCardHandException {
        if (!this.isEnd) {
            if (this.currentPlayer.isIsAllin()) {
                this.check();
            } else if (this.currentPlayer.getAction() == Action.CALL) {
                this.call();
            } else if (this.currentPlayer.getAction() == Action.ALL_IN) {
                this.allIn();
            } else if (this.currentPlayer.getAction() == Action.RAISE) {
                this.raise(this.getCurrentPlayer().getRaiseamount());
            } else if (this.currentPlayer.getAction() == Action.FOLD) {
                this.fold();
            } else if (this.currentPlayer.getAction() == Action.CHECK) {
                this.check();
            }
        }
    }

    /**
     * Next Player
     *
     * @throws SixCardHandException
     * @throws NoMoneyException
     */
    public void nextPlayer() throws SixCardHandException, NoMoneyException {
        if (this.playerthisRound.size() == 0) {
            nextTurn();
        } else {
            this.currentPlayer = this.playerthisRound.pop();
        }
    }

    /**
     * If all of the players already call the right amount of money
     *
     * @return
     */
    public boolean isAllCall() {
        for (Player p : playerinGame) {
            if (!p.isIsCall()) {
                if (!p.isIsAllin()) {
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * Add a Player to this Game
     *
     * @param a
     */
    public void addPlayer(Player a) {
        this.players.add(a);
        this.playerinGame.add(a);
        this.playerthisRound.add(a);
    }

    /**
     * Next Turn, Means Next Round More precisely
     *
     * @throws SixCardHandException
     */
    public void nextTurn() throws SixCardHandException {
        if (this.playerinGame.size() == 1) {
            checkWin();
        } else if (this.isRiverhand && this.isAllCall()) {
            checkWin();
        } else {

            if (this.isTurnhand == true && this.isAllCall()) {
                this.isTurnhand = false;
                this.isRiverhand = true;
                this.callAmount = 0;
                this.poolcards.add(this.theDeck.drawRandomCard());
            } else if (this.isAllCall() && this.isFlop == true) {
                this.isFlop = false;
                this.isTurnhand = true;
                this.callAmount = 0;
                this.poolcards.add(this.theDeck.drawRandomCard());
            } else if (this.isBlind == true && this.isAllCall()) {
                this.isBlind = false;
                this.isFlop = true;
                this.callAmount = 0;
            }

            this.playerthisRound.addAll(this.playerinGame);
            this.currentPlayer = this.playerthisRound.pop();
        }
    }

    /**
     * A method to reset the pool
     */
    public void resetpool() {
        this.theDeck.resetDeck();
        this.poolcards.clear();
        this.poolcards.add(this.theDeck.drawRandomCard());
        this.poolcards.add(this.theDeck.drawRandomCard());
        this.poolcards.add(this.theDeck.drawRandomCard());
    }

    /**
     * A method to reset to make the game restart with the same players
     *
     */
    public void reset() {
        this.resetpool();
        this.moneypool = 0;
        this.isEnd = false;
        this.isFlop = false;
        this.isTurnhand = false;
        this.isRiverhand = false;
        this.isBlind = true;

        this.playerinGame.addAll(this.players);
        this.playerthisRound.addAll(playerinGame);
        this.currentPlayer = this.playerthisRound.pop();
        for (int i = 0; i < this.players.size(); i++) {
            if (this.players.get(i).getMoney() <= 0) {
                this.players.remove(i);
            }
        }
        for (int i = 0; i < this.players.size(); i++) {
            this.players.get(i).reset();
        }

    }

    /**
     * To see if everything is end. That is the check win phase
     *
     * @return
     */
    public boolean isIsEnd() {
        return isEnd;
    }

    /**
     * Check who wins the game. This is very hardcoded beautiful class
     *
     * @throws SixCardHandException
     */
    public void checkWin() throws SixCardHandException {
        this.isEnd = true;
        if (this.playerinGame.size() == 1) {
            this.playerinGame.getFirst().setIsWin(true);
            this.playerinGame.getFirst().setMoney(moneypool + this.playerinGame.
                    getFirst().getMoney());
        } else {
            ArrayList<Hand> handlist = new ArrayList<>();
            for (Player p : playerinGame) {
                ArrayList<Card> h = p.getHand().getHand();
                h.add(this.poolcards.get(0));
                h.add(this.poolcards.get(1));
                h.add(this.poolcards.get(2));
                h.add(this.poolcards.get(3));
                h.add(this.poolcards.get(4));
                p.setHand(GameUtil.findTheBest(h));
            }
            playerinGame.sort(new Player());
            ArrayList<Player> temp = new ArrayList<>();
            temp.addAll(playerinGame);
            int tie = checkTie();
            if (tie > 1) {
                for (int i = 0; i < tie; i++) {
                    temp.get(i).addMoney(moneypool / tie);
                    temp.get(i).setIsWin(true);
                }
            } else {
                playerinGame.pop().addMoney(moneypool);
                temp.get(0).setIsWin(true);
            }
        }

        this.moneypool = 0;

    }

    /**
     * Check how many ties exist
     *
     * @return
     */
    public int checkTie() {
        LinkedList<Player> temp = playerinGame;
        int tienumber = 1;
        while (temp.size() > 1) {
            if (temp.getFirst().getHand().compareTo(temp.get(1).getHand()) != 0) {
                return tienumber;
            } else {
                temp.removeFirst();
                tienumber++;
            }
        }
        return tienumber;
    }

    /**
     * Get the Deck of the Model
     *
     * @return
     */
    public Deck getTheDeck() {
        return theDeck;
    }

    /**
     * Set the PoolCards in the game
     *
     * @param poolcards
     */
    public void setPoolcards(ArrayList<Card> poolcards) {
        this.poolcards = poolcards;
    }

    /**
     * Set the money pool
     *
     * @param moneypool
     */
    public void setMoneypool(double moneypool) {
        this.moneypool = moneypool;
    }

    /**
     *
     * @return Double moneypool
     */
    public double getMoneypool() {
        return moneypool;
    }

    /**
     * Let the current player fold.
     *
     * @throws SixCardHandException
     * @throws NoMoneyException
     */
    public void fold() throws SixCardHandException, NoMoneyException {
        this.currentPlayer.setActionperformed("Fold");
        this.playerinGame.remove(this.currentPlayer);
        this.getCurrentPlayer().setAction(Action.BLANK);
        if (this.playerinGame.size() == 1) {
            checkWin();
        }
        nextPlayer();

    }

    /**
     * Let the current player all in
     *
     * @throws NoMoneyException
     * @throws SixCardHandException
     */
    public void allIn() throws NoMoneyException, SixCardHandException {
        if (this.getCurrentPlayer().getMoney() == 0) {
            throw new NoMoneyException("You don't have money at all");
        }
        if (this.getCurrentPlayer().isIsAllin()) {
            this.check();
        } else {
            this.currentPlayer.setActionperformed("ALLIN");
            double moneyallin = this.getCurrentPlayer().getMoney();
            this.getCurrentPlayer().setMoney(0);
            if (moneyallin > this.callAmount) {
                this.callAmount = moneyallin;
                for (Player p : playerinGame) {
                    p.setIsCall(false);
                }
            }
            this.moneypool += moneyallin;
            this.getCurrentPlayer().setIsAllin(true);
            this.getCurrentPlayer().setIsCall(true);
            this.getCurrentPlayer().setAction(Action.BLANK);
            nextPlayer();

        }
    }

    /**
     * Check if the Game is in Blind phase
     *
     * @return
     */
    public boolean isIsBlind() {
        return isBlind;
    }

    /**
     * Check if the Game is in Turn phase
     *
     * @return
     */
    public boolean isIsTurnhand() {
        return isTurnhand;
    }

    /**
     * Check if the Game is in River Hand phase
     *
     * @return
     */
    public boolean isIsRiverhand() {
        return isRiverhand;
    }

    /**
     * Return the pool cards in the game
     *
     * @return
     */
    public ArrayList<Card> getPoolcards() {
        return poolcards;
    }

    /**
     * Return the called amount(bet amount)
     *
     * @return
     */
    public double getCallAmount() {
        return callAmount;
    }

    /**
     * Make the Current Player Raise Money
     *
     * @param amount
     * @throws NoMoneyException
     * @throws SixCardHandException
     */
    public void raise(double amount) throws NoMoneyException, SixCardHandException {
//        if (amount <= this.callAmount) {
//            throw new CallMoreException("You need to bet more!");
//        }
        if (this.getCurrentPlayer().getMoney() < amount) {
            throw new NoMoneyException("You don't have enough money to raise!");
        }
        if ((int) this.getCurrentPlayer().getMoney() == amount) {
            this.getCurrentPlayer().setIsAllin(true);
        }
        NumberFormat formatter = new DecimalFormat("#0.00");
        this.currentPlayer.setActionperformed("RAISED " + formatter.format(amount) + " $$");
//        this.callAmount = amount;
//        this.moneypool += amount;
        this.callAmount = this.callAmount + amount;
        this.moneypool = this.moneypool + this.callAmount;
        this.getCurrentPlayer().setMoney(this.getCurrentPlayer().getMoney() - this.callAmount);
        for (Player p : playerinGame) {
            p.setIsCall(false);
        }
        this.currentPlayer.resetRaise();
        this.getCurrentPlayer().setIsCall(true);
        this.getCurrentPlayer().setAction(Action.BLANK);

        nextPlayer();
    }

    /**
     * Make the current player call
     *
     * @throws NoMoneyException
     * @throws SixCardHandException
     */
    public void call() throws NoMoneyException, SixCardHandException {
        if (this.callAmount == 0) {
            this.check();
        } else if (this.currentPlayer.getMoney() == this.callAmount) {
            this.allIn();
        } else if (this.getCurrentPlayer().getMoney() < this.callAmount) {
            throw new NoMoneyException("You don't have enough money to call!");
        } else {

            if (this.currentPlayer.isIsCall()) {
                check();
            } else {
                this.currentPlayer.setActionperformed("Call!");
                this.getCurrentPlayer().setMoney(this.getCurrentPlayer().getMoney() - this.callAmount);
                this.moneypool += this.callAmount;
                this.getCurrentPlayer().setIsCall(true);
                this.getCurrentPlayer().setAction(Action.BLANK);
                nextPlayer();

            }
        }
    }

    /**
     * Make the current player check
     *
     * @throws SixCardHandException
     * @throws NoMoneyException
     */
    public void check() throws SixCardHandException, NoMoneyException {
        this.currentPlayer.setActionperformed("Check");
        if (this.getCurrentPlayer().isIsCall() || this.getCurrentPlayer().isIsAllin()) {
            this.getCurrentPlayer().setAction(Action.BLANK);
            nextPlayer();
        } else {
            this.getCurrentPlayer().setAction(Action.BLANK);
        }
    }

}
