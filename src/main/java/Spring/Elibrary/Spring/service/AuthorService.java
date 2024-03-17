package Spring.Elibrary.Spring.service;

import Spring.Elibrary.Spring.constant.Gendre;
import Spring.Elibrary.Spring.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
    AuthorDTO addAuthor (AuthorDTO authorDTO);

    AuthorDTO getAuthor (Long id);

    List<AuthorDTO> getAllAuthors(Gendre gendre, int limit);

    AuthorDTO editAuthor (AuthorDTO authorDTO, Long id);

    AuthorDTO removeAuthor (Long id);
}
