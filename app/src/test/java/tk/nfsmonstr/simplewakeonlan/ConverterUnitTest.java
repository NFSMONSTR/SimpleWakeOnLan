package tk.nfsmonstr.simplewakeonlan;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Testing Converter class
 *
 */

public class ConverterUnitTest {
    @Test
    public void converter_checkHexIntoByte() {
        String[] input  = {"FF","A0","A","0","F",""};
        byte[] output = {(byte)0xFF,(byte)0xA0,0x0A,0x00,0x0F,0x00};
        System.out.println("Starting: converter_checkHexIntoByte");
        for (int i=0;i<input.length;i++) {
            assertEquals(output[i],Converter.hexIntoByte(input[i]));
            System.out.println(input[i].concat(" - tested - ok"));
        }
    }
}
