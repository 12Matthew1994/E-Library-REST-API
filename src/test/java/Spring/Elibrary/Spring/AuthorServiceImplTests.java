package Spring.Elibrary.Spring;

import Spring.Elibrary.Spring.constant.Gendre;
import Spring.Elibrary.Spring.dto.AuthorDTO;
import Spring.Elibrary.Spring.dto.mapper.AuthorMapper;
import Spring.Elibrary.Spring.entity.AuthorEntity;
import Spring.Elibrary.Spring.entity.repository.AuthorRepository;
import Spring.Elibrary.Spring.service.AuthorServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceImplTests {

    @Mock
    private AuthorMapper authorMapper;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    public void testAddAuthor() {
        AuthorDTO inputDTO = new AuthorDTO();
        AuthorEntity savedEntity = new AuthorEntity();
        when(authorMapper.toEntity(inputDTO)).thenReturn(savedEntity);
        when(authorRepository.save(savedEntity)).thenReturn(savedEntity);
        when(authorMapper.toDTO(savedEntity)).thenReturn(inputDTO);

        AuthorDTO result = authorService.addAuthor(inputDTO);

        assertNotNull(result);
    }

    @Test
    public void testGetAuthor() {
        // Arrange
        Long authorId = 1L;
        AuthorEntity mockEntity = new AuthorEntity();
        mockEntity.setId(authorId);

        AuthorDTO mockDTO = new AuthorDTO();
        mockDTO.setId(authorId);

        when(authorRepository.getReferenceById(authorId)).thenReturn(mockEntity);
        when(authorMapper.toDTO(mockEntity)).thenReturn(mockDTO);


        AuthorDTO result = authorService.getAuthor(authorId);


        assertEquals(mockDTO, result);
        verify(authorRepository, times(1)).getReferenceById(authorId);
        verify(authorMapper, times(1)).toDTO(mockEntity);
    }

    @Test
    public void testGetAllAuthors() {

        Gendre gendre = Gendre.female;
        int limit = 10;


        List<AuthorEntity> mockEntities = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            AuthorEntity entity = new AuthorEntity();
            entity.setGendre(gendre);
            mockEntities.add(entity);
        }
        Page<AuthorEntity> mockPage = new PageImpl<>(mockEntities);


        when(authorRepository.getAllByGendre(gendre, PageRequest.of(0, limit))).thenReturn(mockPage);
        when(authorMapper.toDTO(any(AuthorEntity.class))).thenAnswer(invocation -> {
            AuthorEntity argument = invocation.getArgument(0);
            AuthorDTO dto = new AuthorDTO();
            dto.setId(argument.getId());
            dto.setName(argument.getName());

            return dto;
        });


        List<AuthorDTO> result = authorService.getAllAuthors(gendre, limit);


        assertEquals(limit, result.size());
        verify(authorRepository, times(1)).getAllByGendre(gendre, PageRequest.of(0, limit));
        verify(authorMapper, times(limit)).toDTO(any(AuthorEntity.class));
    }

    @Test
    public void testEditAuthor_Success() {
        // Arrange
        Long id = 1L;
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(id);
        AuthorEntity existingEntity = new AuthorEntity();
        existingEntity.setId(id);
        AuthorEntity savedEntity = new AuthorEntity();
        savedEntity.setId(id);


        when(authorRepository.existsById(id)).thenReturn(true);
        when(authorMapper.toEntity(authorDTO)).thenReturn(existingEntity);
        when(authorRepository.save(existingEntity)).thenReturn(savedEntity);
        when(authorMapper.toDTO(savedEntity)).thenReturn(authorDTO);


        AuthorDTO result = authorService.editAuthor(authorDTO, id);


        assertNotNull(result);
        assertEquals(Optional.of(id), result.getId());
        verify(authorRepository, times(1)).existsById(id);
        verify(authorMapper, times(1)).toEntity(authorDTO);
        verify(authorRepository, times(1)).save(existingEntity);
        verify(authorMapper, times(1)).toDTO(savedEntity);
    }

    @Test
    public void testEditAuthor_EntityNotFoundException() {

        Long id = 1L;
        AuthorDTO authorDTO = new AuthorDTO();


        when(authorRepository.existsById(id)).thenReturn(false);


        assertThrows(EntityNotFoundException.class, () -> authorService.editAuthor(authorDTO, id));
        verify(authorRepository, times(1)).existsById(id);
        verify(authorMapper, never()).toEntity(authorDTO);
        verify(authorRepository, never()).save(any());
        verify(authorMapper, never()).toDTO(any());
    }
}


