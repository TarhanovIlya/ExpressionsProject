import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MyFileTest {

    @Test
    void testMyZipFile() throws IOException, JAXBException {
        // Replace with the actual file path in your project
        String testFilePath = "test_zip.txt";

        // Create a MyZipFile instance
        MyFile myZipFile = new MyZipFile();

        // Convert text file to zip
        String zipFileName = myZipFile.ConvertInto(testFilePath);

        // Check if zip file is created
        assertTrue(new File(zipFileName).exists());

        // Convert zip file back to text
        String newFileName = myZipFile.ConvertFrom(zipFileName);

        // Check if the expected file is created
        assertTrue(new File(newFileName).exists());
    }

    @Test
    void testMyJSONFile() throws IOException, JAXBException {
        // Replace with the actual file path in your project
        String testFilePath = "test_json.txt.json";

        // Create a MyJSONFile instance
        MyFile myJSONFile = new MyJSONFile();

        // Convert JSON file to text
        String newFileName = myJSONFile.ConvertFrom(testFilePath);

        // Check if the expected file is created
        assertTrue(new File(newFileName).exists());

        // Convert text file back to JSON
        String jsonFileName = myJSONFile.ConvertInto(newFileName);

        // Check if JSON file is created
        assertTrue(new File(jsonFileName).exists());
    }

    @Test
    void testMyXMLFile() throws IOException, JAXBException {
        // Replace with the actual file path in your project
        String testFilePath = "test_xml.txt.xml";

        // Create a MyXMLFile instance
        MyFile myXMLFile = new MyXMLFile();

        // Convert XML file to text
        String newFileName = myXMLFile.ConvertFrom(testFilePath);

        // Check if the expected file is created
        assertTrue(new File(newFileName).exists());

        // Convert text file back to XML
        String xmlFileName = myXMLFile.ConvertInto(newFileName);

        // Check if XML file is created
        assertTrue(new File(xmlFileName).exists());
    }
}
