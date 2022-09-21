package tk.mgdev.trn2msg;

public enum TransactionType {
    PURCHASE("00"), WITHDRAWAL("01");
    private final String code;
    TransactionType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
