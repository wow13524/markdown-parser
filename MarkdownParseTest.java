import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.Assert.*;   //Imports assert methods from JUnit
import org.junit.*;                 //Imports everything from JUnit
public class MarkdownParseTest {    //Declares the MarkdownParseTest class
    @Test                           //Annotation to mark the method as a JUnit test
    public void addition() {        //Method representing an addition unit test
        System.out.println();
        assertEquals(2, 1 + 1);     //Errors if 2 != 1 + 1, passes otherwise
    }                               //Close brace

    @Test
    public void test_test_file() throws IOException{
        Path fileName = Path.of("test-file.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertArrayEquals(new Object[]{"https://something.com","some-thing.html"},links.toArray());
    }

    @Test
    public void test_test_file_1() throws IOException{
        Path fileName = Path.of("test-file-1.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertArrayEquals(new Object[]{"my-page.html"},links.toArray());
    }

    @Test
    public void test_test_file_2() throws IOException{
        Path fileName = Path.of("test-file-2.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertArrayEquals(new Object[]{"linkie.html"},links.toArray());
    }

    @Test
    public void test_test_file_3() throws IOException{
        Path fileName = Path.of("test-file-3.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertArrayEquals(new Object[0],links.toArray());
    }

    @Test
    public void test_snippet_1() throws IOException{
        Path fileName = Path.of("snippet-1.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertArrayEquals(new Object[]{"`google.com","google.com","ucsd.edu"},links.toArray());
    }

    @Test
    public void test_snippet_2() throws IOException{
        Path fileName = Path.of("snippet-2.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertArrayEquals(new Object[]{"a.com","a.com(())","example.com"},links.toArray());
    }

    @Test
    public void test_snippet_3() throws IOException{
        Path fileName = Path.of("snippet-3.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertArrayEquals(new Object[]{"https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule"},links.toArray());
    }

    @Test
    public void failing_test() throws Exception{
        throw new Exception();
    }
}                                   //Close brace