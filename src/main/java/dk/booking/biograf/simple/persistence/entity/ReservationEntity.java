package dk.booking.biograf.simple.persistence.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by birol on 21-01-2026
 */

@Entity
@Table(name = "reservation")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "oprettet_tid", nullable = false)
    private OffsetDateTime oprettetTid;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationPostEntity> posts = new ArrayList<>();

    public ReservationEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public OffsetDateTime getOprettetTid() { return oprettetTid; }
    public void setOprettetTid(OffsetDateTime oprettetTid) { this.oprettetTid = oprettetTid; }

    public List<ReservationPostEntity> getPosts() { return posts; }
    public void setPosts(List<ReservationPostEntity> lines) { this.posts = posts; }
}
