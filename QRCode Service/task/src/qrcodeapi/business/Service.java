package qrcodeapi.business;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.http.MediaType;

import java.awt.image.BufferedImage;
import java.util.Map;

@org.springframework.stereotype.Service
public class Service {


    public BufferedImage createImage(int size, String data, String correction) {

        QRCodeWriter writer = new QRCodeWriter();
        Map<EncodeHintType, ?> hints = Map.of(EncodeHintType.ERROR_CORRECTION, getErrorCorrectionLevel(correction));
        BufferedImage bufferedImage = null;
        try {
            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, size, size, hints);
            bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        } catch (WriterException e) {
            // handle the WriterException

        }
        return bufferedImage;
    }

    public String checkParams(int size, String type, String contents, String correction) {
        System.out.println("Size: " + size);
        System.out.println("Type: " + type);
        System.out.println("Contents: " + contents);
        System.out.println("Correction: " + correction);
        String sizeErrorMsg = "Image size must be between 150 and 350 pixels";
        String typeErrorMsg = "Only png, jpeg and gif image types are supported";
        String ContentsErrorMsg = "Contents cannot be null or blank";
        String CorrectionErrorMsg = "Permitted error correction levels are L, M, Q, H";
        boolean hasSizeError = false;
        boolean hasTypeError = true;
        boolean hasContentsError = false;
        boolean hasCorrectionError = true;

        if (size < 150 || size > 350) {
            hasSizeError = true;
        }
        if (type.equalsIgnoreCase("png") ||
                type.equalsIgnoreCase("jpeg") ||
                type.equalsIgnoreCase("gif")) {
            hasTypeError = false;

        }
        if (contents.isEmpty() || contents.isBlank()) {
            hasContentsError = true;
        }
        if ("L".equalsIgnoreCase(correction) ||
                "M".equalsIgnoreCase(correction) ||
                "Q".equalsIgnoreCase(correction) ||
                "H".equalsIgnoreCase(correction)) {
            hasCorrectionError = false;
        }

        return hasContentsError ? ContentsErrorMsg :
                hasSizeError ? sizeErrorMsg :
                        hasCorrectionError ? CorrectionErrorMsg :
                                hasTypeError ? typeErrorMsg :
                                        "no errors";
    }

    public MediaType getMediaType(String type) {
        MediaType mediaType = switch (type) {
            case "png", "PNG" -> MediaType.IMAGE_PNG;
            case "jpeg", "JPEG" -> MediaType.IMAGE_JPEG;
            case "gif", "GIF" -> MediaType.IMAGE_GIF;
            default -> MediaType.IMAGE_JPEG;
        };
        return mediaType;
    }

    public ErrorCorrectionLevel getErrorCorrectionLevel(String correction) {
        ErrorCorrectionLevel errorCorrectionLevel = switch (correction) {
            case "L", "l" -> ErrorCorrectionLevel.L;
            case "M", "m" -> ErrorCorrectionLevel.M;
            case "Q", "q" -> ErrorCorrectionLevel.Q;
            case "H", "h" -> ErrorCorrectionLevel.H;
            default -> ErrorCorrectionLevel.H;
        };
        return errorCorrectionLevel;
    }
}
