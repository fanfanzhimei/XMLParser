package com.zhi.www.xmlparser.constant;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2016/10/11.
 */
public class Constants {

    public static final String ROOT_PATH = Environment.getExternalStorageDirectory().getPath();

    public static final String FILE_PATH = ROOT_PATH + File.separator + "ABC";
}
