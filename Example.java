/*the model1 is a test for CPLEX 
min 0.12x+0.15y
60x+60y>=300
12x+6y>=36
10x+30y>=90
x>=0, y>=0
*/
import ilog.concert.*;
import ilog.cplex.*;

public class Example {
	public static void main (String args[]){
		model2(4);
		model1();
	}
public static void model2(int n){
	try{
		//define parameter
		int NN,NV;
		double CV;
		double DN[];
		DN= new double[6];
		double TD [][];
		TD =new double[6][6];
		double LG[][];
		int X,Z;
		X=10000000;
		Z=10000000;
		        NN = 6;
				NV = 3;
				CV = 500;
				DN[0] = 0;
				DN[1] = 300;
				DN[2] = 200;
				DN[3] = 230;
				DN[4] = 430;
				DN[5] = 50;
				TD[0][0] = 0;
				TD[0][1] = 30;
				TD[0][2] = 40;
				TD[0][3] = 100;
				TD[0][4] = 25;
				TD[0][5] = 250;
				TD[1][0] = 30;
				TD[1][0] = 30;
				TD[1][1] = 0;
				TD[1][2] = 120;
				TD[1][3] = 200;
				TD[1][4] = 70;
				TD[1][5] = 10;
				TD[2][0] = 40;
				TD[2][1] = 120;
				TD[2][2] = 0;
				TD[2][3] = 300;
				TD[2][4] = 130;
				TD[2][5] = 95;
				TD[3][0] = 100;
				TD[3][1] = 200;
				TD[3][2] = 300;
				TD[3][3] = 0;
				TD[3][4] = 320;
				TD[3][5] = 50;
				TD[4][0] = 25;
				TD[4][1] = 70;
				TD[4][2] = 130;
				TD[4][3] = 320;
				TD[4][4] = 0;
				TD[4][5] = 70;
				TD[5][0] = 250;
				TD[5][1] = 10;
				TD[5][2] = 95;
				TD[5][3] = 50;
				TD[5][4] = 70;
				TD[5][5] = 0;
				IloCplex cplex2=new IloCplex();
		//define variable
		IloNumVar t[][][] =new IloNumVar[n][][];
			for(int i=0;i<n;i++){
				t[i][i]=cplex2.boolVarArray(n);
			}
		IloNumVar u[]=new IloNumVar [n];
			for(int i=0;i<n;i++){
				IloNumVar buf =null;
				u[i]=buf;
			}
		
		
		//define descision variable
		//define objective
		IloLinearNumExpr obj=cplex2.linearNumExpr();
	    // the code below is the first term of the objective
		for (int k=1;k<NV;k++){
			obj.addTerm(X, u[k]);
			}
		//the code below is the second term of the objective
		for (int i=0;i<NN;i++){
	    	for(int j=0;j<NN;j++){
	    		for(int k=1;k<NV;k++){
	    			if(j!=i){  //if the result is the same point
	    				obj.addTerm(TD[i][j],t[i][j][k]);
	    			}
	    		}
	    	}
	    }
		cplex2.addMinimize(obj);
		//constraint
		//constraint(2)
	for(int j=0;j<NN;j++){
		IloLinearNumExpr expr =cplex2.linearNumExpr();
		for(int i=0;i<NN;i++){
			for(int k=1;k<NV;k++){
				if(i!=j){
					expr.addTerm(1.0, t[i][j][k]);
				}
			}
		}
		cplex2.addEq(1.0, expr);
	}
	//constraint(3)
	for(int j=0;j<NN;j++){
		IloLinearNumExpr expr =cplex2.linearNumExpr();
		for(int i=0;i<NN;i++){
			for(int k=1;k<NV;k++){
				if(i!=j){
					expr.addTerm(1.0, t[j][i][k]);
				}
			}
		}
		cplex2.addEq(1.0, expr);
	}
	//constraint(4)
	IloNumExpr var4=cplex2.numExpr();
	for(int k=0;k<NV;k++){
		IloLinearNumExpr expr=cplex2.linearNumExpr();
		for(int j=0;j<NN;j++){
			for(int i=0;i<NN;i++){
				expr.addTerm(1.0, t[i][j][k]);
			}
			var4=cplex2.prod(DN[j], expr);
		}
	}
	cplex2.addLe(CV, var4);
	//constraint(5)
	for(int k=0;k<NV;k++){
		IloLinearNumExpr expr=cplex2.linearNumExpr();
		for(int j=1;j<NN;j++){
			
			expr.addTerm(1.0, t[0][j][k]);
			expr.addTerm(-1, u[k]);
		}
		cplex2.addEq(0.0, expr);
	}
	//constraint(6)
	for(int k=0;k<NV;k++){
		IloLinearNumExpr expr=cplex2.linearNumExpr();
		for(int i=1;i<NN;i++){
			
			expr.addTerm(1.0, t[i][0][k]);
			expr.addTerm(-1, u[k]);
		}
		cplex2.addEq(0.0, expr);
	}
	/*//constraint(7)
	IloNumExpr var7= cplex2.numExpr();
	for(int i=0;i<NN;i++){
		IloLinearNumExpr expr=cplex2.linearNumExpr();
		for(int j=0;j<NN;j++){
			for(int k=0;k<NV;k++){
				expr.addTerm(1.0, u[i][k]);
				expr.addTerm(-1.0, u[j][k]);
				
				
			}
		}
	}*/
	//constraint(8)

	if(cplex2.solve()){
		System.out.println("the objective is"+cplex2.getObjValue());
		//System.out.println("the x is "+ cplex2.getValue(x));
		//System.out.println("the y is "+ cplex2.getValue(y));
			
		}
		else{
			System.out.println("the model was not solved");
		}
	
	}
	catch(IloException e){
		e.printStackTrace();
	}
}
//this model is a test for CPLEX language
public static void model1() {
	try{ 
		IloCplex cplex1=new IloCplex();
		
		//variable
		IloNumVar x= cplex1.numVar(0,Double.MAX_VALUE,"x");
		IloNumVar y= cplex1.numVar(0,Double.MAX_VALUE,"x");
		
		//expression
		IloLinearNumExpr objective= cplex1.linearNumExpr();
		objective.addTerm(0.12, x);
		objective.addTerm(0.15, y);
		
		//define objective
		cplex1.addMinimize(objective);
		
		//define constraint
		cplex1.addGe(cplex1.sum(cplex1.prod(60, x),cplex1.prod(60, y)), 300);
		cplex1.addGe(cplex1.sum(cplex1.prod(12, x),cplex1.prod(6, y)), 36);
		cplex1.addGe(cplex1.sum(cplex1.prod(10, x),cplex1.prod(30, y)), 90);
		
		//solve
		if(cplex1.solve()){
		System.out.println("the objective is"+cplex1.getObjValue());
		System.out.println("the x is "+ cplex1.getValue(x));
		System.out.println("the y is "+ cplex1.getValue(y));
			
		}
		else{
			System.out.println("the model was not solved");
		}
	}
	
	catch(IloException e){ 
		e.printStackTrace();
	}
}
 }