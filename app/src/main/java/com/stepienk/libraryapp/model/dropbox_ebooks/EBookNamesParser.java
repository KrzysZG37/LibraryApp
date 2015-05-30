package com.stepienk.libraryapp.model.dropbox_ebooks;

import com.stepienk.libraryapp.model.interfaces.dropBoxNamesParserInterface;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Krzysiek on 2015-04-14.
 * eBook parser
 */
public class EBookNamesParser implements dropBoxNamesParserInterface {

    private EBook objEBook;
    private List<EBook> listArray;

    /**
     * Retrieve data from url response and put into eBook object
     *
     * @param url - url to dropBox xml file file with data
     * @return
     */
    public List<EBook> getData(String url) {

        listArray = new ArrayList<EBook>();
        NodeList nList = getDataFromXml(url);

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                objEBook = new EBook();
                setTagValueForEBookObject(objEBook, eElement);

                listArray.add(objEBook);
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
            Document doc = db.parse(new URL(url).openStream());

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
     * @param objEBook - eBook object
     * @param eElement - specific element taken from response
     */
    private void setTagValueForEBookObject(EBook objEBook, Element eElement) {
        objEBook.setId(getTagValue("id", eElement));
        objEBook.setTitle(getTagValue("title", eElement));
        objEBook.setDesc(getTagValue("desc", eElement));
        objEBook.setPubDate(getTagValue("pubDate", eElement));
        objEBook.setLink(getTagValue("link", eElement));
        objEBook.setPdfLink(getTagValue("pdflink", eElement));
    }
}
