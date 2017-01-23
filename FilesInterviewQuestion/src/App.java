import java.io.File;
import java.lang.String;;

public class App {

	public static void main(String[] args) {
		int counter = 1;
		System.out.println("				Project: Project - URL: /Users/Mr_Right/Desktop/projects/");
		for(int j=0; j<4 ; j++){
			String subFolder = "Project"+counter;
			File folder = new File("/Users/Mr_Right/Desktop/projects/"+subFolder);
			File[] listOfFiles = folder.listFiles();
 
			String[] myFiles     = new String[listOfFiles.length];
			String[] myDocuments = new String[listOfFiles.length];
			String[] myExt       = new String[listOfFiles.length];
			
			
			System.out.println("Project: "+subFolder+" - URL: /Users/Mr_Right/Desktop/projects/"+subFolder);
			for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {
				  
				  myFiles[i] = listOfFiles[i].getName();
				  myDocuments[i] = myFiles[i].substring(0, myFiles[i].lastIndexOf('.'));
				  myExt[i] = myFiles[i].substring(myFiles[i].lastIndexOf('.'));
				 
				  //Displaying
				  System.out.println("Document: "+myDocuments[i]+" -Extension: "+myExt[i]+"- URL: /Users/Mr_Right/Desktop/projects/"+myFiles[i]);
				  
				  
			   } 
			 }
		 counter++;
		 System.out.println("--------------------------------------------------------------------------------------");	    
		}
	}
}


