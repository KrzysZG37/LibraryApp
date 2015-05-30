package com.stepienk.libraryapp.model.network;

/**
 * Created by Krzysiek on 2015-03-30.
 * Configuration of application based on network plugged into computer
 * Computer which runs application starts localhost and application running on specific device (smartphone or tablet) retrieve data base on these IP addresses
 */
public class AppConfig {

    //home url
    public static final String URL_REGISTER = "http://192.168.1.4/xampp/www/android_login_api/index.php";
    public static final String URL_GET_DATA = "http://192.168.1.4/xampp/www/android_login_api/getAllBooks.php";

    //FTIMS url
    //public static final String URL_REGISTER = "http://10.31.61.127/xampp/www/android_login_api/index.php";
    //public static final String URL_GET_DATA = "http://10.31.61.127/xampp/www/android_login_api/getAllBooks.php";


    //ife_stud network (IFE building)
    //public static String URL_REGISTER = "http://172.17.195.174/xampp/www/android_login_api/index.php";
    //public static final String URL_GET_DATA = "http://172.17.195.174/xampp/www/android_login_api/getAllBooks.php";

    //rss links for news and e-books
    public static final String URL_NEWS_RSS_FEED = "https://www.dropbox.com/s/25ycugan9rpc25e/imagelistview.xml?dl=1";
    public static final String URL_E_BOOKS_RDD_FEED = "https://www.dropbox.com/s/93ehhvxgyhwxs0g/imagelistview.xml?dl=1";

}
