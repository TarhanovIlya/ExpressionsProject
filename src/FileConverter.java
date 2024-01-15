import com.fathzer.soft.javaluator.DoubleEvaluator;

import javax.xml.bind.JAXBException;
import java.beans.Expression;
import java.io.*;
import java.nio.charset.StandardCharsets;


//will use singleTone pattern
public class FileConverter {


    String fileName;



    public FileConverter(String fileName) {
        this.fileName = fileName;
    }

    public FileConverter() {
        this.fileName = null;
    }


    public void ConvertInto( MyFile fileFormat) throws IOException, JAXBException {
        if ((fileName == null) || (fileName == "")){
            throw new RuntimeException("filename = null or \"\"");
        }
        fileName = fileFormat.ConvertInto(fileName);
    }

    public  void ConvertFrom(MyFile fileFormat) throws IOException, JAXBException {
        if ((fileName == null) || (fileName == "")){
            throw new RuntimeException("filename = null or \"\"");
        }
        fileName = fileFormat.ConvertFrom(fileName);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void compute() throws IOException {

        File file = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);

        String fileContents = new String(fileInputStream.readAllBytes());

        StringBuffer newFileContents = new StringBuffer();

        ExpressionExtractor extractor = new ExpressionExtractor(fileContents);

        if (!extractor.isValid()) {
            throw new RuntimeException("trying to compute invalid string");
        }


        int startText;
        int endText;

        startText =0;
        endText = extractor.GetStart();

        do {
            endText = extractor.GetStart();
            newFileContents.append(fileContents.substring(startText,endText));
            DoubleEvaluator doubleEvaluator = new DoubleEvaluator();
            newFileContents.append(" "+doubleEvaluator.evaluate(extractor.GetEquation())+" ") ;
            startText = extractor.GetEnd();

        } while (extractor.GoToNext());

        newFileContents.append(fileContents.substring(startText));

        fileInputStream.close();

        file.delete();

        FileOutputStream fileOutputStream = new FileOutputStream(this.fileName);
        fileOutputStream.write(newFileContents.toString().getBytes(StandardCharsets.UTF_8));

        fileInputStream.close();

        boolean a = extractor.isValid();
    }


}
