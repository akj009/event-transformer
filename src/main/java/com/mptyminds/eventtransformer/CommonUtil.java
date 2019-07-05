package com.mptyminds.eventtransformer;

import java.util.regex.Pattern;

public class CommonUtil {

    public static final String DOT = ".";

    public static final String PATH_REGEX_STRING = "\\$\\(([a-zA-Z.]+)\\)";
    public static final Pattern PATH_PATTERN = Pattern.compile(PATH_REGEX_STRING);
}