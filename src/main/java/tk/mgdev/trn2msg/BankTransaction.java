package tk.mgdev.trn2msg;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BankTransaction implements Transaction {
    private final String content;

    public BankTransaction(String content) {
        this.content = content;
    }

    public List<String> content() {
        validate();
        List<String> fields = new ArrayList<>();
        int[] fieldSizes = {2, 16, 12, 14, 3};
        int expectedFieldsAmount = fieldSizes.length;

        int startIndex = 0;
        for (int fieldSize : fieldSizes) {
            int endIndexExcluding = startIndex + fieldSize;
            String nextField = fieldContent(startIndex, endIndexExcluding);
            fields.add(nextField);
            startIndex = endIndexExcluding;
        }
        int extractedFieldsAmount = fields.size();

        if (extractedFieldsAmount != expectedFieldsAmount)
            throw new InvalidTransactionException(
                    "Can't parse transaction [" + content + "], "
                            + fields.size() + " of expected " + expectedFieldsAmount + " " +
                            "where parsed successfully");
        return fields;
    }

    private String fieldContent(int start, int endExcluding) {
        return content.substring(start, endExcluding);
    }

    private void validate() throws InvalidTransactionException {
        try {
            validateNull();
            validateLength();
            validateType();
        } catch (RuntimeException e) {
            throw new InvalidTransactionException(e.getMessage());
        }
    }

    private void validateNull() {
        if (content == null)
            throw new NullPointerException("Argument can't be null");
    }

    private void validateLength() {
        int EXPECTED_TRANSACTION_LENGTH = 47;
        if (content.length() != EXPECTED_TRANSACTION_LENGTH)
            throw new IllegalArgumentException(
                    "Transaction length is invalid: " +
                            "[" + content.length() + ", " +
                            "Should be: " + EXPECTED_TRANSACTION_LENGTH + "]");
    }

    private void validateType() {
        String transactionType = content.substring(0, 2);
        boolean isValidType = Stream.of(
                TransactionType.values()).anyMatch((t) -> transactionType.equals(t.getCode()));
        if (!isValidType)
            throw new IllegalArgumentException("Illegal Transaction type: " + transactionType);
    }
}
