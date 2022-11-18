import java.util.Scanner;



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
	/**
	 * Create a Magpie, give it user input, and print its replies.
	 */
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		while (true) {
			String userInput = input.nextLine();
			String response = " ";

			Magpie2 magpie = new Magpie2();

			//go through different methods until " " isnt returned | this is messy unorganized code
			String resp = magpie.getKeywordResponse(userInput);
			if (!resp.equals(" ")){
				response = resp;
			} else {
				response = magpie.getExactResponse(userInput);
			}
			if (response.equals(" ")){
				response = magpie.getUncommittedResponse();
			}
			//last "getResponse" should be random response

			pln(response);
		}
	}

}
