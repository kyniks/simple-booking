package dk.booking.biograf.simple.persistence.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

/**
 * Created by birol on 21-01-2026
 */

@Entity
@Table(name = "forestilling")
public class ForestillingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", nullable = false)
    private FilmEntity film;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sal_id", nullable = false)
    private SalEntity sal;

    @Column(name = "start_tid", nullable = false)
    private OffsetDateTime startTid;

    public ForestillingEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public FilmEntity getFilm() { return film; }
    public void setFilm(FilmEntity film) { this.film = film; }

    public SalEntity getSal() { return sal; }
    public void setSal(SalEntity sal) { this.sal = sal; }

    public OffsetDateTime getStartTid(){ return startTid; }
    public void setStartTid(OffsetDateTime startTid) { this.startTid = startTid; }
}
