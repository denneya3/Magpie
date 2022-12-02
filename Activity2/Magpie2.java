import java.util.Arrays;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * A program to carry on unintelligent conversations with a human user.
 * @version 2.2
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
	private static String lastBlankResponse = " ";
	private static String[] blankResponses = {"Can you say something?","You need to type something, silly.", "I don't have time for blank responses!"};
	 static final String[] keywordResponses = {"mother,sister,my--Im interested in your family! Tell me more please.",
			"dog;cat;fish;hamster;horse--Tell me more about your pets/animals.",
			"mother;sister--I wish I could meet your family.",
			"smith;teacher--To be honest, all I know is that Mr. Smith is a great teacher!",
	"hi;hello;wsp;what's up;whats up; hey--Hello!",
	"blueface--I don't know why he tried killing her...",
	"APCSA;CSA;AP--APCSA is a good class.",
	 "grade;grade--Speaking of grades, Mr. Smith should give us a 500% on this assignment.",
	 "APCSP--I've heard about AP CS Principles, is it an easier AP class?",
	 "lion;roar;wlhs--It's a great day to be a lion!",
	 "love--Why do you love it?",
	 "hate--Hate is a strong word.",
	 "lol;xd;lmao;haha;lmfao;kek--Ha ha!",
	 "what,your,name--I don't have name yet...",
	 "meaning,of,life,universe,everything--The meaning of life, the universe, and everything is 42.",
	 "magpie--That word 'Magpie' is familiar. It is somewhere in my code. I still have no clue what a Magpie is though."};

	/*
	FORMAT: List of words to trigger response--response_text
	Words are separated by , or ;
	, means and <-- all words must be present
	; means or <-- only one of the words must be present
	don't mix ; and , in a list, the list must only have on type of comma
	Do not put spaces in keywords
	 */

	private static String[] exactReponses = {"no,nope--Why not?","goodbye,bye--Please don't go, I don't have anyone else to talk to."};
	// Only separate the keyword list here with commas
	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	public static String reformatText(String text){
		return text.toLowerCase().replaceAll("[^a-zA-Z ]", "").toLowerCase(); // not tested
	}

	public static ArrayList<String> getWordsList(String text, Boolean reformatted){
		if (!reformatted){
			text = reformatText(text);
		}
		ArrayList<String> words = new ArrayList<>(Arrays.asList(text.split(" ")));
		return words;
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
        Considers capitalization
        Considers if keyword is a substring of a word
        Does punctuation as of now.
         */
        /*; is or (this condition or that condition(s))
        , is and (that condition and that conditions(s))
        you cannot mix ; and , !
        */
		text = reformatText(text);
		ArrayList<String> words = getWordsList(text,true);
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

	public static String getBlankResponse(){
		String response = " "; //dont change without changing the variable "lastUncommittalResponse"
		while (true) {
			response = blankResponses[getRandomNumber(0,blankResponses.length)];
			if (!response.equals(lastBlankResponse)){
				lastBlankResponse = response;
				break;
			}
		}
		return response;
	}

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
