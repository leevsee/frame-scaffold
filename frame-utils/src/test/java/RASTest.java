import com.leeves.utils.rsa.RSAUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

/**
 * Description: TODO
 * Package: com.bxd.encrypt
 *
 * @author Leeves
 * @version 1.0.0  2018-06-05
 */
//@RunWith(SpringRunner.class)
@SpringBootTest
public class RASTest {

    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqJbdj_VbgLiRIUas_RCSlyyPboPGkNVLUxt55macxk6AEdWxd9l1M_iH9hy9OadgQ0PeLyFgbzYM5VKUGEGnpstOKHerCICw4KpcHaggAVNaeEd5Q9lb80_F68ANwKmJLlO47zgZrLV7w1i2MMlEcuNozxYrUFoWZU_BjKbtpAQIDAQAB";
    private static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKolt2P9VuAuJEhRqz9EJKXLI9ug8aQ1UtTG3nmZpzGToAR1bF32XUz-If2HL05p2BDQ94vIWBvNgzlUpQYQaemy04od6sIgLDgqlwdqCABU1p4R3lD2VvzT8XrwA3AqYkuU7jvOBmstXvDWLYwyURy42jPFitQWhZlT8GMpu2kBAgMBAAECgYAKfG6T-_4JrO5Rgq8CJCn6Cc348C9URnEHagY2ClEyjf2TqMT3-YIAh70Cif1RYNaPGsHW3I8Jj53hv6S3_fT49uhTMPRmcMIRkmg23Y96SivZ-szkU-liz8wXxC4zGPQjVoMcgVn48U9_fZQktau6lvAg00DZq0mviHax3ouuzQJBAP_urEpFiR0tCm_8NB_nDxwWz13m4uSiN-p1RQJ_Zs39UoyCZ1nqkKi_6zyMKMCRZHHQnf4lzS8CQmagMmNXtTMCQQCqMTxPxfdWgIU6tqH-HKRMU0mvqii93q6nQczStROefKjAAq2SL1tK2it_adj3ZNBBy2wc2qs-xIH0NRr00ED7AkEA9GJ_-lvz1fxQXybAz0Xf076kPNJgDloocqWQRBZLoJ8o2CsdbWriv4Cjq6lasKck9tlyRGDsUNsjdMO72cjCqQJATnO3u4YkB6niwDfj4IFPcyeJdgJYGX4GwG7ifdaenpYUK76QSCywRobfOjKOoyvDnDCrO3nJKnvm861vKZ699QJASBeKJYKAVp3uiHpqZYYN9N-Xp0igFDx71-t29Zfw6u3tB9wIPOVhji8YmZmDi8CahSiy3XlcIthy9oJxKhbr-g";

    @Test
    public void getKey() {
        Map<String, String> keyMap = RSAUtils.createKeys(1024);
        String publicKey = keyMap.get("publicKey");
        String privateKey = keyMap.get("privateKey");
        System.out.println("公钥: \n\r" + publicKey);
        System.out.println("私钥： \n\r" + privateKey);
    }

    @Test
    public void publicEncrypt() {
        String md5 = "AAABBBCCCDDDEEEFFFGGG";
        try {
            String publicEncrypt = RSAUtils.publicEncrypt(md5, RSAUtils.getPublicKey(publicKey));
            String s = RSAUtils.privateDecrypt(publicEncrypt, RSAUtils.getPrivateKey(privateKey));
            System.out.println(publicEncrypt);
            System.out.println(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void privateEncrypt() {
        String md5 = "AAABBBCCCDDDEEEFFFGGG";
        try {
            String publicEncrypt = RSAUtils.privateEncrypt(md5, RSAUtils.getPrivateKey(privateKey));
            String s = RSAUtils.publicDecrypt(publicEncrypt, RSAUtils.getPublicKey(publicKey));
            System.out.println(publicEncrypt);
            System.out.println(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            System.out.println();
        }
    }

    @Test
    public void getKeyFile() {
        try {
            String s = txt2String(ResourceUtils.getFile("classpath:key/publicKey"));
            System.out.println(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void serverSendEncrypt() throws InvalidKeySpecException, NoSuchAlgorithmException, FileNotFoundException {
        String str = "2df764e5887418b48748949761acf851";
        String s = RSAUtils.privateEncrypt(str, RSAUtils.getPrivateKey(txt2String(ResourceUtils.getFile("classpath:" + RSAUtils.PRIVATE_KEY_FILE))));
//        String s = RSAUtils.privateEncrypt(str, RSAUtils.getPrivateKey(privateKey));
        System.out.println(s);
    }

    @Test
    public void clientSendDecrypt() throws InvalidKeySpecException, NoSuchAlgorithmException, FileNotFoundException {
        String str = "bAEmeMI6We5h_VFdcvckUaOdd6o-6l0GXfuTzyDO6dQmooYomcMDt3YPGYjw1C5Yh1FMjzAdpJjFYNXqImFeUCRMAAKNCgceqvGnRuU1hHI68XGgt3oauF940RJALfAZXshQFBPaZdn9QDnBcMK-EFFPvLCDhPpRCX80LovVvns";
        String s = RSAUtils.publicDecrypt(str, RSAUtils.getPublicKey(txt2String(ResourceUtils.getFile("classpath:" + RSAUtils.PUBLIC_KEY_FILE))));
//        String s = RSAUtils.publicDecrypt(str, RSAUtils.getPublicKey(publicKey));
        System.out.println(s);
    }


    @Test
    public void clientSendEncrypt() throws InvalidKeySpecException, NoSuchAlgorithmException, FileNotFoundException {
        String str = "2df764e5887418b48748949761acf8512";
        String s = RSAUtils.publicEncrypt(str, RSAUtils.getPublicKey(txt2String(ResourceUtils.getFile("classpath:" + RSAUtils.PUBLIC_KEY_FILE))));
//        String s = RSAUtils.publicEncrypt(str, RSAUtils.getPublicKey(publicKey));
        System.out.println(s);
    }

    @Test
    public void serverRevicedDecrypt() throws InvalidKeySpecException, NoSuchAlgorithmException, FileNotFoundException {
        String str = "BvOXUvkcci684GO6-nl64ACfAlJwIVxy9SsDI_fmhv8_2-aQ5juSpbSfwQ86YIdQShGMCcTMXFVi32aFbJmMQGOGCgd2r41Ysi4hPWgSU6pVT8kQdrXSQtUI_REan92nlXtI_jen_MsPBWXSlcCQypcxcveuecDzTF2bNZUHEWs";
        String s = RSAUtils.privateDecrypt(str, RSAUtils.getPrivateKey(txt2String(ResourceUtils.getFile("classpath:" + RSAUtils.PRIVATE_KEY_FILE))));
//        String s = RSAUtils.privateDecrypt(str, RSAUtils.getPrivateKey(privateKey));
        System.out.println(s);
    }


    private static String txt2String(File file) {
        StringBuilder result = new StringBuilder();
        try (
                BufferedReader br = new BufferedReader(new FileReader(file))
        ) {
            //构造一个BufferedReader类来读取文件
            String s;
            //使用readLine方法，一次读一行
            while ((s = br.readLine()) != null) {
                result.append(System.lineSeparator()).append(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
