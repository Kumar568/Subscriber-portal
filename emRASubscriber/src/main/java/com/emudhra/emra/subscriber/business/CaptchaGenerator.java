package com.emudhra.emra.subscriber.business;


import java.util.Random;

import org.bouncycastle.util.encoders.Base64;

import org.springframework.stereotype.Component;

import com.emudhra.emra.subscriber.dto.Captcha;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

@Component
public class CaptchaGenerator {

    public Captcha generateCaptcha() {
        String backgroundImage = "iVBORw0KGgoAAAANSUhEUgAAAHgAAAAoAQMAAAAPJKxmAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAGUExURX9/f////xdz5x0AAABbSURBVCjPY/iPChgo5f9gYATR+9HkLzAw2P//h+C/BxH/0PQTxf/DwCBPinoo+MDATqT6ekJ8uAOIVE8c/wADPy75A0C3I8KzHi38acE/AIzFfyB/MlA7faDxAQf2Nq6MfhfrAAAAAElFTkSuQmCC";
        String captcha = generateRandomCaptcha();

        try {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(Base64.decode(backgroundImage)));
            int width = img.getWidth();
            int height = img.getHeight();

            BufferedImage bufferedImage = new BufferedImage(width + 10, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();

            g2d.drawImage(img, 0, 0, null);
            g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
            g2d.setColor(Color.DARK_GRAY);
            g2d.drawString(captcha, 5, height - 10);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);

           String base64Image =java.util.Base64.getEncoder().encodeToString(outputStream.toByteArray());
            
            return new Captcha(captcha, base64Image);
        } catch (Exception e) {
            return null;
        }
    }

    private String generateRandomCaptcha() {
        int length = 6;
        String allowedCharacters = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
        Random rand = new Random();
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int position = rand.nextInt(allowedCharacters.length());
            captcha.append(allowedCharacters.charAt(position));
        }
        return captcha.toString();
    }
}
