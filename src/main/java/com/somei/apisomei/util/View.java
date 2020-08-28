package com.somei.apisomei.util;

public class View {
    static class Public { }
    static class ExtendedPublic extends Public { }
    public static class Internal extends ExtendedPublic { }
}
