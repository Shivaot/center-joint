package com.hitachi.epdi2.constant;

public enum Sheets {
    INSPECTION_SHEET("INSPECTION_SHEET","CenterJoint Assembly Inspection Report"),
    NC_SHEET("NC_SHEET","N C Summery Sheet");


    String sheetName;
    String sheetNameView;

    Sheets(String sheetName, String sheetNameView) {
        this.sheetName = sheetName;
        this.sheetNameView = sheetNameView;
    }

    public String getSheetNameView() {
        return sheetNameView;
    }

    public void setSheetNameView(String sheetNameView) {
        this.sheetNameView = sheetNameView;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
}
