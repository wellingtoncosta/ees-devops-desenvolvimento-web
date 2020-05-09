package br.uece.eesdevops.introducaospringboot;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

class Payloads {

    private static ClassLoader classLoader;

    static {
        classLoader = IntroducaoSpringBootApplication.class.getClassLoader();
    }

    static String newBookRequest() throws IOException {
        InputStream stream = classLoader.getResourceAsStream("new-book-request.json");
        if (stream != null) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        } else {
            throw new IllegalArgumentException("File new-book-request.json could not be loaded.");
        }
    }

    static String updateBookRequest() throws IOException {
        InputStream stream = classLoader.getResourceAsStream("update-book-request.json");
        if (stream != null) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        } else {
            throw new IllegalArgumentException("File update-book-request.json could not be loaded.");
        }
    }

    static String newStudentRequest() throws IOException {
        InputStream stream = classLoader.getResourceAsStream("new-student-request.json");
        if (stream != null) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        } else {
            throw new IllegalArgumentException("File new-student-request.json could not be loaded.");
        }
    }

    static String updateStudentRequest() throws IOException {
        InputStream stream = classLoader.getResourceAsStream("update-student-request.json");
        if (stream != null) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        } else {
            throw new IllegalArgumentException("File update-student-request.json could not be loaded.");
        }
    }

}
