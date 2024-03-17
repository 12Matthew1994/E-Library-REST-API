package Spring.Elibrary.Spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDTO {
    @JsonProperty("_id")
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
