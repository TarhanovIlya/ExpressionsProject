import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionExtractorTest {


    @Test
    public void GetStartTest(){
        String str="expressions (4-2)*5 and 2*6-1";

        ExpressionExtractor extractor = new ExpressionExtractor(str);

        assertEquals(11,extractor.GetStart());

        extractor.GoToNext();
        assertEquals(23,extractor.GetStart());
    }

    @Test
    public void GetEndTest(){
        String str="expressions (4-2)*5 and 2*6-1";

        ExpressionExtractor extractor = new ExpressionExtractor(str);

        assertEquals(20,extractor.GetEnd());

        extractor.GoToNext();
        assertEquals(29,extractor.GetEnd());
    }

    @Test
    public void SimpleExtractionTest(){
        String str="expressions 1+3/5 and 2*6-1";

        ExpressionExtractor extractor = new ExpressionExtractor(str);

        assertEquals("1+3/5",str.substring(extractor.GetStart(),extractor.GetEnd()).trim());

        extractor.GoToNext();
        assertEquals("2*6-1",str.substring(extractor.GetStart(),extractor.GetEnd()).trim());
    }
}