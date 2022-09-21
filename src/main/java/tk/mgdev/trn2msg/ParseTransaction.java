package tk.mgdev.trn2msg;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ParseTransaction {

    public static void main(String[] args) {
        String testFilePath = Objects.requireNonNull(ParseTransaction.class.getClassLoader()
                .getResource("transactionsToParse.txt")).getPath();

        try (BufferedReader br = Files.newBufferedReader(Path.of(testFilePath), StandardCharsets.UTF_8)) {
            String transactionAsString;
            while ((transactionAsString = br.readLine()) != null) {
                Transaction transaction = new BankTransaction(transactionAsString);
                System.out.println(transaction.content());
            }

        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}

