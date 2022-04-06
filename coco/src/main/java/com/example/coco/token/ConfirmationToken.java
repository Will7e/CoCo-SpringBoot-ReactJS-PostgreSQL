package com.example.coco.token;

import com.example.coco.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "confirmToken")
@NoArgsConstructor
public class ConfirmationToken {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
   private Long id;

    @Column(nullable = false)
   private String token;

    @Column(nullable = false)
   private LocalDateTime createAt;
    @Column(nullable = false)
   private LocalDateTime expiredAt;
   private LocalDateTime confirmAt;



    public ConfirmationToken( String token, LocalDateTime createAt, LocalDateTime expiredAt, User user) {
        this.token = token;
        this.createAt = createAt;
        this.expiredAt = expiredAt;
        this.user = user;
    }
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "users_id"
    )

   public User user;



}
