
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class EncryptedOutputStream {
    EncryptedOutputStream(OutputStream output, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidKeyException, IllegalBlockSizeException{

        SecretKey key64 = new SecretKeySpec( new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 }, "Blowfish" );
        Cipher cipher = Cipher.getInstance( "Blowfish" );
        
        cipher.init( Cipher.ENCRYPT_MODE, key64 );
        CipherOutputStream cipherOutputStream = new CipherOutputStream( output, cipher );
    }
}
