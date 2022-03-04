package examples;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;


/**
 *	Radix sort - Sorts a list of words using the radix method. This
 *  means sorting right to left and padding words that aren't long
 *  enough.
 *
 *	@author	Alex Richardson
 *	@since	May 23, 2020
 */
public class RadixSort {
	
	private ArrayList<String> words;		// list of words to sort, loaded into memory
	
	public String WORD_FILE = "data/randomWords.txt";
	
	public String OUTPUT = "output.txt";
	
	private int maxWordLength;				// length of longest string in file
	
	/**
	 * Creates a new structure ready to sort the configured file.
	 * @throws FileNotFoundException if the configured file is not readable
	 */
	public RadixSort() throws FileNotFoundException {
		words = new ArrayList<String>();
	}
	
	public static void main(String[] args) {
		try {
			RadixSort rs = new RadixSort();
			rs.run();
		} catch (java.io.FileNotFoundException e) {
			// Only known cause is if the file is not in the right place according to config
			throw new IllegalStateException(e);
		}
	}
	
	public void run() throws FileNotFoundException {
		maxWordLength = findMaxWordLength();
		readWords();
		words = sortWords(words);
		printWords();
	}
	
	/**
	 *  Finds longest string in our file
	 *  @return maxLength		length of longest string
	 *  @throws java.io.FileNotFoundException if the word file cannot be read
	 */
	public int findMaxWordLength() throws java.io.FileNotFoundException {
		java.util.Scanner findMaxLength = FileUtils.openToRead(WORD_FILE);
		int maxLength = 0;
		while (findMaxLength.hasNext()) {
			String word = findMaxLength.next();
			if (maxLength < word.length())
				maxLength = word.length();
		}
		findMaxLength.close();
		return maxLength;
	}
	
	/**	Read in all words from our file, store them in a class variable.
	 *  Possible memory / speed improvement:  count while finding max length, then read and sort at the same time
	 *  @throws java.io.FileNotFoundException if the word file cannot be read
	 */
	public void readWords() throws FileNotFoundException {
		java.util.Scanner input = FileUtils.openToRead(WORD_FILE);
		int count = 0;
		while (input.hasNext()) {
			words.add(count, input.next());
			count++;
		}
		input.close();
	}
	
	/**	Sort words using Radix Sort
	 *	@param words	a list of words to sort
	 *	@return			a list of the words sorted in lexicographic order
	 */
	public ArrayList<String> sortWords(ArrayList<String> words) {
		ArrayList<String> sortedWords = new ArrayList<String>(words.size());
		ArrayList<LinkedList<String>> list1 = new ArrayList<LinkedList<String>>();
		ArrayList<LinkedList<String>> list2 = new ArrayList<LinkedList<String>>();
		makeEmptyList(list1);
		makeEmptyList(list2);
		for (int i = 0; i < words.size(); i++) {
			placeInBucket(words.get(i), maxWordLength - 1, list1);
		}
		int count = 1;
		for (int i = maxWordLength - 2; i >= 0; i--) {
			ArrayList<LinkedList<String>> empty;
			ArrayList<LinkedList<String>> old;
			if (count % 2 == 0) {
				empty = list1;
				old = list2;
			}
			else {
				empty = list2;
				old = list1;
			}
			makeEmptyList(empty);
			for (int k = 0; k < 27; k++) {
				for (int j = 0; j < old.get(k).size(); j++) {
					placeInBucket(old.get(k).get(j), i, empty);
				}
			}
			count++;
		}
		ArrayList<LinkedList<String>> sorted;
		if (count % 2 == 0)
			sorted = list2;
		else
			sorted = list1;
		int l = 0;
		for (int i = 0; i < 27; i++) {
			for (int k = 0; k < sorted.get(i).size(); k++) {
				sortedWords.add(l, sorted.get(i).get(k));
				l++;
			}
		}
		return sortedWords;
	}
	
	/**
	 *  Makes list have 27 empty buckets.
	 *  @param list		ArrayList to empty
	 */
	public void makeEmptyList(ArrayList<LinkedList<String>> list) {
		list.clear();
		for (int i = 0; i < 27; i++) {
			list.add(new LinkedList<String>());
		}
	}
	
	/**
	 *  Places given word in correct bucket of given array.
	 *  @param word			String to sort
	 *  @param location		int of location of string to sort by
	 *  @param list			ArrayList with buckets
	 */
	public void placeInBucket(String word, int location, ArrayList<LinkedList<String>> list) {
		if (location < 0)
			throw new IllegalArgumentException("location must be >= 0");
		if (word.length() <= location)
			list.get(0).add(word);
		else {
			int bucket = Character.getNumericValue(word.charAt(location)) - Character.getNumericValue('a') + 1;
			if (bucket < 0 || bucket > 26)
				throw new IllegalArgumentException("word has unexpected characters @ position " + location + " : " + word);
			// uses character's value to add word to correct bucket
			list.get(bucket).add(word);
		}
	}
	
	/**	Print the words 
	 * @throws FileNotFoundException if the configured output file cannot be written
	 * */
	public void printWords() throws FileNotFoundException {
		java.io.PrintWriter output = FileUtils.openToWrite(OUTPUT);
		for (int i = 0; i < words.size(); i++)
			output.println(words.get(i));
		output.close();
		System.err.println("All done!");
	}
	
}