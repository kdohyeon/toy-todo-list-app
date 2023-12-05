package org.example.reader;

import com.opencsv.CSVReader;
import org.example.event.Meeting;
import org.example.event.Todo;
import org.example.util.ZonedDateTimeUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class EventCsvReader {
    public List<Todo> readTodo(String path) throws IOException {
        List<Todo> result = new ArrayList<>();

        List<String[]> read = readAll(path);
        for (int i = 0; i < read.size(); i++) {
            if (skipHeader(i)) continue;

            result.add(new Todo(
                    Integer.parseInt(read.get(i)[0]),
                    read.get(i)[2],
                    read.get(i)[3],
                    ZonedDateTimeUtils.of(read.get(i)[4]),
                    ZonedDateTimeUtils.of(read.get(i)[5])));
        }

        return result;
    }

    private static boolean skipHeader(int i) {
        return i == 0;
    }

    public List<Meeting> readMeeting(String path) throws IOException {
        List<Meeting> result = new ArrayList<>();

        List<String[]> read = readAll(path);
        for (int i = 0; i < read.size(); i++) {
            if (skipHeader(i)) continue;

            result.add(new Meeting(
                    Integer.parseInt(read.get(i)[0]),
                    read.get(i)[2],
                    ZonedDateTimeUtils.of(read.get(i)[6]),
                    ZonedDateTimeUtils.of(read.get(i)[7]),
                    new HashSet<>(Arrays.asList(read.get(i)[3].split(","))),
                    read.get(i)[4],
                    read.get(i)[5]
                    )
            );
        }

        return result;
    }

    private List<String[]> readAll(String path) throws IOException {
        InputStream in = getClass().getResourceAsStream(path);
        InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
        CSVReader strings = new CSVReader(reader);
        return strings.readAll();
    }
}
