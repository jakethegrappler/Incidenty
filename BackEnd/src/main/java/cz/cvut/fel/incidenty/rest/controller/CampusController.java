package cz.cvut.fel.incidenty.rest.controller;

import cz.cvut.fel.incidenty.dto.CampusDto;
import cz.cvut.fel.incidenty.model.Campus;
import cz.cvut.fel.incidenty.service.CampusService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/campus")
@RestController
public class CampusController {


    private final CampusService campusService;

    public CampusController(CampusService campusService) {
        this.campusService = campusService;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Campus> createCampus(@RequestBody CampusDto campusDto) {
        return ResponseEntity.ok(campusService.createCampus(campusDto));
    }
}
