import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;

public class Main {
    public static void main(String[] args) {
        try {
            KeyGenerator.createKeys("private.key", "public.key");
        } catch (NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
            e.printStackTrace();
        }

        try {
            SignedFileGenerator.createSignedFile(new TestObject(), SignedFileGenerator.readPrivateKey("private.key"), "signedFile.sign");

            TestObject testObject = (TestObject) SignedFileReader.readSignedObjectFromFile("signedFile.sign", SignedFileReader.readPublicKey("public.key"));

            System.out.println(testObject.getX());
            System.out.println(testObject.getY());

        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | InvalidKeyException | SignatureException | NoSuchProviderException e) {
            e.printStackTrace();
        }
    }
}
