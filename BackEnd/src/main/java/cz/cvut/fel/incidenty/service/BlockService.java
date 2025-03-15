//package cz.cvut.fel.incidenty.service;
//
//import cz.cvut.fel.incidenty.dto.BlockDto;
//import cz.cvut.fel.incidenty.mapper.BlockMapper;
//import cz.cvut.fel.incidenty.model.Block;
//import cz.cvut.fel.incidenty.repository.BlockRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class BlockService {
//
//    private final BlockRepository blockRepository;
//    private final BlockMapper blockMapper;
//
//    public BlockService(BlockRepository blockRepository, BlockMapper blockMapper) {
//        this.blockRepository = blockRepository;
//        this.blockMapper = blockMapper;
//    }
//
//    public Block createBlock(BlockDto blockDto) {
//        Block block = blockMapper.toEntity(blockDto);
//        return blockRepository.save(block);
//    }
//}
