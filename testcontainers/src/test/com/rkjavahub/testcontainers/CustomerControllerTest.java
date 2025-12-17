package com.rkjavahub.testcontainers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.oracle.OracleContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    static DockerImageName myImage = DockerImageName.parse("gvenzl/oracle-xe:21-slim").asCompatibleSubstituteFor("gvenzl/oracle-free");

    static OracleContainer oracle =
            new OracleContainer(myImage)
                   /* .withDatabaseName("testdb")
                    .withUsername("rohit")
                    .withPassword("rohit007")*/
                    .withStartupTimeout(Duration.ofMinutes(5))
                    .withReuse(false);

    @Autowired
    CustomerRepository customerRepository;

    @LocalServerPort
    private Integer port;

    @BeforeAll
    static void beforeAll() {
        oracle.start();
    }

    @AfterAll
    static void afterAll() {
        oracle.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", oracle::getJdbcUrl);
        registry.add("spring.datasource.username", oracle::getUsername);
        registry.add("spring.datasource.password", oracle::getPassword);
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        customerRepository.deleteAll();
        System.out.println("Using port: " + port);
    }

    @Test
    void shouldGetAllCustomers() {
        List<Customer> customers = List.of(
                new Customer("Rohit", "rohit@gmail.com"),
                new Customer("Sachin", "sachin@gmail.com")
        );
        customerRepository.saveAll(customers);

        System.out.println("Using port 1 : " + port);
        given()
                .contentType(ContentType.JSON)
                .port(port)
                .when()
                .get("/api/customers")
                .then()
                .statusCode(200)
                .body(".", hasSize(2));
    }
}