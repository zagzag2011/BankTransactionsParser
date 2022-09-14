package tk.mgdev.trn2msg;

import java.io.BufferedReader;
import java.util.Objects;

/**
 * Hello world!
 */
public class BankTransactionsParser {
    public static void main(String[] args) {
        BankTransactionsParser bankTransactionsParser = new BankTransactionsParser();
        System.out.println(bankTransactionsParser.getFilePath());
    }


    public String getFilePath() {
        return Objects.requireNonNull(getClass().getClassLoader().getResource("transactionsToParse.txt")).getPath();
    }
}

