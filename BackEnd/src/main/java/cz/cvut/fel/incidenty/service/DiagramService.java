package cz.cvut.fel.incidenty.service;

import cz.cvut.fel.incidenty.dto.DiagramDto;
import cz.cvut.fel.incidenty.mapper.DiagramMapper;
import cz.cvut.fel.incidenty.model.Diagram;
import cz.cvut.fel.incidenty.repository.DiagramRepository;
import org.springframework.stereotype.Service;

@Service
public class DiagramService {

    private final DiagramRepository diagramRepository;
    private final DiagramMapper diagramMapper;

    public DiagramService(DiagramRepository diagramRepository, DiagramMapper diagramMapper) {
        this.diagramRepository = diagramRepository;
        this.diagramMapper = diagramMapper;
    }

    public Diagram createDiagram(DiagramDto diagramDto) {
        Diagram diagram = diagramMapper.toEntity(diagramDto);
        return diagramRepository.save(diagram);
    }
}
