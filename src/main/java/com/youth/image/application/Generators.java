package com.youth.image.application;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public class Generators {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    private static class Holder {
        static final SecureRandom numberGenerator = new SecureRandom();
    }

    public static String stringUUID(){
        SecureRandom ng = Holder.numberGenerator;

        //randomly generated bytes
        byte[] randomBytes = new byte[16];
        ng.nextBytes(randomBytes);
        randomBytes[6]  &= 0x0f;  /* clear version        */
//        randomBytes[6]  |= 0x40;  /* set to version 4     */
        randomBytes[8]  &= 0x3f;  /* clear variant        */
        randomBytes[8]  |= 0x80;  /* set to IETF variant  */


        long msb = 0;
        long lsb = 0;
        for (int i=0; i<8; i++)
            msb = (msb << 8) | (randomBytes[i] & 0xff);
        for (int i=8; i<16; i++)
            lsb = (lsb << 8) | (randomBytes[i] & 0xff);


        //1 2 3 4 5 -> 3 2 1 4 5
        return digits(msb, 4) +
                digits(msb >> 16, 4) +
                (digits(msb >> 32, 8) +
                digits(lsb >> 48, 4) +
                digits(lsb, 12));
    }


    private static String digits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return Long.toHexString(hi | (val & (hi - 1))).substring(1);
    }

    //1 2 3 4 5
    //3 2 1 4 5
    public static String prefixUUIDString(){
        UUID uuid = com.fasterxml.uuid.Generators.timeBasedGenerator().generate();
        String[] uuidArr = uuid.toString().split("-");
        return uuidArr[2]+uuidArr[1]+uuidArr[0]+uuidArr[3]+uuidArr[4];
    }


    public static byte[] toBytes(){
        UUID uuidV1 = com.fasterxml.uuid.Generators.timeBasedGenerator().generate();
        String[] uuidV1Parts = uuidV1.toString().split("-");
        String sequentialUUID = uuidV1Parts[2]+uuidV1Parts[1]+uuidV1Parts[0]+uuidV1Parts[3]+uuidV1Parts[4];

        String sequentialUuidV1 = String.join("", sequentialUUID);
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(Long.parseUnsignedLong(sequentialUuidV1.substring(0, 16), 16));
        bb.putLong(Long.parseUnsignedLong(sequentialUuidV1.substring(16), 16));
        return bb.array();
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_ARRAY[v >>> 4];
            hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars).toLowerCase();
    }

}
