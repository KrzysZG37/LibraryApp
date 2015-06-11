package unit_tests;

import android.app.Activity;
import android.content.res.Resources;

import com.stepienk.libraryapp.adapter.dropbox_news.NewsRowAdapter;
import com.stepienk.libraryapp.adapter.dropbox_news.NewsViewHolder;
import com.stepienk.libraryapp.model.dropbox_news.News;
import com.stepienk.libraryapp.model.dropbox_news.NewsNamesParser;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import info.androidhive.LibraryApp.R;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Krzysiek on 2015-04-21.
 */
public class NewsAdapterTests {
    private List<News> listArray;
    private NewsNamesParser newsNamesParser;
    private NewsRowAdapter newsRowAdapter;
    private Activity activity;
    private News objBean;
    private static final String rssFeed = "https://www.dropbox.com/s/25ycugan9rpc25e/imagelistview.xml?dl=1";
    private int id;
    private String title;
    private String pubDate;
    private InputStream inputStream;
    private Resources resources;
    private NewsViewHolder holder;

    @Before
    public void setUp() {
        activity = mock(Activity.class);
        objBean = new News();
        holder = new NewsViewHolder();
        newsRowAdapter = new NewsRowAdapter(activity, R.layout.news_row, listArray);
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
        assignDataForNewsItem();
        try {
            newsNamesParser.setTagForTest(1);
            File fXmlFile = new File("C:\\Users\\Krzysiek\\Documents\\ADVANCED_JAVA\\ADVANCED_JAVAwc\\branches\\SlidingMenu\\app\\src\\main\\res\\xml\\news_content.xml");
            InputStream targetStream = new FileInputStream(fXmlFile);
            newsNamesParser.setInputStream(targetStream);

            NodeList nodeList = newsNamesParser.getDataFromXml(rssFeed);
            Node nNode = nodeList.item(0);
            Element eElement = (Element) nNode;

            Assert.assertTrue(eElement.getElementsByTagName("title").item(0).getTextContent().equals(title));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldSetDataInNewsFragment() {
        objBean.setTitle("test title");
        objBean.setDesc("test description");
        objBean.setId("1");
        objBean.setLink("http://test.link");
        objBean.setPubDate("01-02-2015");
        newsRowAdapter.setObjBean(objBean);
        newsRowAdapter.setDataInNewsFragment(holder);
    }


    public void assignDataForNewsItem() {
        id = 1;
        title = "Wielkanocna pisanka";
        pubDate = "26/03/2015";
    }

}
