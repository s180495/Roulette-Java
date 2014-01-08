import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Roulette
{
    //public static final int MAX_WHEEL_SPINS = 10000000;
    public static final int BLACK = 0;
    public static final int RED = 1;
    
    public static void main(String[] args)
    {
        int inputInt;
        
        do
        {
            System.out.print("Skriv inn antall spinn (ikke negative tall eller bokstaver): ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            try
            {
                String input = br.readLine();
                inputInt = Integer.parseInt(input);
            }
            catch(IOException | NumberFormatException e)
            {
                inputInt = -1;
            }
        }while (inputInt < 0);
        
        long startTime = System.currentTimeMillis();
        
        Random random = new Random();
        int prevRand = random.nextInt(2);
        int[] seqArray = new int[10];
        int seq = 1;
        int[] vals = new int[2];
        vals[prevRand] = 1;
        int count = 1;
        
        while (count < inputInt)
        {
            int rand = random.nextInt(2);
            vals[rand] += 1;
            
            if (rand == prevRand)
                seq += 1;
            else
            {
                if (seq >= seqArray.length)
                {
                    int[] temp = new int[seq+1];
                    System.arraycopy(seqArray, 0, temp, 0, seqArray.length);
                    seqArray = temp;
                }
                
                seqArray[seq] +=1;
                seq = 1;
            }
            
            prevRand = rand;
            count++;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Black: ").append(vals[BLACK]).append("\n");
        sb.append("Red: ").append(vals[RED]).append("\n");
        sb.append("Sequens: ").append("\n");
        
        for (int i = 0; i < seqArray.length; i++)
        {
            sb.append(i).append(": ").append(seqArray[i]).append("\n");         
        }
        
        long endTime = System.currentTimeMillis();
        
        long time = endTime - startTime;
        
        sb.append("\nTid: ").append(time).append(" millisekunder.");
        
        System.out.println(sb.toString());
    }
}
