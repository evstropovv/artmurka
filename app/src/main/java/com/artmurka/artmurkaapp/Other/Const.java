package com.artmurka.artmurkaapp.Other;

import java.util.regex.Pattern;

public class Const {
    public static final String CAT_LIST = "CATEGORY_LIST";
    public static final String TEL_NUMBER = "+380993712602";
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final int CATEGORY_FRAGMENT = 101;
    public static final int ITEM_LIST_FRAGMENT = 102;
    public static final int BASKET_FRAGMENT = 103;
    public static final int WISH_FRAGMENT = 104;
    public static final int ORDER_FRAGMENT = 105;
    public static final int PAY_FRAGMENT = 111;
    public static final int CATEGORY_SETTINGS_FRAGMENT = 106;


}
