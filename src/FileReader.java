import java.io.IOException;

public class FileReader {

    byte[] ReadAs(String fileLocation, MyFile file) throws IOException {

        return file.Read(fileLocation);
    }
    

}
