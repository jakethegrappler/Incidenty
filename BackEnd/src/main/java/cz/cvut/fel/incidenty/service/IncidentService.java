package cz.cvut.fel.incidenty.service;

import cz.cvut.fel.incidenty.dto.IncidentDto;
import cz.cvut.fel.incidenty.mapper.IncidentMapper;
import cz.cvut.fel.incidenty.model.Incident;
import cz.cvut.fel.incidenty.repository.IncidentRepository;
import org.springframework.stereotype.Service;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final IncidentMapper incidentMapper;

    public IncidentService(IncidentRepository incidentRepository, IncidentMapper incidentMapper) {
        this.incidentRepository = incidentRepository;
        this.incidentMapper = incidentMapper;
    }

    public Incident createIncident(IncidentDto incidentDto) {
        Incident incident = incidentMapper.toEntity(incidentDto);
        return incidentRepository.save(incident);
    }
}
