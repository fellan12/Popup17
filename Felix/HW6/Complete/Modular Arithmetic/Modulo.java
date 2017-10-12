
public class Modulo {

    public long num;
    public long mod;

    public Modulo(long num, long mod) {
        this.num = (num < 0) ? (num % mod) + mod: (num % mod);
        this.mod = mod;
    }

    public Modulo addition(Modulo m) {
        return new Modulo(this.num + m.num, this.mod);
    }

    public Modulo subtract(Modulo m) {
        return new Modulo(this.num - m.num, this.mod);
    }

    public Modulo multiply(Modulo m) {
        return new Modulo(this.num * m.num, this.mod);
    }

    public Modulo divide(Modulo m) {
        Modulo inverse = m.inverse();
        return (inverse == null) ? null : this.multiply(inverse);
    }

    private Modulo inverse() {
        long t = 0;
        long newt = 1;
        long r = this.mod;
        long newr = this.num;
        while (newr != 0) {
            long quotient = r / newr;
            long swap = t - (quotient * newt);
            t = newt;
            newt = swap;
            swap = r - (quotient * newr);
            r = newr;
            newr = swap;
        }
        if (r > 1) return null; // does not have an inverse
        return new Modulo(t, this.mod);
    }

    @Override
    public String toString() {
        return this.num + "";
    }
}
