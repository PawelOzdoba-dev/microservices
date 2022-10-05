package pl.app.notification.model;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Template {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String subject;
    @Lob
    private String body;
    private String name;
}
