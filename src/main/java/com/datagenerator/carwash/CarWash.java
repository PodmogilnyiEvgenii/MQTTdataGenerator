package com.datagenerator.carwash;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashMap;

public class CarWash {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CarWash.class);

    public static HashMap<String, Integer> cashIncomeMap = new HashMap();
    public static HashMap<String, Integer> bankIncomeMap = new HashMap();
    public static HashMap<Integer, String> carwashIdMap = new HashMap();

    public static final String[] ID = {"0000.000.00000", "1111.111.11111", "2222.222.22222", "3333.333.33333", "4444.444.44444", "5555.555.55555", "6666.666.66666", "7777.777.77777", "8888.888.88888", "9999.999.99999"};
    public static final String[] WORK_STATUS = {"00", "00", "00", "00", "00", "00", "00", "00", "00", "00"};//00-test,10-work
    public static final String[] TYPE_POST_STATUS = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09"};
    public static final int[] NUMBER_POST = {4, 4, 2, 8, 6, 8, 5, 6, 7, 6};

    public static LinkedHashMap getData(int i) {

        LinkedHashMap<String, String> carWashData = new LinkedHashMap<>();

        carWashData.put("CarWashId", ID[i]);
        carWashData.put("WorkStatus", WORK_STATUS[i]);
        String postStatus = "";
        for (int j = 0; j < NUMBER_POST[i]; j++) {
            postStatus = postStatus.concat(TYPE_POST_STATUS[(int) (Math.random() * 10)]);
        }
        carWashData.put("PostStatus", postStatus);

        if (cashIncomeMap.get(ID[i])==null) {
            cashIncomeMap.put(ID[i], (int) (Math.random() * 100));
        } else {
            cashIncomeMap.put(ID[i], cashIncomeMap.get(ID[i]) + (int) (Math.random() * 100));
        }

        if (bankIncomeMap.get(ID[i])==null) {
            bankIncomeMap.put(ID[i], (int) (Math.random() * 100));
        } else {
            bankIncomeMap.put(ID[i], bankIncomeMap.get(ID[i]) + (int) (Math.random() * 100));
        }

        //bankIncome += (int) (Math.random() * 100);

        carWashData.put("CashIncome", Integer.toString(cashIncomeMap.get(ID[i])));
        carWashData.put("BankIncome", Integer.toString(bankIncomeMap.get(ID[i])));
/*
        LinkedHashMap<String, String> carWashData1 = new LinkedHashMap<>();
        carWashData1.put("test", "test");

 */
        return carWashData;
    }
}
