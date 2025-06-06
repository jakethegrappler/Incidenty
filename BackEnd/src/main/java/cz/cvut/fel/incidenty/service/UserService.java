package cz.cvut.fel.incidenty.service;

import cz.cvut.fel.incidenty.dto.UserDto;
import cz.cvut.fel.incidenty.mapper.UserMapper;
import cz.cvut.fel.incidenty.model.Admin;
import cz.cvut.fel.incidenty.model.Employee;
import cz.cvut.fel.incidenty.model.Student;
import cz.cvut.fel.incidenty.model.User;
import cz.cvut.fel.incidenty.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static cz.cvut.fel.incidenty.model.enums.Role.*;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public void register(UserDto dto) {
        User user;

        switch (dto.role()) {
            case ROLE_ADMIN -> {
                Admin admin = userMapper.toAdmin(dto);
                admin.setPassword(passwordEncoder.encode(dto.password()));
                user = admin;
            }
            case ROLE_EMPLOYEE -> {
                Employee employee = userMapper.toEmployee(dto);
                employee.setPassword(passwordEncoder.encode(dto.password()));
                user = employee;
            }
            case ROLE_STUDENT -> {
                Student student = userMapper.toStudent(dto);
                student.setPassword(passwordEncoder.encode(dto.password()));
                user = student;
            }
            default -> throw new IllegalArgumentException("Neznámá role.");
        }

        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Uživatel s emailem " + email + " nenalezen."));

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found."));
    }


    public User login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email + " nenalezen."));
              if (passwordEncoder.matches(password, user.getPassword())) {
                  return user;
              }
              throw new BadCredentialsException("Wrong password");
    }
}
