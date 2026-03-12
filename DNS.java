import java.util.*;

public class DNSCache {

    private HashMap<String, DNSEntry> cache = new HashMap<>();

    private int hits = 0;
    private int misses = 0;

    // Resolve domain
    public String resolve(String domain) {

        DNSEntry entry = cache.get(domain);

        // Cache HIT
        if (entry != null && !entry.isExpired()) {
            hits++;
            System.out.println("Cache HIT");
            return entry.ipAddress;
        }

        // Cache MISS
        misses++;
        System.out.println("Cache MISS → Querying upstream DNS");

        String ip = queryUpstreamDNS(domain);

        cache.put(domain, new DNSEntry(domain, ip, 300)); // TTL = 300 sec

        return ip;
    }

    // Simulate upstream DNS lookup
    private String queryUpstreamDNS(String domain) {

        // Example simulated IP
        return "172.217.14." + new Random().nextInt(255);
    }

    // Show cache statistics
    public void getCacheStats() {

        int total = hits + misses;

        double hitRate = (total == 0) ? 0 : ((double) hits / total) * 100;

        System.out.println("Hit Rate: " + hitRate + "%");
        System.out.println("Hits: " + hits);
        System.out.println("Misses: " + misses);
    }

    public static void main(String[] args) throws InterruptedException {

        DNSCache dns = new DNSCache();

        System.out.println(dns.resolve("google.com"));
        System.out.println(dns.resolve("google.com"));

        dns.getCacheStats();
    }
}
