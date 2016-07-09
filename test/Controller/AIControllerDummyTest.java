/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2015
 *
 * Name: Justin Eyster
 * Date:
 *
 * Project: csci205_FinalProject
 * Package: Controller.AIControllerDummyTest
 * File: AIControllerDummyTest
 * Description:
 *
 * ****************************************
 */package Controller;

import TexasModel.AI;
import TexasModel.BadCardCreationException;
import TexasModel.Card;
import TexasModel.GameModel;
import static TexasModel.GameUtil.findTheBestfromsix;
import TexasModel.Hand;
import TexasModel.NoMoneyException;
import TexasModel.Player;
import TexasModel.SixCardHandException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author justi_000
 */
public class AIControllerDummyTest {

    public AI dummyAI0;
    public AI dummyAI1;
    public AI dummyAI2;
    public AI dummyAI3;
    public ArrayList<Player> playerList;
    public GameModel gameModel;
    public AIController aiControl0;
    public AIController aiControl1;
    public AIController aiControl2;
    public AIController aiControl3;
    public ArrayList<AIController> aiControlList;

    public AIControllerDummyTest() {
    }

    @Before
    public void setUp() throws SixCardHandException {
        dummyAI0 = new AI("0");
        dummyAI1 = new AI("1");
        dummyAI2 = new AI("2");
        dummyAI3 = new AI("3");
        playerList = new ArrayList<>();
        playerList.add(dummyAI0);
        playerList.add(dummyAI1);
        playerList.add(dummyAI2);
        playerList.add(dummyAI3);
        gameModel = new GameModel(1600.0, playerList);
        gameModel.giveCards();
        aiControl0 = new AIController(gameModel, dummyAI0);
        aiControl1 = new AIController(gameModel, dummyAI1);
        aiControl2 = new AIController(gameModel, dummyAI2);
        aiControl3 = new AIController(gameModel, dummyAI3);
        aiControlList = new ArrayList<>();
        aiControlList.add(aiControl0);
        aiControlList.add(aiControl1);
        aiControlList.add(aiControl2);
        aiControlList.add(aiControl3);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of performTurnAction method, of class AIControllerDummy.
     */
    @Test
    public void testPerformTurnAction() throws NoMoneyException, SixCardHandException {
        AI aiOnDeck = (AI) gameModel.getCurrentPlayer();
        //System.out.println("The AI on deck is: " + aiOnDeck.getName());
        AIController controlOnDeck = null;
        for (AIController aiControl : aiControlList) {
            //System.out.println("Controller of AI" + aiControl.getAi().getName());
            if (aiControl.hasThisAI(aiOnDeck)) {
                //System.out.println("Found the matching AI Controller.");
                controlOnDeck = aiControl;
            }
        }
        // Makes a decision. Updates AI on decision.
        controlOnDeck.performTurnAction();
        gameModel.getPlayerChoice();
    }

    /**
     * Test of performBlindAction method, of class AIControllerDummy.
     */
    @Test
    public void testPerformBlindAction() throws NoMoneyException, SixCardHandException {
        AI aiOnDeck = (AI) gameModel.getCurrentPlayer();
        //System.out.println("The AI on deck is: " + aiOnDeck.getName());
        AIController controlOnDeck = null;
        for (AIController aiControl : aiControlList) {
            //System.out.println("Controller of AI" + aiControl.getAi().getName());
            if (aiControl.hasThisAI(aiOnDeck)) {
                //System.out.println("Found the matching AI Controller.");
                controlOnDeck = aiControl;
            }
        }
        controlOnDeck.performTurnAction();
        assert gameModel.isAllCall() == true;
        assert aiOnDeck.isIsCall() == true;
        gameModel.getPlayerChoice();

        aiOnDeck = (AI) gameModel.getCurrentPlayer();
        //System.out.println("The AI on deck is: " + aiOnDeck.getName());
        for (AIController aiControl : aiControlList) {
            //System.out.println("Controller of AI" + aiControl.getAi().getName());
            if (aiControl.hasThisAI(aiOnDeck)) {
                //System.out.println("Found the matching AI Controller.");
                controlOnDeck = aiControl;
            }
        }
        controlOnDeck.performTurnAction();
        assert gameModel.isAllCall() == true;
        assert aiOnDeck.isIsCall() == true;
        gameModel.getPlayerChoice();

        aiOnDeck = (AI) gameModel.getCurrentPlayer();
        //System.out.println("The AI on deck is: " + aiOnDeck.getName());
        for (AIController aiControl : aiControlList) {
            //System.out.println("Controller of AI" + aiControl.getAi().getName());
            if (aiControl.hasThisAI(aiOnDeck)) {
                //System.out.println("Found the matching AI Controller.");
                controlOnDeck = aiControl;
            }
        }
        controlOnDeck.performTurnAction();
        assert gameModel.isAllCall() == true;
        assert aiOnDeck.isIsCall() == true;
        gameModel.getPlayerChoice();

        aiOnDeck = (AI) gameModel.getCurrentPlayer();
        //System.out.println("The AI on deck is: " + aiOnDeck.getName());
        for (AIController aiControl : aiControlList) {
            //System.out.println("Controller of AI" + aiControl.getAi().getName());
            if (aiControl.hasThisAI(aiOnDeck)) {
                //System.out.println("Found the matching AI Controller.");
                controlOnDeck = aiControl;
            }
        }
        controlOnDeck.performTurnAction();
        assert gameModel.isAllCall() == true;
        assert aiOnDeck.isIsCall() == true;
        gameModel.getPlayerChoice();
    }

    /**
     * Test of performFlopAction method, of class AIControllerDummy.
     */
    @Test
    public void testPerformFlopAction() throws NoMoneyException, SixCardHandException, BadCardCreationException {
        while (gameModel.isIsFlop() == false && gameModel.isIsEnd() == false) {
            AI aiOnDeck = (AI) gameModel.getCurrentPlayer();
            AIController controlOnDeck = null;
            //System.out.println("The AI on deck is: " + aiOnDeck.getName());
            for (AIController aiControl : aiControlList) {
                //System.out.println("Controller of AI" + aiControl.getAi().getName());
                if (aiControl.hasThisAI(aiOnDeck)) {
                    //System.out.println("Found the matching AI Controller.");
                    controlOnDeck = aiControl;
                }
            }
            controlOnDeck.performTurnAction();
            gameModel.getPlayerChoice();
        }

        assert gameModel.isIsFlop();

        // NOW WE ARE IN FLOP ROUND - WHERE IMPORTANT TESTS MUST OCCUR
        AI aiOnDeck = (AI) gameModel.getCurrentPlayer();
        AIController controlOnDeck = null;
        //System.out.println("The AI on deck is: " + aiOnDeck.getName());
        for (AIController aiControl : aiControlList) {
            //System.out.println("Controller of AI" + aiControl.getAi().getName());
            if (aiControl.hasThisAI(aiOnDeck)) {
                //System.out.println("Found the matching AI Controller.");
                controlOnDeck = aiControl;
            }
        }

        controlOnDeck.performTurnAction();
        for (Card c : gameModel.getPoolcards()) {
            aiOnDeck.getHand().addCard(c);
        }
        System.out.println("Hand rank is: " + controlOnDeck.getAi().getHand().getHandRank());
        System.out.println("Hand type is: " + controlOnDeck.getAi().getHand().getHandType().name());
        System.out.println("The controller scores this a: " + controlOnDeck.getCircumstantialRank());
        System.out.println("Decision made: " + controlOnDeck.getMostRecentDecision());

        for (Card c : dummyAI0.getHand().getHand()) {
            System.out.println("Flop hand card - Suite: " + c.getSuite().name() + ", Value: " + c.getValue());
        }

        for (Card c : gameModel.getPoolcards()) {
            aiOnDeck.getHand().removeCard(c);
        }

        gameModel.getPlayerChoice();
    }

    /**
     * Test of performTurnhandAction method, of class AIControllerDummy.
     */
    @Test
    public void testPerformTurnhandAction() throws NoMoneyException, SixCardHandException {
        while (gameModel.isIsTurnhand() == false && gameModel.isIsEnd() == false) {
            AI aiOnDeck = (AI) gameModel.getCurrentPlayer();
            AIController controlOnDeck = null;
            //System.out.println("The AI on deck is: " + aiOnDeck.getName());
            for (AIController aiControl : aiControlList) {
                //System.out.println("Controller of AI" + aiControl.getAi().getName());
                if (aiControl.hasThisAI(aiOnDeck)) {
                    //System.out.println("Found the matching AI Controller.");
                    controlOnDeck = aiControl;
                }
            }
            controlOnDeck.performTurnAction();
            gameModel.getPlayerChoice();
        }
        System.out.println("Is turnhand: " + gameModel.isIsTurnhand() + ". Game over: " + gameModel.isIsEnd());
        System.out.println("If game is over, this test will fail, and that's okay.");

        assert (gameModel.isIsTurnhand() || gameModel.isIsEnd());

        // NOW WE ARE IN TURNHAND ROUND - WHERE IMPORTANT TESTS MUST OCCUR
        AI aiOnDeck = (AI) gameModel.getCurrentPlayer();
        AIController controlOnDeck = null;
        //System.out.println("The AI on deck is: " + aiOnDeck.getName());
        for (AIController aiControl : aiControlList) {
            //System.out.println("Controller of AI" + aiControl.getAi().getName());
            if (aiControl.hasThisAI(aiOnDeck)) {
                //System.out.println("Found the matching AI Controller.");
                controlOnDeck = aiControl;
            }
        }

        controlOnDeck.performTurnAction();
        ArrayList<Card> sixCardList = new ArrayList<>();
        for (Card c : gameModel.getPoolcards()) {
            sixCardList.add(c);
        }
        for (Card c : aiOnDeck.getHand().getHand()) {
            sixCardList.add(c);
        }
        Hand originalHand = aiOnDeck.getHand();
        aiOnDeck.setHand(findTheBestfromsix(sixCardList));
        System.out.println("Hand rank is: " + controlOnDeck.getAi().getHand().getHandRank());
        System.out.println("Hand type is: " + controlOnDeck.getAi().getHand().getHandType().name());
        System.out.println("The controller scores this a: " + controlOnDeck.getCircumstantialRank());
        System.out.println("Decision made: " + controlOnDeck.getMostRecentDecision());

        for (Card c : dummyAI0.getHand().getHand()) {
            System.out.println("Turn hand card - Suite: " + c.getSuite().name() + ", Value: " + c.getValue());
        }

        aiOnDeck.setHand(originalHand);

        gameModel.getPlayerChoice();
    }

    /**
     * Test of performRiverhandAction method, of class AIControllerDummy.
     */
    @Test
    public void testPerformRiverhandAction() throws NoMoneyException, SixCardHandException {
        while (gameModel.isIsRiverhand() == false && gameModel.isIsEnd() == false) {
            AI aiOnDeck = (AI) gameModel.getCurrentPlayer();
            AIController controlOnDeck = null;
            //System.out.println("The AI on deck is: " + aiOnDeck.getName());
            for (AIController aiControl : aiControlList) {
                //System.out.println("Controller of AI" + aiControl.getAi().getName());
                if (aiControl.hasThisAI(aiOnDeck)) {
                    //System.out.println("Found the matching AI Controller.");
                    controlOnDeck = aiControl;
                }
            }
            controlOnDeck.performTurnAction();
            gameModel.getPlayerChoice();
        }
        System.out.println("Is riverhand: " + gameModel.isIsRiverhand() + ". Game over: " + gameModel.isIsEnd());

        assert (gameModel.isIsRiverhand() || gameModel.isIsEnd());
    }

}
