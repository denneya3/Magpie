import java.util.Arrays;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 * 		    Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @
 * @version April 2053
 */
public class Magpie2
{
	static final String[] randomPhrases = {"Can you tell me more?","I don't understand.","Hm.","Can you rephrase it?",
			"I don't know how to respond to that.","Oh.","Okay.","Um.","Can we talk about something else?"};
	private static String lastUncommittedResponse = " ";

	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */
	public String getGreeting()
	{
		return "Hello, let's talk.";
	}

	public static String reformatText(String text){
		return text.toLowerCase().replaceAll("[^a-zA-Z ]", "").toLowerCase(); // not tested
	}

	public static String getExactResponse(String text){
        /*
        Does account for punctuation as of now!
         */
		String [] wordList = {"no,nope--Why not?"};
		text = reformatText(text);
		for (String s : wordList){
			String[] localConditions = s.split("--"); // code "repeated" later
			String[] keywords = localConditions[0].split(",");
			for (String k : keywords){
				if (text.equals(k)){
					return localConditions[1];
				}
			}
		}
		return " ";
	}
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	private static String[] wordList = {"mother,sister,my--Im interested in your family!",
			"dog;cat--Tell me more about your pets.",
			"mother;sister--I wish I could meet your family.",
			"Mr. Smith,teacher--To be honest, all I know is that Mr. Smith is a great teacher!"};

	public static String getKeywordResponse(String text) {
        /*Considers capitalizatoon
        Considers if keyword is a substring of a word
        Does punctuation as of now.
         */
        /*; is or (this condition or that condition(s))
        , is and (that condition and that conditions(s))
        you cannot mix ; and , !
        */
		text = reformatText(text);
		ArrayList<String> words = new ArrayList<>();
		words.addAll(Arrays.asList(text.split(" ")));
		LinkedList<String> possibleResponses = new LinkedList<>();
		for (String s : wordList) {
			String[] localConditions = s.split("--");
			String[] keywords;
			if (localConditions[0].contains(";")) { //OR
				keywords = localConditions[0].split(";");
				for (String keyword : keywords) {
					if (words.contains(keyword)) {
						possibleResponses.addLast(localConditions[1]); //AND has priority
					}
				}
			} else { //(AND) ALL MUST BE PRESENT
				keywords = localConditions[0].split(",");
				int contained = 0;
				for (String keyword : keywords) {
					if (words.contains(keyword)) {
						contained++;
					}
				}
				if (contained == keywords.length) {
					possibleResponses.addFirst(localConditions[1]); //priority over OR
				}
			}
		}
		int responseArraySize = possibleResponses.size();
		if (responseArraySize == 0) {return " ";} else { //if there are no possible responses
			return possibleResponses.get(0);}
	}

	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	public static String getUncommittedResponse(){
		String response = " "; //dont change without changing the variable "lastUncommittedResponse"
		while (true) {
			response = randomPhrases[getRandomNumber(0,randomPhrases.length)];
			if (!response.equals(lastUncommittedResponse)){ //while (condition) ?
				lastUncommittedResponse = response;
				break;
			}
		}
		return response;
	}
}
