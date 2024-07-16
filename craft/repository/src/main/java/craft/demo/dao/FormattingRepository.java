package craft.demo.dao;

import org.springframework.stereotype.Repository;

import craft.demo.model.Format;

@Repository
public interface FormattingRepository {

	Format getMarkdownToHtmlFormatter ();
}
