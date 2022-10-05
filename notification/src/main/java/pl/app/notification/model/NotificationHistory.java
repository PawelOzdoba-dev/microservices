package pl.app.notification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)//audyt
@Builder
public class NotificationHistory {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String subject;
    @Lob
    private String body;
    private String receiver;
    @Enumerated(EnumType.STRING)
    private MailStatus status;
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    private int counter;
}
