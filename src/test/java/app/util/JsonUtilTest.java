package app.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import app.book.Book;

public class JsonUtilTest {
	
	private static String EXPECTED_OUTPUT = "{\n" + 
			"  \"title\" : \"Harry potter\",\n" + 
			"  \"author\" : \"Rowling\",\n" + 
			"  \"isbn\" : \"12345\"\n" + 
			"}";
	
	@Test
	 void testDataToJsonAndAssertOnOutput() {
		//Given
		Book sampleBook = Book.builder().author("Rowling").title("Harry potter").isbn("12345").build();
		//When
		String outputJson = JsonUtil.dataToJson(sampleBook);
		//Then
		assertEquals(EXPECTED_OUTPUT,outputJson,"incorrect json output");
	}

}
