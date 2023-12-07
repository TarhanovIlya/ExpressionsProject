import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;

class MyFileImplementationsTest {

    @Test
    public void MyZipFile_ConvertInto() throws IOException {


        MyZipFile myZipFile = new MyZipFile();

        myZipFile.ConvertInto("testFile.txt");

        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream("testFile.txt.zip"));
        ZipEntry zipEntry = zipInputStream.getNextEntry();

        assertEquals(zipEntry.getName(),"testFile.txt");

    }


    @Test
   public void MyZipFile_ConvertFrom() throws IOException {


        MyZipFile myZipFile = new MyZipFile();

        myZipFile.ConvertFrom("testFile.txt.zip");


    }




}