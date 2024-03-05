package ca.jrvs.apps.grep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.List;
import java.util.stream.Collectors;

public class JavaLambdaImp extends JavaGrepImp {
    final Logger logger = LoggerFactory.getLogger(JavaLambdaImp.class);

    private String regex;
    private String rootPath;
    private String outFile;

    public static void main(String[] args) {
        if (args.length != 3){
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }


        JavaLambdaImp currSession = new JavaLambdaImp();
        currSession.setRegex(args[0]);
        currSession.setRootPath(args[1]);
        currSession.setOutFile(args[2]);

        try {
            currSession.process();
        }
        catch (Exception e) {
            currSession.logger.error("Error : ", e);
        }
    }



    @Override
    public List<File> listFiles(String rootDir) {
        List<File> fileList = new ArrayList<>();

        try(Stream<Path> pathStream = Files.walk(Paths.get(rootDir))){
            fileList = pathStream
                    .map(Path::toFile)
                    .filter(file -> Files.isRegularFile(file.toPath()) || Files.isDirectory(file.toPath()))
                    .collect(Collectors.toList());
        }
        catch(Exception e){
            throw new IllegalArgumentException("Error in directory: " + rootDir + ". Cannot return files.");
        }
        return fileList;
    }

    @Override
    public List<String> readLines(File inputFile) {
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))){
            return reader.lines().filter(line -> line != null).collect(Collectors.toList());
        }
//        catch (FileNotFoundException e){
//            throw new RuntimeException("Error file not found: ",e);
//        }
        catch (IOException e){
            throw new RuntimeException("Error IO Exception: ",e);
        }

    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        File outputFile = new File(getOutFile());


        try(BufferedWriter br = new BufferedWriter(new FileWriter(outputFile))){


            lines.forEach(line -> {
                try{
                    br.write(line);
                    br.newLine();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            });
        }
        catch (IOException e){
            throw new RuntimeException("IO Exception: ",e);
        }
    }
}
