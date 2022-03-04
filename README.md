# Java-Examples

## Overview

This is a repository for examples that I've coded for purposes of learning Java and best-practices.

The best way to learn is by doing, and although I would normally try to find battle-tested open-source, I can use the code here for the purpose of using it as a reference or basis for building more exotic data types in the future.

## RadixSort

A clever algorithm that can sort faster than O(n * log(n)).

### To build the code

First build FileUtils. Then,

`javac src/examples/RadixSort.java -s bin --class-path bin`

### To run the code

`java -classpath bin examples.RadixSort`

### To use as a library

```
import examples.RadixSort;

// CAUTION:  Make sure the RadixSort.WORD_FILE matches the location of the data file you are trying to sort, and that
//  RadixSort.OUTPUT matches the location where you want the sorted data written.

try {
	RadixSort rs = new RadixSort();
	rs.run();
} catch (java.io.FileNotFoundException e) {
	// Only known cause is if the file is not in the right place according to config described in the CAUTION above.
	throw new IllegalStateException(e);
}


```

## FileUtils

Convenience functions for read/writing files that just improve the error messages thrown if a file cannot be opened for read
or write.

### To build the code

`mkdir bin`
`javac src/examples/FileUtils.java -s bin`

## To use as a library

```
import examples.FileUtils;

String INPUT_FILE_NAME = "SOME_INPUT_FILE_PATH";
String OUTPUT_FILE_NAME = "SOME_OUTPUT_FILE_PATH";

try {
  java.util.Scanner some_file_scanner = FileUtils.openToRead(INPUT_FILE_NAME);
  java.io.PrintWriter output = FileUtils.openToWrite(OUTPUT_FILE_NAME);
} catch (java.io.FileNotFoundException e) {
  throw new IllegalStateException(e); // Error message will show the filename that wasn't found
}

```

## Author

Alex Richardson

## License

Copyright 2022 [Alex Richardson](https://github.com/alexrich729)

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
