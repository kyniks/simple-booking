package dk.booking.biograf.simple.api.dto;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
/**
 * Created by birol on 19-01-2026 at 20:55
 */

public class OpretReservationRequestDto {

    @NotEmpty
    private List<@Valid ReservationsPostDto> posts;

    public OpretReservationRequestDto() {}

    public OpretReservationRequestDto(List<ReservationsPostDto> posts) {
        this.posts = posts;
    }

    public List<ReservationsPostDto> getPosts() { return posts; }
    public void setPosts(List<ReservationsPostDto> posts) { this.posts = posts; }
}
