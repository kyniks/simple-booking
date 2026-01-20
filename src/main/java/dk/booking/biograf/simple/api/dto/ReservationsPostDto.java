package dk.booking.biograf.simple.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
/**
 * Created by birol on 19-01-2026 at 20:56
 */
public class ReservationsPostDto {
    @NotNull
    private Long forestillingId;

    @NotNull
    @Min(1)
    private Integer antalPladser;

    public ReservationsPostDto() {}

    public ReservationsPostDto(Long forestillingId, Integer antalPladser) {
        this.forestillingId = forestillingId;
        this.antalPladser = antalPladser;
    }

    public Long getForestillingId() { return forestillingId; }
    public void setForestillingId(Long forestillingId) { this.forestillingId = forestillingId; }

    public Integer getAntalPladser() { return antalPladser; }
    public void setAntalPladser(Integer antalPladser) { this.antalPladser = antalPladser; }
}
