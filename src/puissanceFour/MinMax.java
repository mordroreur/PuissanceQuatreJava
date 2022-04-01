package puissanceFour;

public class MinMax {

    private static float sum(float[] t)
    {
        float s = 0;
        for (int i = 0; i < t.length; i++)
        {
            s += t[i];
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
                result[i]+=3;
            }
            else if(Tcopy.addConditionalDiscs(i, play) && Tcopy.addDiscs(i, play))
            {
                if(play == ourTeam)
                {
                    result[i]++;
                }
                else
                {
                    result[i]-=2;
                }
            }
            else{
                if(ourTeam == play){result[i] += (float)sum(simulate(ourTeam, other, other, Tcopy, profondeur-1, orginalProfondeur))/Tcopy.getWidth();}
                else {result[i] += (float)sum(simulate(ourTeam, other, ourTeam, Tcopy, profondeur-1, orginalProfondeur))/Tcopy.getWidth();}
                
            }
        }   


        return result;
    }

    public static int whereToPlay(Team ourTeam, Team other, TerrainPuissance T)
    {
        float[] result = simulate(ourTeam, other, ourTeam, T, 7, 7);

        /*String toPrint = "";
        for (float f : result) {
            toPrint += f + "|";
        }
        System.out.println(toPrint);*/


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
