/**
 * 
 */
package net.i2p.crypto.eddsa.math.ed25519;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import net.i2p.crypto.eddsa.Utils;
import net.i2p.crypto.eddsa.math.ScalarOps;
import org.junit.Test;

/**
 * @author str4d
 *
 */
public class Ed25519ScalarOpsTest {
    /**
     * Test method for {@link net.i2p.crypto.eddsa.math.bigint.BigIntegerScalarOps#reduce(byte[])}.
     */
    @Test
    public void testReduce() {
        ScalarOps sc = new Ed25519ScalarOps();
        // Example from test case 1
        byte[] r = Utils.hexToBytes("b6b19cd8e0426f5983fa112d89a143aa97dab8bc5deb8d5b6253c928b65272f4044098c2a990039cde5b6a4818df0bfb6e40dc5dee54248032962323e701352d");
        assertThat(sc.reduce(r), is(equalTo(Utils.hexToBytes("f38907308c893deaf244787db4af53682249107418afc2edc58f75ac58a07404"))));
    }

    /**
     * Test method for {@link net.i2p.crypto.eddsa.math.bigint.BigIntegerScalarOps#multiplyAndAdd(byte[], byte[], byte[])}.
     */
    @Test
    public void testMultiplyAndAdd() {
        ScalarOps sc = new Ed25519ScalarOps();
        // Example from test case 1
        byte[] h = Utils.hexToBytes("86eabc8e4c96193d290504e7c600df6cf8d8256131ec2c138a3e7e162e525404");
        byte[] a = Utils.hexToBytes("307c83864f2833cb427a2ef1c00a013cfdff2768d980c0a3a520f006904de94f");
        byte[] r = Utils.hexToBytes("f38907308c893deaf244787db4af53682249107418afc2edc58f75ac58a07404");
        byte[] S = Utils.hexToBytes("5fb8821590a33bacc61e39701cf9b46bd25bf5f0595bbe24655141438e7a100b");
        assertThat(sc.multiplyAndAdd(h, a, r), is(equalTo(S)));
    }

}
