package com.zhi.www.xmlparser.service;

import android.util.Xml;
import com.zhi.www.xmlparser.domain.Person;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/10.
 */
public class ParserUtils {

    /**
     * 解析xml文件
     * @param xml  IO工具
     * @return  返回解析出来的List集合
     */
    public static List<Person> ParseXML(InputStream xml) throws Exception {
        List<Person> list = null;
        Person person = null;
        int event = 0;
        // xml文件解析器
        XmlPullParser xmlPullParser = Xml.newPullParser();
        xmlPullParser.setInput(xml, "utf-8");
        event = xmlPullParser.getEventType();

        while (XmlPullParser.END_DOCUMENT != event) {
            switch (event) {
                case XmlPullParser.START_DOCUMENT:  // 文档开始
                    list = new ArrayList<Person>();
                    break;
                case XmlPullParser.START_TAG:
                    if ("person".equals(xmlPullParser.getName())) {
                        person = new Person();
                        int id = new Integer(xmlPullParser.getAttributeValue(0));
                        person.setId(id);
                    }
                    if ("name".equals(xmlPullParser.getName())) {
                        String name = xmlPullParser.nextText();
                        if (null != name) {
                            person.setName(name);
                        }
                    }

                    if ("age".equals(xmlPullParser.getName())) {
                        String age = xmlPullParser.nextText();
                        if (null != age) {
                            person.setAge(new Integer(age));
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("person".equals(xmlPullParser.getName())) {
                        list.add(person);
                        person = null;
                    }
                    break;
            }
            event = xmlPullParser.next();  // 需要手动跳转到下一行继续解析
        }
        return list;
    }


    /**
     * 将List集合写入xml文件中
     * @param persons   要写的List集合
     * @param fos   IO工具
     * @throws Exception
     */
    public static void writeXml(List<Person> persons, FileOutputStream fos) throws Exception {
        XmlSerializer serializer = Xml.newSerializer();
        serializer.setOutput(fos, "utf-8");
        serializer.startDocument("utf-8", true);
        serializer.startTag(null, "persons");
        for(Person person:persons){
            serializer.startTag(null, "person");

            serializer.attribute(null, "id", String.valueOf(person.getId()));

            serializer.startTag(null, "name");
            serializer.text(person.getName());
            serializer.endTag(null, "name");

            serializer.startTag(null, "age");
            serializer.text(person.getAge()+"");
            serializer.endTag(null, "age");

            serializer.endTag(null, "person");
        }
        serializer.endTag(null, "persons");
        serializer.endDocument();

        fos.flush();
        fos.close();
    }
}
