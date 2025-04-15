package com.finbox.idea_collab_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

@Entity
@Table(name = "idea_reaction", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"idea_id", "employee_id"})
})
@Getter
@Setter
@NoArgsConstructor
@ToString
public class IdeaReaction {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Timestamp votedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "vote_status", nullable = false)
    private VoteStatus voteStatus;
}

