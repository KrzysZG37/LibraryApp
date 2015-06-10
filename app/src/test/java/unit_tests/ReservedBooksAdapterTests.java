package unit_tests;

import com.stepienk.libraryapp.model.sql_reserved_books.ReservedBooksNamesParser;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Created by Krzysiek on 2015-06-09.
 */
public class ReservedBooksAdapterTests {

    private ReservedBooksNamesParser reservedBooksNamesParser;
    private JSONObject jsonResponse = mock(JSONObject.class);
    private JSONArray jsonArray = mock(JSONArray.class);

    @Before
    public void setUp() {
        reservedBooksNamesParser = new ReservedBooksNamesParser();
    }

    @Test
    public void shouldReturnJsonWithBookContent() throws JSONException {
        setUpJson();
        reservedBooksNamesParser.setTagForTest(1);
        reservedBooksNamesParser.setJsonObject(jsonResponse);
        reservedBooksNamesParser.getAllBooks();
        jsonArray = reservedBooksNamesParser.getAllBooks();
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
