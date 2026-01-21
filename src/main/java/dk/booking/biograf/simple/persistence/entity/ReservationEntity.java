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

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime oprettetDato;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "kunde_id", nullable = false)
    private KundeEntity kunde;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationPostEntity> posts = new ArrayList<>();

    public ReservationEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public OffsetDateTime getOprettetDato() { return oprettetDato; }
    public void setOprettetDato(OffsetDateTime OprettetDato) { this.oprettetDato = oprettetDato; }

    public List<ReservationPostEntity> getPosts() { return posts; }
    public void setPosts(List<ReservationPostEntity> lines) { this.posts = posts; }

    public KundeEntity getKunde() {
        return kunde;
    }

    public void setKunde(KundeEntity kunde) {
        this.kunde = kunde;
    }
}
