package fitnessapp.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivityIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testLogActivity() {
        String requestBody = "{"userId":"123","type":"Running","duration":30,"intensity":"High"}";
        ResponseEntity<String> response = restTemplate.postForEntity(
                "/api/activities",
                requestBody,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Running");
    }
}