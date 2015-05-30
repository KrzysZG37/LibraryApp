package unit_tests;

import android.content.res.Resources;

import com.stepienk.libraryapp.model.dropbox_news.News;
import com.stepienk.libraryapp.model.dropbox_news.NewsNamesParser;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.List;

import info.androidhive.LibraryApp.R;

import static org.mockito.Mockito.mock;

/**
 * Created by Krzysiek on 2015-04-21.
 */
public class NewsAdapterTests {
    private List<News> listArray;
    private NewsNamesParser newsNamesParser;
    private static final String rssFeed = "https://www.dropbox.com/s/25ycugan9rpc25e/imagelistview.xml?dl=1";
    private int id;
    private String title;
    private String pubDate;
    private InputStream inputStream;
    private Resources resources;

    @Before
    public void setUp() {
        newsNamesParser = new NewsNamesParser();
        inputStream = mock(InputStream.class);
        resources = mock(Resources.class);
    }

    @Test
    public void shouldReturnNews() {
        listArray = newsNamesParser.getData(rssFeed);
        News itemToTest = null;
        itemToTest = listArray.get(0);
        assignDataForNewsItem();
        String convertedId = "" + id;

        Assert.assertEquals(convertedId, itemToTest.getId());
        Assert.assertEquals(title, itemToTest.getTitle());
        Assert.assertEquals(pubDate, itemToTest.getPubDate());
    }

    @Test
    public void shouldReturnDataFromXml() {
        NodeList nodeList = newsNamesParser.getDataFromXml(rssFeed);
        Node nNode = nodeList.item(0);
        Assert.assertNotNull(nNode.getTextContent());
    }

    @Test
    public void shouldReturnTagValue() {
        NodeList nodeList = newsNamesParser.getDataFromXml(rssFeed);
        Node nNode = nodeList.item(0);
        Element eElement = (Element) nNode;

        Assert.assertTrue(newsNamesParser.getTagValue("id", eElement).equals("1"));
    }

    @Test
    public void shouldReturnDataFromXmlOffline() {
        newsNamesParser.setTagForTest(1);
        inputStream = resources.getSystem().openRawResource(R.xml.news_content);
        newsNamesParser.setInputStream(inputStream);
        NodeList nodeList = newsNamesParser.getDataFromXml(rssFeed);
        Node nNode = nodeList.item(0);
        Assert.assertNotNull(nNode.getTextContent());
    }


    public void assignDataForNewsItem() {
        id = 1;
        title = "Wielkanocna pisanka";
        pubDate = "26/03/2015";
    }
}
