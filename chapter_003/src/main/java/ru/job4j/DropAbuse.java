package ru.job4j;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

/**
 * Class for delete abuse words from stream.
 * @author Alexander Ivanov
 * @since 19.03.2017
 * @version 1.0
 */
public class DropAbuse {
    /**
     * Read text with abuse words from stream and wright text without them to outputStream.
     * @param in - InputStream.
     * @param out - Outputstream.
     * @param abuse words.
     */
    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
            boolean abuseInStream = false;
            int c;
            while (reader.ready()) {
                reader.mark(100);
                for (int i = 0; i < abuse.length; i++) {
                    char[] abuseToArray = abuse[i].toCharArray();
                    int wordLength = 0;
                        for (int j = 0; j < abuseToArray.length; j++) {
                            if (reader.ready()) {
                                c = reader.read();
                                String letterFromStream = String.valueOf((char) c).toUpperCase();
                                String letterFromAbuseWord = String.valueOf(abuseToArray[j]).toUpperCase();
                                if (!letterFromStream.equals(letterFromAbuseWord)) {
                                    abuseInStream = false;
                                    j = abuseToArray.length;
                                } else {
                                    abuseInStream = true;
                                    wordLength++;
                                }
                            } else {
                                j = abuseToArray.length;
                            }
                        }

                    if (abuseInStream && wordLength == abuseToArray.length) {
                        reader.reset();
                        reader.skip(abuseToArray.length);
                        i = abuse.length;
                    } else {
                        reader.reset();
                    }
                }
                if (!abuseInStream) {
                    writer.write(reader.read());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
