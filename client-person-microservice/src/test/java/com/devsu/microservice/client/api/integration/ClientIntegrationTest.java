package com.devsu.microservice.client.api.integration;

import com.devsu.microservice.client.api.repository.ClientRepository;
import com.devsu.microservice.client.api.test.TestContainersConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Transactional
class ClientIntegrationTest extends TestContainersConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testCreateClient() throws Exception {
        String clientJson = "{ " +
                "\"name\": \"Juan Pérez\", " +
                "\"gender\": \"Masculino\", " +
                "\"age\": 30, " +
                "\"identification\": \"123456789\", " +
                "\"address\": \"Calle Principal 123\", " +
                "\"phone\": \"555-1234\", " +
                "\"password\": \"contraseñaSegura\", " +
                "\"status\": true " +
                "}";

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Juan Pérez")))
                .andExpect(jsonPath("$.gender", is("Masculino")))
                .andExpect(jsonPath("$.age", is(30)))
                .andExpect(jsonPath("$.identification", is("123456789")))
                .andExpect(jsonPath("$.address", is("Calle Principal 123")))
                .andExpect(jsonPath("$.phone", is("555-1234")))
                .andExpect(jsonPath("$.status", is(true)));

        TestTransaction.flagForRollback();
        TestTransaction.end();
    }
}
