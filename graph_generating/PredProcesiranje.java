import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import matrix_implementation.Matrix_alg;


public class PredProcesiranje {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//v args 0 pot do grafov za najkrajše poti!
		File dir = new File(args[0]);   
	    File[] fileList = dir.listFiles();
	    //int stPonovitev=10;
		
	    for(int m=0; m<fileList.length;m++){
	    	if (fileList[m].toString().endsWith(".txt") && !fileList[m].toString().endsWith("result.txt")){
	    		int[][] test_graph_matrix=BranjePodatkov.preberiMatrikoPath(fileList[m].toString());
		    
	    		int [] result=Matrix_alg.FloydWarshall_normal(test_graph_matrix);
		    
	    		String name=fileList[m]+"_result.txt";
	    		PrintWriter writer=null;
	    		try {
	    			writer = new PrintWriter(name, "UTF-8");
	    		} catch (FileNotFoundException e) {
				e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			    
			    for (int k=0; k<result.length;k++){
					writer.println(k+" "+result[k]);
			    }
			    writer.close();
		    }
	    }

	}

}
