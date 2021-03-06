package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test EvenBytesStream.
 *
 * @author Alexander Ivanov
 * @since 04.03.2017
 * @version 1.0
 */
public class EvenBytesStreamTest {
    /**
     * test stream with even bytes.
     */
    @Test
    public void whenBytesStreamIsEven() {
        final String number = "1000";
        byte[] byteArray = number.getBytes();
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray)) {
            EvenBytesStream evenBytesStream = new EvenBytesStream();
            final boolean resultEven = evenBytesStream.isNumber(byteArrayInputStream);
            final boolean checkEven = true;
            assertThat(resultEven, is(checkEven));
        } catch (IOException e) {
            System.out.println("Stream error");
        }
    }
    /**
     * test stream with odd bytes.
     */
    @Test
    public void whenBytesStreamIsOdd() {
        final String number = "1001";
        byte[] byteArray = number.getBytes();
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray)) {
            EvenBytesStream evenBytesStream = new EvenBytesStream();
            final boolean resultOdd = evenBytesStream.isNumber(byteArrayInputStream);
            final boolean checkOdd = false;
            assertThat(resultOdd, is(checkOdd));
        } catch (IOException e) {
            System.out.println("Stream error");
        }
    }
}
