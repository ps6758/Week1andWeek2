import java.util.*;

class Transaction {

    int id;
    int amount;
    String merchant;
    String account;
    long time;

    Transaction(int id,int amount,String merchant,String account,long time){
        this.id=id;
        this.amount=amount;
        this.merchant=merchant;
        this.account=account;
        this.time=time;
    }
}

public class TwoSumTransactions {

    public static void findTwoSum(List<Transaction> list,int target){

        HashMap<Integer,Transaction> map = new HashMap<>();

        for(Transaction t : list){

            int complement = target - t.amount;

            if(map.containsKey(complement)){

                System.out.println(
                        "Match: " +
                        map.get(complement).id +
                        " + " + t.id
                );
            }

            map.put(t.amount,t);
        }
    }
}
