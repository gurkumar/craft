package craft.demo.formatter;


import craft.demo.formatter.FormatterDTO;

public interface MarkdownToHtmlConvertingService {
	
	FormatterDTO getMarkdownToHtmlConvertedText (String input);

}
