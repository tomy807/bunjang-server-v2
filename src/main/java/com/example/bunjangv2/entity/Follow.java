package com.example.bunjangv2.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="follow")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long id;

    @JoinColumn(name = "from_user_id")
    @ManyToOne
    private User fromUser;

    @JoinColumn(name = "to_user_id")
    @ManyToOne
    private User toUser;

    public Follow(User fromUser, User toUser) {
        this.fromUser = fromUser;
        fromUser.getFollowings().add(this);
        this.toUser = toUser;
        toUser.getFollowers().add(this);
    }
}
