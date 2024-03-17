package Spring.Elibrary.Spring;

import Spring.Elibrary.Spring.dto.BookDTO;
import Spring.Elibrary.Spring.dto.mapper.BookMapper;
import Spring.Elibrary.Spring.entity.BookEntity;
import Spring.Elibrary.Spring.entity.filter.BookFilter;
import Spring.Elibrary.Spring.entity.repository.BookRepository;
import Spring.Elibrary.Spring.service.BookServiceImpl;
import jakarta.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookServiceIntegrationTests {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private BookRepository bookRepository;



    @Test
    public void testAddBook(){

        BookDTO inputDTO = new BookDTO();
        BookEntity savedEntity = new BookEntity();

        when(bookMapper.toEntity(inputDTO)).thenReturn(new BookEntity());
        when(bookRepository.save(any(BookEntity.class))) .thenReturn(savedEntity);
        when(bookMapper.toDTO(savedEntity)).thenReturn(inputDTO);

        BookDTO result = bookService.addBook(inputDTO);

        assertNotNull(result);
        assertEquals(result, inputDTO);
        verify(bookRepository, times(1)).save(any(BookEntity.class));
        verify(bookRepository, times(1)).save(any(BookEntity.class));
        verify(bookMapper, times(1)).toDTO(savedEntity);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetBook() {
        Long bookId = 1L;
        BookEntity bookEntity = new BookEntity();
        BookDTO expectedBookDTO = new BookDTO();
        when(bookRepository.getReferenceById(bookId)).thenReturn(bookEntity);
        when(bookMapper.toDTO(bookEntity)).thenReturn(expectedBookDTO);

        BookDTO actualBookDTO = bookService.getBook(bookId);

        assertEquals(expectedBookDTO, actualBookDTO);
    }

    @Test
    public void testGetAllBooks() {
        int limit = 10;
        List<BookEntity> bookEntities = new ArrayList<>();
        List<BookDTO> expectedBookDTOs = new ArrayList<>();
        Page<BookEntity> page = new PageImpl<>(bookEntities);
        when(bookRepository.findAll(PageRequest.of(0, limit))).thenReturn(page);
        when(bookMapper.toDTO(any(BookEntity.class))).thenReturn(new BookDTO());

        BookFilter bookFilter = new BookFilter();
        bookFilter.setLimit(limit);


        List<BookDTO> actualBookDTOs = bookService.getAllBooks(bookFilter);

        assertEquals(expectedBookDTOs.size(), actualBookDTOs.size());
    }

    @Test
    public void testEditBook() {
        Long bookId = 1L;
        BookDTO bookDTO = new BookDTO();
        BookEntity bookEntity = new BookEntity();
        when(bookRepository.existsById(bookId)).thenReturn(true);
        when(bookMapper.toEntity(bookDTO)).thenReturn(bookEntity);
        when(bookRepository.save(bookEntity)).thenReturn(bookEntity);
        when(bookMapper.toDTO(bookEntity)).thenReturn(bookDTO);

        BookDTO updatedBookDTO = bookService.editBook(bookDTO, bookId);

        assertEquals(bookDTO, updatedBookDTO);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testRemoveBook() {
        Long bookId = 1L;
        when(bookRepository.findById(bookId)).thenReturn(java.util.Optional.empty());

        bookService.removeBook(bookId);
    }
}

