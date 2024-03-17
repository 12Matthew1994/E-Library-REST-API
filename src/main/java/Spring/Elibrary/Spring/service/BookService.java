package Spring.Elibrary.Spring.service;

import Spring.Elibrary.Spring.dto.BookDTO;
import Spring.Elibrary.Spring.entity.filter.BookFilter;

import java.util.List;

public interface BookService {

    BookDTO addBook (BookDTO bookDTO);

    BookDTO getBook (Long bookId);

    List <BookDTO> getAllBooks(BookFilter bookFilter);

    BookDTO editBook (BookDTO bookDTO, long bookId);

    BookDTO removeBook (Long bookId);
}
