package com.iko.iko.common.util;

import java.util.List;

public class EntityListUtil {
    public static String convertStringListToString(List<String> argStringList) {
        StringBuilder stream = new StringBuilder();
        int idx = 0;
        for(String stringListValue : argStringList) {
            if(idx == 0) {
                idx++;
                stream.append(stringListValue);
            } else {
                stream.append(";" + stringListValue);
            }
        }

        return stream.toString();
    }
}