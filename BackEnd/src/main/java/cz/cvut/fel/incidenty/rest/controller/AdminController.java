package cz.cvut.fel.incidenty.rest.controller;

import cz.cvut.fel.incidenty.dto.AdminDto;
import cz.cvut.fel.incidenty.model.Admin;
import cz.cvut.fel.incidenty.service.AdminService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Admin> createAdmin(@RequestBody AdminDto adminDto) {
        return ResponseEntity.ok(adminService.createAdmin(adminDto));
    }
}
