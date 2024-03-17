package Spring.Elibrary.Spring.controller;

import Spring.Elibrary.Spring.dto.BookDTO;
import Spring.Elibrary.Spring.entity.filter.BookFilter;
import Spring.Elibrary.Spring.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @PostMapping({"/book", "/book/"})
    public BookDTO addBook (@RequestBody BookDTO bookDTO){
        return bookService.addBook(bookDTO);
    }

    @GetMapping({"/book/{bookId}", "/book/{bookId}/"})
    public BookDTO getBook (@PathVariable Long bookId){
        return bookService.getBook(bookId);
    }

    @GetMapping({"/book", "/book/"})
    public List <BookDTO> getAllBooks(BookFilter bookFilter){
        return bookService.getAllBooks(bookFilter);
    }

    @PutMapping({"/book/{bookId}", "/book/{bookId}/"})
    public BookDTO editBook (@PathVariable Long id, @RequestBody BookDTO bookDTO){
        return bookService.editBook(bookDTO, id);
    }

    @DeleteMapping({"/book/{bookId}", "/book/{bookId}/"})
    public BookDTO removeBook (@PathVariable Long id){
        return bookService.removeBook(id);
    }
}
