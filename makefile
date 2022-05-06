CPATH=.;lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar
test: MarkdownParse.java MarkdownParseTest.java
	javac -cp "$(CPATH)" MarkdownParse.java MarkdownParseTest.java
	java -cp "$(CPATH)" org.junit.runner.JUnitCore MarkdownParseTest