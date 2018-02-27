/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paigowpoker;

import java.util.*;
/**
 *
 * @author Matt Barnette
 */
class deck {
        int cardValue[] = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13};
        int suitValue[] = new int[]{1,2,3,4};
        public String cardArray[] = new String[]{"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        public String suitArray[] = new String[]{"C","S","D","H"};
        /*Creates a new unshuffled deck of cards.  The cards are returned as an 
        ArrayList of arrays, with each array contaiing a number representing a 
        card Value and a number representing a suit value
        */
        public ArrayList<int[]> newDeck() {
            ArrayList<int[]> deckArray = new ArrayList();
          
            for (int suitValue1 : suitValue) {
                for (int cardValue1 : cardValue) {
                    int[] card = new int[]{cardValue1,suitValue1};
                    deckArray.add(card);
                }
            }        
            return deckArray;
        }
        /*Shuffles the deck between 20 and 30 times and by using the ArrayList
        Collections.shuffle class. Returns an ArrayList of Arrays that makes up
        the newly shuffled deck.  Each array in the arraylist contains a number 
        to determine card value and a number to determine suit value
        */
        public ArrayList<int[]> shuffleDeck(ArrayList<int[]> deck){
            Random rand = new Random();
            int numOfShuffles = rand.nextInt(30) + 10;
            
            for (int i = 0; i < numOfShuffles; i++) {
                Collections.shuffle(deck);
            }
            return deck;
        }
        /* This mehtod takes the numbers of a specific card that represent 
        the card value and suit value and returns the actual card value and 
        suit value of each card. This is mostly for visual purposes for the user.
        */
        public String getCard(int[] card) {
            String cardType = new String();
            cardType = cardArray[card[0]-1] + suitArray[card[1]-1];
            return cardType;
        }
        
        /*This method deals out a hand of 7 cards from a deck. in Pai Gow poker 
        a player gets the next 7 cards off the top of the deck so it is set to 
        draw the hands in this fashion.
        */
        public ArrayList hand(ArrayList deck){
            ArrayList newHand = new ArrayList();
            int cardsPerHand = 7;
            
            for(int i=0; i<cardsPerHand; i++) {
                Object newCard = deck.remove(0);
                newHand.add(newCard);
            } 
            return newHand;
        }
    }

