package ca.jrvs.apps.grep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaGrepImp implements JavaGrep{
    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;
    private String rootPath;
    private String outFile;

    public static void main(String[] args) {
        if (args.length != 3){
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }


        JavaGrepImp currSession = new JavaGrepImp();
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
    public void process() throws IOException {
        List<String> matchedLines = new ArrayList<>();

        for (File file : listFiles(getRootPath())){
            for (String line : readLines(file)){
                if (containsPattern(line)){
                    matchedLines.add(line);
                }
            }
        }

        writeToFile(matchedLines);

    }

    @Override
    public List<File> listFiles(String rootDir) {
        List<File> fileList = new ArrayList<>();

        try{
            File currDir = new File(rootDir);

            if(currDir.isDirectory()){
                File[] files = currDir.listFiles();
                fileList.addAll(Arrays.asList(files));
            }
            else{
                throw new IllegalArgumentException("Directory: " + rootDir + " is not valid.");
            }
        }
        catch(Exception e){
            throw new IllegalArgumentException("Error in directory: " + rootDir + ". Cannot return files.");
        }
        return fileList;
    }

    @Override
    public List<String> readLines(File inputFile) {
        List<String> lineList = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();

            while (line != null) {
                lineList.add(line);
                line = reader.readLine();
            }
            reader.close();
            return lineList;
        }
        catch (FileNotFoundException e){
            throw new RuntimeException("Error file not found: ",e);
        }
        catch (IOException e){
            throw new RuntimeException("Error IO Exception: ",e);
        }

    }

    @Override
    public boolean containsPattern(String line) {
        return line.matches(getRegex());
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        File outputFile = new File(getOutFile());
        FileWriter fr = null;
        BufferedWriter br = null;

        try{
            fr = new FileWriter(outputFile);
            br = new BufferedWriter(fr);
            for (String fileLine : lines){
                br.write(fileLine + '\n');
            }
        }
        catch (IOException e){
            throw new RuntimeException("IO Exception: ",e);
        }
        finally{
            try{
                br.close();
                fr.close();
            }
            catch(IOException e){
                throw new IOException(e);
            }
        }
    }

    @Override
    public String getRootPath() {
        return this.rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getRegex() {
        return this.regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getOutFile() {
        return this.outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }
}
