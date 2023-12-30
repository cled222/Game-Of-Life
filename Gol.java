import java.io.*;

public class Gol {
    public static void main (String [] args)
    {
        boolean [][] board = readFile();
        int i = 0;
        do
        {
            printBoard(board,i);
            mySleep(1000);
            board = playGame(board);
            i++;
        }while(i<10);
    }

    public static void printBoard(boolean [][] arr, int gen)
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        System.out.println("Generation " + gen + ":");
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j< arr[i].length; j++)
            {
                System.out.print(arr[i][j] ? "*" : " ");
            }
            System.out.println();
        }
    }

    public static void mySleep(int duration)
    {
        try{
            Thread.sleep(duration);
        }catch(Exception e){}
    }

    public static boolean [][] readFile()
    {
        boolean [][] brd = null;
        int i = 0, size;

        try
        {
            BufferedReader br = new BufferedReader( new InputStreamReader(System.in));

            String line;
            while((line = br.readLine()) != null)
            {
                //convert the line of the text into a line in my board
                if (i==0)
                {
                    size = line.length();
                    brd = new boolean [size][size];
                }
                fillboard(brd, line, i);
                i++;
            }
            br.close();
        }catch(Exception e) {}
        return brd;
    }

    public static void fillboard(boolean [][] brd, String line, int row)
    {
        for(int i = 0; i<line.length(); i++)
        {
            brd[row][i] = (line.charAt(i) == '*') ? true : false;
        }
    }
//creates a new 2d array with new generation
    public static boolean [][] playGame(boolean [][] brd)
    {
        boolean [][] newbrd = new boolean [brd.length][brd.length];

        for(int i = 1; i<brd.length-1; i++)
        {
            for(int j = 1; j< brd[i].length-1; j++)
            {
                int neighbors = countNeighbors(brd, i, j);

                if(brd[i][j])
                {
                    newbrd[i][j] = neighbors ==2 || neighbors == 3;
                }
                else
                {
                    newbrd[i][j] = (neighbors == 3);
                }
            }
        }
        return newbrd;
    }

    public static int countNeighbors(boolean [][] brd, int row, int col)
    {
        int count = 0;
        for(int i = -1; i < 2; i++)
        {
            for(int j = -1; j < 2; j++)
            {
                if(!((i == 0) && (j == 0)) && brd[row+i][col + j])
                {
                    count ++;
                }
            }
        }


        return count;
    }
}

