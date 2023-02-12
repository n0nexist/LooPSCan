package www.n0nexist.gq;

import java.net.InetAddress;

public class DNSLookup {

    public static String Lookup(String ip) {
        try {
            InetAddress address = InetAddress.getByName(ip);
            return address.getCanonicalHostName();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public static String getDNS(String domain) {
        try {
            InetAddress[] addresses = InetAddress.getAllByName(domain);
            StringBuilder response = new StringBuilder();
            for (InetAddress address : addresses) {
                response.append(address.getHostAddress()).append("\n");
            }
            return response.toString().strip();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
