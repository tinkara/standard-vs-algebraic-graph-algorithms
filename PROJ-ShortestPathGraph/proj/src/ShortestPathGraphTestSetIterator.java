import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
public class ShortestPathGraphTestSetIterator extends DefaultTestSetIterator {
	
  @Override
  public TestCase getCurrent(){
    if (currentInputLine == null) {
      ErrorStatus.setLastErrorMessage(ErrorStatus.ERROR, "No valid input!");
      return null;
    }
    
    ShortestPathGraphTestCase tCase = new ShortestPathGraphTestCase();
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
    
    String[] pov=params[params.length-1].split("p");
    if (pov.length != 2) {
        ErrorStatus.setLastErrorMessage(ErrorStatus.ERROR, "Invalid testset file - line " + lineNumber);
        return null;
    }
    

    EParameter parameter1 = new EParameter("E", "", ParameterType.INT, pov[1]);
    
    String[] voz=pov[0].split("v");
    if (voz.length != 2) {
        ErrorStatus.setLastErrorMessage(ErrorStatus.ERROR, "Invalid testset file - line " + lineNumber);
        return null;
    }
    EParameter parameter2 = new EParameter("V", "", ParameterType.INT, voz[1]);
        
    tCase.addParameter(parameter1);
    tCase.addParameter(parameter2);
    
    
    String filePath = testSet.entity_rootdir;
    String filename=filePath+"/"+testResult[0]+".txt";
    
    int[][] matrika=BranjePodatkov.preberiMatrikoPath(filename);
    tCase.graph=fromMatrixToStandard(matrika, Integer.parseInt(voz[1]));
    int[] result=BranjePodatkov.preberiClusterje(filePath, testResult[1], Integer.parseInt(voz[1]));
    tCase.shortestPath=result;
    
    return tCase;
  }
  
  
  
  // TEST
    
    public static void main(String args[]) {
    	String dataroot     = "data_root"; // a folder with the "projects" folder
        String projName     = "ShortestPathGraph";
    
    ETestSet testSet = ATTools.getFirstTestSetFromProject(dataroot, projName);
    ShortestPathGraphTestSetIterator stsi = new ShortestPathGraphTestSetIterator();
    stsi.setTestSet(testSet);
    
    ATTools.iterateAndPrintTests(stsi);
  }
    public static int[][][] fromMatrixToStandard(int[][] test_graph_matrix,int stVozlisc ){
		int[][][] test_graph_standard=new int[stVozlisc][][];
	    for(int i=0; i<stVozlisc;i++){
	    	int stSosedov=0;
	    	for(int j=0; j<stVozlisc;j++){
	    		if(test_graph_matrix[i][j]<Integer.MAX_VALUE){
	    			stSosedov++;
	    		}
	    	}
	    	int[][] temp=new int[stSosedov][];
	    	int s=0;
	    	for(int j=0; j<stVozlisc;j++){
	    		if(test_graph_matrix[i][j]<Integer.MAX_VALUE){
	    			int[] t=new int[2];
	    			t[0]=j;
	    			t[1]=test_graph_matrix[i][j];
	    			temp[s]=t.clone();
	    			s++;
	    		}
	    	}
	    	test_graph_standard[i]=temp.clone();
	    }
	    return test_graph_standard;
    }
    
  
}
 