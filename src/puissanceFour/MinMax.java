package puissanceFour;

public class MinMax {

    private static float min(float[] t)
    {
        float s = t[0];
        for (int i = 1; i < t.length; i++)
        {
            if (s > t[i]) s = t[i];
        }

        return s;
    }

    private static float max(float[] t)
    {
        float s = t[0];
        for (int i = 1; i < t.length; i++)
        {
            if (s < t[i]) s = t[i];
        }

        return s;
    }

    private static float[] simulate(Team ourTeam, Team other, Team play, TerrainPuissance T, int profondeur, int orginalProfondeur, int turn)
    {
        float[] result = new float[T.getWidth()];
        if(profondeur == 0)
        {
            return result;
        }


        for (int i = 0; i < T.getWidth(); i++)
        {   
            TerrainPuissance Tcopy = T.copy();
            if(!T.addConditionalDiscs(i) && profondeur == orginalProfondeur)
            {
                result[i] -= 10;
                continue;
            }
            if(turn <= 6 && i == (int)((float)T.getWidth()/2)) result[i] += 4/(turn+1);
            if(Tcopy.testWin(i, ourTeam, other, 4)){result[i] -= 2;}

            
            if(Tcopy.addConditionalDiscs(i) && Tcopy.testWin(i, play, 3))
            {
                if(play == ourTeam)
                {
                    result[i]+=0.5;
                    if(Tcopy.testWin(i, ourTeam, other, 4))
                    {
                        result[i]-=2.0;
                    }
                }
                else
                {
                    result[i]-=1.5;
                }
            }else if(play == ourTeam && Tcopy.addConditionalDiscs(i) && Tcopy.testWin(i, other, 3))
            {
                result[i]+=1.5;
            }
                
            if(play == ourTeam && Tcopy.addConditionalDiscs(i) && Tcopy.testWin(i, other)){
                if(profondeur > 5) System.out.println(i);
                result[i] = (int)4.0;
            }
            else if(Tcopy.addConditionalDiscs(i) && Tcopy.addDiscs(i, play))
            {
                if(play == ourTeam)
                {
                    result[i]=(int)5.0;
                }
                else
                {
                    result[i]=(int)-2.0;
                }
            }
            else{
                if(ourTeam == play){result[i] += (float)min(simulate(ourTeam, other, other, Tcopy, profondeur-1, orginalProfondeur, 10));}
                else {result[i] += (float)max(simulate(ourTeam, other, ourTeam, Tcopy, profondeur-1, orginalProfondeur, 10));}
                
            }
        }   


        return result;
    }

    public static void print(float[] result){
        String s = "";
        for (float f : result) {
            s += f + "|";   
        }
        System.out.println(s);
    }

    public static int whereToPlay(Team ourTeam, Team other, TerrainPuissance T, int turn)
    {
        float[] result = simulate(ourTeam, other, ourTeam, T, 7, 7, turn);

        print(result);

        float max = result[0];
        int maxI = 0;
        for (int i = 1; i < result.length ; i++)
        {
            if(result[i] > max)
            {
                max = result[i];
                maxI = i;
            }
        }

        return maxI;
    }

}
