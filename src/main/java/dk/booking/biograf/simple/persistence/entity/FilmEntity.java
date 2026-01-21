package dk.booking.biograf.simple.persistence.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.query.JpqlQueryBuilder;

/**
 * Created by birol on 21-01-2026
 *
 */

@Table(name = "film")
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "varighed_min", nullable = false)
    private Integer varighedMinuter;

    public FilmEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getVarighedMinuter() { return varighedMinuter; }
    public void setVarighedMinuter(Integer varighedMinuter) { this.varighedMinuter = varighedMinuter; }
}
