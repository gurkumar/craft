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
import craft.demo.handler.HeadingFormatHandler;

@ExtendWith(SpringExtension.class)
public class HeadingFormatHandlerTest {
	
	@Mock
	private  FormatHandler urlFormatHandler;
	
	private static Map<String, String> markdownToHtmlFormatter;
	
	@InjectMocks
	private HeadingFormatHandler headingFormatHandler;
	
	@BeforeEach
	void init() {
		 markdownToHtmlFormatter 
	    = Arrays.stream(new String[][] { 
	                        { "#", "<h1>{0}</h1>" }, 
	                        { "##", "<h2>{0}</h2>" }, 
	                        { "###", "<h3>{0}</h3>" }, 
	                        { "####", "<h4>{0}</h4>" }, 
	                        { "#####", "<h5>{0}</h5>" }, 
	                        { "######", "<h6>{0}</h6>" }
	                         }) 
	          .collect(Collectors.toMap( 
	              keyMapper -> keyMapper[0], valueMapper -> valueMapper[1])); 
	}
	@Test
	 void testApplyFormatHeading1 () {
		String[] lines = getTestInputForHeading1().split("\\R");
		List<String> markdowns = Arrays.stream(lines).collect(Collectors.toList());
	
		List<Integer> modifiedIndices = new ArrayList <> ();
		headingFormatHandler.applyFormat(markdowns, markdownToHtmlFormatter, modifiedIndices);
		String actualResult = String.join("\n", markdowns);
		assertEquals (getOutputHeading1 (), actualResult);
	}
	@Test
	 void testApplyFormatHeading2 () {
		String[] lines = getTestInputForHeading2().split("\\R");
		List<String> markdowns = Arrays.stream(lines).collect(Collectors.toList());
	
		List<Integer> modifiedIndices = new ArrayList <> ();
		headingFormatHandler.applyFormat(markdowns, markdownToHtmlFormatter, modifiedIndices);
		String actualResult = String.join("\n", markdowns);
		assertEquals (getOutputHeading2 (), actualResult);
	}
	
	@Test
	 void testApplyFormatHeading3 () {
		String[] lines = getTestInputForHeading3().split("\\R");
		List<String> markdowns = Arrays.stream(lines).collect(Collectors.toList());
	
		List<Integer> modifiedIndices = new ArrayList <> ();
		headingFormatHandler.applyFormat(markdowns, markdownToHtmlFormatter, modifiedIndices);
		String actualResult = String.join("\n", markdowns);
		assertEquals (getOutputHeading3 (), actualResult);
	}
	
	@Test
	 void testApplyFormatHeading4 () {
		String[] lines = getTestInputForHeading4().split("\\R");
		List<String> markdowns = Arrays.stream(lines).collect(Collectors.toList());
	
		List<Integer> modifiedIndices = new ArrayList <> ();
		headingFormatHandler.applyFormat(markdowns, markdownToHtmlFormatter, modifiedIndices);
		String actualResult = String.join("\n", markdowns);
		assertEquals (getOutputHeading4 (), actualResult);
	}
	
	@Test
	 void testApplyFormatHeading5 () {
		String[] lines = getTestInputForHeading5().split("\\R");
		List<String> markdowns = Arrays.stream(lines).collect(Collectors.toList());
	
		List<Integer> modifiedIndices = new ArrayList <> ();
		headingFormatHandler.applyFormat(markdowns, markdownToHtmlFormatter, modifiedIndices);
		String actualResult = String.join("\n", markdowns);
		assertEquals (getOutputHeading5 (), actualResult);
	}
	
	@Test
	 void testApplyFormatHeading6 () {
		String[] lines = getTestInputForHeading6().split("\\R");
		List<String> markdowns = Arrays.stream(lines).collect(Collectors.toList());
	
		List<Integer> modifiedIndices = new ArrayList <> ();
		headingFormatHandler.applyFormat(markdowns, markdownToHtmlFormatter, modifiedIndices);
		String actualResult = String.join("\n", markdowns);
		assertEquals (getOutputHeading6 (), actualResult);
	}
	
	@Test
	public void testInvalidHeader () {
		String[] lines = getInvalidTestInput().split("\\R");
		List<String> markdowns = Arrays.stream(lines).collect(Collectors.toList());
	
		List<Integer> modifiedIndices = new ArrayList <> ();
		headingFormatHandler.applyFormat(markdowns, markdownToHtmlFormatter, modifiedIndices);
		String actualResult = String.join("\n", markdowns);
		assertEquals (getInvalidTestInput (), actualResult);
	}
	
	@Test
	public void testMoreThanSixHashesHeader () {
		String[] lines = getMoreThanSixHashesTestInput().split("\\R");
		List<String> markdowns = Arrays.stream(lines).collect(Collectors.toList());
	
		List<Integer> modifiedIndices = new ArrayList <> ();
		headingFormatHandler.applyFormat(markdowns, markdownToHtmlFormatter, modifiedIndices);
		String actualResult = String.join("\n", markdowns);
		assertEquals (getMoreThanSixHashesTestInput (), actualResult);
	}
	private String getTestInputForHeading1 () {
		return "# Sample Document\n"
				+ "\n"
				+ "Hello!";
	}
	private String getTestInputForHeading2 () {
		return "## Sample Document\n"
				+ "\n"
				+ "Hello!";
	}
	private String getTestInputForHeading3 () {
		return "### Sample Document\n"
				+ "\n"
				+ "Hello!";
	}
	private String getTestInputForHeading4 () {
		return "#### Sample Document\n"
				+ "\n"
				+ "Hello!";
	}
	private String getTestInputForHeading5 () {
		return "##### Sample Document\n"
				+ "\n"
				+ "Hello!";
	}
	private String getTestInputForHeading6 () {
		return "###### Sample Document\n"
				+ "\n"
				+ "Hello!";
	}
	
	private String getInvalidTestInput () {
		return "#Sample Document\n"
				+ "\n"
				+ "Hello!";
	}
	
	private String getMoreThanSixHashesTestInput () {
		return "####### Sample Document\n"
				+ "\n"
				+ "Hello!";
	}
	
	private String getOutputHeading1 () {
		return "<h1>Sample Document</h1>\n"
				+ "\n"
				+ "Hello!";
	}
	
	private String getOutputHeading2 () {
		return "<h2>Sample Document</h2>\n"
				+ "\n"
				+ "Hello!";
	}
	
	private String getOutputHeading3 () {
		return "<h3>Sample Document</h3>\n"
				+ "\n"
				+ "Hello!";
	}
	
	private String getOutputHeading4 () {
		return "<h4>Sample Document</h4>\n"
				+ "\n"
				+ "Hello!";
	}
	
	private String getOutputHeading5 () {
		return "<h5>Sample Document</h5>\n"
				+ "\n"
				+ "Hello!";
	}
	
	private String getOutputHeading6 () {
		return "<h6>Sample Document</h6>\n"
				+ "\n"
				+ "Hello!";
	}
	
	
		
}
