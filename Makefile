JAVAC=javac
sources=$(wildcard *.java)
classes=$(sources:.java=.class)
tests = $(wildcard *Test.java)
JUNIT=lib/junit.jar
HAMCREST=lib/hamcrest.jar

all: $(classes)

install:
	mkdir -p lib
	curl http://albertlatacz.published.s3.amazonaws.com/javarepl/javarepl.jar > lib/javarepl.jar
	curl http://bit.ly/My9IXz > lib/junit.jar
	curl http://bit.ly/1gbl25b > lib/hamcrest.jar

clean:
	rm -f $(classes)

%.class : %.java
	$(JAVAC) -cp '.:$(JUNIT)' $<

test: $(classes) 
	java -cp '.:./test:$(JUNIT):$(HAMCREST)' org.junit.runner.JUnitCore $(basename $(tests))

repl:
	java -jar lib/javarepl.jar

.PHONY: test clean repl