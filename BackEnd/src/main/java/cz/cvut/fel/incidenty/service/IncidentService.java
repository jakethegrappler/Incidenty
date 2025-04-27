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
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final IncidentMapper incidentMapper;

    public IncidentService(IncidentRepository incidentRepository, IncidentMapper incidentMapper) {
        this.incidentRepository = incidentRepository;
        this.incidentMapper = incidentMapper;
    }

    public Incident updateIncident(Long id, IncidentDto updatedIncidentDto) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incident nenalezen"));

        incident.setDetail(updatedIncidentDto.detail());
        incident.setSolution(updatedIncidentDto.solution());
        incident.setNote(updatedIncidentDto.note());
        incident.setIssueDate(LocalDateTime.now()); // Například automaticky při editaci aktualizujeme issueDate

        return incidentRepository.save(incident);
    }


    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }


    public Incident createIncident(IncidentDto dto, MultipartFile photo) {
        Incident incident = incidentMapper.toEntity(dto);

        incident.setCustomPhoneNumber(dto.customPhoneNumber());


        // Uložení fotky
        if (photo != null && !photo.isEmpty()) {
            try {
                String filename = UUID.randomUUID() + "_" + photo.getOriginalFilename();
                Path path = Paths.get("uploads", filename);
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
