package Spring.Elibrary.Spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    @NotNull
    @JsonProperty("_id")
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private LocalDate year;
    @NotBlank
    private String language;
    @NotNull
    private List authorIds;
    private List<AuthorDTO> authors;

    private boolean isAvailable;

    @JsonProperty("isAvailable")
    public boolean isAvailable(){
        return isAvailable;
    }
}
