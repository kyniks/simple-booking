package dk.booking.biograf.simple.service;

import dk.booking.biograf.simple.api.dto.ForestillingDto;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by birol on 21-01-2026 at 18:39
 */
public interface ForestillingService {
    List<ForestillingDto> listForestilinger(LocalDate dato);
    ForestillingDto findForestillingById(long id);
}
