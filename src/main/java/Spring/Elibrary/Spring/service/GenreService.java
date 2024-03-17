package Spring.Elibrary.Spring.service;

import Spring.Elibrary.Spring.dto.GenreDTO;

public interface GenreService {

    GenreDTO addGenre (GenreDTO genreDTO);

    GenreDTO editGenre (GenreDTO genreDTO, Long genreId);

    GenreDTO removeGenre (Long id);
}
