package calculator;

public class ParsedInput {
    private final String delimiterRegex;
    private final String body;

    ParsedInput(String delimiterRegex, String body) {
        this.delimiterRegex = delimiterRegex;
        this.body = body;
    }

    public String getDelimiterRegex() {
        return delimiterRegex;
    }

    public String getBody() {
        return body;
    }
}
