package com.zhi.www.xmlparser.test;

import android.content.res.AssetManager;
import android.test.AndroidTestCase;
import android.util.Log;

import com.zhi.www.xmlparser.constant.Constants;
import com.zhi.www.xmlparser.domain.Person;
import com.zhi.www.xmlparser.service.ParserUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/10.
 */
public class TestParser extends AndroidTestCase {
    private static final String TAG = "TestParser";
    private static final String fileName = "Copy.xml";
    public void test() {
        AssetManager assetManager = this.getContext().getAssets();
        try {
            InputStream xml = assetManager.open("Person.xml");
            List<Person> personList = ParserUtils.ParseXML(xml);
            for (Person person : personList) {
                Log.e(TAG, person.getId() + ";---" + person.getName() + ";---" + person.getAge());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入文件到本地sd卡目录中
     * （assets目录可以读，不可以写，  未装AVD，真机也未root，无法访问/data/data 目录）
     */
    public void test2(){
        try {
            File f = checkDir(fileName);
            FileOutputStream outputStream = new FileOutputStream(f);
            List<Person> persons = new ArrayList<Person>();
            persons.add(new Person(1, "丽丽" , 10));
            persons.add(new Person(2, "塔塔" , 20));
            persons.add(new Person(3, "雨雨" , 30));
            persons.add(new Person(4, "露露" , 40));
            persons.add(new Person(5, "君君" , 50));
            persons.add(new Person(6, "粘粘" , 60));
            ParserUtils.writeXml(persons, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File checkDir(String name) throws IOException {
        String path = Constants.FILE_PATH;
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        File f = new File(path, name);
        if(!f.exists()){
            try {
                f.createNewFile();
                return f;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}