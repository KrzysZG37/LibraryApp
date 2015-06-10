package unit_tests;

import com.stepienk.libraryapp.model.sql_available_books.BooksNamesParser;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Created by stepienk on 2015-05-29.
 */
public class AvailableBooksAdapterTests {

    private BooksNamesParser booksNamesParser;
    private JSONObject jsonResponse = mock(JSONObject.class);
    private JSONArray jsonArray = mock(JSONArray.class);

    @Before
    public void setUp() {
        booksNamesParser = new BooksNamesParser();
    }

    @Test
    public void shouldReturnJsonWithBookContent() throws JSONException {
        setUpJson();
        booksNamesParser.setTagForTest(1);
        booksNamesParser.setJsonObject(jsonResponse);
        booksNamesParser.getAllBooks();
        jsonArray = booksNamesParser.getAllBooks();
        Assert.assertNotNull(jsonArray);
    }


    public void setUpJson() {
        try {
            jsonResponse.put("name", "Test Book");
            jsonResponse.put("description", "Test Description");
            jsonResponse.put("description", "Test Description");
            jsonResponse.put("date", "29-05-2015");
            jsonResponse.put("image", "https://test");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
