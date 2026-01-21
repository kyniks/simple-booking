package dk.booking.biograf.simple.persistence.entity;

import jakarta.persistence.*;

/**
 * Created by birol on 21-01-2026
 */

@Entity
@Table(name = "reservation_post")
public class ReservationPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false)
    private ReservationEntity reservation;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "forestilling_id", nullable = false)
    private ForestillingEntity forestilling;

    @Column(name = "antal_pladser", nullable = false)
    private Integer antalPladser;

    public ReservationPostEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ReservationEntity getReservation() { return reservation; }
    public void setReservation(ReservationEntity reservation) { this.reservation = reservation; }

    public ForestillingEntity getForestilling() { return forestilling; }
    public void setforestilling(ForestillingEntity forestilling) { this.forestilling = forestilling; }

    public Integer getAntalPladser() { return antalPladser; }
    public void setAntalPladser(Integer seatCount) { this.antalPladser = antalPladser; }
}
