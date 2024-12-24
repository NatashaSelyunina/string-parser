package by.selunina;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser {

    private static final String INPUT_FILE = "C:/Users/Natasha/Desktop/ObjectModule.bsl";

    private static final String OUTPUT_FILE = "res/output.txt";

    private static final String SEP_LINE = ";";

    private static final String SEP_PART = "=";

    private static final String REGEX = "NStr\\(\"([^\"]*)\"\\)";

    public static void readFromFile() {

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            int lineNumber = 0;
            Pattern pattern = Pattern.compile(REGEX);

            while (br.readLine() != null) {
                String line = br.readLine();
                lineNumber++;

                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String foundString = matcher.group(1);
                    writeToFile(foundString, lineNumber, bw);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToFile(String foundString, int lineNumber, BufferedWriter bw) throws IOException {
        String[] lines = foundString.split(SEP_LINE);

        for (String line : lines) {
            String[] parts = line.split(SEP_PART);
            String languageCode = parts[0].trim();
            String text = parts[1].trim().replaceAll("^'|'$", "");
            bw.write(lineNumber + ": " + languageCode + " : " + text);
            bw.newLine();
        }
    }
}
