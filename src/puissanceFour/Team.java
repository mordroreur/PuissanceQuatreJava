package puissanceFour;

public class Team {
    private Color color;
    private int id;

    public Team(int id, double red, double green, double blue)
    {
        this.color = new Color(red, green, blue);
        this.id = id;
    }

    protected Team(int id, Color color)
    {
        this.id = id;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getId() {
        return id;
    }
}
