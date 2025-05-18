package cz.cvut.fel.incidenty.rest.controller;

import cz.cvut.fel.incidenty.dto.IncidentDto;
import cz.cvut.fel.incidenty.model.Incident;
import cz.cvut.fel.incidenty.model.enums.Type;
import cz.cvut.fel.incidenty.repository.IncidentRepository;
import cz.cvut.fel.incidenty.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/incident")
@RestController
public class IncidentController {

    private final IncidentService incidentService;

    @Autowired
    private IncidentRepository incidentRepository;


    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Incident> createIncident(
            @RequestPart("incident") IncidentDto incidentDto,
            @RequestPart(value = "photo", required = false) MultipartFile photo
    ) {
        return ResponseEntity.ok(incidentService.createIncident(incidentDto, photo));
    }
    @GetMapping("/all")
    public List<Incident> getAllIncidents() {
        return incidentService.getAllIncidents();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Incident> updateIncident(@PathVariable Long id, @RequestBody IncidentDto updatedIncidentDto) {
        return ResponseEntity.ok(incidentService.updateIncident(id, updatedIncidentDto));
    }

    @GetMapping("/stats/{sector}")
    public ResponseEntity<Map<String, Long>> getIncidentStatsBySector(@PathVariable String sector) {
        List<Incident> incidents = incidentRepository.findByPositionIgnoreCase(sector);

        Map<String, Long> stats = incidents.stream()
                .collect(Collectors.groupingBy(i -> i.getType().name(), Collectors.counting()));


        return ResponseEntity.ok(stats);
    }

    @GetMapping("/incident-types")
    public ResponseEntity<Type[]> getIncidentTypes() {
        return ResponseEntity.ok(Type.values());
    }


}
