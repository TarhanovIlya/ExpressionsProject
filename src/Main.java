import com.fathzer.soft.javaluator.DoubleEvaluator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {

        MyFile myFile = new MyTXTFile();
        MyFile myFile_zip = new MyZipFile();

        byte[] bytes = new byte[1024];


        bytes= myFile_zip.Read("MyZipFileTest.zip");

        bytes= myFile_zip.ConvertFrom(bytes);

        myFile.Write("try_zip_to_txt_conversion.txt",bytes);
    }
}