package dk.booking.biograf.simple.persistence.entity;
import jakarta.persistence.*;

/**
 * Created by birol on 21-01-2026
 */

@Entity
@Table(name = "sal")
public class SalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String navn;

    @Column(nullable = false)
    private Integer kapacitet;

    public SalEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNavn() { return navn; }
    public void setName(String name) { this.navn = navn; }

    public Integer getKapasitet() { return kapacitet; }
    public void setKapasitet(Integer Kapasitet) { this.kapacitet = kapacitet; }
}
