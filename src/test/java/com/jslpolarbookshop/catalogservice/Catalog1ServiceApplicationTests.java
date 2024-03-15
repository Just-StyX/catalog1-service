package com.jslpolarbookshop.catalogservice;

import com.jslpolarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Catalog1ServiceApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void contextLoads() {
	}
	@Test
	void whenPostRequestThenBookCreated() {
		var expectedBook = new Book("1234567891", "Title", "Author", 9.0);
		webTestClient.post()
				.uri("/books")
				.bodyValue(expectedBook)
				.exchange()
				.expectStatus().isCreated()
				.expectBody().isEmpty();
	}
}
