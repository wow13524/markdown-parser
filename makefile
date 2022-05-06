CPATH=.:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar
MarkdownParseTest.class: MarkdownParse.java MarkdownParseTest.java
	javac -cp $(CPATH) MarkdownParse.java MarkdownParseTest.java
test: MarkdownParse.class MarkdownParseTest.class
	make MarkdownParseTest.class
	java -cp $(CPATH) org.junit.runner.JUnitCore MarkdownParseTest