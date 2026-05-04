package com.iflag.config;

import com.iflag.entity.User;
import com.iflag.enums.Role;
import com.iflag.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class DataInitializer {

    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if(userRepository.findByEmail("owner@iflag.com").isEmpty()){
                User owner = new User();
                owner.setFullName("Owner");
                owner.setEmail("owner@iflag.com");
                owner.setPassword(passwordEncoder.encode("123456"));
                owner.setPhone("01000000000");
                owner.setRole(Role.OWNER);
                owner.setActive(true);

                userRepository.save(owner);
            }

            if(userRepository.findByEmail("admin@iflag.com").isEmpty()){
                User admin = new User();
                admin.setFullName("Admin");
                admin.setEmail("admin@iflag.com");
                admin.setPassword(passwordEncoder.encode("123456"));
                admin.setPhone("01000000001");
                admin.setRole(Role.ADMIN);
                admin.setActive(true);

                userRepository.save(admin);
            }

            if(userRepository.findByEmail("coach@iflag.com").isEmpty()){
                User coach = new User();
                coach.setFullName("Coach");
                coach.setEmail("coach@iflag.com");
                coach.setPassword(passwordEncoder.encode("123456"));
                coach.setPhone("01000000002");
                coach.setRole(Role.COACH);
                coach.setActive(true);

                userRepository.save(coach);
            }

            if (userRepository.findByEmail("member@iflag.com").isEmpty()) {
                User member = new User();
                member.setFullName("Member");
                member.setEmail("member@iflag.com");
                member.setPassword(passwordEncoder.encode("123456"));
                member.setPhone("01000000003");
                member.setRole(Role.MEMBER);
                member.setActive(true);

                userRepository.save(member);
            }

        };
    }
}
