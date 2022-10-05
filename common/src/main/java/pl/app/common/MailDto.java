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
public class MailDto {
    private String name;
    private String receiver;
    private Map<String, Object> variables;
}
