package tk.nfsmonstr.simplewakeonlan;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * Testing Checker class
 *
 */
public class CheckerUnitTest {
    @Test
    public void checker_checkIp() throws Exception {
        System.out.println("Starting: checker_checkIp");
        assertEquals(true,Checker.checkIp("127.0.0.1"));
        System.out.println("127.0.0.1 - tested - ok");
        assertEquals(false,Checker.checkIp("627.0.0.1"));
        System.out.println("627.0.0.1 - tested - ok");
        assertEquals(true,Checker.checkIp("8.8.8.8"));
        System.out.println("8.8.8.8 - tested - ok");
        assertEquals(true,Checker.checkIp("127.99.99.99"));
        System.out.println("127.99.99.99 - tested - ok");
        assertEquals(false,Checker.checkIp("127.0.0"));
        System.out.println("127.0.0 - tested - ok");
    }

    @Test
    public void checker_checkMac() throws Exception {
        System.out.println("Starting: checker_checkMac");
        assertEquals(true,Checker.checkMac("00:0a:95:9d:68:16"));
        System.out.println("00:0a:95:9d:68:16 - tested - ok");
        assertEquals(true,Checker.checkMac("00:0A:95:9D:68:16"));
        System.out.println("00:0A:95:9D:68:16 - tested - ok");
        assertEquals(true,Checker.checkMac("00:0a:95:9D:68:16"));
        System.out.println("00:0a:95:9D:68:16 - tested - ok");
        assertEquals(false,Checker.checkMac("00:0A:959D:68:16"));
        System.out.println("00:0A:959D:68:16 - tested - ok");
        assertEquals(false,Checker.checkMac("00:0A:95-9D:68:16"));
        System.out.println("00:0A:95-9D:68:16 - tested - ok");
        assertEquals(true,Checker.checkMac("00-0A-95-9D-68-16"));
        System.out.println("00-0A-95-9D-68-16 - tested - ok");
    }
}