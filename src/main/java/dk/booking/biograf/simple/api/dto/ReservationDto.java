package dk.booking.biograf.simple.api.dto;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Created by birol on 19-01-2026 at 20:58
 */

public class ReservationDto {

    private Long id;
    private OffsetDateTime oprettetTid;
    private List<ReservationsPostDto> posts;

    public ReservationDto() {}

    public ReservationDto(Long id, OffsetDateTime oprettetTid, List<ReservationsPostDto> posts) {
        this.id = id;
        this.oprettetTid = oprettetTid;
        this.posts = posts;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public OffsetDateTime getOprettetTid() { return oprettetTid; }
    public void setOprettetTid(OffsetDateTime oprettetTid) { this.oprettetTid = oprettetTid; }

    public List<ReservationsPostDto> getPosts() { return posts; }
    public void setPosts(List<ReservationsPostDto> posts) { this.posts = posts; }
}
