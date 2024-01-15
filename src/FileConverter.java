import javax.xml.bind.JAXBException;
import java.io.IOException;

public class FileConverter {
    String fileName;

    public FileConverter(String fileName) {
        this.fileName = fileName;
    }

    public void ConvertInto( MyFile fileFormat) throws IOException, JAXBException {
        fileName = fileFormat.ConvertInto(fileName);
    }

    public  void ConvertFrom(MyFile fileFormat) throws IOException, JAXBException {
        fileName = fileFormat.ConvertFrom(fileName);

    }
}
