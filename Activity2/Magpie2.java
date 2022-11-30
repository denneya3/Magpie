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
	/*
	Don't make useless methods for no reasons!
	For instance, instead of creating a Greeting method, you can simply utilize the keyword-response method.
	Try to utilize existing methods as much as possible
	 */

	static final String[] randomPhrases = {"Can you tell me more?","I don't understand.","Hm.","Can you rephrase it?",
			"I don't know how to respond to that.","Oh.","Okay.","Um.","Can we talk about something else?"};
	private static String lastUncommittalResponse = " ";

	 static final String[] keywordResponses = {"mother,sister,my--Im interested in your family! Tell me more please.",
			"dog;cat--Tell me more about your pets.",
			"mother;sister--I wish I could meet your family.",
			"smith;teacher--To be honest, all I know is that Mr. Smith is a great teacher!",
	"hi;hello;wsp;what's up;whats up; hey--Hello!",
	"blueface--I don't know why he tried killing her...",
	"APCSA;CSA;AP--APCSA is a good class."};
	/*
	FORMAT: List of words to trigger response--response_text
	Words are separated by , or ;
	, means and <-- all words must be present
	; means or <-- only one of the words must be present
	don't mix ; and , in a list, the list must only have on type of comma
	 */

	//FIX IT SO keywordReponses and ExactResponses can work with "Mr. Smith" "What's up"

	private static String[] exactReponses = {"no,nope--Why not?"};
	// Only separate the keyword list here with commas

	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	public static String reformatText(String text){
		return text.toLowerCase().replaceAll("[^a-zA-Z ]", "").toLowerCase(); // not tested
	}
	public static String getExactResponse(String text){
        /*
        Returns a response if text is equal to something in the exactReponses list
        Does account for punctuation as of now!
         */
		text = reformatText(text);
		for (String s : exactReponses){
			String[] localConditions = s.split("--"); // code "repeated" later
			String[] keywords = localConditions[0].split(",");
			for (String k : keywords){
				if (text.equals(k.toLowerCase())){
					return localConditions[1];
				}
			}
		}
		return " ";
	}

	public static String getKeywordResponse(String text) {
        /*
        Returns a response based on keywords found in text
        Considers capitalizatoon
        Considers if keyword is a substring of a word
        Does punctuation as of now.
         */
        /*; is or (this condition or that condition(s))
        , is and (that condition and that conditions(s))
        you cannot mix ; and , !
        */
		text = reformatText(text);
		ArrayList<String> words = new ArrayList<>(Arrays.asList(text.split(" ")));
		LinkedList<String> possibleResponses = new LinkedList<>();
		for (String s : keywordResponses) {
			String[] localConditions = s.split("--");
			String[] keywords;
			if (localConditions[0].contains(";")) { //OR
				keywords = localConditions[0].split(";");
				for (String keyword : keywords) {
					if (words.contains(keyword.toLowerCase())) {
						possibleResponses.addLast(localConditions[1]); //AND has priority
					}
				}
			} else { //(AND) ALL MUST BE PRESENT
				keywords = localConditions[0].split(",");
				int contained = 0;
				for (String keyword : keywords) {
					if (words.contains(keyword.toLowerCase())) {
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

	//Default response to use if nothing else fits
	public static String getUncommittalResponse(){
		String response = " "; //dont change without changing the variable "lastUncommittalResponse"
		while (true) {
			response = randomPhrases[getRandomNumber(0,randomPhrases.length)];
			if (!response.equals(lastUncommittalResponse)){ //while (condition) ?
				lastUncommittalResponse = response;
				break;
			}
		}
		return response;
	}
}
