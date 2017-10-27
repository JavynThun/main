package seedu.address.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;

/**
 * Class to help write html file based on template
 */
public class ProfileHtmlWriter {

    public static final String DEFAULT_TEMPLATE_PATH = "src/main/resources/view/profile_template.html";
    public static final String DEFAULT_PROFILE_PATH_FORMAT = "src/main/resources/view/profiles/%s";
    public static final String EMPTY_FIELD = "";

    private ArrayDeque<String> values;

    public ProfileHtmlWriter() {
        this(EMPTY_FIELD, EMPTY_FIELD, EMPTY_FIELD, EMPTY_FIELD, EMPTY_FIELD, EMPTY_FIELD);
    }

    public ProfileHtmlWriter(String name, String phone, String email, String address, String company, String website) {
        values = new ArrayDeque<String>();
        values.addLast(name);
        values.addLast(phone);
        values.addLast(email);
        values.addLast(address);
        values.addLast(company);
        values.addLast(website);
    }

    /**
     * Write new html file based on given template
     * @param htmlTemplatePath
     * @param profilePath
     * @throws IOException
     */
    public void writeProfileHtml(String htmlTemplatePath, String profilePath) throws IOException {
        String line;
        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new FileReader(htmlTemplatePath));
        ArrayDeque<Integer> fieldIndexes = new ArrayDeque<Integer>();
        int i = 0;

        FileWriter fileWriter = new FileWriter(profilePath);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        // How to read file in java line by line?
        while ((line = bufferedReader.readLine()) != null) {
            if (i == 0) {
                String[] indexes = line.split("\\s+");
                for (int j = 1; j < indexes.length - 1; j++) {
                    fieldIndexes.addLast(Integer.parseInt(indexes[j]));
                }
            } else if (fieldIndexes.size() > 0 && i == fieldIndexes.peekFirst() - 1) {
                fieldIndexes.poll();
                String value = values.poll();
                line = String.format(line, value);
            }
            printWriter.print(line);
            i++;
        }

        printWriter.close();
        bufferedReader.close();
    }
}
