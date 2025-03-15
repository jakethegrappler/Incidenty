package cz.cvut.fel.incidenty.service;

import cz.cvut.fel.incidenty.dto.CampusDto;
import cz.cvut.fel.incidenty.mapper.CampusMapper;
import cz.cvut.fel.incidenty.model.Campus;
import cz.cvut.fel.incidenty.repository.CampusRepository;
import org.springframework.stereotype.Service;

@Service
public class CampusService {

    private final CampusRepository campusRepository;
    private final CampusMapper campusMapper;

    public CampusService(CampusRepository campusRepository, CampusMapper campusMapper) {
        this.campusRepository = campusRepository;
        this.campusMapper = campusMapper;
    }

    public Campus createCampus(CampusDto campusDto) {
        Campus campus = campusMapper.toEntity(campusDto);
        return campusRepository.save(campus);
    }
}
