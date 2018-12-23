package com.mcnichol.vault;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    @LocalServerPort
    private int port;

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void name() {


        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + port + "/", String.class);

        assertThat(forEntity.getStatusCode(), equalTo(HttpStatus.OK));

        assertThat(Objects.requireNonNull(forEntity.getHeaders().get("MyVaultNamespace")).get(0), equalTo("MyValue"));

    }
}