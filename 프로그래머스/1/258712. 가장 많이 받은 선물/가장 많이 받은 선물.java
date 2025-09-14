import java.util.*;

class Solution {
    private static HashMap<String, Person> people;
    
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        people = new HashMap<>();
        
        for (int i = 0; i < friends.length; i++) {
            Person person = new Person(friends[i]);
            people.put(friends[i], person);
        }
        
        for (int i = 0; i < gifts.length; i++) {
            String[] two = gifts[i].split(" ");
            Person giver = people.get(two[0]);
            Person receiver = people.get(two[1]);
            
            // 선물 지수 기록
            giver.giveGift(two[1]);
            receiver.receiveGift(two[0]);
        }
        
        // 가장 많은 선물을 받는 친구가 받을 선물의 수 계산
        int giftCount;
        
        for (int i = 0; i < friends.length; i++) {
            Person person = people.get(friends[i]);
            giftCount = 0;
            
            for (int j = 0; j < friends.length; j++) {
                if (i == j) continue;
                
                int give = person.giveRecord.getOrDefault(friends[j], 0); // 준 횟수
                int receive = person.receiveRecord.getOrDefault(friends[j], 0); // 받은 횟수
                
                if (give > receive) {
                    giftCount++;
                } else if (give == receive){
                    // 선물 지수로 비교
                    if (person.giftScore > people.get(friends[j]).giftScore) {
                        giftCount++;
                    }
                }
            }
            
            answer = Math.max(answer, giftCount);
        }
        
        return answer;
    }
    
    static class Person {
        String name;
        int giftScore;
        
        HashMap<String, Integer> giveRecord;
        HashMap<String, Integer> receiveRecord;
        
        Person(String name) {
            this.name = name;
            this.giveRecord = new HashMap<>();
            this.receiveRecord = new HashMap<>();
        }
        
        void giveGift(String name) {
            giveRecord.put(name, giveRecord.getOrDefault(name, 0) + 1);
            giftScore++;
        }
        
        void receiveGift(String name) {
            receiveRecord.put(name, receiveRecord.getOrDefault(name, 0) + 1);
            giftScore--;
        }
    }
}