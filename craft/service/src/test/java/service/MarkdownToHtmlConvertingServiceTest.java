package service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import craft.demo.formatter.FormatterDTO;
import craft.demo.formatter.MarkdownToHtmlConvertingService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MarkdownToHtmlConvertingServiceTest {

	@Autowired
	private  MarkdownToHtmlConvertingService markdownToHtmlConvertingService;
	    
	 @Test
	 public void testMarkdownToHtmlConversion() {
		FormatterDTO formatterDTO =  markdownToHtmlConvertingService.getMarkdownToHtmlConvertedText(getInput());
		assertNotNull (formatterDTO.getFormattedText());
	 }
	 
	 private String getInput () {
		 return "# Sample Document\n"
		 		+ "\n"
		 		+ "Hello!\n"
		 		+ "\n"
		 		+ "This is sample markdown for the [a456](ab)](def) homework assignment.";
	 }
}
