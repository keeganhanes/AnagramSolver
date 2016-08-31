// Vikram R. Sringari
// 1/7/2016
// CSE143
// TA: Chloe Lathe
// Assignment #1: LetterInventory 
// The LetterInventory object uses data specifically the nonalphabetical
// character of the data and counts the number of each character in the 
// data. This class includes public methods size, which gets the number of
// alphabetical characters in the data, isEmpty, which says if the data
// doesn't contain alphabetical characters, get, which gets the count
// of a specified alphabetical character, set, which sets the number of
// specified alphabetical characters in the data, toString, which produces
// an alphabetized string of the characters in the data, add, which adds 
// the counts of each character in two objects and produces a new object
// from the sum, and subtract, which subtracts the counts of each character 
// from one object by another to produce a new object that contains the
// difference of the counts. As one can see this object focuses around
// alphabetical character data.

public class LetterInventory {
	
	public static final int ALPHABET = 26;//This constant is used
                                         //make reference to the
                                         //alphebetical letters
	
   private int[] letterCount;// This is used to store the counts
                             // for each letter of the alphabet
   
   private int size;// This is used to get the number of 
                    // nonalphabetical characters in latter methods
             
   
   //pre: The contructor takes a data of type String to get a count 
   //     for each alphabetical letter in the String. This does not 
   //     include other nonalphabetical symbols or spaces since they 
   //     aren't part of the alphabet.
   //post: The constructor makes a count of each letter in String from
   //      the parameter, this does not include other nonalphabetical 
   //      symbols or spaces since they aren't part of the alphabet.
	public LetterInventory(String data){
      size = 0;
      letterCount = new int[ALPHABET];
      for(int i = 0; i < data.length(); i++){
         if(alphabetChecker(Character.toLowerCase(data.charAt(i)))){
            letterCount[Character.toLowerCase(data.charAt(i)) - 'a']++;
            size++;
         }
      }
	}
   
   //pre: This private (helper) method takes a in char value, and
   //     uses it for checking if it is a letter.
   //post: This method returns a boolean values that states true 
   //      if the character from the parameter is actually a alphbetical
   //      letter rather than a symbol or a space. This is used in
   //      the get method, set method and constructor to dettermine
   //      whether the parameter that passes in a char or the char 
   //      from the data String it self are actually letters.
   //      This method is capable of accomplishing this by taking 
   //      the char, which has it's own int value and subtracting it 
   //      from char 'a' which gives the range for the method to check
   //      to be between 0 (incluse) and 26 (exclusive). 
   private boolean alphabetChecker(char letter){
      int letterCheck = (int) letter - 'a';
      return letterCheck < ALPHABET && letterCheck >= 0;// checks to see if 
                                                        // letter is in range
                                                        // of the alphabet
   }
   
   //pre: This method takes a character and uses it to get a count for 
   //     the amount of that specific character in a data String.
   //     An exception to the parameter use is that the character
   //     cannot be a nonalphabetical character since this method only 
   //     counts the number of alphabetical letters from the data String
   //post: This method returns the int value of the number of that
   //      specific character in the data String. This basically
   //      gives the number of a specific letter within a String.
	public int get(char letter){
      if(!(alphabetChecker(Character.toLowerCase(letter)))){
         throw new IllegalArgumentException();
      }
      return letterCount[Character.toLowerCase(letter) - 'a'];// subtracts int
                                                              // value of letter
                                                              // by int value of 
                                                              // 'a' to get the count
   }
   
