import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

interface MyFile {
    abstract byte[] Read(String fileName) throws IOException;
    abstract byte[] ConvertFrom(byte[] bytes) throws IOException;
    abstract byte[] ConvertInto(byte[] bytes) throws IOException;
    abstract byte[] GetBytes(byte[] bytes) throws IOException;
    abstract void Write(String FileName,byte[] bytes) throws IOException;
}






class MyZipFile implements MyFile{

    byte[] myBytes = new byte[1024];


    @Override
    //  only reads first entry of zipFile, so it won't Read others. Don't use
    //  on archives with multiple files
    public byte[] Read(String fileName) throws IOException {

        ZipFile a = new ZipFile(fileName);

        Enumeration<?extends ZipEntry> enumeration = a.entries();
        ZipEntry entry =enumeration.nextElement();
        InputStream inputStream = a.getInputStream(entry);


        return inputStream.readAllBytes();

    }

    @Override
    public byte[] ConvertFrom(byte[] bytes) throws IOException {
        ZipInputStream zipIn = new ZipInputStream(new ByteArrayInputStream(bytes));
        ZipEntry entry = zipIn.getNextEntry();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;

        ZipOutputStream zipOutputStream = new ZipOutputStream(out);

        zipOutputStream.putNextEntry(entry);

        while ((len = zipIn.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        out.close();
        zipOutputStream.close();
        return out.toByteArray();
    }

    @Override
    public byte[] ConvertInto(byte[] bytes) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(out);
        ZipEntry entry = new ZipEntry("data");
        zipOut.putNextEntry(entry);
        zipOut.write(bytes);
        zipOut.closeEntry();
        zipOut.close();

        myBytes =out.toByteArray();

        return myBytes;
    }

    @Override
    public byte[] GetBytes(byte[] bytes) throws IOException {
        return new byte[0];
    }

    //will create .zip file with one entry. Both .zip file and entry have a name defined by parameter "FileName"
    //TODO add some unit tests
    @Override
    public void Write(String FileName, byte[] bytes) throws IOException {

        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(FileName));

        // создаем новую запись в архиве
        ZipEntry entry = new ZipEntry(FileName);
        zipOut.putNextEntry(entry);

        // записываем данные из массива байтов в архив
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            zipOut.write(buffer, 0, len);
        }

        // закрываем текущую запись и поток вывода архива
        zipOut.closeEntry();
        zipOut.close();
    }

}






class MyTXTFile implements MyFile{

    byte[] myBytes = new byte[1024];


    @Override
    public byte[] Read(String fileName) throws IOException {

        InputStream inputStream = new FileInputStream(fileName);

        return inputStream.readAllBytes();

    }

    @Override
    public byte[] ConvertFrom(byte[] bytes) throws IOException {
        return new byte[0];
    }

    @Override
    public byte[] ConvertInto(byte[] bytes) throws IOException {

        byte[] myBytes = new byte[1024];
        return myBytes;
    }

    @Override
    public byte[] GetBytes(byte[] bytes) throws IOException {
        return myBytes;
    }


    @Override
    public void Write(String FileName, byte[] bytes) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(FileName);

        fileOutputStream.write(bytes);
    }
}