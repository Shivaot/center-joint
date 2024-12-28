package com.hitachi.epdi2.util;

public class SheetUtils {

    public static String getDisplayName(String actualName){
        String modelName = "";
        switch (actualName) {
            case "EX200":
                modelName = "EX200";
                break;
            default:
        }
        return modelName;
    }
}
