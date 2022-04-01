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

    private static float[] simulate(Team ourTeam, Team other, Team play, TerrainPuissance T, int profondeur, int orginalProfondeur)
    {
        TerrainPuissance Tcopy = T.copy();
        float[] result = new float[T.getWidth()];
        if(profondeur == 0)
        {
            return result;
        }


        for (int i = 0; i < T.getWidth(); i++)
        {   
            if(!T.addConditionalDiscs(i, play) && profondeur == orginalProfondeur)
            {
                result[i] -= 10;
                continue;
            }


            if(play == ourTeam && Tcopy.addConditionalDiscs(i, play) && Tcopy.testWin(i, other)){
                result[i]+=1.0*((float)profondeur/orginalProfondeur);
            }
            else if(Tcopy.addConditionalDiscs(i, play) && Tcopy.addDiscs(i, play))
            {
                if(play == ourTeam)
                {
                    result[i]+=1.0*((float)profondeur/orginalProfondeur);
                }
                else
                {
                    result[i]-=2.0*((float)profondeur/orginalProfondeur);
                }
            }
            else{
                if(ourTeam == play){result[i] += (float)min(simulate(ourTeam, other, other, Tcopy, profondeur-1, orginalProfondeur));}
                else {result[i] += (float)max(simulate(ourTeam, other, ourTeam, Tcopy, profondeur-1, orginalProfondeur));}
                
            }
        }   


        return result;
    }

    public static int whereToPlay(Team ourTeam, Team other, TerrainPuissance T)
    {
        float[] result = simulate(ourTeam, other, ourTeam, T, 7, 7);

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
