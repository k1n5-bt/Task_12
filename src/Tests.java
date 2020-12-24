import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
public class Tests{

    @Test
    public void shouldThrowNumberFormatException(){
        Exception exception = assertThrows(NumberFormatException.class, () -> Integer.decode(""));
        assert "Zero length string".equals(exception.getMessage());
    }

    @Test
    public void shouldDecodeNegativeNumber(){
        assert -21 == Integer.decode("-21");
    }
    @Test
    public void shouldDecodeSimpleNumber(){
        assert 21 == Integer.decode("21");
    }

    @Test
    public void shouldDecode16RadixNumber(){
        assert 0X12 == Integer.decode("0X12");
        assert 0x12 == Integer.decode("0x12");
        assert 0x12 == Integer.decode("0X12");
        assert 0X12 == Integer.decode("0x12");

        assert 0x12 == Integer.decode("#12");
    }

    @Test
    public void shouldDecode8RadixNumber(){
        assert 012 == Integer.decode("012");
    }

    @ParameterizedTest
    @ValueSource(strings = {"#-12", "0x-123", "0-14"})
    public void shouldThrowExceptionWhenSignCharacterInWrongPosition(String value){
        Exception exception = assertThrows(NumberFormatException.class, ()-> Integer.decode(value));
        assert exception.getMessage().equals("Sign character in wrong position");
    }

    @Test
    public void shouldHandleMaxAndMinIntegerCases(){
        assert Integer.MAX_VALUE == Integer.decode(String.valueOf(Integer.MAX_VALUE));
        assert Integer.MIN_VALUE == Integer.decode(String.valueOf(Integer.MIN_VALUE));
    }

    @Test
    public void shouldDecodeNegativeNumbers(){
        assert -12 == Integer.decode("-12");
        assert -012 == Integer.decode("-012");
        assert -0x12 == Integer.decode("-0x12");
        assert -0x12 == Integer.decode("-#12");
    }

}
