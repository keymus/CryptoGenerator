import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.*;

public class KeyGenerator {
    private static final String ALGORITHM = "DSA";
    private static final String SECURE_RANDOM_ALGORITHM = "SHA1PRNG";
    private static final String PROVIDER = "SUN";
    private static PrivateKey privateKey;
    private static PublicKey publicKey;
    private static final int keySize = 1024;

    /**
     * Запись ключа в файл
     * @param filePath путь к ключу
     * @param key объект ключа
     * @throws IOException
     */
    public static void saveKey(final String filePath, final Object key) throws IOException
    {
        if (key != null){
            //запись ключа в файл
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(key);
            oos.close();
        }
    }

    /**
     * Сохранение ключей в файлы
     * @param privateKeyFilepath путь к закрытому ключу
     * @param publicKeyFilepath путь к открытому ключу
     */
    public static void createKeys(String privateKeyFilepath, String publicKeyFilepath) throws NoSuchAlgorithmException, NoSuchProviderException, IOException {
        //инициализация keyPairGenerator
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM, PROVIDER);
        SecureRandom random = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM);
        keyPairGenerator.initialize(keySize, random);

        //Создание пары ключей
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();

        //сохранение ключей в файл
        saveKey(privateKeyFilepath, privateKey);
        saveKey(publicKeyFilepath, publicKey);
    }



    /**
     * Создание пары ключей
     * @return Пару ключей(закрытый и открытый)
     */
    public static KeyPair createKeys() throws NoSuchAlgorithmException, NoSuchProviderException {
        //инициализация генератора ключей
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM, PROVIDER);
        SecureRandom random = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM);
        keyPairGenerator.initialize(1024, random);

        return keyPairGenerator.generateKeyPair();
    }

    public static PrivateKey getPrivateKey() {
        return privateKey;
    }

    public static void setPrivateKey(PrivateKey privateKey) {
        KeyGenerator.privateKey = privateKey;
    }

    public static String getALGORITHM() {
        return ALGORITHM;
    }

    public static String getSecureRandomAlgorithm() {
        return SECURE_RANDOM_ALGORITHM;
    }

    public static String getPROVIDER() {
        return PROVIDER;
    }

    public static PublicKey getPublicKey() {
        return publicKey;
    }

    public static void setPublicKey(PublicKey publicKey) {
        KeyGenerator.publicKey = publicKey;
    }

    public static int getKeySize() {
        return keySize;
    }
}
