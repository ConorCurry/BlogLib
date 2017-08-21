JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
	  TextUnit.java \
	  Post.java \
	  Blog.java \
	  Test.java \

default: classes

test:
	java -ea Test

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
