import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class App {

	public static void main(String[] args) {
		String[] lines = {
				"Line 1",
				"Line 2 2",
				"Line 3 33",
				"Line 4 4 4 4"
				};
		
		try(FileSystem zipFs = openZip(Paths.get("myData.zip"))){
			copyToZip(zipFs);
			writeToFileInZip1(zipFs,lines);
			writeToFileInZip2(zipFs,lines);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private static FileSystem openZip(Path zipPath) throws IOException, URISyntaxException {
		
		Map<String,String> providerProps = new HashMap<>();
		providerProps.put("create", "true");
		
		URI zipURI = new URI("jar:file",zipPath.toUri().getPath(), null);
		FileSystem zipFs = FileSystems.newFileSystem(zipURI, providerProps); 
		
		return zipFs;
		
	}
	
	private static void copyToZip(FileSystem zipFs) throws IOException{
		
		Path sourceFile = Paths.get("myfile.txt");
		Path destFile   = zipFs.getPath("/myfileCopy.txt");
		
		Files.copy(sourceFile, destFile , StandardCopyOption.REPLACE_EXISTING);
	}
	
	private static void writeToFileInZip1(FileSystem zipFs, String[] data){
		try(BufferedWriter writer = Files.newBufferedWriter(zipFs.getPath("/myNewFile1.txt"))){
			for(String s: data){
				writer.write(s);
				writer.newLine();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private static void writeToFileInZip2(FileSystem zipFs, String[] data) throws IOException{
		
		Files.write(zipFs.getPath("/myNewFile2.txt"), Arrays.asList(data)
				, Charset.defaultCharset(), StandardOpenOption.CREATE );
	}
	
	
}
