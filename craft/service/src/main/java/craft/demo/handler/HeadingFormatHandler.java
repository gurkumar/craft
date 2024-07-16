package craft.demo.handler;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HeadingFormatHandler implements FormatHandler{

	private final FormatHandler urlFormatHandler;

	/**
	 * <p>
	 * {@link HeadingFormatHandler#applyFormat(List, Map, List)} converts the '#' into headings based on the count
	 * headings 1 through 6 are valid. if number of hashes exceed 6 it is not converted.
	 * </p>
	 * <p>
	 * list of modified indices is to determine if heading has to be added in which case no downstream handler will touch the entry
	 * </p>
	 */
	@Override
	public void applyFormat(List<String> markdowns, Map<String, String> markDownToHtml,List<Integer>modifiedIndices) {
		//check if markdown has heading initiator followwed by Space
		//I am making an assumption that  #(1 or more)  followed by space is heading 
		//hash followed by anything else is not heading
		if (markdowns != null && markDownToHtml != null) {
			for (int i=0;i<markdowns.size();i++) {
				String currentLine = markdowns.get(i);
				if (currentLine != null && !currentLine.isEmpty() && currentLine.charAt(0) == '#') {
					int j = 1;
					while (j < currentLine.length() && currentLine.charAt(j) == '#')
						j++;
					if(Character.isWhitespace(currentLine.charAt(j))){
						String key = getKey (j);
						if (markDownToHtml.containsKey(key.toString())) {
							String formatToApply = markDownToHtml.get(key.toString());
							markdowns.set(i, MessageFormat.format (formatToApply,currentLine.substring(j+1)));
							modifiedIndices.add(i);
						}

					} 

				}
				
			}

		}

		 urlFormatHandler.applyFormat(markdowns, markDownToHtml,modifiedIndices) ;
	}

   private String getKey (int count) {
	   StringBuilder key = new StringBuilder();
	   for (int k=1;k<=count;k++) {
			key.append("#");
		}
	   return key.toString();
   }
}
