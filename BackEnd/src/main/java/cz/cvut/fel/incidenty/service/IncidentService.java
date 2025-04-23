package cz.cvut.fel.incidenty.service;

import cz.cvut.fel.incidenty.dto.IncidentDto;
import cz.cvut.fel.incidenty.mapper.IncidentMapper;
import cz.cvut.fel.incidenty.model.Incident;
import cz.cvut.fel.incidenty.repository.IncidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final IncidentMapper incidentMapper;

    public IncidentService(IncidentRepository incidentRepository, IncidentMapper incidentMapper) {
        this.incidentRepository = incidentRepository;
        this.incidentMapper = incidentMapper;
    }

    public Incident createIncident(IncidentDto dto, MultipartFile photo) {
        Incident incident = incidentMapper.toEntity(dto);

        if (photo != null && !photo.isEmpty()) {
            try {
                String filename = UUID.randomUUID() + "_" + photo.getOriginalFilename();
                Path path = Paths.get("uploads", filename); // např. složka uploads/
                Files.createDirectories(path.getParent());
                Files.write(path, photo.getBytes());

                incident.setPhotoPath(path.toString());
            } catch (IOException e) {
                throw new RuntimeException("Chyba při ukládání souboru", e);
            }
        }

        return incidentRepository.save(incident);
    }

}
