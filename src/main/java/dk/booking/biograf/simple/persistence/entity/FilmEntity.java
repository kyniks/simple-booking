package dk.booking.biograf.simple.persistence.entity;

import jakarta.persistence.*;

/**
 * Created by birol on 21-01-2026
 *
 */

@Entity
@Table(name = "film")
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titel;

    @Column(name = "varighed_minutter", nullable = false)
    private Integer varighedMinuter;

    public FilmEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitel() { return titel; }
    public void setTitel(String titel) { this.titel = titel; }

    public Integer getVarighedMinuter() { return varighedMinuter; }
    public void setVarighedMinuter(Integer varighedMinuter) { this.varighedMinuter = varighedMinuter; }
}
