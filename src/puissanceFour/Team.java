package puissanceFour;

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

    protected Team(int id, ColorFour color, boolean isCpu)
    {
        this.id = id;
        this.color = color;
        this.CPU = isCpu;
    }

    public java.awt.Color getColor() {
        return color.getColor();
    }

    public int getId() {
        return id;
    }

    public boolean isACpu() {return this.CPU;}
}
