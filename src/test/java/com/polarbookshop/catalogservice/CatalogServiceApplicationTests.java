package com.polarbookshop.catalogservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void contextLoads() {
    }

    @Test
    void whenPostRequestThenBookCreated() {
        Book request = Book.of("1234567899", "Noise", "Daniel Kaneman", 15.75);
        Book response = webTestClient.post()
            .uri("/books")
            .bodyValue(request)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(Book.class).returnResult().getResponseBody();
        assertThat(response).isNotNull();
        assertThat(response.isbn()).isEqualTo(request.isbn());
        assertThat(response.author()).isEqualTo(request.author());
        assertThat(response.title()).isEqualTo(request.title());
        assertThat(response.price()).isEqualTo(request.price());
    }

}
