//TCS CodeVita Sofa Problem
import java.util.*;

class Sofa 
{
    int fsr, fsc, ssr, ssc;
    char dir;
    int moves;

    public Sofa(int fsr, int fsc, int ssr, int ssc, char d, int m) 
    {
        this.fsr = fsr;
        this.fsc = fsc;
        this.ssr = ssr;
        this.ssc = ssc;
        this.dir = d;
        this.moves = m;
    }
}
//1_2_1_3

class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt(); int C = sc.nextInt(); sc.nextLine();
        char grid[][] = new char[R][C];
        int fsr,fsc,ssr,ssc,sofaCount=0;
        fsr=fsc=ssr=ssc = -1;
        Queue<Sofa> q = new LinkedList<>();
        for(int row=0; row<R; row++)
        {
            for(int col=0; col<C; col++)
            {
                char ch = sc.next().charAt(0);
                grid[row][col] = ch;
                if(ch=='s')
                {
                    sofaCount++;
                    if(sofaCount==1)
                    {
                        fsr = row; fsc = col;
                    }
                    else
                    {
                        ssr = row; ssc = col;
                        Sofa s = new Sofa(fsr,fsc,ssr,ssc,(fsr==ssr)?'H':'V',0);
                        q.add(s);
                    }
                }
            }
        }
        sc.close();

        Set<String> vis = new HashSet<>();
        while(!q.isEmpty())
        {
            Sofa s = q.poll();
            if(grid[s.fsr][s.fsc]=='S' && grid[s.ssr][s.ssc]=='S')
            {
                System.out.println(s.moves); return;
            }
            if(s.dir=='H')
            {
                if (s.ssc < C - 1 && grid[s.ssr][s.ssc + 1] != 'H') 
                {
                    if (canAdd(s.ssr, s.ssc, s.ssr, s.ssc + 1, vis)) 
                    {
                        q.add(new Sofa(s.ssr, s.ssc, s.ssr, s.ssc + 1, 'H', s.moves + 1));
                    }

                }

                if (s.fsc > 0 && grid[s.fsr][s.fsc - 1] != 'H') 
                {
                    if (canAdd(s.fsr, s.fsc - 1, s.fsr, s.fsc, vis)) 
                    {
                        q.add(new Sofa(s.fsr, s.fsc - 1, s.fsr, s.fsc, 'H', s.moves + 1));
                    }

                }

                if (s.fsr > 0 && grid[s.fsr - 1][s.fsc] != 'H' && grid[s.ssr - 1][s.ssc] != 'H') 
                {
                    if (canAdd(s.fsr - 1, s.fsc, s.ssr - 1, s.ssc, vis)) 
                    {
                        q.add(new Sofa(s.fsr - 1, s.fsc, s.ssr - 1, s.ssc, 'H', s.moves + 1));
                    }
                }

                if (s.fsr < R - 1 && grid[s.fsr + 1][s.fsc] != 'H' && grid[s.ssr + 1][s.ssc] != 'H') 
                {
                    if (canAdd(s.fsr + 1, s.fsc, s.ssr + 1, s.ssc, vis)) 
                    {
                        q.add(new Sofa(s.fsr + 1, s.fsc, s.ssr + 1, s.ssc, 'H', s.moves + 1));
                    }
                }

                if (s.fsr > 0 && grid[s.fsr - 1][s.fsc] != 'H' && grid[s.ssr - 1][s.ssc] != 'H') 
                {
                    if (canAdd(s.fsr - 1, s.fsc, s.fsr, s.fsc, vis)) 
                    {
                        q.add(new Sofa(s.fsr - 1, s.fsc, s.fsr, s.fsc, 'V', s.moves + 1));
                    }

                    if (canAdd(s.ssr - 1, s.ssc, s.ssr, s.ssc, vis)) 
                    {
                        q.add(new Sofa(s.ssr - 1, s.ssc, s.ssr, s.ssc, 'V', s.moves + 1));
                    }
                }

                if (s.fsr < R - 1 && grid[s.fsr + 1][s.fsc] != 'H' && grid[s.ssr + 1][s.ssc] != 'H') 
                {
                    if (canAdd(s.fsr, s.fsc, s.fsr + 1, s.fsc, vis)) 
                    {
                        q.add(new Sofa(s.fsr, s.fsc, s.fsr + 1, s.fsc, 'V', s.moves + 1));
                    }

                    if (canAdd(s.ssr, s.ssc, s.ssr + 1, s.ssc, vis))
                    {
                        q.add(new Sofa(s.ssr, s.ssc, s.ssr + 1, s.ssc, 'V', s.moves + 1));
                    }
                }

            }
            else
            {
                if (s.ssr < R - 1 && grid[s.ssr + 1][s.ssc] != 'H') 
                {
                    if (canAdd(s.ssr, s.ssc, s.ssr + 1, s.ssc, vis)) 
                    {
                        q.add(new Sofa(s.ssr, s.ssc, s.ssr + 1, s.ssc, 'V', s.moves + 1));
                    }
                }

                if (s.fsr > 0 && grid[s.fsr - 1][s.fsc] != 'H') 
                {
                    if (canAdd(s.fsr - 1, s.fsc, s.fsr, s.fsc, vis)) 
                    {
                        q.add(new Sofa(s.fsr - 1, s.fsc, s.fsr, s.fsc, 'V', s.moves + 1));
                    }
                }

                if (s.fsc > 0 && grid[s.fsr][s.fsc - 1] != 'H' && grid[s.ssr][s.ssc - 1] != 'H') 
                {
                    if (canAdd(s.fsr, s.fsc - 1, s.ssr, s.ssc - 1, vis)) 
                    {
                        q.add(new Sofa(s.fsr, s.fsc - 1, s.ssr, s.ssc - 1, 'V', s.moves + 1));
                    }
                }

                if (s.ssc < C - 1 && grid[s.fsr][s.fsc + 1] != 'H' && grid[s.ssr][s.ssc + 1] != 'H') 
                {
                    if (canAdd(s.fsr, s.fsc + 1, s.ssr, s.ssc + 1, vis)) 
                    {
                        q.add(new Sofa(s.fsr, s.fsc + 1, s.ssr, s.ssc + 1, 'V', s.moves + 1));
                    }
                }

                if (s.fsc > 0 && grid[s.fsr][s.fsc - 1] != 'H' && grid[s.ssr][s.ssc - 1] != 'H') 
                {
                    if (canAdd(s.fsr, s.fsc - 1, s.fsr, s.fsc, vis)) 
                    {
                        q.add(new Sofa(s.fsr, s.fsc - 1, s.fsr, s.fsc, 'H', s.moves + 1));
                    }

                    if (canAdd(s.ssr, s.ssc - 1, s.ssr, s.ssc, vis)) 
                    {
                        q.add(new Sofa(s.ssr, s.ssc - 1, s.ssr, s.ssc, 'H', s.moves + 1));
                    }
                }

                if (s.ssc < C - 1 && grid[s.fsr][s.fsc + 1] != 'H' && grid[s.ssr][s.ssc + 1] != 'H') 
                {
                    if (canAdd(s.fsr, s.fsc, s.fsr, s.fsc + 1, vis)) 
                    {
                        q.add(new Sofa(s.fsr, s.fsc, s.fsr, s.fsc + 1, 'H', s.moves + 1));
                    }

                    if (canAdd(s.ssr, s.ssc, s.ssr, s.ssc + 1, vis)) 
                    {
                        q.add(new Sofa(s.ssr, s.ssc, s.ssr, s.ssc + 1, 'H', s.moves + 1));
                    }
                }
            }
        }

        System.out.println("Impossible!");
    }

    static final String DELIM = "-";
    private static boolean canAdd(int fsr, int fsc, int ssr, int ssc, Set<String> vis)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(fsr).append(DELIM).append(fsc).append(DELIM);
        sb.append(ssr).append(DELIM).append(ssc);
        String key = sb.toString();
        if(vis.contains(key))
        {
            return false;
        }
        vis.add(key);
        return true;
    }
}