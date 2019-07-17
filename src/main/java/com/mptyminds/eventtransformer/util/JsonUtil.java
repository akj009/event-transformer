package com.mptyminds.eventtransformer.util;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.StringUtils;

import static com.mptyminds.eventtransformer.util.CommonUtil.NULL;
import static com.mptyminds.eventtransformer.util.CommonUtil.PATH_SEPARATOR_CHARACTER;

@Log4j2
public class JsonUtil {

    public static String findStringOnPath(String paths, Any deserJsonObject) {
        for (String path : paths.split(PATH_SEPARATOR_CHARACTER)) {
            try {
                final String stringOnPath = deserJsonObject.get(path.split("\\.")).as(String.class);
                if(StringUtils.hasText(stringOnPath)) {
                    return stringOnPath;
                }
            } catch (Exception e) {
                log.warn("path not found in input json");
            }
        }

        return (String) NULL;
    }

    public static Any parseStringToAny(String jsonStr) {
        if(StringUtils.hasText(jsonStr)) {
            try {
                return JsonIterator.deserialize(jsonStr);
            } catch (Exception e) {
                log.error(":::: unable to parse json str: {}", e.getMessage());
            }
        }
        return (Any) NULL;
    }
}
