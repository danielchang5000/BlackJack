import java.util.Scanner;

public class twentyoneproject {

public static int NUM_CARDS = 52;
public static char[] suit = {'H', 'S', 'D', 'C'};
public static char[] type = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
public static int[] value = {2,3,4,5,6,7,8,9,10,10,10,10,11};

/**
 * Should permute the deck of cards, effectively shuffling it.
 * You must use the Fisher-Yates / Durstenfeld shuffle algorithm
 *  described in the assignment writeup.
 */
public static void shuffle(int cards[]) {
    for (int i = 51; i>=0; i--) {
        int temp; 
        
       // int j = random()%(i+1); 
        int j = (int)(Math.random()*i+1);
        temp = cards[i];
        cards[i] = cards[j];
        cards[j] = temp; 
}
}

/**
 * Prints the card in a "pretty" format:   "type-suit"
 *  Examples:  K-C  (King of clubs), 2-H (2 of hearts)
 *  Valid Types are: 2,3,4,5,6,7,8,9,10,J,Q,K,A
 *  Valid Suits are: H, D, C, S
 */
public static void printCard(int id) {
    if (id >= 0 && id <= 12) {
        System.out.print(type[id%13] + "-" + "H"); 
   }
   else if (id >= 13 && id <= 25) {
        System.out.print(type[id%13] + "-" + "S"); 
   }
   else if (id >= 26 && id <= 38) {
        System.out.print(type[id%13] + "-" + "D"); 
   }
   else if (id >= 39 && id <= 51) {
        System.out.print(type[id%13] + "-" + "C"); 
   }
}

/**
 * Returns the numeric value of the card.
 *  Should return 11 for an ACE and can then
 *  be adjusted externally based on the sum of the score.
 */
public static int cardValue(int id) {
    int num = id%13; 
    return value[num]; 
}

/**
 * Should print out each card in the hand separated by a space and
 * then print a newline at the end.  
 * Should use print card() to print out each card.
 */
public static void printHand(int hand[], int numCards) {
    for (int i = 0; i<numCards; i++){
        printCard(hand[i]);
        System.out.print(" "); 
    }
    System.out.println();
}

/**
 * Should return the total score of the provided hand.  
 * ACES should be treated as 11s to make the highest possible hand
 *  and only being reduced to 1s as needed to avoid busting.
 */
public static int getBestScore(int hand[], int numCards) {
    int sum = 0;
  int numofaces = 0; 
  for (int i = 0; i<numCards; i++) {
	//If your card is not an Ace, add the value to sum.
     if (hand[i]%13!=12) {
    	 sum+=value[hand[i]%13];
     }
         //
	//If your card is an Ace AND the sum is greater and equal to 11, add 1 to your sum.
     if (hand[i]%13==12&&sum>=11) { // greater than 11, 
    	 sum+=1;
         numofaces++; 
     }
	//If your card is an Ace AND the sum is less than 11, add 11 to the sum.
     if(hand[i]%13==12&&sum<11) { 
         sum+=11;
         numofaces++; 
     }
  }
  if (sum > 21 && numofaces != 0) { 
      sum = 0; 
      for (int j = 0; j<numCards; j++) {
          if (hand[j]%13 == 12) { 
              sum++;
          }
          else {
              sum += value[hand[j]%13]; 
          }
      }
  }
  return sum; 
}


	public static void main(String[] args) {
		//initialize 3 arrays. 
		int[] cards=new int[52];
		int[] phand=new int[9];
		int[] dhand=new int[9];
// 

// 
//
//


    boolean done = false;
    
    Scanner sc = new Scanner(System.in);

    while (!done) {
        char decision;
        int dhandsize = 2;
        int phandsize = 2;
          
    for(int i=0; i<NUM_CARDS;i++){
        cards[i]=i;
    } 
    shuffle(cards);
    phand[0]=cards[0];
	dhand[0]=cards[1];
	phand[1]=cards[2];
	dhand[1]=cards[3];
	//
	//
	//
	//
   
	//print out “Dealer: ?” followed by the dealer’s second card. The ? represents the first card which is hidden. 
	System.out.println("Dealer:?");
	printCard(dhand[1]);
    
    System.out.println();

//Print out the player’s hand (both cards). “Player: “ followed by both cards.
    System.out.println("Player: ");
    printHand(phand,phandsize);
    
    while (getBestScore(phand, phandsize) < 21) { //hit or stay
        System.out.println("Type 'h' to hit or 's' to stay: ");
        decision = sc.next().charAt(0);
// if decision is a ‘h’, we do a hit
        if (decision=='h')		 { 
     phand[phandsize]=cards[phandsize+2];
     phandsize++;
     //set player’s next card to the next card 
	//update the total size of the player’s hand by 1
          	//
	//

            System.out.print("Player: ");
            printHand(phand, phandsize);          
        }
        else //stay
           break;   
    }
   
	//if the Score of the player’s hand is more than 21
    if (getBestScore(phand, phandsize)>21) { 

        
        System.out.println("Player busts");
        System.out.println("Lose " + getBestScore(phand, phandsize) +
            " " + getBestScore(dhand, dhandsize));
    }
    	//if the Score of the player’s hand is less or equal to 21
    if(getBestScore(phand, phandsize)<=21) {
    
    //while the Score of the dealer is less than 17
        while (getBestScore(dhand, dhandsize)<17) {
            dhand[dhandsize] = cards[phandsize+dhandsize]; 
            dhandsize++; 
        }

	//Print out the dealer’s new hand tp say “Dealer: “ followed by their cards/hand 
	System.out.print("Dealer: ");
	printHand(dhand,dhandsize);
        
        
        if (getBestScore(dhand, dhandsize) > 21) {
        	// print out what happens when this condition is true. 2 print statements. 
        	System.out.println("Dealer busts");
            System.out.println("Lose " + getBestScore(phand, phandsize) +
                " " + getBestScore(dhand, dhandsize));

        }
            
        else {
// if our score was less than the dealer’s
 if (getBestScore(phand, phandsize)<getBestScore(dhand, dhandsize))	{		  
       
               System.out.println("Lose " + getBestScore(phand, phandsize) + 
                    " " + getBestScore(dhand, dhandsize));
                
            }
            else if(getBestScore(phand, phandsize)==getBestScore(dhand, dhandsize)) { 
          // print out what happens when this is true
            	System.out.println("Tie " + getBestScore(phand, phandsize) + 
            		" " + getBestScore(dhand, dhandsize));
               
            }
            else {
// print out what happens here. 
            	System.out.println("Win " + getBestScore(phand, phandsize) + 
                		" " + getBestScore(dhand, dhandsize));
                   
               
            }
            
            
        }
    }
        
    System.out.println();

    System.out.println("Type in yes to lose money again?");
   //
    decision = sc.next().charAt(0);

// user’s choice (y, n) is stored in decision
// if y, play again
// if n, stop playing (use break)
    
    if (decision=='y') { 
       done=false;
    }
    else {
       done=true;
       break; 	
    }
    		
    }
    sc.close();
    
	}

	
}