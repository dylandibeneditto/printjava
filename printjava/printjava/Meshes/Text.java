package printjava.Meshes;

import printjava.Meshes.Field;
import printjava.Point;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;

public class Text extends Field {
    private Font font;
    
    private static Path2D textOutline;
    private static Rectangle2D textBounds;
    private static BufferedImage distanceField;
    private static double maxDistanceForInterpolation = 10; // Controls smoothness
    private static double resolution = 200;
    private static int imageWidth;
    private static int imageHeight;
    private static double imageScale;
    private static double minX, minY, maxX, maxY;
    
    public Text(String text, double fontSize, double extrusionHeight) {
        this(text, fontSize, extrusionHeight, 200);
    }

    public Text(String text, double fontSize, double extrusionHeight, int resolution) {
        this(text, fontSize, extrusionHeight, resolution, new Font("Monospace", Font.BOLD, (int)resolution));
    }

    public Text(String text, double fontSize, double extrusionHeight, String fontPath) throws IOException {
        this(text, fontSize, extrusionHeight, 200, loadCustomFont(fontPath, 200));
    }

    public Text(String text, double fontSize, double extrusionHeight, int resolution, String fontPath) throws IOException {
        this(text, fontSize, extrusionHeight, resolution, loadCustomFont(fontPath, resolution));
    }

    private Text(String text, double fontSize, double extrusionHeight, int resolution, Font font) {
        super((p) -> isInsideText(p, text, fontSize, 1.0), 50, 2, 1.0+1, 10000, 250, 5);
        
        this.font = font;
        FontRenderContext frc = new FontRenderContext(null, true, true);

        double w = this.font.getStringBounds(text, frc).getWidth() * fontSize / resolution;
        super.width = w;
        super.xDivisions = (int)(w * resolution);
        double h = this.font.getStringBounds(text, frc).getHeight() * fontSize / resolution;
        super.yDivisions = (int)(h * resolution);
        super.height = h;
        
        initializeTextOutline(text, fontSize, font, frc);
        generateDistanceField();

        this.scale.z = extrusionHeight*2;
    }

    private static Font loadCustomFont(String fontPath, int size) throws IOException {
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            return customFont.deriveFont(Font.BOLD, size);
        } catch (Exception e) {
            throw new IOException("Failed to load font file: " + fontPath, e);
        }
    }
    
    private void initializeTextOutline(String text, double fontSize, Font font, FontRenderContext frc) {
        textOutline = new Path2D.Double();
        double xOffset = 0.0;
        double characterSpacing = 0.1;
        double scale = fontSize / resolution;
        
        for (char c : text.toCharArray()) {
            String charAsString = String.valueOf(c);
            GlyphVector glyphVector = font.createGlyphVector(frc, charAsString);
            Path2D charOutline = (Path2D) glyphVector.getOutline();
            
            AffineTransform translation = AffineTransform.getTranslateInstance(xOffset, 0);
            charOutline.transform(translation);
            textOutline.append(charOutline, false);
            
            xOffset += glyphVector.getGlyphMetrics(0).getAdvance() + characterSpacing;
        }
        
        textOutline.transform(AffineTransform.getScaleInstance(scale, scale));
        
        textOutline.transform(AffineTransform.getScaleInstance(1, -1));
        
        textBounds = textOutline.getBounds2D();
        minX = textBounds.getMinX() - maxDistanceForInterpolation * fontSize;
        minY = textBounds.getMinY() - maxDistanceForInterpolation * fontSize;
        maxX = textBounds.getMaxX() + maxDistanceForInterpolation * fontSize;
        maxY = textBounds.getMaxY() + maxDistanceForInterpolation * fontSize;

        double centerX = (textBounds.getMinX() + textBounds.getMaxX()) / 2;
        double centerY = (textBounds.getMinY() + textBounds.getMaxY()) / 2;
        textOutline.transform(AffineTransform.getTranslateInstance(-centerX, -centerY));

        textBounds = textOutline.getBounds2D();
        minX = textBounds.getMinX() - maxDistanceForInterpolation * fontSize;
        minY = textBounds.getMinY() - maxDistanceForInterpolation * fontSize;
        maxX = textBounds.getMaxX() + maxDistanceForInterpolation * fontSize;
        maxY = textBounds.getMaxY() + maxDistanceForInterpolation * fontSize;
    }
    
    private void generateDistanceField() {
        imageScale = resolution;
        imageWidth = (int)Math.ceil((maxX - minX) * imageScale);
        imageHeight = (int)Math.ceil((maxY - minY) * imageScale);
        
        distanceField = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = distanceField.createGraphics();
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, imageWidth, imageHeight);
        
        g2d.setColor(Color.BLACK);
        
        AffineTransform textToImage = new AffineTransform();
        textToImage.scale(imageScale, imageScale);
        textToImage.translate(-minX, -minY);
        
        g2d.fill(textToImage.createTransformedShape(textOutline));
        g2d.dispose();
    }
    
    private static double isInsideText(Point p, String text, double fontSize, double extrusionHeight) {
        if(Math.abs(p.z) > extrusionHeight/2) {
            return 1.0;
        }

        if (distanceField == null || textOutline == null) {
            return 1.0;
        }

        if (p.x < minX || p.x > maxX || p.y < minY || p.y > maxY) {
            return 1.0;
        }

        int ix = (int)((p.x - minX) * imageScale);
        int iy = (int)((p.y - minY) * imageScale);

        if (ix < 0 || ix >= imageWidth || iy < 0 || iy >= imageHeight) {
            return 1.0;
        }

        int rgb = distanceField.getRGB(ix, iy);
        int gray = (rgb & 0xFF);

        return gray < 128 ? 0.0 : 1.0;
    }

    // alignment methods

    public void alignLeft() {
        this.anchor.x = -this.width / 2;
    }

    public void alignRight() {
        this.anchor.x = this.width / 2;
    }

    public void alignCenter() {
        this.anchor.x = 0;
    }

    public void alignTop() {
        this.anchor.y = this.height / 2;
    }

    public void alignBottom() {
        this.anchor.y = -this.height / 2;
    }

    public void alignMiddle() {
        this.anchor.y = 0;
    }
}