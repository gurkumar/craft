package craft.demo.handler;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UrlFormatHandler implements FormatHandler{

	private final FormatHandler unformattedTextHandler;
	private static final String URL_MARKDOWN = "url";
	private static final String OPEN_SQUARE_BRACKET = "[";
	private static final String CLOSED_SQUARE_BRACKET = "]";
	private static final String OPEN_ROUND_PARENTHESIS = "(";
	private static final String CLOSED_ROUND_PARENTHESIS = ")";
	
	/**
	 * <p>
	 * {@link UrlFormatHandler#applyFormat(List, Map, List)} generates the href tag
	 * Rules for generating the href tag
	 *  1)[]() forms the url and the link text
	 *  2) unbalanced parenthesis are appended as is and not considered to be translated into href [[456]ab)](def)
	 *   will be [<a href="def">456]ab)</a>
	 *  3) Nested href
	 *   [[abc](456)](123)  <a href="123"><a href="456">abc</a></a>
	 * </p>
	 *   
	 *   
	 */
	@Override
	public void applyFormat(List<String> markdowns, Map<String, String> markDownToHtml,List<Integer>modifiedIndices) {
		if (markdowns != null && markDownToHtml != null) {
			for (int i=0;i<markdowns.size();i++) {
				String currentLine = markdowns.get(i);
				StringBuilder strb = new StringBuilder ();
				int j=0;
				while (j<currentLine.length()){
					if (currentLine.charAt(j) == OPEN_SQUARE_BRACKET.charAt(0)) {
						j = processLink (currentLine, j,markDownToHtml,strb);
					} else {
						strb.append(currentLine.charAt(j));
						j++;
					}

				}
				markdowns.set(i, strb.toString());
			}	
			unformattedTextHandler.applyFormat(markdowns, markDownToHtml,modifiedIndices);
		}
	}

	/** enter this when you encounter [ character**/
	private int processLink (String current, int currIndex, Map<String, String> markDownToHtml,StringBuilder strb ) {
		Stack<String> linkStack = new Stack <>();
		linkStack.add(String.valueOf(current.charAt(currIndex)));
		currIndex++;
		while (currIndex<current.length()
				&& linkStack.size()>0) {
			if (current.charAt(currIndex) == CLOSED_ROUND_PARENTHESIS.charAt(0)) {

				processStack (linkStack,markDownToHtml,strb);


			} else {
				linkStack.add(String.valueOf(current.charAt(currIndex)));
			}
			currIndex++;
		}
	
		StringBuilder leftover = new StringBuilder();
		while (linkStack.size()>0) {
			leftover.append(linkStack.pop());
		}
		strb.append(leftover.reverse());
		return currIndex;

	}
	
	/**this method processed the entries in stack to determine if valid []() combination is formed**/
	private void processStack (Stack<String> linkStack,Map<String, String> markDownToHtml,StringBuilder strb) {
		StringBuilder url = new StringBuilder ();
		while (linkStack.size()>0 && (!linkStack.peek().equals(OPEN_ROUND_PARENTHESIS)) && !linkStack.peek().equals(OPEN_SQUARE_BRACKET)) {
			url.append(linkStack.pop());
		}
        if (linkStack.size() ==0 || (!linkStack.peek().equals(OPEN_ROUND_PARENTHESIS))) {
        	//opening round parenthesis not found so this is not url
        	linkStack.add(CLOSED_ROUND_PARENTHESIS + url);
        	return;
        }

        //url found need to find matching []
		linkStack.pop(); // to pop '('

		if (linkStack.peek().equals(CLOSED_SQUARE_BRACKET))
			linkStack.pop();
		else {
			linkStack.add (CLOSED_ROUND_PARENTHESIS+url+OPEN_ROUND_PARENTHESIS);
			return;
		}

		/** until this point i have found matching round along with  square closed **/
		
		
		StringBuilder linkText = new StringBuilder();
		while (linkStack.size()>0 && !linkStack.peek().equals(OPEN_SQUARE_BRACKET)) {
			linkText.append(linkStack.pop());
		}
		if (linkStack.size() == 0 /*|| !linkStack.peek().equals(CLOSED_SQUARE_BRACKET)*/) {
			linkStack.add (CLOSED_ROUND_PARENTHESIS+url+OPEN_ROUND_PARENTHESIS + CLOSED_SQUARE_BRACKET);
			return;
		}
		
		//if (linkStack.size()>0 && linkStack.peek().equals(CLOSED_SQUARE_BRACKET)) {
			linkStack.pop();//pop ']'
			linkText.reverse();
			url.reverse();
			if (linkText != null && url != null) {
				if ( markDownToHtml.containsKey(URL_MARKDOWN)) {
					String formattedStr = MessageFormat.format(markDownToHtml.get(URL_MARKDOWN),url, linkText);
					if (linkStack.size() == 0) {
						strb.append(formattedStr);

					} else {
						//reverse and add
						StringBuilder str = new StringBuilder(formattedStr);
						linkStack.add(str.reverse().toString());
					}	
				}

			}

		//} 
	}

	

}
