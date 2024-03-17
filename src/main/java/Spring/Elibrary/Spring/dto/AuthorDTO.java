package Spring.Elibrary.Spring.dto;

import Spring.Elibrary.Spring.constant.Gendre;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {

    @JsonProperty("_id")
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotNull
    private Date birthdate;
    @NotBlank
    private String country;

    private Gendre gendre;
}
