//package cz.cvut.fel.incidenty.service;
//
//import cz.cvut.fel.incidenty.dto.TypeDto;
//import cz.cvut.fel.incidenty.mapper.TypeMapper;
//import cz.cvut.fel.incidenty.model.Type;
//import cz.cvut.fel.incidenty.repository.TypeRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TypeService {
//
//    private final TypeRepository typeRepository;
//    private final TypeMapper typeMapper;
//
//    public TypeService(TypeRepository typeRepository, TypeMapper typeMapper) {
//        this.typeRepository = typeRepository;
//        this.typeMapper = typeMapper;
//    }
//
//    public Type createType(TypeDto typeDto) {
//        Type type = typeMapper.toEntity(typeDto);
//        return typeRepository.save(type);
//    }
//}
