package cn.tuyucheng.taketoday.quarkus.service;

import cn.tuyucheng.taketoday.quarkus.model.Book;
import cn.tuyucheng.taketoday.quarkus.repository.BookRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Transactional
@ApplicationScoped
public class LibraryService {

	@Inject
	BookRepository bookRepository;

	public Set<Book> find(String query) {
		if (query == null) {
			return bookRepository.findAll().stream().collect(toSet());
		}

		return bookRepository.findBy(query).collect(toSet());
	}
}