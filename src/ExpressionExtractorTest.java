import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionExtractorTest {


    @Test
    public void GetStartTest(){
        String str="expressions (4-2)*5 and 2*6-1 ";

        ExpressionExtractor extractor = new ExpressionExtractor(str);

        assertEquals(11,extractor.GetStart());

        extractor.GoToNext();
        assertEquals(23,extractor.GetStart());
    }

    @Test
    public void GetEndTest(){
        String str="expressions (4-2)*5 and 2*6-1 ";

        ExpressionExtractor extractor = new ExpressionExtractor(str);

        assertEquals(20,extractor.GetEnd());

        extractor.GoToNext();
        assertEquals(30,extractor.GetEnd());
    }

    @Test
    public void GetEquationTest(){
        String str="expressions (4-2)*5 and 2*6-1 ";
        ExpressionExtractor extractor = new ExpressionExtractor(str);

        assertEquals("(4-2)*5",extractor.GetEquation());

        extractor.GoToNext();
        assertEquals("2*6-1",extractor.GetEquation());
    }

    @Test
    public void StringWithNoExpression(){
        String string = "";
        ExpressionExtractor extractor = new ExpressionExtractor("");

        assertEquals(extractor.isValid(),false);


        String string2 = "no expressions at all";

        ExpressionExtractor extractor1 = new ExpressionExtractor(string2);

        assertEquals(extractor1.isValid(),false);


    }


    @Test
    public void DoubleExtractionTest(){
        String str="expressions 1.3+3.7/5.4 and 7.2*6-1.1 ";

        ExpressionExtractor extractor = new ExpressionExtractor(str);

        assertEquals("1.3+3.7/5.4",str.substring(extractor.GetStart(),extractor.GetEnd()).trim());

        extractor.GoToNext();
        assertEquals("7.2*6-1.1",str.substring(extractor.GetStart(),extractor.GetEnd()).trim());
    }

    @Test
    public void SimpleExtractionTest(){
        String str="expressions 1+3/5 and 2*6-1 and ((1+2-5)) (brace test (1+3)/3 )";

        ExpressionExtractor extractor = new ExpressionExtractor(str);

        assertEquals("1+3/5",str.substring(extractor.GetStart(),extractor.GetEnd()).trim());

        extractor.GoToNext();
        assertEquals("2*6-1",str.substring(extractor.GetStart(),extractor.GetEnd()).trim());

        extractor.GoToNext();
        assertEquals("((1+2-5))",str.substring(extractor.GetStart(),extractor.GetEnd()).trim());

        extractor.GoToNext();
        assertEquals("(1+3)/3",str.substring(extractor.GetStart(),extractor.GetEnd()).trim());

    }
}