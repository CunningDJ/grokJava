# Grokking The Coding Interview: Java Practice Suite
This is my code suite, including homemade build and run tools and testing library, for practicing the Grokking the Coding Interview algo patterns in Java.

## Instructions
1. Add methods to an existing .java file and tests in the `main()` method (using the Tester class), or create a new .java file with `package com.cunningdj.grokJava` at the top and do the same.
2. The command line tool `gtool` can handle all clean, build, and run operations, based on the command given.  Run the tool without an argument to see the available commands.  Run with commands to perform the desired operations,
  * `br` will clean, build, and run the given class all in one (you don't need to include the package name for the class).  E.g. `gtool br GrokkingFastSlowPointers` will clean and build all .java files and then run the main method for the `GrokkingFastSlowPointers` class.
