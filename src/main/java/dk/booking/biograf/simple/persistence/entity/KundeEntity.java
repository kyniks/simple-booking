package dk.booking.biograf.simple.persistence.entity;
import jakarta.persistence.*;

/**
 * Created by birol on 21-01-2026
 */

@Entity
@Table(name = "kunde")
public class KundeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String navn;

    @Column(nullable = false, unique = true)
    private String email;

    public KundeEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNavn() { return navn; }
    public void setNavn(String name) { this.navn = navn; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
