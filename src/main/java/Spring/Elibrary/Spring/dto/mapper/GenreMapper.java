package Spring.Elibrary.Spring.dto.mapper;

import Spring.Elibrary.Spring.dto.GenreDTO;
import Spring.Elibrary.Spring.entity.GenreEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDTO toDTO (GenreEntity source);

    GenreEntity toEntity (GenreDTO source);
}
