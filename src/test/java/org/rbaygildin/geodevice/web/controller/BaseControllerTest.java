package org.rbaygildin.geodevice.web.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class BaseControllerTest {

    @Autowired
    private ResourceLoader resourceLoader;

    protected String loadToString(String location) throws IOException {
        Path resourcePath = resourceLoader.getResource(location).getFile().toPath();
        byte[] resourceContent = Files.readAllBytes(resourcePath);
        return new String(resourceContent, StandardCharsets.UTF_8);
    }
}
