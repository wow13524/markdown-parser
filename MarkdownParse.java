//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    private static int countOccurences(String markdown,String target){
        return markdown.length() - markdown.replace(target,"").length();
    }

    private static boolean isWithinInlineCode(String markdown,int end){
        String subMarkdown = markdown.substring(0,end);
        return countOccurences(subMarkdown,"`") % 2 != 0;
    }

    private static boolean isIndexEscaped(String markdown,int index){
        return index > 0 && markdown.substring(index-1,index).equals("\\");
    }

    private static int indexOfMarkdown(String markdown,String toFind,int index){
        do{
            index = markdown.indexOf(toFind,++index);
        }while(index >= 0 && (isWithinInlineCode(markdown,index) || isIndexEscaped(markdown,index)));
        return index;
    }

    private static int indexOfCloseParen(String markdown,int openParen){
        int index = markdown.indexOf(")",openParen);
        if(index == -1){
            return -1;
        }
        int occurrences = countOccurences(markdown.substring(openParen+1,index),"(");
        for(int i=0;i<occurrences;i++){
            index = markdown.indexOf(")",index+1);
        }
        return index;
    }

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = indexOfMarkdown(markdown,"[",currentIndex);
            int closeBracket = indexOfMarkdown(markdown,"]", openBracket);
            int openParen = indexOfMarkdown(markdown,"(", closeBracket);
            int closeParen = indexOfCloseParen(markdown, openParen);
            int spaceInLink = markdown.indexOf(" ", openParen);

            //break if no more link patterns are found
            if(openBracket < 0 || closeBracket < 0 || openParen < 0 || closeParen < 0){
                break;
            }

            currentIndex = closeParen + 1;

            //continue if link is actually an image
            if((openBracket != 0 && markdown.charAt(openBracket-1) == '!')){
                continue;
            }
            //continue if there is a space between ] and ()
            if(openParen != closeBracket + 1){
                continue;
            }
            //continue if there is a space in the middle of the link
            if(spaceInLink != -1 && spaceInLink <= closeParen){
                continue;
            }
            //continue if there is a line break in the text
            if(markdown.substring(openBracket+1,closeBracket).contains("\n\n")){
                continue;
            }
            //continue if there is a line break in the link
            if(markdown.substring(openParen+1,closeParen).contains("\n\n")){
                continue;
            }

            toReturn.add(markdown.substring(openParen + 1, closeParen).strip());
        }
        return toReturn;
    }

    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}
