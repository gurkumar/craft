package craft.demo.handler;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class UnformattedTextHandler implements FormatHandler{

	private static final String UNFORMATTED_START = "unformated_start";
	private static final String UNFORMATTED_END = "unformated_end";
	private static final String UNFORMATTED = "unformated";
	
	/**
	 * <p>
	 * {@link UnformattedTextHandler#applyFormat(List, Map, List)} adds paragraph tag to the text
	 * </p>
	 * <p>
	 * headings are not be touched by this tag so it checks if markdowns have been modified to determine if tags
	 * need to be added
	 * another rule is that no line breaks constitutes a paragraph.
	 * </p>
	 */
	@Override
	public void applyFormat(List<String> markdowns, Map<String, String> markDownToHtml,List<Integer>modifiedIndices) {
		//modified indices cannnot be considered unformatted text
		int i=0;
		
		while (i<markdowns.size()) {
			if (!modifiedIndices.contains(i) && markdowns.get(i) != "") {
				if (((i+1<markdowns.size() && markdowns.get(i+1).isEmpty()) || (i+1 == markdowns.size()))
						&& markDownToHtml.containsKey(UNFORMATTED)) {
					String formattedText = MessageFormat.format(markDownToHtml.get(UNFORMATTED), markdowns.get(i));
					markdowns.set(i, formattedText);
					i++;
				} else if (i+1<markdowns.size() && !markdowns.get(i+1).isEmpty()
						&&  markDownToHtml.containsKey(UNFORMATTED_START) &&  markDownToHtml.containsKey(UNFORMATTED_END)) {
					
					String formattedText = MessageFormat.format(markDownToHtml.get(UNFORMATTED_START), markdowns.get(i));
					markdowns.set(i, formattedText);
					formattedText = MessageFormat.format(markDownToHtml.get(UNFORMATTED_END), markdowns.get(i+1));
					markdowns.set(i+1, formattedText);
					i=i+2;
				}
			} else {
				i++;
			}
			
		}
		 
	}

}
