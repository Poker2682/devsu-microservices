package com.devsu.microservice.client.api.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ClientTest {

    @Test
    void testGettersAndSetters() {
        Client client = new Client();

        client.setPassword("contraseñaSegura");
        client.setStatus(true);
        client.setName("Juan Pérez");
        client.setGender("Masculino");
        client.setAge(30);
        client.setIdentification("123456789");
        client.setAddress("Calle Principal 123");
        client.setPhone("555-1234");

        assertThat(client.getPassword()).isEqualTo("contraseñaSegura");
        assertThat(client.getStatus()).isTrue();
        assertThat(client.getName()).isEqualTo("Juan Pérez");
        assertThat(client.getGender()).isEqualTo("Masculino");
        assertThat(client.getAge()).isEqualTo(30);
        assertThat(client.getIdentification()).isEqualTo("123456789");
        assertThat(client.getAddress()).isEqualTo("Calle Principal 123");
        assertThat(client.getPhone()).isEqualTo("555-1234");
    }

    @Test
    void testCopy() {
        Client source = new Client();
        source.setPassword("contraseñaSegura");
        source.setStatus(true);
        source.setName("María López");
        source.setGender("Femenino");
        source.setAge(25);
        source.setIdentification("987654321");
        source.setAddress("Avenida Secundaria 456");
        source.setPhone("555-5678");

        Client target = new Client();
        target.copy(source);

        assertThat(target.getPassword()).isEqualTo("contraseñaSegura");
        assertThat(target.getStatus()).isTrue();
        assertThat(target.getName()).isEqualTo("María López");
        assertThat(target.getGender()).isEqualTo("Femenino");
        assertThat(target.getAge()).isEqualTo(25);
        assertThat(target.getIdentification()).isEqualTo("987654321");
        assertThat(target.getAddress()).isEqualTo("Avenida Secundaria 456");
        assertThat(target.getPhone()).isEqualTo("555-5678");
    }

    @Test
    void testPartialCopy() {
        Client source = new Client();
        source.setName("María López");
        source.setGender("Femenino");

        Client target = new Client();
        target.setPassword("contraseñaInicial");
        target.setStatus(false);
        target.setAge(30);
        target.setIdentification("123456789");
        target.setAddress("Calle Principal 123");
        target.setPhone("555-1234");

        target.copy(source);

        assertThat(target.getPassword()).isEqualTo("contraseñaInicial");
        assertThat(target.getStatus()).isFalse();
        assertThat(target.getName()).isEqualTo("María López");
        assertThat(target.getGender()).isEqualTo("Femenino");
        assertThat(target.getAge()).isEqualTo(30);
        assertThat(target.getIdentification()).isEqualTo("123456789");
        assertThat(target.getAddress()).isEqualTo("Calle Principal 123");
        assertThat(target.getPhone()).isEqualTo("555-1234");
    }
}
