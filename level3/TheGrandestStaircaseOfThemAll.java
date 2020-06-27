public class TheGrandestStaircaseOfThemAll
{
	public static void main(String[] args)
	{
		System.out.println(solution(9));
	}
	
	public static int solution(int n) 
    {
		int[][] steps = new int[n+1][n+1];
		
		steps[0][0] = 1;
		
		for(int last=1; last <= n; last++)
		{
			 for (int left = 0; left  <= n; left ++) 
	         {
				 steps[last][left] = steps[last - 1][left];
				 
				 if(left >= last)
				 {
					 steps[last][left] += steps[last - 1][left - last];
				 }
			  }
		}
		
		for(int i = 0; i<steps.length; i++)
		{
			for(int j = 0; j<steps[i].length; j++)
			{
				System.out.println("i: "+i+" and j is: "+j+" value is: "+steps[i][j]);
			}
		}
		
		return steps[n][n] - 1;
    }

}
