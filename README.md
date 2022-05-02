# Grokking The Coding Interview: Java Practice Suite
This is my code suite, including homemade build and run tools and testing library, for practicing the Grokking the Coding Interview algo patterns in Java (and also refreshing on some Java and bash basics).

# Instructions
## Creating/Adding Java Classes & Tests
### Adding to an Existing Java Class & Tests
Add methods to an existing .java file and tests in the class's `main()` method (using the Tester class for test utilities - see example main() methods in existing files)

### Creating a new Java Class & Tests
Create a new .java file/class with `package com.cunningdj.grokJava` at the top (or whatever you'd like to set the `DEFAULT_PACKAGE_NAME`](https://github.com/CunningDJ/grokJava/blob/master/gtool#L5) to) and do the same.


## `gtool` - Home Made Clean, Build & Run Tool
* The command line tool `gtool` can handle all `clean`, `build`, and `run` operations, using these as the respective command argument (e.g. `gtool clean`).
* Run the tool without an argument to see the available commands.  Run with commands to perform the desired operations.

### BE AWARE
* `gtool build` will automatically `clean` before building.
* `gtool run` will neither build nor clean automatically.  To get all of these in one, use the `gtool br` command (see below)

### `gtool br` - Easy `clean`, `build` & `run` All-In-One
The `br` command argument will clean, build, and run the given class all in one (you don't need to include the package name for the class).

**Example**:  `gtool br GrokkingFastSlowPointers` will clean and build all .java files and then run the main method for the `GrokkingFastSlowPointers` class.

### Verbose
If for some reason you want a verbose printout of the stages being performed by `gtool`, simply change the [initialized `VERBOSE` value](https://github.com/CunningDJ/grokJava/blob/master/gtool#L9) to `1`.  Maybe I'll add a `-v` flag but I don't feel like it right now.