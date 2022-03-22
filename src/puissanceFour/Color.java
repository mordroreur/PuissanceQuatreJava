
public class Color {
    private final int MAX = 255; 
    private double red;
    private double green;
    private double blue;

    public Color(double red, double green, double blue)
    {
        if((red < 0 || red > MAX) || (green < 0 || green > MAX) || (blue < 0 || blue > MAX))
        {
            throw new IllegalArgumentException("A color value is between 0 and " + MAX);
        }
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public double getBlue() {
        return blue;
    }

    public double getGreen() {
        return green;
    }

    public double getRed() {
        return red;
    }

    public double[] getRGB() {
        double[] rgb = {red, green, blue};
        return rgb;
    }
}
