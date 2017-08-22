# BlogLib Coding Challenge
## Implemented using Java 8. A makefile is used for automated building and running the test code.

## Instructions For Use:
Clone the repository. In the directory, run ```make```. This will compile the project using javac. Note that this project has Java 8 dependencies (```java.time.Instant``` is used to track post creation times).

Then, to run the tests (found in Test.java), use ```make test```. This simply runs the test code, using java runtime flags to enable the assertion statements (which are used to eliminate a JUnit dependency, since this is a pretty simple project that doesn't have rigorous testing requirements, just some sanity checks :simple_smile: ).
