package ru.rsdev.smscontroller;

import java.util.HashMap;

/**
 * Created by Дом on 10.05.2015.
 */
public class Boiler extends HashMap<String, String> {

    public static final String txtBoilerName = "txtBoilerName";
    public static final String txtParamEdit1 = "txtParamEdit1";
    public static final String txtParamEdit2 = "txtParamEdit2";
    public static final String txtParamEdit3 = "txtParamEdit3";
    public static final String txtParamEdit4 = "txtParamEdit4";
    public static final String txtSmsNumber = "txtSmsNumber";
    public static final String txtData = "txtData";


    public Boiler(String Name, String Param1, String Param2, String Param3, String Param4, String SmsNumber, String Data) {
        super();
        super.put(txtBoilerName, Name);
        super.put(txtParamEdit1, Param1);
        super.put(txtParamEdit2, Param2);
        super.put(txtParamEdit3, Param3);
        super.put(txtParamEdit4, Param4);
        super.put(txtSmsNumber, SmsNumber);
        super.put(txtData, Data);
    }
}