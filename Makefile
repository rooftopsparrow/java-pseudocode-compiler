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
	curl http://search.maven.org/remotecontent?filepath=junit/junit/4.11/junit-4.11.jar > lib/junit.jar
	curl http://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar > lib/hamcrest.jar

clean:
	rm -f $(classes)

%.class : %.java
	$(JAVAC) -cp '.:$(JUNIT)' $<

test: $(classes) 
	java -cp '.:./test:$(JUNIT):$(HAMCREST)' org.junit.runner.JUnitCore $(basename $(tests))

repl:
	java -jar lib/javarepl.jar

.PHONY: test clean repl