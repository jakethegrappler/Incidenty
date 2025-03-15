package cz.cvut.fel.incidenty.rest.controller;

import cz.cvut.fel.incidenty.dto.DiagramDto;
import cz.cvut.fel.incidenty.model.Diagram;
import cz.cvut.fel.incidenty.service.DiagramService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/diagram")
@RestController
public class DiagramController {

    private final DiagramService diagramService;

    public DiagramController(DiagramService diagramService) {
        this.diagramService = diagramService;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diagram> createDiagram(@RequestBody DiagramDto diagramDto) {
        return ResponseEntity.ok(diagramService.createDiagram(diagramDto));
    }
}
