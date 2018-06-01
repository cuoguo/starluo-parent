import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

public class NoSQLManagerForMongoDBHelper {

    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        File configFile = new File("C:\\ProgramData\\NoSQL Manager Group\\NoSQL Manager for MongoDB\\appConfig.xml");
        Files.copy(configFile.toPath(), baos);
        
        Runtime.getRuntime().exec("reg delete \"HKEY_CURRENT_USER\\Software\\NoSQL Manager Group\" /f");
        delete(new File("C:\\ProgramData\\NoSQL Manager Group"));
        new File("C:\\ProgramData\\NoSQL Manager Group\\NoSQL Manager for MongoDB").mkdirs();
        
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(configFile));
        bos.write(baos.toByteArray());
        bos.close();
    }
    
    private static void delete(File f) {
        if(f.isDirectory()) {
            for(File ff : f.listFiles()) {
                delete(ff);
            }
        }
        f.delete();
    }
}
