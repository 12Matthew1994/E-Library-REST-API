package Spring.Elibrary.Spring.controller;

import Spring.Elibrary.Spring.dto.GenreDTO;
import Spring.Elibrary.Spring.service.GenreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GenreController {

    @Autowired
    private GenreServiceImpl genreService;

    @PostMapping({"/genre", "/genre/"})
    public GenreDTO addGenre (@RequestBody GenreDTO genreDTO){
        return genreService.addGenre(genreDTO);
    }

    @PutMapping({"/genre/{genreId}", "/genre/{genreId}/"})
    public GenreDTO editGenre(@RequestBody GenreDTO genreDTO, @PathVariable Long id){
        return genreService.editGenre(genreDTO, id);
    }

    @DeleteMapping({"/genre/{genreId}", "/genre/{genreId}/"})
    public GenreDTO removeGenre(@PathVariable Long id){
        return genreService.removeGenre(id);
    }
}
