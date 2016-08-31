// Vikram R. Sringari
// 2/25/2016
// CSE143
// TA: Chloe Lathe
// Assignment #6: AnagramSolver
// This Class Object takes a list of words that is used as a dictionary for 
// all possible anagrams. These are printed out (with print method) based on the words
// passed in and the number of words for each anagram for that words desired for the 
// anagrams. 
import java.util.*;

public class AnagramSolver{
   
   private Map<String, LetterInventory> wordLetters;// These words are 
                                                    //mapped to their letterinventory
   
   private Stack<String> fullDictionary;// This is the entire dictionary
   
   //pre: It takes a list of strings, each of which represent a word in the dictionary
   //post: It takes a list of strings and which represents a dictionary and makes them
   //      usefull for producing anagrams. This list strings for the dictionary of
   //      of reference words for anagrams, comes from txt files.
   public AnagramSolver(List<String> list){ 
      wordLetters = new HashMap<String,LetterInventory>();
      fullDictionary = new Stack<String>();
      for(int i = 0; i < list.size(); i++){
         fullDictionary.push(list.get(i));
         wordLetters.put(list.get(i), new LetterInventory(list.get(i)));
      } 
   }
   
   //pre: It takes a String s which is the word that will be used to produce an anagram of,
   //     and takes an int max which is the number of words that will constitute a single
   //     anagram that is printed out. If int max is zero, the program produces highest
   //     amount of words for each anagram which is the highest it can possibly be 
   //     (unlimited potentially). No matter what is passed into the string, only the  
   //     letter characters are used for each anagram. This method also throws an
   //     IllegalArgumentException, when int max is less than zero since the number of 
   //     words in an anagram cannot be less than zero.
   //post: It takes a word or phrase and uses the characters of that String to turn it 
   //      int all possible anagrams based on the list of words that was passed in by the 
   //      constructor. The number of words in each anagram that is printed out is based on
   //      int max, unless it is zero (then highest amount of words for anagram is printed).
   //      The anagrams are printed based order of the words in the list (dictionary)
   //      passed in the constructor.
   public void print(String s, int max){
      if(max < 0){
         throw new IllegalArgumentException(); 
      }
      LetterInventory letters = new LetterInventory(s);
      Stack<String> newDictionary = new Stack<String>();
      Stack<String> anagrams = new Stack<String>();// This is for clarity***
      for(String key : fullDictionary){  
         LetterInventory guessLetters = wordLetters.get(key);
         if(letters.subtract(guessLetters) != null){
            newDictionary.push(key);// This is where the newDictionary cuts down the amount 
         }                          // of words possible to make the anagrams for String s 
      }
      printHelp(letters, max, newDictionary, anagrams);
   }
   
   //pre: It takes the letterInventory from String s, takes the max number of words in the 
   //     anagrams, takes the Stack of Strings that represent the cut down dictionary
   //     and takes in a Stack whihc is used to print out each word possible for the anagrams
   //     then pop out them once it dettermines if the word combination is imposslbe 
   //     to produce a correct anagram, or once it finds the correct answer.
   //post: This method takes the letters of String s and each word of the newDictionary and 
   //      subtracts them each time for each word that is possible to make the anagram. This
   //      method uses recursion to go through newdictionary to dettermine if it's letters 
   //      can be subtracted by letterinventory letters. It does this each time untill it 
   //      finds the correct list of words that produce an anagram. This happens when letters 
   //      is empty and amount of words left from max number of words is zero. It does this 
   //      for each possible words combination to see each possible combination is an anagram 
   //      but only prints out the correct anagram since it uses recursive backtracking.
   private void printHelp(LetterInventory letters, int max, Stack<String> newDictionary,
                          Stack<String> anagrams){
      if((max >= anagrams.size()|| max == 0) && letters.isEmpty()){// base case
         System.out.println(anagrams);// all possible anagrams are printed
      }else{// recursive case
         for(String key : newDictionary){
            LetterInventory lettersGuess = wordLetters.get(key);
            LetterInventory lettersLeft = letters.subtract(lettersGuess);
            if(lettersLeft != null){// checks if it is safe to use word in the anagarm
               anagrams.push(key);
               printHelp(lettersLeft, max, newDictionary, anagrams);// recursion
               anagrams.pop();
            }  
         } 
      }
   }
   
}