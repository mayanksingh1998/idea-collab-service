package com.finbox.idea_collab_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

@Entity
@Table(name = "collaboration_request", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"idea_id", "employee_id"})
})
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@AllArgsConstructor
@Builder
public class CollaborationRequest {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idea_id")
    private Idea idea;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;


    @CreationTimestamp
    @Column(name = "request_at", nullable = false)
    private Timestamp requestAt;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RequestStatus status;
}

