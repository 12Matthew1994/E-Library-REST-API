package Spring.Elibrary.Spring.service;

import Spring.Elibrary.Spring.dto.BookDTO;
import Spring.Elibrary.Spring.dto.mapper.BookMapper;
import Spring.Elibrary.Spring.entity.BookEntity;
import Spring.Elibrary.Spring.entity.filter.BookFilter;
import Spring.Elibrary.Spring.entity.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        BookEntity book = bookMapper.toEntity(bookDTO);
        BookEntity savedEntity = bookRepository.save(book);
        return bookMapper.toDTO(savedEntity);
    }

    @Override
    public BookDTO getBook(Long bookId) {
        BookEntity book = bookRepository.getReferenceById(bookId);
            return bookMapper.toDTO(book);
    }

    @Override
    public List<BookDTO> getAllBooks(BookFilter bookFilter) {
        List<BookDTO> books = new ArrayList<>();
        for(BookEntity book : bookRepository.findAll(PageRequest.of(0, bookFilter.getLimit()))){
            books.add(bookMapper.toDTO(book));
        }
        return books;
    }

    @Override
    public BookDTO editBook(BookDTO bookDTO, long bookId) {
        if(!bookRepository.existsById(bookId)){
            throw new EntityNotFoundException("Book with id " + bookId + " wasn't found in the database.");
        }
        BookEntity entity = bookMapper.toEntity(bookDTO);
        entity.setId(bookId);
        BookEntity saved = bookRepository.save(entity);
        return bookMapper.toDTO(saved);
    }

    @Override
    public BookDTO removeBook(Long bookId) {
        BookEntity entity = bookRepository.findById(bookId).orElseThrow(EntityNotFoundException::new);
        BookDTO book = bookMapper.toDTO(entity);
        bookRepository.delete(entity);
        return book;
    }
}
