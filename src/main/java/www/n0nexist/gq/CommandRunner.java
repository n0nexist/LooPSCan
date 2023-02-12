package www.n0nexist.gq;

import java.io.*;

public class CommandRunner {
    public static void runcmd(String range) throws IOException, InterruptedException {
        String command = Cmd.getCmd()+" "+range;
        Process process = Runtime.getRuntime().exec(command);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("THREADS: ")){
                System.out.print('\r' + line);
            }else{
                System.out.println(line);
            }
            System.out.flush();
        }
    }
}

