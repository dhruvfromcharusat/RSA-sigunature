import java.math.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class rsa_sig_server {

    public static void main(String args[]) throws IOException {
        try{
        int i1;
        BigInteger p, q, p_n, q_n, n, fi, e, e_, d, i;
        BigInteger mesg, cipher_text, plain_text;
        BigInteger b = BigInteger.valueOf(0);
        BigInteger bi = BigInteger.valueOf(1);
        BigInteger bii = BigInteger.valueOf(2);
        // for euclidean algorithm.
        BigInteger r, t1, t2, t, qo;

        Scanner sc = new Scanner(System.in);

        ServerSocket serverSocket = new ServerSocket(8088);
        System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
        Socket server = serverSocket.accept();
        System.out.println("Just connected to " + server.getRemoteSocketAddress());

        System.out.print("Please Enter prime P: ");
        p = sc.nextBigInteger();
        System.out.print("Please Enter prime Q: ");
        q = sc.nextBigInteger();
        n = p.multiply(q);
        p_n = p.subtract(bi);
        q_n = q.subtract(bi);
        fi = p_n.multiply(q_n);
        System.out.print("N is: ");
        System.out.println(n);
        System.out.print("toitent function fi of n is: ");
        System.out.println(fi);

        // e=bii;
        System.out.print("Enter your selected public key: ");
        e = sc.nextBigInteger();

        while (true) {

            BigInteger result = fi.gcd(e);
            i1 = result.intValue();
            if (i1 == 1) {
                System.out.print("Public key calculated is :");
                System.out.print(e);
                System.out.println("   ");
                break;
            } else {
                System.out.println("Not a symetric pair: ");
                // e=e.add(bi);
                break;
            }
        }
        System.out.println("----------------------------------");
        System.out.print("Your public key is: ");
        System.out.println(e);
        System.out.println("----------------------------------");
        /*
         * for(i=BigInteger.valueOf(0);i.compareTo(fi)==-1;i=i.add(bi)) {
         * if(((e.mod(fi)).multiply((i.mod(fi)))).mod(fi).compareTo(bi)==0) break; }
         * d=i;
         */
        e_ = e;
        // Extended euclidean's algorithm.

        d = b;
        t1 = b;
        t2 = bi;
        while (e_.compareTo(b) == 1) {
            qo = fi.divide(e_);
            r = fi.mod(e_);
            fi = e_;
            e_ = r;
            // t=t1-qo*t2;
            t = t1.subtract(qo.multiply(t2));
            t1 = t2;
            t2 = t;
        }
        if (fi.compareTo(bi) == 0) {
            if (t1.compareTo(b) == -1) {
                d = t1.add(t2);
            } else {
                d = t1;
            }
        }

        System.out.print("Calculated private key is: ");
        // d=e.modInverse(fi);
        System.out.print(d);
        System.out.println("    ");        

        System.out.print("Enter the message to encrypt: ");
        mesg = sc.nextBigInteger();

        cipher_text = mesg.modPow(d, n);

        System.out.println("----------------------------------");
        System.out.println("Cipher Text of the entered message: ");
        System.out.println(cipher_text);
        System.out.println("----------------------------------");


        OutputStream outToclient = server.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToclient);
        out.writeUTF(e.toString());
        out.writeUTF(n.toString());
        out.writeUTF(cipher_text.toString());

        }
        catch(SocketTimeoutException s){
            System.out.println("Socket timed out!");
        }
        catch(IOException e){}
    }
}