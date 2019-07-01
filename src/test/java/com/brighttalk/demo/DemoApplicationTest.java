package com.brighttalk.demo;

import com.brighttalk.demo.dto.RealmRequest;
import com.brighttalk.demo.repository.RealmRepository;
import com.sun.tools.javac.util.List;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static com.brighttalk.demo.service.RealmBuilder.*;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTest {

  final static String REALM_NOT_FOUND_RESPONSE_JSON = "{code:RealmNotFound}";
  final static String REALM = "{id: 1, name: dummyName, description: dummyDescription, " +
      "key: AIA1esBlFEUzlSyBHCY4uWpyK6deweCh}";

  @LocalServerPort
  private int port;

  TestRestTemplate restTemplate = new TestRestTemplate();

  @Autowired
  RealmRepository realmRepository;

  @Test
  @Sql(scripts = "classpath:dummy-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(scripts = "classpath:clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  public void get_success() throws JSONException {
    HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());

    ResponseEntity<String> response = restTemplate.exchange(
        getUrl("/service/user/realm/" + DUMMY_ID),
        HttpMethod.GET, entity, String.class);

    JSONAssert.assertEquals(REALM, response.getBody(), false);
  }

  @Test
  public void get_realmNotFound() throws JSONException {
    HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());

    ResponseEntity<String> response = restTemplate.exchange(
        getUrl("/service/user/realm/" + DUMMY_ID),
        HttpMethod.GET, entity, String.class);

    JSONAssert.assertEquals(REALM_NOT_FOUND_RESPONSE_JSON, response.getBody(), false);
  }

  @Test
  public void create_success() throws JSONException {
    long dataBefore = realmRepository.count();

    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<RealmRequest> entity = new HttpEntity<>(new RealmRequest(DUMMY_NAME, DUMMY_DESCRIPTION), headers);

    ResponseEntity<String> response = restTemplate.exchange(
        getUrl("/service/user/realm"),
        HttpMethod.POST, entity, String.class);

    long dataAfter = realmRepository.count();

    JSONAssert.assertEquals(REALM, response.getBody(), false);
    assertEquals(dataBefore + 1, dataAfter);
  }

  private String getUrl(String path) {
    return "http://localhost:" + port + path;
  }

}