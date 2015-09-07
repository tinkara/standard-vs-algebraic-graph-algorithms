import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;



public class BranjePodatkov {
	public static double[][] preberiMatrikoFileInputStream(String path, String fileName){
		double[][] test_graph_matrix=null;
		String strFilePath=path+"/"+fileName+".txt";
		try
	    {
	      //create FileInputStream object
	      FileInputStream fin = new FileInputStream(strFilePath);
	     
	       DataInputStream din = new DataInputStream(fin);
	       //stevilo vozlisc
	       int stVozlisc=Integer.parseInt(din.readLine());
	       //stevilo povezav
	       int stPovezav=Integer.parseInt(din.readLine());
	       
	       //matrika velikost stVozlisc X stVozlisc
	       test_graph_matrix=new double [stVozlisc][stVozlisc];
	       for(int i=0; i<stVozlisc;i++){
	    	   for(int j=0; j<stVozlisc;j++){
	    		   test_graph_matrix[i][j]=0;
	    	   }
	       }
	       
	       //vozlisce od
	       int from=0;
	       //vozlisce do
	       int to=0;
	       String temp="a";
	       for(int e=0; e<stPovezav;e++){
	    	   temp = din.readLine();
	    	   String[] t=temp.split("\t");
	    	   from=Integer.parseInt(t[0]);
	    	   to=Integer.parseInt(t[1]);
	    	   test_graph_matrix[from][to]=1;
	       }
	       din.close();
	       
	    } catch (Exception e) {
	    	System.out.println("Error");
	    }
		return test_graph_matrix;
	}
	
	public static int[][] preberiMatrikoPath(String strFilePath){
		int[][] test_graph_matrix=null;
		try
	    {
			Scanner sc = new Scanner(new File(strFilePath));
		    int stVozlisc = sc.nextInt();
		    int stPovezav = sc.nextInt();
	       //matrika velikost stVozlisc X stVozlisc
	       test_graph_matrix=new int [stVozlisc][stVozlisc];
	       for(int i=0; i<stVozlisc;i++){
	    	   for(int j=0; j<stVozlisc;j++){
	    		   if(i==j){
	    			   test_graph_matrix[i][j]=0;
	    		   }
	    		   else{
	    			   test_graph_matrix[i][j]=Integer.MAX_VALUE;
	    		   }
	    	   }
	       }
	       
	       //vozlisce od
	       int from=0;
	       //vozlisce do
	       int to=0;
	       //cena
	       int cena=0;
	       for(int e=0; e<stPovezav;e++){
	    	   from=sc.nextInt();
	    	   to=sc.nextInt();
	    	   cena=sc.nextInt();
	    	   test_graph_matrix[from][to]=cena;
	       }
	       sc.close();
	    } catch (Exception e) {
	    	System.out.println("Error branje");
	    	System.out.print(e);
	    }
	    
		return test_graph_matrix;
	}
	
	public static double[][] preberiMatriko(String path, String fileName){
		double[][] test_graph_matrix=null;
		String strFilePath=path+"/"+fileName+".txt";
		try
	    {
			Scanner sc = new Scanner(new File(strFilePath));
		    int stVozlisc = sc.nextInt();
		    int stPovezav = sc.nextInt();
	       
	       //matrika velikost stVozlisc X stVozlisc
	       test_graph_matrix=new double [stVozlisc][stVozlisc];
	       for(int i=0; i<stVozlisc;i++){
	    	   for(int j=0; j<stVozlisc;j++){
	    		   test_graph_matrix[i][j]=0;
	    	   }
	       }
	       
	       //vozlisce od
	       int from=0;
	       //vozlisce do
	       int to=0;
	       for(int e=0; e<stPovezav;e++){
	    	   from=sc.nextInt();
	    	   to=sc.nextInt();
	    	   test_graph_matrix[from][to]=1;
	       }
	       
	    } catch (Exception e) {
	    	System.out.println("Error");
	    }
		return test_graph_matrix;
	}
	
	public static int[] preberiClusterje(String path, String fileName, int stVozlisc){
		String strFilePath=path+"/"+fileName+".txt";
		int[] clusters= new int[stVozlisc];
		try
	    {
			Scanner sc = new Scanner(new File(strFilePath));
			for(int i=0; i<stVozlisc;i++){
				int ind=sc.nextInt();
				clusters[ind]=sc.nextInt();
			}
	    }
		catch(Exception e){
			System.out.println("Error clusters read");
		}
		return clusters;
	}
	
	public static Edge[][] fromMatrixToStandard(double[][] test_graph_matrix,int stVozlisc){
		Edge[][] test_graph_standard=new Edge[stVozlisc][];
	    for(int i=0; i<stVozlisc;i++){
	    	int stSosedov=0;
	    	for(int j=0; j<stVozlisc;j++){
	    		if(test_graph_matrix[i][j]>0){
	    			stSosedov++;
	    		}
	    	}
	    	Edge[] temp=new Edge[stSosedov];
	    	int s=0;
	    	for(int j=0; j<stVozlisc;j++){
	    		if(test_graph_matrix[i][j]>0){
	    			temp[s]=new Edge(j,1);
	    			s++;
	    		}
	    	}
	    	test_graph_standard[i]=temp;
	    }
	    return test_graph_standard;
	}
	
	 public static Edge[][] fromMatrixToStandard(int[][] test_graph_matrix,int stVozlisc ){
			Edge[][] test_graph_standard=new Edge[stVozlisc][];
		    for(int i=0; i<stVozlisc;i++){
		    	int stSosedov=0;
		    	for(int j=0; j<stVozlisc;j++){
		    		if(test_graph_matrix[i][j]>0){
		    			stSosedov++;
		    		}
		    	}
		    	Edge[] temp=new Edge[stSosedov];
		    	int s=0;
		    	for(int j=0; j<stVozlisc;j++){
		    		if(test_graph_matrix[i][j]>0){
		    			temp[s]=new Edge(j,1);
		    			s++;
		    		}
		    	}
		    	test_graph_standard[i]=temp;
		    }
		    return test_graph_standard;
		}
}
