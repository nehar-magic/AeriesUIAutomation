package com.project.automaxn.utils;

public class XPathObjects {

    public class MedicalLogPageXpaths{
        public static final String ADD_NEW_RECORD_BTN = "//input[contains(@value, 'Add New Record')]";
        public static final String SAVE_BTN_TITLE = "Save Record";
        public static final String CD_DROPDOWN_GRID = "//input[contains(@onchange, 'CD_DropDownGrid')]";
        public static final String ST_KENDO_TIME_PICKER = "//input[contains(@ID, 'ST_txtKendoTimePicker')]";
        public static final String ET_KENDO_TIME_PICKER = "//input[contains(@ID, 'ET_txtKendoTimePicker')]";
        public static final String RE_DROPDOWN_GRID = "//input[contains(@onchange, 'RE_DropDownGrid')]";
        public static final String TXT_CO_NAME = "ctl00$MainContent$subMED$rptMedicalLog$ctl01$txtCO";
        public static final String BC_DROPDOWN_GRID = "//input[contains(@onchange, 'BC_DropDownGrid')]";
        public static final String IN_DROPDOWN_GRID = "//input[contains(@onchange, 'IN_DropDownGrid')]";
        public static final String VERIFY_RECORD = "//*[contains(text(), '%s')]";


    }

}