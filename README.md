# excel-parser
Print the content of an Excel file line by line


## Installation
Just use the jar file in `target/uberjar/`

## Usage


    $ java -jar excel-parser-0.1.0-standalone.jar [excel-file]


## Examples

    $ java -jar excel-parser-0.1.0-standalone.jar test.xls
    $ java -jar excel-parser-0.1.0-standalone.jar test.xlsx

### Bugs
* For excelf files with a lot unicode, the default file system `POIFSFileSystem` may fail at `org.apache.poi.poifs.filesystem.ODocumentInputStream.readUShort`, in this case you have to use `NPOIFSFileSystem`.

### TODO
* Print the worksheet with number of lines for better parsing of the output.

## License

Copyright © 2015 Ha.Minh

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
