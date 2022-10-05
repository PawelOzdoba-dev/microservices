package pl.app.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordMailDto {
    private String firstname;
    private String surname;
    private String link;
}
