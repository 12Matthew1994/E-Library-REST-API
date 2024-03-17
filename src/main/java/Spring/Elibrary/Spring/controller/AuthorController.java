package Spring.Elibrary.Spring.controller;

import Spring.Elibrary.Spring.constant.Gendre;
import Spring.Elibrary.Spring.dto.AuthorDTO;
import Spring.Elibrary.Spring.dto.mapper.AuthorMapper;
import Spring.Elibrary.Spring.service.AuthorService;
import Spring.Elibrary.Spring.service.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController {

    @Autowired
    private AuthorServiceImpl authorService;




    @PostMapping({"/author", "/author/"})
    public AuthorDTO addAuthor (@RequestBody AuthorDTO authorDTO){
        return authorService.addAuthor(authorDTO);
    }

    @GetMapping(value = {"/male", "/male/"})
    public List<AuthorDTO> getMaleAuthor (@RequestParam (required = false, defaultValue = Integer.MAX_VALUE+"") int limit){
        return authorService.getAllAuthors(Gendre.male, limit);
    }

    @GetMapping(value = {"/female", "/female/"})
    public List<AuthorDTO> getFemaleAuthor (@RequestParam(required = false, defaultValue = Integer.MAX_VALUE+"") int limit){
        return authorService.getAllAuthors(Gendre.female, limit);
    }

    @GetMapping({"/author/{authorId}", "/author/{authorId}/"})
    public AuthorDTO getAuthor (@PathVariable Long authorId){
        return authorService.getAuthor(authorId);
    }

    @PutMapping({"/author/{authorId}", "/author/{authorId}/"})
    public AuthorDTO editAuthor (@RequestBody AuthorDTO authorDTO, @PathVariable Long authorId){
        return authorService.editAuthor(authorDTO, authorId);
    }

    @DeleteMapping({"/author/{authorId}", "/author/{authorId}/"})
    public AuthorDTO removeAuthor (@PathVariable Long authorId){
        return authorService.removeAuthor(authorId);
    }

}
