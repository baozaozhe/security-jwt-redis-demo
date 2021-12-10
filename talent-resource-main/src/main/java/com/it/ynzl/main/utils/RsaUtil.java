package com.it.ynzl.main.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class RsaUtil {

    private static org.bouncycastle.jce.provider.BouncyCastleProvider provider = new org.bouncycastle.jce.provider.BouncyCastleProvider();

    // 公钥
    private static String publickey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIrLqMF1hdwY+nOLMrH2l2KM8r0LWRzu30yZHGkrzadq0fy3Rai6FZycgwJE8ra8IF1DqydembyFIVTas2ajeNMCAwEAAQ==";

    //2048
   /* private static String publickey1 ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo5tLK7WqOrZ0dHVZoE01\n" +
            "yOFJOmkoW9yPuUpeyaRQeQKpl/0qYrdOZ0Db43apkVWZIi6bzxgq2gx998hi/6ux\n" +
            "iuMr2F59vUA+ZYKsvFT4kF7VHs2sMq89djXqk757dA7sC61sLiFDX74DKqxonREt\n" +
            "DVaf+An88s4svuVZ4tvLjBRoXK4TWIY3IdthCN/Qd/eW/U4oRyS8qmPfCV2u1h/e\n" +
            "Kq8LqyTlxDDsxa6aCGsTA6jyLAGcVpeSXy2SkDF6U7qpVvQcFL2cxQPnJqBz6d/d\n" +
            "Tj9OnR0HsH2+Kzstgp3D4xIB7nrD9e8Hwzyto3J6pr2MM/L6Mly1kqRtqoOLu+5c\n" +
            "CQIDAQAB\n";*/
    //4096
    private static String publickey1 = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAy9jxYnAPr0K8wNQk1Kak\n" + "bBzJjcj7oRM4V/tMljeHRNWfbPGvnjlEBhYv7K4V4t+oDYIpQTlxE+cl2bcMbVnc\n" + "+42jktNBfKiGw4euFn2ISTUBbP1025Sl2dULHsrYj1ZGA5rWklyTnoqVBvWZlq11\n" + "YGATQl4liAuhNUeAY+M9xJoHDXyF1EOb0hjui8//nGlO9sp8MlN4Dul3Qksfi/rh\n" + "1bdnWv3NOU/EsiAbr+BmWPpAKJfgAHmpZvwxqQQphQl9R73enq04ScCWYiliw9n7\n" + "mVq3By5R/75B4kyJnwmfknmwqbNKQ0EH0w66NhTgzEEgcbZ16Nx/EkVy94ijnjGn\n" + "wQ19r5pTOLLMaOr3zzW7txuHNxOWL2yt/+8peq4+vuzEFVBrolv9KBnONVb8dMXq\n" + "mAHO/vgWeloPu85qnjOU7YDKh3OVBEpoEgrnvwOqyPnisnY3gzzwbif05Uk/MOP2\n" + "FqmhrkIBgCFyULCjMfYziQQTxEgYhcP6fZC1wsBZVN0e83yc+VsqoYqlJze4rWuR\n" + "3IP0IRApSqOp2szXJLxQD9UfPxHCxOAzlEc2UGDt7Fp4NX9MQ9oc3C5BYCDRVFyu\n" + "wyvloPde7gVSRa0muWeDVS2K5qFYlHWrEe5qlL+PNrO7VLV5o4gUuzoT6k8L6FaH\n" + "HK5AWlwPBebWgKIm8WkkEt8CAwEAAQ==";
    // 私钥
    private static String privatekey = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAisuowXWF3Bj6c4sysfaXYozyvQtZHO7fTJkcaSvNp2rR/LdFqLoVnJyDAkTytrwgXUOrJ16ZvIUhVNqzZqN40wIDAQABAkAJbJ6+1B4Mvd1zrwX4M4g/9tYcpAKfBlE/QD4/nevO7EWxi97EOzSM7SEDgZAlUW+ttGM3kEeL3GzaYJmBIXyBAiEA6twdqN0fX8nZwytreKlFDm8EyaflCa6XO8IlbEXYoiECIQCXSe4pKHTP0YN40CSg5BGl8Tu4p+N3Mbag9BO+6sYkcwIhAK1VJs6p463eW0kfkoHK65xrcy0hLHio0hTYhKYUJn4BAiBwW+7uUGaY5bCv7F4Xw+q7hLLnDIFqR9isJzn3A+ROswIhAKsTgTACcjXK1jkQk8wI7G52TN87Ay4gfFLBr+RRYWVj";

   /* private static String privatekey1 ="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCjm0srtao6tnR0\n" +
            "dVmgTTXI4Uk6aShb3I+5Sl7JpFB5AqmX/Spit05nQNvjdqmRVZkiLpvPGCraDH33\n" +
            "yGL/q7GK4yvYXn29QD5lgqy8VPiQXtUezawyrz12NeqTvnt0DuwLrWwuIUNfvgMq\n" +
            "rGidES0NVp/4Cfzyziy+5Vni28uMFGhcrhNYhjch22EI39B395b9TihHJLyqY98J\n" +
            "Xa7WH94qrwurJOXEMOzFrpoIaxMDqPIsAZxWl5JfLZKQMXpTuqlW9BwUvZzFA+cm\n" +
            "oHPp391OP06dHQewfb4rOy2CncPjEgHuesP17wfDPK2jcnqmvYwz8voyXLWSpG2q\n" +
            "g4u77lwJAgMBAAECggEBAJ+IUT948Fo6Ccp5l4CxaS3NSBuV6M3Sf68dLtqOyA2H\n" +
            "pBnZupfxyJj0bLYbwnM+NYlYS4QgO8DNh5OzKZ3HLOOSavGcCh4dY/qV9pvae1pD\n" +
            "rqtPwIbXzCXVozyX9EeSOWeVnrRIqSrV3L6iyYvG8NhdzLUQAEA+/PDUMdb9njij\n" +
            "KcwoZ2hGED3w8s2QwuKcqOTrmLjoJ1hgr5LEBB1kBRkqZxr1GQbbMzy5K8Kntole\n" +
            "p9PmgNrz9XXIOoN6RD0tNFxYyE9B2zQC81zmAFJx2Y6o45Du9+WB0ux7AQ0oy7X0\n" +
            "hy/TT6tcVreMIvQIMMX1KAEyrj0zW2XRcKOn5wzjn8UCgYEA2ekQMj/wKVXaEluJ\n" +
            "kpqWNO+0lQzJ/HTk91jGqHsa4z/GwaLzF2GCs1BmXFNhLxHUzxLsoJiB/CmT+cE6\n" +
            "c3UMZ+pUf6lCcB/D6eyQCafRTLVodEN42KwaXqK6rZhBKPFiJTsGKiwAMRftoR+2\n" +
            "DcUwkq6Mn3doIw758L9oyJ+RgNsCgYEAwDRGHV/zQAsgRCzqfvS1dz8LF9GZeI1v\n" +
            "I0jSUTuqFEpgBZbHAwD2bWAhEKED2DwkZ0jzooUvQluiOXrpvodYY9REzTVGqaCn\n" +
            "jrICOqhYEu2u9PE9f/q7IC6UrOz2U3FlRE4s1hNaminv9S3lYwu92O5pt7iGfpmd\n" +
            "faezy2NOKesCgYA3BMxtE42ssht8VRZYKKXSOc+Lbo41KsKA3S5TbbmtNPYW//CG\n" +
            "r70TTzJ5DyhTfRoRKoMKTVdMdqcBAVOjwSLfvd+V2AOJUcvibTM07OVAi/ESodKK\n" +
            "SnHiAQ5HNFEHaGLSaaO11CQ8QQFjlCEgOvB3qJCi1sA5UdezfHCVbwyKzwKBgQC+\n" +
            "dk6Qp1dWPHBB/rx1CCuWujtU5BgP9R+PuIbphpVYvAcBItOdL2QDKMI7R0OXEKoG\n" +
            "S61afF5DhKhyC1H7wwUdD+kJ7ORUDWZdKhq5Y767Kxs3kKMf4idykZY08jB6qaDv\n" +
            "GgSmMtGSeUJXFJ7+ElGT2VWbgFiOtczyCrBJOOPo9wKBgQCZaQkxvr8UT/4P7i0h\n" +
            "AD2sM5flXAyzHEQC8z+5woe6+Zfbm2gfwN68/xcswRnx1ryyaiCJMNFgzgpsSPVf\n" +
            "9OcsrYuuJYg+Mm6YAzpVDsVLz3EoiVApx6GrLKk//yq47sMUodscQ79wLa78tC1G\n" +
            "zhsKGi2wxukAeT/CYvlE75a8Og==\n";
*/

    private static String privatekey1 = "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\n" + "tqSEbzmPKfGtGGjjgaE1uta+sySZAeMrig==";

    // base64字符串转byte[]
    public static byte[] base64String2ByteFun(String base64Str) {
        return Base64.decodeBase64(base64Str);
    }

    // byte[]转base64
    public static String byte2Base64StringFun(byte[] b) {
        return Base64.encodeBase64String(b);
    }

    /**
     * 加密，针对数据库
     */
    public static String x509Encodedkeys(String str) {
        try {

            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(base64String2ByteFun(publickey));
            KeyFactory         keyFactory         = KeyFactory.getInstance("RSA");
            PublicKey          publicKey          = keyFactory.generatePublic(x509EncodedKeySpec);
            //				Cipher cipher = Cipher.getInstance("RSA/NONE/OAEPWithSHA1AndMGF1Padding");
            Cipher cipher = Cipher.getInstance("RSA", provider);
            //				Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] result = cipher.doFinal(str.getBytes());
            //				1.out.println("RSA公钥加密，私钥解密--加密:"
            //						+ Base64.encodeBase64String(result));
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            //				1.out.println("加密失败");
            return null;
        }
    }

    /**
     * 加密-针对前端参数
     *
     * @param str
     * @return
     */
    public static String frontEndX509Encodedkeys(String str) {
        try {

            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(base64String2ByteFun(publickey1));
            KeyFactory         keyFactory         = KeyFactory.getInstance("RSA");
            PublicKey          publicKey          = keyFactory.generatePublic(x509EncodedKeySpec);
            // Cipher cipher = Cipher.getInstance("RSA/NONE/OAEPWithSHA1AndMGF1Padding");
            // Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] result = cipher.doFinal(str.getBytes());
            //				1.out.println("RSA公钥加密，私钥解密--加密:"
            //						+ Base64.encodeBase64String(result));
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            //				1.out.println("加密失败");
            return null;
        }
    }


    /**
     * 解密-针对前端参数
     */
    public static String pkcs8Encodedkeys(String str) {
        try {
            byte[]              result              = base64String2ByteFun(str);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(base64String2ByteFun(privatekey1));
            KeyFactory          keyFactory          = KeyFactory.getInstance("RSA");
            PrivateKey          privateKey          = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher              cipher              = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result = cipher.doFinal(result);
            //          RSA公钥加密，私钥解密--解密:
            return new String(result, "utf-8");

        } catch (Exception e) {
            //		    "解密失败
            return null;
        }

    }

    /**
     * 解密-针对数据库
     */
    public static String pkcs8EncodedkeysDb(String str) {
        try {
            byte[]              result              = base64String2ByteFun(str);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(base64String2ByteFun(privatekey));
            KeyFactory          keyFactory          = KeyFactory.getInstance("RSA");
            PrivateKey          privateKey          = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            // Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
            //Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            Cipher cipher = Cipher.getInstance("RSA", provider);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result = cipher.doFinal(result);
            //          RSA公钥加密，私钥解密--解密:
            return new String(result, "utf-8");
        } catch (Exception e) {
            //		    "解密失败
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(LocalDateTime.now()
                                        .plusDays(-1)
                                        .toInstant(ZoneOffset.of("+8"))
                                        .toEpochMilli());
        System.out.println("密码："+pkcs8EncodedkeysDb("GRHp9AKGp8fQ55i+QYY1XSTF3YE+DPREikXaGO8zT1loB7qqlrAjR4gOPbaMN2wDE1YXN/1x3BiT7xBVgnAhSw=="));
    }

}
