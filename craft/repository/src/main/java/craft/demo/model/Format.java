package craft.demo.model;

import java.util.Map;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@Entity
@RequiredArgsConstructor
public class Format {
   final Map<String,String> markDownToHtml;
    
}
