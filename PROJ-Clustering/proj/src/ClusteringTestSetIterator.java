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
public class ClusteringTestSetIterator extends DefaultTestSetIterator {
   
  
  @Override
  public TestCase getCurrent() {
    if (currentInputLine == null) {
      ErrorStatus.setLastErrorMessage(ErrorStatus.ERROR, "No valid input!");
      return null;
    }
    
    ClusteringTestCase tCase = new ClusteringTestCase();
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
    
    //nstavimo oba parametra testCase-a
    tCase.matrix=BranjePodatkov.preberiMatriko(filePath, fileName);
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
  
  // TEST
    
    public static void main(String args[]) {
    String dataroot     = "data_root"; // a folder with the "projects" folder
    String projName     = "Clustering";
    
    ETestSet testSet = ATTools.getFirstTestSetFromProject(dataroot, projName);
    ClusteringTestSetIterator stsi = new ClusteringTestSetIterator();
    stsi.setTestSet(testSet);
    
    ATTools.iterateAndPrintTests(stsi);
  }
  
}
 