package puissanceFour;

public class Team {
    private ColorFour color;
    private int id;

    public Team(int id, float red, float green, float blue)
    {
        this.color = new ColorFour(red, green, blue);
        this.id = id;
    }

    protected Team(int id, ColorFour color)
    {
        this.id = id;
        this.color = color;
    }

    public java.awt.Color getColor() {
        return color.getColor();
    }

    public int getId() {
        return id;
    }
}
