package puissanceFour;
import java.awt.Color;

public class Team {
    private ColorFour color;
    private int id;
    private boolean CPU;


    public Team(int id, float red, float green, float blue, boolean isCpu) 
    {
        this.color = new ColorFour(red, green, blue);
        this.id = id;
        this.CPU = isCpu;
    }

    public Team(int id, Color color, boolean isCpu)
    {
        this.id = id;
        this.color = new ColorFour(color.getRed(), color.getGreen(), color.getBlue());
        this.CPU = isCpu;
    }

    public Color getColor() {
        return color.getColor();
    }

    public int getId() {
        return id;
    }

    public boolean isACpu() {return this.CPU;}
}
