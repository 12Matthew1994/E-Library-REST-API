package Spring.Elibrary.Spring.dto.mapper;

import Spring.Elibrary.Spring.dto.BookDTO;
import Spring.Elibrary.Spring.entity.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO toDTO (BookEntity source);

    BookEntity toEntity (BookDTO source);
}
