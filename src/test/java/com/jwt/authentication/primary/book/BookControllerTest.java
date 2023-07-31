package com.jwt.authentication.primary.book;

import com.jwt.authentication.primary.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {
   private static RestTemplate restTemplate;

   @Autowired
   private BookRepository bookRepository;
   @LocalServerPort
   private int port;
   private String baseUrl = "http://localhost";

   @BeforeAll
   public static void init() {
      restTemplate = new RestTemplate();
   }

   @BeforeEach
   public void setUp() {
      baseUrl = baseUrl.concat(":").concat(port + "").concat("/saveBook");
   }
   @Test
   public void saveBook(){
      Book book = Book.builder().bookId(1).bookIsbnNumber("0195153448").build();
      User user = User.builder().id(1).username("arun6121143").email("menonarun23@gmail.com").name("arun").phoneNumber("6355483779").build();
      Book response = restTemplate.postForObject(baseUrl+"/{userId}",book,Book.class,user.getId());
      assertNotNull(response);
   }
}