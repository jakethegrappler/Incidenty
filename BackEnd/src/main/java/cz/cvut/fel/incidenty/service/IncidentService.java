package cz.cvut.fel.incidenty.service;

import cz.cvut.fel.incidenty.dto.IncidentDto;
import cz.cvut.fel.incidenty.mapper.IncidentMapper;
import cz.cvut.fel.incidenty.model.Incident;
import cz.cvut.fel.incidenty.model.User;
import cz.cvut.fel.incidenty.repository.IncidentRepository;
import cz.cvut.fel.incidenty.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final IncidentMapper incidentMapper;
    private final UserRepository userRepository;


    public IncidentService(IncidentRepository incidentRepository, IncidentMapper incidentMapper, UserRepository userRepository) {
        this.incidentRepository = incidentRepository;
        this.incidentMapper = incidentMapper;
        this.userRepository = userRepository;

    }

    public Incident updateIncident(Long id, IncidentDto updatedIncidentDto) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incident nenalezen"));

        incident.setDetail(updatedIncidentDto.detail());
        incident.setSolution(updatedIncidentDto.solution());
        incident.setNote(updatedIncidentDto.note());
        incident.setIssueDate(updatedIncidentDto.issueDate());
        incident.setIzs(updatedIncidentDto.izs());
        incident.setVerified(updatedIncidentDto.verified());

        return incidentRepository.save(incident);
    }

    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    public Incident createIncident(IncidentDto dto, MultipartFile photo) {
        Incident incident = incidentMapper.toEntity(dto);
        incident.setCustomPhoneNumber(dto.customPhoneNumber());
        incident.setVerified(false); // 🆕 výchozí hodnota

        // Uložení bez fotky, kvůli ID
        Incident savedIncident = incidentRepository.save(incident);

        if (photo != null && !photo.isEmpty()) {
            try {
                String extension = photo.getOriginalFilename()
                        .substring(photo.getOriginalFilename().lastIndexOf('.'));
                String filename = "incident_" + savedIncident.getId() + extension;
                Path path = Paths.get("uploads", filename);
                Files.createDirectories(path.getParent());
                Files.write(path, photo.getBytes());

                savedIncident.setPhotoPath("uploads/" + filename);
                incidentRepository.save(savedIncident); // ulož s fotkou
            } catch (IOException e) {
                throw new RuntimeException("Chyba při ukládání souboru", e);
            }
        }
        List<User> recipients = userRepository.findAll().stream()
                .filter(user -> user.getRole().toString().equals("ROLE_ADMIN") || user.getRole().toString().equals("ROLE_EMPLOYEE"))
                .toList();

        for (User user : recipients) {
            user.getNotifications().add(savedIncident.getId());
        }

        userRepository.saveAll(recipients);

        return savedIncident;
    }
}
