package craft.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import craft.demo.dao.FormattingRepository;
import craft.demo.formatter.MarkdownToHtmlConvertingService;
import craft.demo.formatter.MarkdownToHtmlConvertingServiceImpl;
import craft.demo.handler.HeadingFormatHandler;
import craft.demo.handler.UnformattedTextHandler;
import craft.demo.handler.UrlFormatHandler;

@Configuration
public class ServiceConfiguration  extends RepositoryConfiguration{
	
	@Bean
	public UnformattedTextHandler unformattedTextHandler () {
		return new UnformattedTextHandler ();
	}
	
	@Bean
	public UrlFormatHandler urlFormatHandler (UnformattedTextHandler unformattedTextHandler) {
		return new UrlFormatHandler (unformattedTextHandler);
	}
	@Bean
	public HeadingFormatHandler headingFormatHandler (UrlFormatHandler urlFormatHandler) {
		return new HeadingFormatHandler (urlFormatHandler);
	}
	
	@Bean 
	public MarkdownToHtmlConvertingService markdownToHtmlConvertingService (FormattingRepository formattingRepository,HeadingFormatHandler headingFormatHandler) {
		return new MarkdownToHtmlConvertingServiceImpl (formattingRepository, headingFormatHandler);
	}
	

}
