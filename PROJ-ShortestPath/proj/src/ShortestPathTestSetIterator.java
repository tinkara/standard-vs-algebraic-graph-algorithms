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
public class ShortestPathTestSetIterator extends DefaultTestSetIterator {
	
  @Override
  public TestCase getCurrent(){
    if (currentInputLine == null) {
      ErrorStatus.setLastErrorMessage(ErrorStatus.ERROR, "No valid input!");
      return null;
    }
    
    ShortestPathTestCase tCase = new ShortestPathTestCase();
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
    
    tCase.matrix=matrika;
    int[] result=BranjePodatkov.preberiClusterje(filePath, testResult[1], Integer.parseInt(voz[1]));
    tCase.shortestPath=result;
    
    return tCase;
  }
  
  
  
  // TEST
    
    public static void main(String args[]) {
    	String dataroot     = "data_root"; // a folder with the "projects" folder
        String projName     = "ShortestPath";
    
    ETestSet testSet = ATTools.getFirstTestSetFromProject(dataroot, projName);
    ShortestPathTestSetIterator stsi = new ShortestPathTestSetIterator();
    stsi.setTestSet(testSet);
    
    ATTools.iterateAndPrintTests(stsi);
  }
    
  
}
 