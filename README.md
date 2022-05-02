# Grokking The Coding Interview: Java Practice Suite
This is my code suite, including homemade build and run tools and testing library, for practicing the Grokking the Coding Interview algo patterns in Java (and also refreshing on some Java and bash basics).

## Instructions
## Creating/Adding Java Classes & Tests
### Adding to an Existing Java Class & Tests
Add methods to an existing .java file and tests in the class's `main()` method (using the Tester class for test utilities - see example main() methods in existing files)

### Creating a new Java Class & Tests
Create a new .java file/class with `package com.cunningdj.grokJava` at the top and do the same.


## `gtool` - Home Made Clean, Build & Run Tool
* The command line tool `gtool` can handle all `clean`, `build`, and `run` operations, using these as the respective command argument.
* Run the tool without an argument to see the available commands.  Run with commands to perform the desired operations,

### `gtool br` - Easy clean, build & run All-In-One
The `br` command argument will clean, build, and run the given class all in one (you don't need to include the package name for the class).
* E.g. `gtool br GrokkingFastSlowPointers` will clean and build all .java files and then run the main method for the `GrokkingFastSlowPointers` class.
