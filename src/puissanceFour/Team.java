class Team {
    private Color color;
    private int id;

    private Team(int id, double red, double green, double blue)
    {
        this.color = new Color(red, green, blue);
        this.id = id;
    }

    private Team(int id, Color color)
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
