package com.hitachi.epdi2.constant;

public final class Constant {

    private Constant() {}

    public static final class APIMessage {

        public static final String MODEL_SAVE_MESSAGE = "Model is Saved Successfully!";
        public static final String ERROR_MESSAGE = "Something went wrong!";
        public static final String ERROR_MESSAGE_MULTIPLE_MODEL = "Please Enter Unique Model Name !";
        public static final String MODEL_DELETE_MESSAGE = "Model is Deleted Successfully!";

        public static final String CHECKSHEET_SAVE_MESSAGE = "Checksheet is Saved Successfully!";
        public static final String CHECKSHEET_UPDATE_MESSAGE = "Checksheet is Updated Successfully!";
        public static final String CHECKSHEET_FETCH = "Checksheet Fetch Successfully";

    }

    public static final class PresentationConstant {
        public static final String CHECK_SHEET_TITLE = "title";
        public static final String IS_INSPECTION_SHEET = "IS_INSPECTION_SHEET";
        public static final String SHEET_TYPE = "sheetType";
        public static final String CONTEXT = "context";
        public static final String IS_FINAL_SUBMIT = "isFinalSubmit";
        public static final String MODEL_LIST = "modelList";
        public static final String SHEET_HEADING = "heading";
    }

    public static final class Role {
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
        public static final String ROLE_QC = "ROLE_QC";
    }
}
