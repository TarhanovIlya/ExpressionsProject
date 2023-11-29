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

import static org.junit.jupiter.api.Assertions.*;

class MyFileImplementationsTest {

    MyFile myFile;

    byte[] zipFile_AfterRead=new byte[0];

    //MyZipFile tests
    @Test
    public void Read_MyZipFile() throws IOException {

        myFile = new MyZipFile();



        ZipFile zipFile = new ZipFile("MyZipFile_ReadTest.zip");
        Enumeration<?extends ZipEntry> enumeration = zipFile.entries();
        ZipEntry entry = enumeration.nextElement();
        InputStream inputStream = zipFile.getInputStream(entry);

        zipFile_AfterRead =myFile.Read("MyZipFile_ReadTest.zip");

        assertTrue(Arrays.equals(zipFile_AfterRead,inputStream.readAllBytes()));
    }

    @Test
    public void Write_MyZipFile() throws IOException {

        myFile = new MyZipFile();

        myFile.Write("MyZipFile_WriteTest.zip",myFile.Read("MyZipFile_ReadTest.zip"));

        File file1 = new File("MyZipFile_WriteTest.zip");
        File file2 = new File("MyZipFile_ReadTest.zip");

        FileInputStream f1 = new FileInputStream(file1);
        FileInputStream f2 = new FileInputStream(file2);

        byte[] bytes_from_MyZipFile_WriteTest = f1.readAllBytes();
        byte[] bytes_from_MyZipFile_ReadTest = f2.readAllBytes();


        assertEquals(bytes_from_MyZipFile_WriteTest,bytes_from_MyZipFile_WriteTest);
    }

    @Test
    public void ConvertInto_MyZipFile() throws IOException {
        myFile = new MyZipFile();



        ZipFile zipFile = new ZipFile("MyZipFile_SecondLevel_Test.zip");
        Enumeration<?extends ZipEntry> enumeration = zipFile.entries();
        ZipEntry entry = enumeration.nextElement();
        InputStream inputStream = zipFile.getInputStream(entry);



        assertTrue(Arrays.equals(myFile.Read("MyZipFile_SecondLevel_Test.zip"),inputStream.readAllBytes()));










//        //just to be sure if it compares Array contents right
//
//
//
//        ZipFile zipFile2 = new ZipFile("MyZipFileTest.zip");
//        Enumeration<?extends ZipEntry> enumeration2 = zipFile2.entries();
//        ZipEntry entry2 = enumeration2.nextElement();
//        InputStream inputStream2 = zipFile2.getInputStream(entry2);
//
//
//
//        //made this to actually see the contents of byte[] arrays;
//        byte[] b1 =myFile.Read("MyZipFile_SecondLevel_Test.zip");
//        byte[] b2 =inputStream2.readAllBytes();
//
//        assertFalse(Arrays.equals(b1,b2));
    }





    //MyTXTFile tests
    @Test
    public void Read_MyTXTFile() throws IOException {

        myFile = new MyTXTFile();

        byte[] bytes = myFile.Read("MyTXTFile_ReadTest.txt");
        String line = new String(bytes);

        assertEquals("this is a test text for MyTXTFile_ReadTest",line);
    }


}