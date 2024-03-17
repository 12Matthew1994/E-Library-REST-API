package Spring.Elibrary.Spring.service;

import Spring.Elibrary.Spring.constant.Gendre;
import Spring.Elibrary.Spring.dto.AuthorDTO;
import Spring.Elibrary.Spring.dto.mapper.AuthorMapper;
import Spring.Elibrary.Spring.entity.AuthorEntity;
import Spring.Elibrary.Spring.entity.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    AuthorMapper authorMapper;

    @Autowired
    AuthorRepository authorRepository;


    @Override
    public AuthorDTO addAuthor(AuthorDTO authorDTO) {
        AuthorEntity entity = authorMapper.toEntity(authorDTO);
        AuthorEntity savedEntity = authorRepository.save(entity);
        return authorMapper.toDTO(savedEntity);
    }

    @Override
    public AuthorDTO getAuthor(Long id) {
        AuthorEntity entity = authorRepository.getReferenceById(id);
        return authorMapper.toDTO(entity);
    }

    @Override
    public List<AuthorDTO> getAllAuthors(Gendre gendre, int limit) {
        Page<AuthorEntity> pageOfAuthors = authorRepository.getAllByGendre(gendre, PageRequest.of(0, limit));
        List<AuthorEntity> authorEntities = pageOfAuthors.getContent();

        List<AuthorDTO> authors = new ArrayList<>();
        for (AuthorEntity e : authorEntities){
            authors.add(authorMapper.toDTO(e));
        }
        return authors;
    }

    @Override
    public AuthorDTO editAuthor(AuthorDTO authorDTO, Long id) {
        if(!authorRepository.existsById(id)){
            throw new EntityNotFoundException("Author with id " + id + " wasn't found in the database.");
        }
        AuthorEntity entity = authorMapper.toEntity(authorDTO);
        entity.setId(id);
        AuthorEntity saved = authorRepository.save(entity);
        return authorMapper.toDTO(saved);
    }

    @Override
    public AuthorDTO removeAuthor(Long id) {
        AuthorEntity entity = authorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        AuthorDTO author = authorMapper.toDTO(entity);
        authorRepository.delete(entity);
        return author;
    }
}
