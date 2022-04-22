import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.Assert.*;   //Imports assert methods from JUnit
import org.junit.*;                 //Imports everything from JUnit
public class MarkdownParseTest {    //Declares the MarkdownParseTest class
    @Test                           //Annotation to mark the method as a JUnit test
    public void addition() {        //Method representing an addition unit test
        assertEquals(2, 1 + 1);     //Errors if 2 != 1 + 1, passes otherwise
    }                               //Close brace

    @Test
    public void test_test_file() throws IOException{
        Path fileName = Path.of("test-file.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertArrayEquals(new Object[]{"https://something.com","some-thing.html"},links.toArray());
    }
}                                   //Close brace