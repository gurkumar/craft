package craft.demo.dao;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import craft.demo.model.Format;


public class FormattingRepositoryImpl  implements FormattingRepository{
	
	private static Map<String, String> markdownToHtmlFormatter 
    = Arrays.stream(new String[][] { 
                        { "#", "<h1>{0}</h1>" }, 
                        { "##", "<h2>{0}</h2>" }, 
                        { "###", "<h3>{0}</h3>" }, 
                        { "####", "<h4>{0}</h4>" }, 
                        { "#####", "<h5>{0}</h5>" }, 
                        { "######", "<h6>{0}</h6>" }, 
                        { "unformated", "<p>{0}</p>" },
                        { "unformated_start", "<p>{0}" },
                        { "unformated_end", "{0}</p>" },
                        {"url","<a href=\"{0}\">{1}</a>"}           
                        }) 
          .collect(Collectors.toMap( 
              keyMapper -> keyMapper[0], valueMapper -> valueMapper[1])); 

	@Override
	public Format getMarkdownToHtmlFormatter() {
		return new Format(markdownToHtmlFormatter);
	}

	
   
	

}
