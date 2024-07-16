package craft.demo.formatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import craft.demo.dao.FormattingRepository;
import craft.demo.formatter.FormatterDTO;
import craft.demo.handler.FormatHandler;
import lombok.RequiredArgsConstructor;
import craft.demo.model.Format;

@RequiredArgsConstructor
@Service
public class MarkdownToHtmlConvertingServiceImpl implements MarkdownToHtmlConvertingService{
	private final FormattingRepository formattingRepository;
	private final FormatHandler headingFormatHandler;

	
	@Override
	public FormatterDTO getMarkdownToHtmlConvertedText(String markdown) {
		Format format = formattingRepository.getMarkdownToHtmlFormatter();
		String[] lines = markdown.split("\\R");
		List<String> markdowns = Arrays.stream(lines).collect(Collectors.toList());
		List<Integer>modifiedIndices = new ArrayList <> ();
		headingFormatHandler.applyFormat(markdowns, format.getMarkDownToHtml(), modifiedIndices);
		String formattedText = String.join("\n", markdowns);
		FormatterDTO formatterDTO = new FormatterDTO ();
		formatterDTO.setFormattedText(formattedText);
		return formatterDTO;
	}

}
