package Spring.Elibrary.Spring.service;

import Spring.Elibrary.Spring.dto.GenreDTO;
import Spring.Elibrary.Spring.dto.mapper.GenreMapper;
import Spring.Elibrary.Spring.entity.GenreEntity;
import Spring.Elibrary.Spring.entity.repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService{

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreRepository genreRepository;


    @Override
    public GenreDTO addGenre(GenreDTO genreDTO) {
        GenreEntity entity = genreMapper.toEntity(genreDTO);
        GenreEntity savedEntity = genreRepository.save(entity);
        return genreMapper.toDTO(savedEntity);
    }

    @Override
    public GenreDTO editGenre(GenreDTO genreDTO, Long genreId) {
        if(!genreRepository.existsById(genreId)){
            throw new EntityNotFoundException("Genre with id " + genreId + " wasn't found in the database.");
        }
        GenreEntity entity = genreMapper.toEntity(genreDTO);
        entity.setId(genreId);
        GenreEntity saved = genreRepository.save(entity);
        return genreMapper.toDTO(saved);
    }

    @Override
    public GenreDTO removeGenre(Long id) {
        GenreEntity entity = genreRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        GenreDTO genre = genreMapper.toDTO(entity);
        genreRepository.delete(entity);
        return genre;
    }
}
