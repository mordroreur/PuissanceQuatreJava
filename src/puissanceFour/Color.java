package puissanceFour;

class Color {
    private final int MAX = 255; 
    private double red;
    private double green;
    private double blue;

    protected Color(double red, double green, double blue)
    {
        if((red < 0 || red > MAX) || (green < 0 || green > MAX) || (blue < 0 || blue > MAX))
        {
            throw new IllegalArgumentException("A color value is between 0 and " + MAX);
        }
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    protected double getBlue() {
        return blue;
    }

    protected double getGreen() {
        return green;
    }

    protected double getRed() {
        return red;
    }

    protected double[] getRGB() {
        double[] rgb = {red, green, blue};
        return rgb;
    }
}
