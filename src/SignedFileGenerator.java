import java.io.*;
import java.security.*;

/**
 * Подписание и сохранение объекта в файл
 * чтение закрытого ключа из файла
 */
public class SignedFileGenerator {
    private static final String SIGNATURE_ALGORITHM = "SHA1withDSA";
    private static final String PROVIDER = "SUN";

    /**
     * Чтение закрытого ключа из файла
     * @param filePath путь к файлу для чтения
     * @return Объект закрытого ключа
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static PrivateKey readPrivateKey(String filePath) throws IOException, ClassNotFoundException
    {
        //Чтение ключа из файла
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        Object object = ois.readObject();

        return (PrivateKey) object;
    }

    /**
     * Подпись и сохранение объекта
     * @param o объект который требуется подписать
     * @param privateKey закрытый ключ, на основании которого происходит подпись
     * @param signedFilepath путь для записи подписанного объекта
     */
    public static void createSignedFile(Object o, PrivateKey privateKey, String signedFilepath) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, IOException, SignatureException {
        //Cоздание подписи
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM, PROVIDER);

        //создание подписанного оюъекта на основе подписи и закрытого ключа
        SignedObject signedObject = new SignedObject((Serializable) o, privateKey, signature);

        //запись подписанного объекта в файл
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(signedFilepath));
        outputStream.writeObject(signedObject);
    }
}
