# Def Method Input Parser Project
## Classes
- `App.java` (includes mainline)
- `InputParser.java` (contains logic for parsing, preparing output)
- `Person.java` (Person object to model person attributes passed in input)

## Tests
- `InputParserTest.java` (JUnit & Mockito unit test class)

## How to use
- navigate to the "Def Method/src" directory in terminal
- to compile:
  - `javac -cp ../lib/mockito-all-2.0.2-beta.jar:../lib/junit-4.13-beta-1.jar main/*.java`
- to run:
  - two inputs, the location of the input file and a number (1-3) for the different sorting options
  - `java main.App ./main/pipe.txt 2` - in this case, the input file ("pipe.txt") is in the same directory as the .java files
- you'll then see an output file ("output.txt") generated in `Def Method/src`

## Note
- `1` corresponds to sorted by gender (females before males) then by last name ascending
- `2` corresponds to sorted by birth date, ascending then by last name ascending
- `3` corresponds to sorted by last name, descending



