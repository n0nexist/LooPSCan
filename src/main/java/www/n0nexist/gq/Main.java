package www.n0nexist.gq;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner s = new Scanner(System.in);
        Logo.printlogo();

        System.out.println("--- Options ---" +
                "\na) random servers from minecraft-mp.com" +
                "\nb) random servers from minecraft-tracker.com" +
                "\nc) random servers from mcl.ist" +
                "\n" +
                "Options: (can be more than one)");
        System.out.print("LooPSCan# ");
        String opzioni = s.nextLine().toLowerCase();

        System.out.println("--- Command ---" +
                "\nInsert your quboscanner command " +
                "\n(the target to scan will be placed at the end of your command)");
        System.out.print("LooPSCan# ");
        String comando = s.nextLine();
        Cmd.setCmd(comando);

        s.close();

        new Scan(
                opzioni.contains("a"),
                opzioni.contains("b"),
                opzioni.contains("c")
        );
        
        

    }
}