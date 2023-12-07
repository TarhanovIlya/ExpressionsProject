import com.fathzer.soft.javaluator.DoubleEvaluator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {

        MyFile myFile = new MyJSONFile();
        MyFile myFile1 = new MyZipFile();

//          myFile1.ConvertInto("txt_to_json.txt");

            myFile.ConvertInto("testFile.txt");
//
//          myFile.ConvertFrom("testFile.txt.zip.json");
//
}
}