	//pre: This method takes a integer number value as a parameter
   //     to set the number of the specific character letters based
   //     what char parameter letter is. This information produces 
   //     data for the object. The exceptions to these methods include 
   //     character values that are not alphbetical letters, since 
   //     nonalphabetical data is not signficant or measured in this
   //     object. Another exception is that the integer value cannot 
   //     less than 0 so that there aren't less specifc characters letters 
   //     than that already exist.
   //post: This method sets the data in the object based on the number 
   //      (from int value) and alphbetical character (from char letter)
   //      The .set method keeps the data information produced in 
   //      previous calls to the same object, and only can specify a lower 
   //      number count (through int value) of char value that already is
   //      that already existed if that value is less the number of 
   //      that specific characters with in the existing data.
	public void set(char letter, int value){
      if(!(alphabetChecker(Character.toLowerCase(letter))) || value < 0){
         throw new IllegalArgumentException();
      }
      int initialValue = letterCount[Character.toLowerCase(letter) - 'a'];
      letterCount[Character.toLowerCase(letter) - 'a'] = value;
      size += value - initialValue;// subtracts original count value of letter
                                   // from the value to add to size
                                   // this allows size to decrease if value is
                                   // less than initialCount for that letter
   }
	
   //pre: N/A (no parameters or exceptions)
   //post: This method returns the int value or in other words the
   //      the number of letters (characters) within a the data string.
	public int size(){
      return size;
   }
   
	//pre: N/A (no parameters or exceptions)
   //post: This method returns a boolean value that says if it is true
   //      if the data contains not letter characters and false if the
   //      data does contain letters.
	public boolean isEmpty(){
      return size == 0;
   }
   
	//pre: N/A (no parameters or exceptions)
   //post: This method returns String of the alphabetical characters
   //      in the data in alphabetical order, and will show the number
   //      of a specific alphabetical based on number repetitions of that
   //      of that character in the String. The String in enclosed in 
   //      brackets.
	public String toString(){
      String inventory = "[";
      for(int i = 0; i < ALPHABET; i++){
         for(int j = 1; j <= letterCount[i]; j++){
            inventory += (char)(i + 'a');
         }
      }
      inventory += "]";
      return inventory;
   }
   
	//pre: This method takes a different LetterInventory object 
   //     as a parameter in order to add the data of the number count 
   //     of each letter in the this existing object of LetterInventory 
   //     to the the data of the number count of each letter in the
   //     (LetterInventory) other object.
   //post: This method, based on the data from this LetterInventory 
   //      object and another LetterInventory object returns
   //      a new LetterInventory object that combines the counts
   //      for each letter from both LetterInventory object.
	public LetterInventory add(LetterInventory other){
      LetterInventory sumation = new LetterInventory("");
      for(int i = 0; i < ALPHABET; i++){
         sumation.letterCount[i] = letterCount[i] + other.letterCount[i]; 
   //    adds number count                                                                   
   //    for each index (i)                                                                     
      }
      sumation.size = size + other.size;// adds both sizes to produce 
                                        //  size for summation
      return sumation;
   }
   
	//pre: This method takes a different LetterInventory object 
   //     as a parameter in order to subtract the data of the number count 
   //     of each letter in the this existing object of LetterInventory 
   //     from the the data of the number count of each letter in the
   //     (LetterInventory) other object.
   //post: This method, based on the data from this LetterInventory 
   //      object and another LetterInventory object returns
   //      a new LetterInventory object subtracts the number
   //      of counts for each letter of this LetterInventory object
   //      based on the counts fro each letter of a different
   //      LetterInventory object. If the count number of specific 
   //      letter in the other LetterInventory object is larger
   //      the counts of this LetterInventory object, the method
   //      will return null since one cannot subtract the count
   //      number from this object if the count number of this object
   //      is zero (that would produce a negative count);
   	public LetterInventory subtract(LetterInventory other){
      LetterInventory difference = new LetterInventory("");
      for(int i = 0; i < ALPHABET; i++){
         if(other.letterCount[i] > letterCount[i]){
            return null;
         }
         difference.letterCount[i] = letterCount[i] - other.letterCount[i];
         // adds number count                                                                   
         // for each index (i)
      }
      difference.size = size - other.size;// subtracts on this size from  
                                          // other size for difference   
      return difference;  
   }  
}
