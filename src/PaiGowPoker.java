/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paigowpoker;

import java.util.*;


/**
 *
 * @author drumboy
 */


public class PaiGowPoker {
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
       deck Deck = new deck();
       handEval handEval1 = new handEval();
       String finalDeck = new String();
       ArrayList<int[]> gameDeck = Deck.newDeck();
       gameDeck = Deck.shuffleDeck(gameDeck);
       
        for ( int[] gameDeck1 : gameDeck) {
            finalDeck = finalDeck + Deck.getCard(gameDeck1) + " ";
        }
       
       System.out.println("Deck: " + finalDeck);
       
       ArrayList<int[]> hand1 = Deck.hand(gameDeck);
       ArrayList<int[]> hand2 = Deck.hand(gameDeck);
       
        String hand1string = new String();
       String hand2string = new String();
       
      /* hand1.set(0,new int[] {3,1});
       hand1.set(1,new int[] {3,2});
       hand1.set(2,new int[] {3,3});
       hand1.set(3,new int[] {4,1});
       hand1.set(4,new int[] {4,2});
       hand1.set(5,new int[] {9,1});
       hand1.set(6,new int[] {9,2}); */
       
       for (int[] card1 : hand1) {
           hand1string = hand1string + Deck.getCard(card1) + " ";
       }
       
       for (int[] card2 : hand2) {
           hand2string = hand2string + Deck.getCard(card2) + " ";
       }
       
       int[] hand1eval;
       int[] hand2eval;
       
       
       
       
       System.out.println("Hand 1: " + hand1string);
       hand1eval = handEval1.handEval(hand1);
       System.out.println("Hand 2: " + hand2string);
       hand2eval = handEval1.handEval(hand2);
    }
    
    
    
   
}
    
   
   
       
