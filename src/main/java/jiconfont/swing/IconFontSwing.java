package jiconfont.swing;

import jiconfont.IconCode;
import jiconfont.IconFont;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Copyright (c) 2016 jIconFont <BR>
 * <BR>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:<BR>
 * <BR>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.<BR>
 * <BR>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public final class IconFontSwing {

    private static List<IconFont> fonts = new ArrayList<>();

    private IconFontSwing() {
    }

    /**
     * Register an icon font.
     *
     * @param iconFont the icon font.
     */
    public static synchronized void register(IconFont iconFont) {
        if (!IconFontSwing.fonts.contains(iconFont)) {
            IconFontSwing.fonts.add(iconFont);
        }
    }

    /**
     * Builds a font.
     *
     * @param fontFamily the font family.
     * @return the font.
     */
    public static synchronized Font buildFont(String fontFamily) {
        try {
            for (IconFont iconFont : IconFontSwing.fonts) {
                if (iconFont.getFontFamily().equals(fontFamily)) {
                    return Font.createFont(Font.TRUETYPE_FONT, iconFont.getFontInputStream());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(IconFontSwing.class.getName()).log(Level.SEVERE, "Font load failure", ex);
        }

        Logger.getLogger(IconFontSwing.class.getName()).log(Level.SEVERE, "Font not found: {0}", fontFamily);
        throw new IllegalArgumentException("Font not found: " + fontFamily);
    }


    /**
     * Builds an image.
     *
     * @param iconCode the icon code.
     * @param size     the size.
     * @return the image.
     */
    public static Image buildImage(IconCode iconCode, float size) {
        return buildImage(iconCode, size, Color.BLACK);
    }

    /**
     * Builds an image.
     *
     * @param iconCode the icon code.
     * @param size     the size.
     * @param color    the size.
     * @return the image.
     */
    public static Image buildImage(IconCode iconCode, float size, Color color) {
        Font font = buildFont(iconCode, size);
        String text = Character.toString(iconCode.getUnicode());
        return buildImage(text, font, color, 0);
    }

    /**
     * Builds an rotated image.
     *
     * @param iconCode the icon code.
     * @param size     the size.
     * @param color    the size.
     * @param rotation the rotation in number of 90 degree arcs
     * @return the rotated image.
     */
    public static Image buildImage(IconCode iconCode, float size, Color color, int rotation) {
        Font font = buildFont(iconCode, size);
        String text = Character.toString(iconCode.getUnicode());
        return buildImage(text, font, color, rotation);
    }

    /**
     * Builds an icon.
     *
     * @param iconCode the icon code.
     * @param size     the size.
     * @return the icon.
     */
    public static Icon buildIcon(IconCode iconCode, float size) {
        return buildIcon(iconCode, size, Color.BLACK);
    }

    /**
     * Builds an icon.
     *
     * @param iconCode the icon code.
     * @param size     the size.
     * @param color    the size.
     * @return the icon.
     */
    public static Icon buildIcon(IconCode iconCode, float size, Color color) {
        return buildIcon(iconCode, size, color, 0);
    }

    /**
     * Builds an rotated icon.
     *
     * @param iconCode the icon code.
     * @param size     the size.
     * @param color    the size.
     * @param rotation the rotation in number of 90 degree arcs
     * @return the rotated icon.
     */
    public static Icon buildIcon(IconCode iconCode, float size, Color color, int rotation) {
        return new ImageIcon(buildImage(iconCode, size, color, rotation));
    }

    /**
     * Builds an stacked image.
     *
     * @param iconCode1 the icon code for the icon on the bottom.
     * @param iconCode2 the icon code for the icon on the top.
     * @param size      the size.
     * @return the stacked image.
     */
    public static Image buildStackedImage(IconCode iconCode1, IconCode iconCode2, float size) {
        return buildStackedImage(iconCode1, iconCode2, size, Color.BLACK);
    }

    /**
     * Builds an stacked image.
     *
     * @param iconCode1 the icon code for the icon on the bottom.
     * @param iconCode2 the icon code for the icon on the top.
     * @param size      the size.
     * @param color     the size.
     * @return the stacked image.
     */
    public static Image buildStackedImage(IconCode iconCode1, IconCode iconCode2, float size, Color color) {
        return buildStackedImage(iconCode1, iconCode2, size, color, 0);
    }

    /**
     * Builds an rotated stacked image.
     *
     * @param iconCode1 the icon code for the icon on the bottom.
     * @param iconCode2 the icon code for the icon on the top.
     * @param size      the size.
     * @param color     the size.
     * @param rotation  the rotation in number of 90 degree arcs
     * @return the rotated stacked image.
     */
    public static Image buildStackedImage(IconCode iconCode1, IconCode iconCode2, float size, Color color, int rotation) {
        Font font1 = buildFont(iconCode1, size);
        String text1 = Character.toString(iconCode1.getUnicode());
        Image image1 = buildImage(text1, font1, color, 0);

        Font font2 = buildFont(iconCode2, size);
        String text2 = Character.toString(iconCode2.getUnicode());
        Image image2 = buildImage(text2, font2, color, 0);

        return buildStackedImage(image1, image2, rotation);
    }

    /**
     * Builds an stacked icon.
     *
     * @param iconCode1 the icon code for the icon on the bottom.
     * @param iconCode2 the icon code for the icon on the top.
     * @param size      the size.
     * @return the stacked icon.
     */
    public static Icon buildStackedIcon(IconCode iconCode1, IconCode iconCode2, float size) {
        return buildStackedIcon(iconCode1, iconCode2, size, Color.BLACK);
    }

    /**
     * Builds an stacked icon.
     *
     * @param iconCode1 the icon code for the icon on the bottom.
     * @param iconCode2 the icon code for the icon on the top.
     * @param size      the size.
     * @param color     the size.
     * @return the stacked icon.
     */
    public static Icon buildStackedIcon(IconCode iconCode1, IconCode iconCode2, float size, Color color) {
        return new ImageIcon(buildStackedImage(iconCode1, iconCode2, size, color));
    }

    public static Image buildStackedImage(Image image1, Image image2) {
        return buildStackedImage(image1, image2, 0);
    }


    /**
     * Builds an rotated stacked image.
     *
     * @param image1    the image on the bottom.
     * @param image2    the image on the top.
     * @param rotation  the rotation in number of 90 degree arcs
     * @return the rotated stacked image.
     */
    public static Image buildStackedImage(Image image1, Image image2, int rotation) {
        int width = Math.max(image1.getWidth(null), image2.getHeight(null));
        int height = Math.max(image1.getWidth(null), image2.getHeight(null));

        BufferedImage stacked = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = stacked.createGraphics();

        if(rotation != 0) {
            AffineTransform transform = new AffineTransform();
            transform.quadrantRotate(rotation, width / 2D, height / 2D);
            g2d.transform(transform);
        }

        g2d.drawImage(image1, 0, 0, null);
        g2d.drawImage(image2, 0, 0, null);
        g2d.dispose();

        return stacked;
    }

    private static BufferedImage buildImage(String text, Font font, Color color, int rotation) {
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(font);

        Dimension dim = label.getPreferredSize();
        int width = dim.width + 1;
        int height = dim.height + 1;
        label.setSize(width, height);

        BufferedImage bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufImage.createGraphics();

        if(rotation != 0) {
            AffineTransform transform = new AffineTransform();
            transform.quadrantRotate(rotation, width / 2D, height / 2D);
            g2d.transform(transform);
        }

        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        label.print(g2d);
        g2d.dispose();
        return bufImage;
    }

    private static Font buildFont(IconCode iconCode, float size) {
        Font font = IconFontSwing.buildFont(iconCode.getFontFamily());
        return font.deriveFont(size);
    }

}