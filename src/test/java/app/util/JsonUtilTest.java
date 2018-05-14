package app.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import app.book.Book;

public class JsonUtilTest {
	
	@Test
	 void testDataToJsonAndAssertOnOutput() {
		//Given
		Book sampleBook = Book.builder().author("Rowling").title("Harry potter").isbn("12345").build();
		//When
		String outputJson = JsonUtil.dataToJson(sampleBook);
		//Then
		JSONObject jsonObject = new JSONObject(outputJson);
		assertEquals(jsonObject.length(),3,"incorrect number of keys");
		assertEquals(jsonObject.get("title"),"Harry potter","value for title is not correct");
		assertEquals(jsonObject.get("author"),"Rowling","value for author is not correct");
		assertEquals(jsonObject.get("isbn"),"12345","value for isbn is not correct");
	}

}
