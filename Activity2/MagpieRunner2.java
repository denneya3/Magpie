import java.util.Scanner;
import java.util.ArrayList;


/**
 * A simple class to run the Magpie class.
 * @author Laurie White
 * @version April 2012
 */
public class MagpieRunner2
{
	public static void pln(String txt){
		System.out.println(txt);
	}
	public static void main(String[] args) // the Magpie Runner
	{
		Scanner input = new Scanner(System.in);
		while (true) {
			String userInput = input.nextLine();
			String response = " ";
			Magpie2 magpie = new Magpie2();
			if (userInput.length()==0 || magpie.getWordsList(userInput,false).size()==0){
				response = magpie.getBlankResponse();
			}
			else{ // First priority
				response = magpie.getKeywordResponse(userInput);;
			}
			if (response.equals(" ")) { // Second  priority
				response = magpie.getExactResponse(userInput);
			}
			if (response.equals(" ")){ // Last priority
				response = magpie.getUncommittalResponse(); //last "getResponse" should be random response
			}

			pln(response);
		}
	}

}
