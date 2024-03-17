package Spring.Elibrary.Spring.dto.mapper;

import Spring.Elibrary.Spring.dto.AuthorDTO;
import Spring.Elibrary.Spring.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDTO toDTO (AuthorEntity source);

    AuthorEntity toEntity (AuthorDTO source);
}
