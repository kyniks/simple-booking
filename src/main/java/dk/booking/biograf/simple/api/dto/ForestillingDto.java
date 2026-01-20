package dk.booking.biograf.simple.api.dto;

import java.time.OffsetDateTime;

/**
 * Created by birol on 19-01-2026 at 20:53
 */

public class ForestillingDto {
    private Long id;
    private String filmTitel;
    private OffsetDateTime startTid;
    private Integer totalPladser;
    private Integer ledigePladser;

    public ForestillingDto() {}

    public ForestillingDto(Long id, String filmTitel, OffsetDateTime startTid,
                           Integer totalPladser, Integer ledigePladser) {
        this.id = id;
        this.filmTitel = filmTitel;
        this.startTid = startTid;
        this.totalPladser = totalPladser;
        this.ledigePladser = ledigePladser;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFilmTitel() { return filmTitel; }
    public void setFilmTitel(String filmTitel) { this.filmTitel = filmTitel; }

    public OffsetDateTime getStartTid() { return startTid; }
    public void setStartTid(OffsetDateTime startTid) { this.startTid = startTid; }

    public Integer getTotalPladser() { return totalPladser; }
    public void setTotalPladser(Integer totalPladser) { this.totalPladser = totalPladser; }

    public Integer getLedigePladser() { return ledigePladser; }
    public void setLedigePladser(Integer ledigePladser) { this.ledigePladser = ledigePladser; }
}
