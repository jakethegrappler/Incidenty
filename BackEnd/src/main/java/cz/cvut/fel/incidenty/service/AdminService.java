package cz.cvut.fel.incidenty.service;


import cz.cvut.fel.incidenty.dto.AdminDto;
import cz.cvut.fel.incidenty.mapper.AdminMapper;
import cz.cvut.fel.incidenty.model.Admin;
import cz.cvut.fel.incidenty.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    public AdminService(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    public Admin createAdmin(AdminDto adminDto) {
        Admin admin = adminMapper.toEntity(adminDto);

        adminRepository.save(admin);
        return admin;
    }
}
