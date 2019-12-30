import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.*;

/**
 * Чтение и верификация подписанного объекта из файла
 * Чтение открытого ключа из файла
 */
public class SignedFileReader {

    /**
     * Чтение подписанного объекта из файла
     * @param objectFilepath путь к файлу подписанного объекта
     * @param publicKey открытый ключ
     * @return
     */
    public static Object readSignedObjectFromFile(String objectFilepath, PublicKey publicKey) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        ObjectInputStream oos = new ObjectInputStream(new FileInputStream(objectFilepath));

        Object signedObject = oos.readObject();

        return verifySignedObject((SignedObject) signedObject, publicKey);
    }

    /**
     * Верификация подписанного объекта
     * @param o подписанный объект
     * @param publicKey открытый ключ
     * @return
     */
    public static Object verifySignedObject(SignedObject o, PublicKey publicKey) throws IOException, ClassNotFoundException, SignatureException, InvalidKeyException, NoSuchAlgorithmException {
        Object x = null;
        Signature signature = Signature.getInstance(publicKey.getAlgorithm());
        boolean verified = o.verify(publicKey, signature);

        if (verified){
            x = o.getObject();
        }
        return x;
    }

    /**
     * Чтение открытого ключа из файла
     * @param filePath путь к файлу открытого ключа
     * @return
     */
    public static PublicKey readPublicKey(final String filePath) throws IOException, ClassNotFoundException {
        PublicKey publicKey;

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        Object publicKeyObject = ois.readObject();

        publicKey = (PublicKey) publicKeyObject;

        return publicKey;
    }
}
