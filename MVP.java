import ilog.concert.*;
import ilog.cplex.*;
public class MVP {
	
	public static void solveme(int n,int k){//n is the node that you need
		//k is the number of trucks
		//this is the set of N
		int[] xPos = new int[n];
		int[] yPos = new int[n];
		for(int i=0; i<n; i++){////random data
			xPos[i] = (int)(Math.random()*100);
			yPos[i] = (int) (Math.random()*100);
		}
		double[][] t = new double[n][n];//travel distance
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				t[i][j] = Math.sqrt(Math.pow(xPos[i]-xPos[j], 2)+Math.pow(yPos[i]-yPos[j], 2));
			}
		}
	
	}
	public static void model(){
		
	}
	public static void main(String args[]){
		
	}
}
