package craft.demo.handler;

import java.util.List;
import java.util.Map;

public interface FormatHandler {
	
	void applyFormat (List<String> markdowns, Map<String,String> markDownToHtml,List<Integer>modifiedIndices);
	
	
}
