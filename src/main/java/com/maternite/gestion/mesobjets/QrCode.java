package com.maternite.gestion.mesobjets;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.sf.jasperreports.util.Base64Util;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class QrCode {

    // Attribute s:
    String data = "test";
    String charset = "UTF-8";
    Map<EncodeHintType, ErrorCorrectionLevel> hashMap =
            new HashMap<EncodeHintType, ErrorCorrectionLevel>();

    public QrCode() {
    }

    public byte[] getQR(String texte, int width, int height) {
        try {
            String charset = "UTF-8";
            Map hintMap = new HashMap();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            BitMatrix matrix = new MultiFormatWriter().encode(
                    texte, BarcodeFormat.QR_CODE, width, height, hintMap);
            MatrixToImageWriter.writeToStream(matrix, "jpg", stream);
            stream.flush();

            byte[] data = stream.toByteArray();
            stream.close();

            return data;
        }
        catch (Exception exc){
        }
        return  null;
    }

    // Methods :
    public BufferedImage createQR(
            String data,
            int height, int width)
    {
        try{
            BitMatrix matrix
                    = new MultiFormatWriter().encode(data,
                    BarcodeFormat.QR_CODE, width, height);

            return MatrixToImageWriter.toBufferedImage(matrix);
        }
        catch (Exception exc){

        }
        return null;
    }

    // Convert into IMAGE :
    public String convertImageToText(BufferedImage image, String type){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String retour ="";
        try{
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            retour = encoder.encode(imageBytes);
            bos.close();
            //System.out.println("QR Code : "+retour);
        }
        catch (Exception exc){
        }
        return retour;
    }


    // Convert into IMAGE :
    public String convertImageToTextByte(byte[] imageByte){
        String retour ="";
        try{
            retour = Base64.getEncoder().encodeToString(imageByte);
        }
        catch (Exception exc){
        }
        return retour;
    }
}
