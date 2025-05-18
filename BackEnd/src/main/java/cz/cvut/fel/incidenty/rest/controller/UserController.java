package cz.cvut.fel.incidenty.rest.controller;

import cz.cvut.fel.incidenty.dto.AuthRequestDto;
import cz.cvut.fel.incidenty.dto.UserDto;
import cz.cvut.fel.incidenty.model.User;
import cz.cvut.fel.incidenty.repository.UserRepository;
import cz.cvut.fel.incidenty.service.UserService;
import cz.cvut.fel.incidenty.config.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserDto userDto) {
        userService.register(userDto);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.email(), userDto.password())
        );

        UserDetails userDetails = userService.loadUserByUsername(userDto.email());
        String token = jwtUtil.generateToken(userDetails);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequestDto authRequestDto) {
        String email = authRequestDto.email();
        String password = authRequestDto.password();
        User user = userService.login(email,password);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        UserDetails userDetails = userService.loadUserByUsername(email);
        String token = jwtUtil.generateToken(userDetails);
        String role = user.getRole().toString();


        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("role", role);


        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserInfo(@RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.replace("Bearer ", "");
        String email = jwtUtil.extractUsername(token);

        User user = userService.findByEmail(email); // metoda, která vrací entitu User
        return ResponseEntity.ok(user);
    }

    @PutMapping("/notifications/remove/{incidentId}")
    public ResponseEntity<Void> removeNotification(@PathVariable Long incidentId) {
        List<User> notifiedUsers = userRepository.findAll().stream()
                .filter(user -> user.getRole().toString().equals("ROLE_ADMIN") || user.getRole().toString().equals("ROLE_EMPLOYEE"))
                .toList();

        for (User user : notifiedUsers) {
            user.getNotifications().remove(incidentId);
        }

        userRepository.saveAll(notifiedUsers);

        return ResponseEntity.ok().build();
    }


}



