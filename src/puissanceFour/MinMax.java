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
            if(!T.addConditionalDiscs(i, play) && profondeur == orginalProfondeur)
            {
                result[i] = -10;
                continue;
            }  
            
                
            if(Tcopy.addConditionalDiscs(i, play) && Tcopy.addDiscs(i, play))
            {
                if(play == ourTeam)
                {
                    result[i]=(float)5.0;
                    continue;
                }
                else
                {
                    result[i]=(float)-5.0;
                    continue;
                }
            }else if(Tcopy.nbrAlign(i) >= 3)
            {
                if(play == ourTeam)
                {
                    result[i] = (float)1.5;
                }
                else
                {
                    result[i] = (float)-2.5;
                }
            }else if(Tcopy.nbrAlign(i) >= 2)
            {
                if(play == ourTeam)
                {
                    result[i] = (float)1.0;
                }
                else
                {
                    result[i] = (float)-1.0;
                }
            }else if(profondeur == orginalProfondeur && i == (int)((float)T.getWidth()/2)){
                result[i] = (float)2.0;
            } 
            
            float r;
            if(ourTeam == play)
            {
                r = (float)min(simulate(ourTeam, other, other, Tcopy, profondeur-1, orginalProfondeur, 10));
                
            }
            else {
                r = (float)max(simulate(ourTeam, other, ourTeam, Tcopy, profondeur-1, orginalProfondeur, 10));
            }

            if(Math.abs(r) > Math.abs(result[i]))
            {
                result[i] = r;
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
        int profondeur = 7;
        float[] result = simulate(ourTeam, other, ourTeam, T, profondeur, profondeur, turn);

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
