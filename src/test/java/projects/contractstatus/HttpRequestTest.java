package projects.contractstatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import projects.contractstatus.entity.Code;
import projects.contractstatus.entity.Transaction;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void posting10thContractShouldReturnCreated() throws Exception {
        // Given
        Code cd = new Code(10);
        cd.setId(0L);
        Transaction tr = new Transaction(cd, 329646, new Date(), Transaction.StatusName.NEW);

        var request = new HttpPost("http://localhost:" + port + "/transactions/");
        request.setHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity("{\n" +
                "        \"id\": 1,\n" +
                "        \"code\": {\n" +
                "            \"id\": 1,\n" +
                "            \"code\": 10\n" +
                "        },\n" +
                "        \"contractNumber\": 802372,\n" +
                "        \"time\": \"2022-11-27T16:13:47.839+00:00\",\n" +
                "        \"status\": \"NEW\"\n" +
                "    }"));

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_CREATED));
    }

    @Test
    public void gettingWrongContractCodeShouldReturnNotFound() throws Exception {
        // Given
        HttpUriRequest request = new HttpGet( "http://localhost:" + port + "/transactions/-1" );

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void getting10thContractCodeShouldReturnOk() throws Exception {
        // Given
        HttpUriRequest request = new HttpGet( "http://localhost:" + port + "/transactions/10" );

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void getting10thContractStatusShouldReturnOk() throws Exception {
        // Given
        HttpUriRequest request = new HttpGet( "http://localhost:" + port + "/transactions/status/10" );

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String responseString = new BasicResponseHandler().handleResponse(httpResponse);
        // Then
        assertThat(
                responseString,
                containsString("\"code\":10,"+"\"status\":"));
    }
}
