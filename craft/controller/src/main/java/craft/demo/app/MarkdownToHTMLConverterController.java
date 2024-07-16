package craft.demo.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import craft.demo.formatter.FormatterDTO;
import craft.demo.formatter.MarkdownToHtmlConvertingService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MarkdownToHTMLConverterController {
	
	private final MarkdownToHtmlConvertingService markdownToHtmlConvertingService;
	
	
	@PostMapping ("/api/convert/md-file/html-file")
	public ResponseEntity<String> markdownToHtmlConverter (@RequestBody String input) {
		FormatterDTO formatterDTO = markdownToHtmlConvertingService.getMarkdownToHtmlConvertedText(input);
		return ResponseEntity.ok(formatterDTO.getFormattedText());
	}

}
