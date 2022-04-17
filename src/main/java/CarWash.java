import java.util.LinkedHashMap;

public class CarWash {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(mainApp.class);

    public static final String[] ID = {"0000.000.00000", "0001.001.00001", "0001.001.00002", "0002.001.00001", "0002.002.00001", "0002.002.00002", "0002.003.00001", "0002.003.00002", "0003.001.00001", "0003.002.00001"};
    public static final String[] WORK_STATUS = {"test", "work", "work", "work", "work", "work", "work", "work", "work", "work"};
    public static final String[] TYPE_POST_STATUS = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09"};
    public static final int[] NUMBER_POST = {4, 4, 2, 8, 6, 8, 5, 6, 7, 6};

    public static LinkedHashMap getData (int i) {
        long cashIncome = 0;
        long bankIncome = 0;

        LinkedHashMap<String, String> carWashData = new LinkedHashMap<>();

        carWashData.put("CarWashId", CarWash.ID[i]);
        carWashData.put("WorkStatus", CarWash.WORK_STATUS[i]);
        String postStatus = "";
        for (int j = 0; j < CarWash.NUMBER_POST[i]; j++) {
            postStatus = postStatus.concat(CarWash.TYPE_POST_STATUS[(int) (Math.random() * 10)]);
        }
        carWashData.put("PostStatus", postStatus);

        cashIncome += (int) (Math.random() * 100);
        bankIncome += (int) (Math.random() * 100);

        carWashData.put("CashIncome", Long.toString(cashIncome));
        carWashData.put("BankIncome", Long.toString(bankIncome));

        return carWashData;
    }
}
