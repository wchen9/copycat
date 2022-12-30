package com.example.springbootdemo.controller;

import com.example.springbootdemo.dto.RandomDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginSimulatorController {

    private String PRIVATE_KEY = "1E60B85D78F6126778011C8311D8772C3C07DB20F32A95BF4EBAD6EA2F373CDA";
    private static String PUBLIC_KEY = "F5E85A5F3A8F32905689C57DF448DD0D16C41167C5B301EC511F9E487BF7922AB2E9E4A6CB514D9096A881A80A5E595997F3E7CE1E5B85C17ED41A9751D2B714";

    @RequestMapping("/do")
    public String doLogin() {
        log.info("print hello ");

        RandomDTO serverRandom = getRandom();
        System.out.println("serverRandom" + serverRandom.toString());
        RandomDTO clientRandom = getRandom();
        System.out.println("clientRandom" + clientRandom.toString());
        Base64.Decoder base64Decoder = Base64.getDecoder();

        byte[] cliRandom = base64Decoder.decode(clientRandom.getRandom());
        cliRandom = ArrayUtils.addAll(new byte[]{4}, cliRandom);

        byte[] srvRandom = base64Decoder.decode(serverRandom.getRandom());
        byte[] privateKey = parseHexStr2Byte(PRIVATE_KEY);

        System.out.println("private key = " + Arrays.toString(privateKey));
        System.out.println(hex(privateKey));

        System.out.println(Arrays.toString(privateKey));

        return "hello";
    }

    public static void main(String[] args) {
        String pk = "8A/4iyumwh1Urp7URKQBZtUIMEj2qrlMFJ3mTZ8oyWlxv6cGeo7jNSY3U+efdJgpSz463ZrOvDNzubTU5J81iw==";
        Base64.Decoder base64Decoder = Base64.getDecoder();
        byte[] decodedPK = base64Decoder.decode(pk);
        System.out.println(Arrays.toString(decodedPK));
        System.out.println(hex(decodedPK));

        System.out.println("----");

        String sign = "nqMPr82G9o2nGLmhsS7e2SRzIP4AvUF6qiczF74MAl+cPc2F3jF1g7/WE4VbfNODyas++S0Xhb7QUsba6zqvyQ==";
        byte[] decodedSign = base64Decoder.decode(sign);
        System.out.println("sign" + Arrays.toString(decodedSign));
        System.out.println(hex(decodedSign));

        System.out.println("----");

        byte[] publicKeyByte = parseHexStr2Byte(PUBLIC_KEY);
        String publicKeyEncoded = Base64.getEncoder().encodeToString(publicKeyByte);
        System.out.println(publicKeyEncoded);

    }

    public RandomDTO getRandom() {
        RandomDTO randomDTO = new RandomDTO();
        byte[] randomBytes = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(randomBytes);

        String randomStr = Base64.getEncoder().encodeToString(randomBytes);
        System.out.println(Arrays.toString(randomBytes));
        System.out.println(randomStr);

        randomDTO.setRandom(randomStr);
        randomDTO.setSessionIdentity(UUID.randomUUID().toString());

        return randomDTO;
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return new byte[0];
        } else {
            byte[] result = new byte[hexStr.length() / 2];

            for (int i = 0; i < hexStr.length() / 2; ++i) {
                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte) (high * 16 + low);
            }

            return result;
        }
    }

    public static String hex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            int decimal = (int) aByte & 0xff;               // bytes widen to int, need mask, prevent sign extension
            // get last 8 bits
            String hex = Integer.toHexString(decimal);
            if (hex.length() % 2 == 1) {                    // if half hex, pad with zero, e.g \t
                hex = "0" + hex;
            }
            result.append(hex);
        }
        return result.toString();
    }
}
