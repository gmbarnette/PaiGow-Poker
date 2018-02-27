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
       PaiGowPoker newGame = new PaiGowPoker();
       PaiGowPoker.deck Deck = newGame.new deck();
       PaiGowPoker.handEval handEval1 = newGame.new handEval();
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
    
    class deck{
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
    
    /*The class is used to determined to Evaluate a hand.  It ranks all possible 
    hands the 7 cards could produce and splits the 7 cards into a 5 card high 
    hand and a 2 card low hand.  It then passes these two hands along with their
    hand rankings back to the main program
    */
    public class handEval{
       /*The main hand evaluation method */
       public int[] handEval(ArrayList<int[]> hand) {
          int[] handResult = new int[2]; 
          int[] cardValues = new int[13];
          int[] suitValues = new int[4];
           
          for(int[] card1 : hand) {
              cardValues[card1[0]-1] = cardValues[card1[0]-1] + 1;    
          }
          
          for(int[] suit1: hand) {
              suitValues[suit1[1]-1] = suitValues[suit1[1] - 1] + 1;
          }
         
          ArrayList<Integer> pairResult = evalPairs(cardValues);
          ArrayList<Integer> tripsResult = evalTrips(cardValues);
          ArrayList<Integer> flushResult = new ArrayList();
          ArrayList<Integer> straightResult = evalStraight(cardValues);
          ArrayList<Integer> FHResult = new ArrayList();
          ArrayList<Integer> straightFlushResult = new ArrayList();
          
          if(pairResult.get(0) > 0 && tripsResult.get(0) > 0) {
              FHResult = evalFH(pairResult,tripsResult); 
          }
          else {FHResult.add(0);}
          if(suitValues[0] > 4 || suitValues[1] > 4 || suitValues[2] > 4 || 
            suitValues[3] > 4) {
             flushResult = evalFlush(suitValues,hand);
         }
          else {
              flushResult.add(0);
          }
          
          if(flushResult.get(0) > 0 && straightResult.get(0) > 0) {
              straightFlushResult = evalStraightFlush(flushResult);
          }
          else {straightFlushResult.add(0);}
          String pairs = new String();
          String trips = new String();
          String flush = new String();
          String straight = new String();
          String SF = new String();
          String FH = new String();
          
          if(flushResult.get(0) > 0) {
              for(int card: flushResult) {
                  flush = flush + card + " ";
              }
          }
          if (pairResult.get(0) > 0) {
            pairs = pairResult.get(0) + " ";  
            for(int i = 1; i < pairResult.size(); i++) {
              pairs = pairs + pairResult.get(i) + " ";
            }
          }
          if (tripsResult.get(0) > 0) {
              trips = tripsResult.get(0) + " ";
              for(int i = 1; i <tripsResult.size(); i++) {
                  trips = trips + tripsResult.get(i) + " ";
              }
          }
          if(straightResult.get(0) > 0) {
              straight= "Straight: ";
              for(int i = 0; i < straightResult.size(); i++) {
                  straight = straight + straightResult.get(i) + " ";
              } 
          } 
          if(straightFlushResult.get(0) > 0) {
              SF = "Straight Flush: ";
              for(int i = 0; i < straightFlushResult.size(); i++) {
                  SF = SF + straightResult.get(i) + " ";
              }
          }
          if(FHResult.get(0) > 0) {
              FH = "FULL HOUSE: ";
              for(int i = 0; i < FHResult.size(); i++) {
                  FH = FH + FHResult.get(i) + " ";
              }
          }
          if(pairResult.get(0)>0){
             System.out.println(pairs);
          }
          if(tripsResult.get(0) > 0) {
              System.out.println(trips);
          }
          if(flushResult.get(0) > 0) {
              System.out.println(flush);
          }
          if(straightResult.get(0) > 0) {
              System.out.println(straight);
          }
          if(straightFlushResult.get(0) > 0) {
              System.out.println(SF);
          }
          if(FHResult.get(0) > 0) {
              System.out.println(FH);
          }
          return handResult;
       }
       /* Evaluates if and how many pairs are present in a 7 card hand and 
       returns the number of pairs and the values of the pairs.  Does not
       take suit into account.
       */
       public ArrayList<Integer> evalPairs(int[] cardValues) {
         ArrayList<Integer> pairs = new ArrayList();
         int arrayCounter = 0;
         pairs.add(0);
         for (int i = 0; i < cardValues.length; i++) {
             if (cardValues[i] == 2) {
                 pairs.set(0,pairs.get(0) + 1);
                 pairs.add(i+1);
             }
         }
         return pairs;
       }
       
       /*Evaluates wheter or not three of a kind is present in the hand.  Returns
       how many three of a kinds are present and their values.
       */
       public ArrayList<Integer> evalTrips(int[] cardValues) {
           ArrayList<Integer> trips = new ArrayList();
           trips.add(0);
           for(int i = 0; i < cardValues.length; i++) {
               if ( cardValues[i]== 3) {
                   trips.set(0, trips.get(0) + 1);
                   trips.add(i+1);      
               }
           }
           return trips;
       }
      
        /*Evaluates whether or not a Flush is present.  If there is a flush, 
       returns all card values that could be part of the flush.  In a 7 card hand
       if all 7 cards are of the same suit, it will return the values of all 7
       cards.
       */
       public ArrayList<Integer> evalFlush(int[] suitValues,ArrayList<int[]> hand) {
          int[] heartValues = new int[13];
          int[] diamondValues = new int[13];
          int[] spadeValues = new int[13];
          int[] clubValues = new int[13];
          ArrayList<Integer> flush = new ArrayList();
          flush.add(0);
           
           if(suitValues[0] > 4) {
              for(int[] card1 : hand) {
                  if(card1[1] == 1) {
                    clubValues[card1[0]-1] = clubValues[card1[0]-1] + 1;
                  }
              }
              flush.set(0,1);
              for(int i = 0; i < clubValues.length; i++) {
                  if(clubValues[i] > 0) {
                      flush.add(i+1);
                  }
              }
           }
           if(suitValues[1] > 4) {
              for(int[] card1 : hand) {
                  if(card1[1] == 2) {
                      spadeValues[card1[0]-1] = spadeValues[card1[0]-1] + 1;
                  }
              } 
              flush.set(0,2);
              for(int i = 0; i < spadeValues.length; i ++) {
                  if(spadeValues[i] > 0) {
                      flush.add(i+1);
                  }
              }
           }
           if(suitValues[2] > 4) {
              for(int[] card1 : hand) {
                  if(card1[1] == 3){
                      diamondValues[card1[0]-1] = diamondValues[card1[0]-1] + 1;
                  }
              } 
              flush.set(0,3);
              for(int i = 0; i < diamondValues.length; i++) {
                  if(diamondValues[i] > 0) {
                      flush.add(i+1);
                  }
              }
           }
           if(suitValues[3] > 4) {
              for(int[] card1 : hand) {
                  if(card1[1] == 4) {
                      heartValues[card1[0]-1] = heartValues[card1[0]-1] + 1;
                  }
              } 
              flush.set(0,4);
              for(int i = 0; i < heartValues.length; i++) {
                  if(heartValues[i] > 0 ) {
                      flush.add(i+1);
                  }
              }
           }
           return flush;
       }
       
       /*Evaluates whether or not a straight of 5 or more cards is present
       in a hand
       */
       
       public ArrayList<Integer> evalStraight(int[] cardValues) {
           ArrayList<Integer> straight = new ArrayList();
           straight.add(0);
           
           for(int i = 0; i < 10; i++) {          
               if(( i < 9 && cardValues[i] > 0 && cardValues[i+1] > 0 && cardValues[i+2] > 0 && cardValues[i+3] > 0 && cardValues[i+4] > 0) || 
                   (i == 9 && cardValues[i] > 0 && cardValues[i+1] > 0 && cardValues[i+2] > 0 && cardValues[i+3] > 0 && cardValues[0] > 0)) {
                   
                   for(int x=i; x < 13; x++) {
                       if(cardValues[x] == 0) {
                           break;
                       }
                       straight.add(x+1);
                       if(x == 12 && cardValues[x] > 0) {
                           if(cardValues[0] > 0) {
                               straight.add(1);
                           }
                       }
                   }
                   if(straight.size() > 1) {
                       straight.set(0, straight.get(straight.size()-1));
                       break;
                   }   
                }
               
               }
              
           return straight;
       }
       
       /*Will confirm a fullHouse but only return the values of the full house if
       there are also 2 pairs because otherwise the full house will be split up into
       3 of a kind and a pair
       */
       public ArrayList<Integer> evalFH(ArrayList<Integer> pairs, ArrayList<Integer> trips) {
            ArrayList<Integer> fullHouse = new ArrayList();
            fullHouse.add(0);
            //*Sets the first position to 1 indicating that there is a fullHouse */
            fullHouse.set(0,1);
                
                /*Returns the Value of the Full house only if there are also 2 pairs, otherwise
                  the full house will be broken up into 3 of a kind and a pair
                */
                if(pairs.get(0)> 1) {
                    fullHouse.add(trips.get(trips.size()-1));
                    fullHouse.add(pairs.get(1));
                }
           return fullHouse;
       }
       /*Will evaluate wheter a hand contains a straightflush.  This method is 
       only called if both a straight and a flush have been previously found
       within a hand.  The flush cards are then passsed to this method and this 
       method passes them on to the straight method to determine if a straight 
       is present within the flush cards.
       */
       public ArrayList<Integer> evalStraightFlush(ArrayList<Integer> cards) {
           ArrayList<Integer> straightFlush = new ArrayList();
           int[] cardsForEval = new int[13];
           for(int i = 1; i < cards.size(); i++) {
               cardsForEval[cards.get(i)-1]=cardsForEval[cards.get(i)-1] + 1;
           }
           straightFlush = evalStraight(cardsForEval);
           
           
           return straightFlush;
       }
       
       /* This functions takes in the ArrayLists from the Hand evalusator that
       evaluated the hands potential for each different kind od hand and splits
       them into a high hand and a low hand.
       */
       
       public ArrayList<int[]> splitHand(ArrayList<Integer> pairs, ArrayList<Integer> trips,
                                         ArrayList<Integer> fullHouse, ArrayList<Integer> Straight,
                                         ArrayList<Integer> flush, ArrayList<Integer> straightFlush,
                                         ArrayList<int[]> hand) {
           ArrayList<int[]> splitHand = new ArrayList();
           int[] highHand = new int[5];
           int[] lowHand = new int[2];
           
           if(straightFlush.get(1) > 0) {
               if(pairs.get(1) >0) {
                   for(int i = 0; i < pairs.size(); i++) {
                       
                   }
               }
           }
           
           splitHand.add(highHand);
           splitHand.add(lowHand);
           return splitHand;
           
       }
    }
}