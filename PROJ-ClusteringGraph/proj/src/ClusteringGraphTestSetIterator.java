import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import si.fri.algotest.entities.EParameter;
import si.fri.algotest.entities.EResultDescription;
import si.fri.algotest.entities.ETestSet;
import si.fri.algotest.entities.ParameterType;
import si.fri.algotest.entities.TestCase;
import si.fri.algotest.execute.DefaultTestSetIterator;
import si.fri.algotest.global.ATLog;
import si.fri.algotest.tools.ATTools;
import si.fri.algotest.global.ErrorStatus;


/**
 *
 * @author ...
 */
public class ClusteringGraphTestSetIterator extends DefaultTestSetIterator {
   
  
  @Override
  public TestCase getCurrent() {
	  if (currentInputLine == null) {
	      ErrorStatus.setLastErrorMessage(ErrorStatus.ERROR, "No valid input!");
	      return null;
	    }
	    
	    ClusteringGraphTestCase tCase = new ClusteringGraphTestCase();
	    EParameter testIDPar = EResultDescription.getTestIDParameter("Test-" + Integer.toString(lineNumber));
	    tCase.addParameter(testIDPar); 
	    
	    String[] testResult=currentInputLine.split("//");
	    if (testResult.length != 2) {
	        ErrorStatus.setLastErrorMessage(ErrorStatus.ERROR, "Invalid testset file - line " + lineNumber);
	        return null;
	    }
	    String[] params = testResult[0].split("_");
	    if (params.length != 4) {
	        ErrorStatus.setLastErrorMessage(ErrorStatus.ERROR, "Invalid testset file - line " + lineNumber);
	        return null;
	    }
	    //razdeli tako, da v drugem delu dobimo verjetnost (vzamemo zadnji del imena)
	    String[] ver=params[params.length-1].split("ver");
	    if (ver.length != 2) {
	        ErrorStatus.setLastErrorMessage(ErrorStatus.ERROR, "Invalid testset file - line " + lineNumber);
	        return null;
	    }
	    EParameter parameter1 = new EParameter("Probability", "", ParameterType.INT, ver[1]);
	    //dobimo stevilo clusterjev
	    String[] clus=ver[0].split("c");
	    if (clus.length != 2) {
	        ErrorStatus.setLastErrorMessage(ErrorStatus.ERROR, "Invalid testset file - line " + lineNumber);
	        return null;
	    }
	    EParameter parameter2 = new EParameter("Clusters", "", ParameterType.INT, clus[1]);
	    //dobimo stevilo povezav
	    String[] pov=clus[0].split("p");
	    if (pov.length != 2) {
	        ErrorStatus.setLastErrorMessage(ErrorStatus.ERROR, "Invalid testset file - line " + lineNumber);
	        return null;
	    }
	    EParameter parameter3 = new EParameter("E", "", ParameterType.INT, pov[1]);
	    
	    //dobimo stevilo povezav
	    String[] voz=pov[0].split("v");
	    if (voz.length != 2) {
	        ErrorStatus.setLastErrorMessage(ErrorStatus.ERROR, "Invalid testset file - line " + lineNumber);
	        return null;
	    }
	    EParameter parameter4 = new EParameter("V", "", ParameterType.INT, voz[1]);
	    
	    tCase.addParameter(parameter1);
	    tCase.addParameter(parameter2);
	    tCase.addParameter(parameter3);
	    tCase.addParameter(parameter4);
	    
	    //dobimo podatke za pot do datoteke z grafom
	    String filePath = testSet.entity_rootdir;
	    String fileName =testResult[0];
    
	    //nastavimo oba parametra testCase-a
	    double[][] matrika=BranjePodatkov.preberiMatriko(filePath, fileName);
	    tCase.graph= fromMatrixToStandard (matrika, Integer.parseInt(voz[1]));
	    tCase.clusters=BranjePodatkov.preberiClusterje(filePath, testResult[1], Integer.parseInt(voz[1]));
	    
	    return tCase;
  }
  private static void izpisi_matrix_double(double[][] result) {
		for(int i=0; i<result.length;i++){
			for(int j=0; j<result[i].length;j++){
				System.out.print(" "+result[i][j]+" ");
			} 
			System.out.println();
		}		
	}
  public static Edge[][] fromMatrixToStandard_v1(double[][] test_graph_matrix,int stVozlisc){
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
  
  public static double[][][] fromMatrixToStandard(double[][] test_graph_matrix,int stVozlisc){
		double[][][] test_graph_standard=new double[stVozlisc][][];
	    for(int i=0; i<stVozlisc;i++){
	    	int stSosedov=0;
	    	for(int j=0; j<stVozlisc;j++){
	    		if(test_graph_matrix[i][j]>0){
	    			stSosedov++;
	    		}
	    	}
	    	double[][] temp=new double[stSosedov][];
	    	int s=0;
	    	for(int j=0; j<stVozlisc;j++){
	    		if(test_graph_matrix[i][j]>0){
	    			double[] t=new double[2];
	    			t[0]=j;
	    			t[1]=1;
	    			temp[s]=t.clone();
	    			s++;
	    		}
	    	}
	    	test_graph_standard[i]=temp.clone();
	    }
	    return test_graph_standard;
	}
  
  // TEST
    
    public static void main(String args[]) {
    String dataroot     = "data_root"; // a folder with the "projects" folder
    String projName     = "ClusteringGraph";
    
    ETestSet testSet = ATTools.getFirstTestSetFromProject(dataroot, projName);
    ClusteringGraphTestSetIterator stsi = new ClusteringGraphTestSetIterator();
    stsi.setTestSet(testSet);
    
    ATTools.iterateAndPrintTests(stsi);
  }
  
}
 