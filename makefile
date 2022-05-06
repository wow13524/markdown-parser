CPATH=.:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar
MarkdownParse.class: MarkdownParse.java
	javac -cp $(CPATH) MarkdownParse.java
MarkdownParseTest.class: MarkdownParse.class MarkdownParseTest.java
	javac -cp $(CPATH):MarkdownParse.class MarkdownParseTest.java
test: MarkdownParse.class MarkdownParseTest.class
	make MarkdownParse.class
	make MarkdownParseTest.class
	java -cp $(CPATH) org.junit.runner.JUnitCore MarkdownParseTest