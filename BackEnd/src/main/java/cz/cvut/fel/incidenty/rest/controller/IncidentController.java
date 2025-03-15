package cz.cvut.fel.incidenty.rest.controller;

import cz.cvut.fel.incidenty.dto.IncidentDto;
import cz.cvut.fel.incidenty.model.Incident;
import cz.cvut.fel.incidenty.service.IncidentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/incident")
@RestController
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Incident> createIncident(@RequestBody IncidentDto incidentDto) {
        return ResponseEntity.ok(incidentService.createIncident(incidentDto));
    }
}
