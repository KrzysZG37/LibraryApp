package unit_tests;

import com.stepienk.libraryapp.model.dropbox_ebooks.EBookNamesParser;
import com.stepienk.libraryapp.model.dropbox_ebooks.EBook;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by stepienk on 2015-04-23.
 */
public class EBooksAdapterTests {
    private List<EBook> listArray;
    private EBookNamesParser eBookNamesParser;
    private static final String rssFeed = "https://www.dropbox.com/s/93ehhvxgyhwxs0g/imagelistview.xml?dl=1";
    private int id;
    private String title;
    private String pubDate;

    @Before
    public void setUp() {
        eBookNamesParser = new EBookNamesParser();
    }

    @Test
    public void shouldReturnEBooks() {
        listArray = eBookNamesParser.getData(rssFeed);
        EBook itemToTest = null;
        itemToTest = listArray.get(0);
        assignDataForEBookItem();
        String convertedId = "" + id;

        Assert.assertEquals(convertedId, itemToTest.getId());
        Assert.assertEquals(title, itemToTest.getTitle());
        Assert.assertEquals(pubDate, itemToTest.getPubDate());
    }

    @Test
    public void shouldReturnDataFromXml() {
        NodeList nodeList = eBookNamesParser.getDataFromXml(rssFeed);
        Node nNode = nodeList.item(0);
        Assert.assertNotNull(nNode.getTextContent());
    }

    @Test
    public void shouldReturnTagValue() {
        NodeList nodeList = eBookNamesParser.getDataFromXml(rssFeed);
        Node nNode = nodeList.item(0);
        Element eElement = (Element) nNode;

        Assert.assertTrue(eBookNamesParser.getTagValue("id", eElement).equals("1"));
    }

    @Test
    public void shouldReturnDataFromXmlOffline() {
        assignDataForEBookItem();
        try {
            eBookNamesParser.setTagForTest(1);
            File fXmlFile = new File("C:\\Users\\Krzysiek\\Documents\\ADVANCED_JAVA\\ADVANCED_JAVAwc\\branches\\SlidingMenu\\app\\src\\main\\res\\xml\\ebooks_content.xml");
            InputStream targetStream = new FileInputStream(fXmlFile);
            eBookNamesParser.setInputStream(targetStream);

            NodeList nodeList = eBookNamesParser.getDataFromXml(rssFeed);
            Node nNode = nodeList.item(0);
            Element eElement = (Element) nNode;

            Assert.assertTrue(eElement.getElementsByTagName("title").item(0).getTextContent().equals(title));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void assignDataForEBookItem() {
        id = 1;
        title = "Instrukcja do Audi TT MK2";
        pubDate = "06/04/2015";
    }
}
