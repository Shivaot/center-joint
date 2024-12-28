package com.hitachi.epdi2.bootstrap;

import com.hitachi.epdi2.entity.Model;
import com.hitachi.epdi2.entity.User;
import com.hitachi.epdi2.repository.ModelRepository;
import com.hitachi.epdi2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


/**
 * Shiva Created on 29/12/24
 */
@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final ModelRepository modelRepository;

    public DataLoader(UserRepository userRepository, ModelRepository modelRepository) {
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public void run(ApplicationArguments args) {

        if (userRepository.count() < 2) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            addUser(passwordEncoder, "qc@mail.in", "ROLE_QC");
            addUser(passwordEncoder, "admin@mail.in", "ROLE_ADMIN");
        }
        initModels();
    }

    private void addUser(PasswordEncoder passwordEncoder, String emails, String role) {
        User user = new User();
        user.setEmail(emails);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode("pass"));
        user.setRole(role);
        userRepository.save(user);
    }

    private void initModels() {
        modelRepository.deleteAll();
        List<String> models = Collections.singletonList("EX200");
        for (String model : models) {
            Model model1 = new Model();
            model1.setModelName(model);
            modelRepository.save(model1);
        }
    }
}
