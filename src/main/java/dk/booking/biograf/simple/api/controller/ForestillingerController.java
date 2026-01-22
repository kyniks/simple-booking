package dk.booking.biograf.simple.api.controller;

import dk.booking.biograf.simple.api.dto.ForestillingDto;
import dk.booking.biograf.simple.service.ForestillingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
/**
 * Created by birol on 19-01-2026 at 21:44
 */

@RestController
@RequestMapping("/biograf/api/forestillinger")
public class ForestillingerController {

    private final ForestillingService forestillingService;

    public ForestillingerController(ForestillingService forestillingService) {
        this.forestillingService = forestillingService;
    }

    @GetMapping
    public List<ForestillingDto> getForestilinger( @RequestParam(name = "dato", required = false)
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dato
    ) {
        return forestillingService.listForestilinger(dato);
    }

    @GetMapping("/{id}")
    public ForestillingDto getForestillingById(@PathVariable long id) {
        return forestillingService.findForestillingById(id);
    }
}
