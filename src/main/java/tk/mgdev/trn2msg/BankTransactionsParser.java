package tk.mgdev.trn2msg;

import java.util.Comparator;
import java.util.Objects;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String firstString = "a";
        firstString.strip();
        App app = new App();
        System.out.println(app.getFilePath());
    }


    public String getFilePath() {
        return Objects.requireNonNull(getClass().getClassLoader().getResource("transactionsToParse.txt")).getPath();
    }
}

