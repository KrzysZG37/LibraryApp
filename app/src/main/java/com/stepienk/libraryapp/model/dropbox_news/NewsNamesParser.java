package com.stepienk.libraryapp.model.dropbox_news;

import android.content.res.Resources;

import com.stepienk.libraryapp.model.interfaces.dropBoxNamesParserInterface;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import info.androidhive.LibraryApp.R;

/**
 * Created by Krzysiek on 2015-04-14.
 * news parser
 */
public class NewsNamesParser implements dropBoxNamesParserInterface {

    private News objNews;
    private List<News> listArray;
    private int tagForTest = 0;
    private InputStream inputStream;

    /**
     * Retrieve data from url response and put into news object
     *
     * @param url - url to dropBox xml file file with data
     * @return
     */
    public List<News> getData(String url) {
        listArray = new ArrayList<News>();
        NodeList nList = getDataFromXml(url);

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                objNews = new News();
                setTagValueForNewsObject(objNews, eElement);

                listArray.add(objNews);
            }
        }

        return listArray;
    }

    /**
     * Retrieve data from xml and place in NodeList
     *
     * @param url - url of data placed in dropbox
     * @return
     */
    public NodeList getDataFromXml(String url) {
        NodeList nList = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = null;
            if (tagForTest == 0) {
                doc = db.parse(new URL(url).openStream());
            } else if (tagForTest == 1) {
                doc = db.parse(inputStream);
            }


            doc.getDocumentElement().normalize();

            nList = doc.getElementsByTagName("item");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nList;
    }

    /**
     * Retrieve data and put into object based on tag saved in xml file
     *
     * @param sTag     - value of tag
     * @param eElement - specific element taken from response
     * @return
     */
    public static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
                .getChildNodes();
        Node nValue = nlList.item(0);
        return nValue.getNodeValue();
    }

    /**
     * Setting specific data in specific eBook object
     *
     * @param objNews  - news object
     * @param eElement - specific element taken from response
     */
    private void setTagValueForNewsObject(News objNews, Element eElement) {
        objNews.setId(getTagValue("id", eElement));
        objNews.setTitle(getTagValue("title", eElement));
        objNews.setDesc(getTagValue("desc", eElement));
        objNews.setPubDate(getTagValue("pubDate", eElement));
        objNews.setLink(getTagValue("link", eElement));
    }

    public void setTagForTest(int tag) {
        this.tagForTest = tag;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }


}
