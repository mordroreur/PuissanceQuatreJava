package puissanceFour;

import java.awt.Color;

class ColorFour {
    private final int MAX = 255; 
    private float red;
    private float green;
    private float blue;

    protected ColorFour(float red, float green, float blue)
    {
        if((red < 0 || red > MAX) || (green < 0 || green > MAX) || (blue < 0 || blue > MAX))
        {
            throw new IllegalArgumentException("A color value is between 0 and " + MAX);
        }
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    protected float getBlue() {
        return blue;
    }

    protected float getGreen() {
        return green;
    }

    protected float getRed() {
        return red;
    }

    protected float[] getRGB() {
        float[] rgb = {red, green, blue};
        return rgb;
    }

    public Color getColor(){
        return new Color((int)(red), (int)(green), (int)(blue));
    }

}
