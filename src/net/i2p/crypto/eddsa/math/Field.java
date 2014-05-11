package net.i2p.crypto.eddsa.math;

import java.io.Serializable;

/**
 * An EdDSA finite field. Includes several pre-computed values.
 * @author str4d
 *
 */
public class Field implements Serializable {
    private static final long serialVersionUID = 8746587465875676L;

    public final FieldElement zero;
    public final FieldElement one;
    public final FieldElement two;
    public final FieldElement four;
    public final FieldElement five;
    public final FieldElement eight;

    private final int b;
    private final FieldElement q;
    /**
     * q-2
     */
    private final FieldElement qm2;
    /**
     * (q-5) / 8
     */
    private final FieldElement qm5d8;
    private final Encoding enc;
    private final FieldElement I;

    public Field(int b, byte[] q, Encoding enc) {
        this.b = b;
        this.enc = enc;
        this.enc.setField(this);

        this.q = fromByteArray(q);

        // Set up constants
        zero = fromByteArray(Constants.ZERO);
        one = fromByteArray(Constants.ONE);
        two = fromByteArray(Constants.TWO);
        four = fromByteArray(Constants.FOUR);
        five = fromByteArray(Constants.FIVE);
        eight = fromByteArray(Constants.EIGHT);

        // Precompute values
        qm2 = this.q.subtract(two);
        qm5d8 = this.q.subtract(five).divide(eight);
        I = two.pow(this.q.subtract(one).divide(four));
    }

    public FieldElement fromByteArray(byte[] x) {
        return enc.decode(x);
    }

    public int getb() {
        return b;
    }

    public FieldElement getQ() {
        return q;
    }

    public FieldElement getQm2() {
        return qm2;
    }

    public FieldElement getQm5d8() {
        return qm5d8;
    }

    public Encoding getEncoding(){
        return enc;
    }

    public FieldElement getI() {
        return I;
    }

    @Override
    public int hashCode() {
        return q.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Field))
            return false;
        Field f = (Field) obj;
        return b == f.b && q.equals(f.q);
    }
}
