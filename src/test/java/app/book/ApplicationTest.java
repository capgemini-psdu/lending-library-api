package app.book;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import app.Application;
import spark.Spark;

@SuppressWarnings("deprecation")
public class ApplicationTest {

	HttpClient client = HttpClientBuilder.create().build();

	@BeforeAll
	public static void setup() {
		Application.main(null);
		Spark.awaitInitialization();
	}

	@AfterAll
	public static void tearDown() {
		Spark.stop();
	}

	@Test
	public void testGetEndpointForAllBooksAndAssertOnBodyAndStatus() throws ClientProtocolException, IOException {
		// Given
		String url = "http://localhost:5678/books/";
		HttpGet request = new HttpGet(url);
		request.addHeader("Accept", "application/json");
		// When
		HttpResponse response = client.execute(request);
		// Then
		HttpEntity entity = response.getEntity();
		String retSrc = EntityUtils.toString(entity);
		JSONArray jsonArray = new JSONArray(retSrc);
		assertEquals(HttpStatus.OK_200, response.getStatusLine().getStatusCode(), "Unexpected status code");
		assertEquals(12, jsonArray.length(), "Unexpected number of values");
	}

	@Test
	public void testGetEndpointForSingleBookAndAssertOnBodyAndStatus()
			throws ClientProtocolException, IOException, URISyntaxException {
		// Given
		String url = "http://localhost:5678/books/9781936594290";
		HttpGet request = new HttpGet(url);
		HttpParams params = new BasicHttpParams();
		params.setParameter("isbn", "9781936594290");
		request.setParams(params);
		request.addHeader("Accept", "application/json");
		// When
		HttpResponse response = client.execute(request);
		// Then
		HttpEntity entity = response.getEntity();
		String retSrc = EntityUtils.toString(entity);
		JSONObject jsonObject = new JSONObject(retSrc);
		assertEquals(HttpStatus.OK_200, response.getStatusLine().getStatusCode(), "Unexpected status code");
		assertEquals("Jane Austen", jsonObject.get("author"), "Unexpected value");
	}

}
