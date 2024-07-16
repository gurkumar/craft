package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import craft.demo.handler.UnformattedTextHandler;

@ExtendWith(SpringExtension.class)
public class UnformattedTextHandlerTest {
	
	private static Map<String, String> markdownToHtmlFormatter;
	
	@InjectMocks
	private UnformattedTextHandler unformattedTextHandler;
	
	@BeforeEach
	void init() {
		 markdownToHtmlFormatter 
	    = Arrays.stream(new String[][] { 
	    	 { "unformated", "<p>{0}</p>" },
             { "unformated_start", "<p>{0}" },
             { "unformated_end", "{0}</p>" },
	                         }) 
	          .collect(Collectors.toMap( 
	              keyMapper -> keyMapper[0], valueMapper -> valueMapper[1])); 
	}
	@Test
	 void testApplyFormatUnformattedText () {
		String[] lines = getTestInput().split("\\R");
		List<String> markdowns = Arrays.stream(lines).collect(Collectors.toList());
	
		List<Integer> modifiedIndices = new ArrayList <> ();
		unformattedTextHandler.applyFormat(markdowns, markdownToHtmlFormatter, modifiedIndices);
		String actualResult = String.join("\n", markdowns);
		assertEquals (getTestOutput (), actualResult);
	}
	
	private String getTestInput () {
		return "Hello there\n"
				+ "\n"
				+ "How are you?\n"
				+ "What's going on?";
	}
	
	private String getTestOutput () {
		return "<p>Hello there</p>\n"
				+ "\n"
				+ "<p>How are you?\n"
				+ "What's going on?</p>"
				;
	}
}
