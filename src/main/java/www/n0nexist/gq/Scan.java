package www.n0nexist.gq;

import java.io.IOException;
import java.util.ArrayList;

public class Scan {

    private boolean mcmp;
    private boolean mctracker;
    private boolean mclist;

    String[] skip = {"aternos", "ploud", "guard", "tcpshield", "minehut", "shockbyte"};

    public Scan(boolean a, boolean b, boolean c){
        mcmp = a;
        mctracker = b;
        mclist = c;
        Log.log("minecraft-mp.com => "+a);
        Log.log("minecraft-tracker.com => "+b);
        Log.log("mcl.ist => "+c);

        if (!mcmp && !mctracker && !mclist){
            System.out.println("ERROR => you must select at least one option");
            System.exit(-1);
        }

        while (true) {
            Log.log("Starting new scan...");

            if (mclist) {
                Log.log("Retrieving servers from mcl.ist");
                for (String x : getServers("mclist")) {

                    String check = isValid(x);
                    if (check!="*no*"&&!(check.contains("Error: "))){
                        check = ipToRange(check);
                        Log.log("[mcl.ist] Scanning "+x+" => "+check);
                        try {
                            CommandRunner.runcmd(check);
                        } catch (IOException e) {
                            Log.log("IOEXCEPTION WHILE SCANNING");
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            Log.log("INTERRUPTEDEXCEPTION WHILE SCANNING");
                            e.printStackTrace();
                        }
                    }else{
                        Log.log("[mcl.ist] Skipping "+x);
                    }

                }
            }

            if (mcmp) {
                Log.log("Retrieving servers from minecraft-mp.com");
                for (String x : getServers("mcmp")) {

                    String check = isValid(x);
                    if (check!="*no*"&&!(check.contains("Error: "))){
                        check = ipToRange(check);
                        Log.log("[minecraft-mp] Scanning "+x+" => "+check);
                        try {
                            CommandRunner.runcmd(check);
                        } catch (IOException e) {
                            Log.log("IOEXCEPTION WHILE SCANNING");
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            Log.log("INTERRUPTEDEXCEPTION WHILE SCANNING");
                            e.printStackTrace();
                        }
                    }else{
                        Log.log("[minecraft-mp] Skipping "+x);
                    }

                }
            }

            if (mctracker) {
                Log.log("Retrieving servers from minecraft-tracker.com");
                for (String x : getServers("mctracker")) {

                    String check = isValid(x);
                    if (check!="*no*"&&!(check.contains("Error: "))){
                        check = ipToRange(check);
                        Log.log("[minecraft-tracker] Scanning "+x+" => "+check);
                        try {
                            CommandRunner.runcmd(check);
                        } catch (IOException e) {
                            Log.log("IOEXCEPTION WHILE SCANNING");
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            Log.log("INTERRUPTEDEXCEPTION WHILE SCANNING");
                            e.printStackTrace();
                        }
                    }else{
                        Log.log("[minecraft-tracker] Skipping "+x);
                    }

                }
            }


        }
    }


    String isValid(String x){
        String domain = "";
        try {
            domain = x.split(":")[0];
        }catch (Exception e){
            domain = x;
        }
        String resolved = DNSLookup.getDNS(domain);
        String lookup = DNSLookup.Lookup(resolved);
        boolean valid = true;
        for (String y : skip){
            if (lookup.contains(y)||x.contains(y)){
                valid = false;
            }
        }
        if (valid){
            return resolved;
        }else{
            return "*no*";
        }
    }


    String ipToRange(String ip){
        String[] temp = ip.split("\\.");
        return temp[0]+"."+temp[1]+"."+temp[2]+".*";
    }


    ArrayList<String> getServers(String website){
        ArrayList<String> temp = new ArrayList<String>();
        String servers = "";
        try {
            int limit = 0;
            switch (website) {
                case "mcmp":
                    servers = SocketConnection.getURLSource("https://minecraft-mp.com/servers/random/");
                    limit = servers.split("<strong>").length;
                    for (int c=5;c<limit;c++){
                            String t = servers.split("<strong>")[c].split("</strong>")[0];
                            if (!t.contains("Private Server")
                            &&!t.contains("Minecraft-mp.com")
                            &&!t.startsWith("#")
                            &&!temp.contains(t)) {
                                    temp.add(t);
                            }
                    }
                    break;
                case "mctracker":
                    servers = SocketConnection.getURLSource("https://minecraft-tracker.com/servers/random/");
                    limit = servers.split("</span></a>").length;
                    for (int c=1;c<limit;c++){
                        temp.add(servers.split("</span></a>")[c].split("</button>")[0].strip());
                    }
                    break;
                case "mclist":
                    servers = SocketConnection.getURLSource("https://mcl.ist/");
                    limit = servers.split("href=\"/server/").length;
                    for (int c=2;c<limit;c++){
                        temp.add(servers.split("href=\"/server/")[c].split("\"")[0]);
                    }
                    break;
            }
        }catch(Exception e){
            Log.log("UNKNOWN ERROR");
            e.printStackTrace();
        }
        return temp;
    }

}
