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
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import craft.demo.handler.FormatHandler;
import craft.demo.handler.UrlFormatHandler;

@ExtendWith(SpringExtension.class)
public class UrlFormatHandlerTest {
	@Mock
	private  FormatHandler unformattedTextHandler;
	
	private static Map<String, String> markdownToHtmlFormatter;
	
	@InjectMocks
	private UrlFormatHandler urlFormatHandler;
	
	@BeforeEach
	void init() {
		 markdownToHtmlFormatter 
	    = Arrays.stream(new String[][] { 
	    	  {"url","<a href=\"{0}\">{1}</a>"}        
	                         }) 
	          .collect(Collectors.toMap( 
	              keyMapper -> keyMapper[0], valueMapper -> valueMapper[1])); 
	}
	@Test
	 void testApplyFormat () {
		String[] lines = getTestInputForUrl().split("\\R");
		List<String> markdowns = Arrays.stream(lines).collect(Collectors.toList());
	
		List<Integer> modifiedIndices = new ArrayList <> ();
		urlFormatHandler.applyFormat(markdowns, markdownToHtmlFormatter, modifiedIndices);
		String actualResult = String.join("\n", markdowns);
		System.out.println (actualResult);
		assertEquals (getTestOutput (), actualResult);
	}
	
	
	
	@Test
	 void testApplyFormatNestedInput () {
		String[] lines = getNestedTestInputForUrl().split("\\R");
		List<String> markdowns = Arrays.stream(lines).collect(Collectors.toList());
	
		List<Integer> modifiedIndices = new ArrayList <> ();
		urlFormatHandler.applyFormat(markdowns, markdownToHtmlFormatter, modifiedIndices);
		String actualResult = String.join("\n", markdowns);
		System.out.println (actualResult);
		assertEquals (getNestedTestOutputForUrl (), actualResult);
	}
	
	@Test
	 void testApplyFormatInvalidInputInnerLinkText () {
		String[] lines = getInvalidInputForInnerLinkText().split("\\R");
		List<String> markdowns = Arrays.stream(lines).collect(Collectors.toList());
	
		List<Integer> modifiedIndices = new ArrayList <> ();
		urlFormatHandler.applyFormat(markdowns, markdownToHtmlFormatter, modifiedIndices);
		String actualResult = String.join("\n", markdowns);
		System.out.println (actualResult);
		assertEquals (getInvalidOutputForInnerLinkText (), actualResult);
	}
	
	@Test
	 void testApplyFormatInvalidInputMissingParenthesis () {
		String[] lines = getInvalidInputForMissingParenthesis().split("\\R");
		List<String> markdowns = Arrays.stream(lines).collect(Collectors.toList());
	
		List<Integer> modifiedIndices = new ArrayList <> ();
		urlFormatHandler.applyFormat(markdowns, markdownToHtmlFormatter, modifiedIndices);
		String actualResult = String.join("\n", markdowns);
		System.out.println (actualResult);
		assertEquals (getInvalidOutputForMissingParenthesis (), actualResult);
	}
	
	
	
	private String getTestInputForUrl () {
		return "This is sample markdown for the [Mailchimp](https://www.mailchimp.com) homework assignment."			  
				+ "";
	}
	
	private String getNestedTestInputForUrl () {
		return "This is sample markdown for the [[abc](456)](123) homework assignment.";
	}
	
	private String getInvalidInputForInnerLinkText () {
		return "This is sample markdown for the [[abc)](def) homework assignment.";
	}
	
	private String getInvalidInputForMissingParenthesis () {
		return "This is sample markdown for the  [(def) homework assignment.";
	}
	
	private String getTestOutput () {
		return "This is sample markdown for the <a href=\"https://www.mailchimp.com\">Mailchimp</a> homework assignment.";
	}
	
	private String getNestedTestOutputForUrl () {
		return "This is sample markdown for the <a href=\"123\"><a href=\"456\">abc</a></a> homework assignment.";
	}
	
	private String getInvalidOutputForInnerLinkText () {
		return "This is sample markdown for the [<a href=\"def\">abc)</a> homework assignment.";
	}
	
    private String getInvalidOutputForMissingParenthesis () {
    	return "This is sample markdown for the  [(def) homework assignment.";
    }
}
