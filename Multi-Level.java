import java.util.*;

public class MultiLevelCache {

    private L1Cache L1 = new L1Cache(10000);

    private HashMap<String,String> L2 = new HashMap<>();

    private HashMap<String,String> database = new HashMap<>();

    private HashMap<String,Integer> accessCount = new HashMap<>();

    public String getVideo(String videoId){

        // L1 check
        if(L1.containsKey(videoId)){
            System.out.println("L1 Cache HIT");
            return L1.get(videoId);
        }

        // L2 check
        if(L2.containsKey(videoId)){
            System.out.println("L2 Cache HIT");

            String data = L2.get(videoId);

            L1.put(videoId,data); // promote to L1

            return data;
        }

        // L3 database
        System.out.println("Database HIT");

        String data = database.get(videoId);

        if(data != null){
            L2.put(videoId,data);
        }

        return data;
    }
}
