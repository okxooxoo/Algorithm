import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        Guest[] guests = new Guest[book_time.length];
        PriorityQueue<Guest> pq = new PriorityQueue<>();
        
        int idx = 0;    
        for (String[] time : book_time) {
            int startHours = Integer.parseInt(time[0].split(":")[0]);
            int startMinutes = Integer.parseInt(time[0].split(":")[1]);
            
            int endHours = Integer.parseInt(time[1].split(":")[0]);
            int endMinutes = Integer.parseInt(time[1].split(":")[1]);
            
            Guest guest = new Guest(startHours, startMinutes, endHours, endMinutes);
            guests[idx++] = guest;
        }
        
        Arrays.sort(guests, new Comparator<Guest>() {
            @Override
            public int compare(Guest a, Guest b) {
                if (a.getStartTimes() == b.getStartTimes()) {
                    return Integer.compare(a.getEndTimes(), b.getEndTimes());
                }
                
                return Integer.compare(a.getStartTimes(), b.getStartTimes());
            }
        });
        
        int answer = 0;
        
        for (Guest guest : guests) {
            while (!pq.isEmpty()) {
                if (pq.peek().getEndTimes() + 10 <= guest.getStartTimes()) {
                    pq.poll();
                } else {
                    break;
                }
            }
            
            pq.add(guest);
            answer = Math.max(pq.size(), answer);
        }
        
        return answer;
    }
    
    static class Guest implements Comparable<Guest> {
        int startHours;
        int startMinutes;
        int endHours;
        int endMinutes;
        
        Guest(int startHours, int startMinutes, int endHours, int endMinutes) {
            this.startHours = startHours;
            this.startMinutes = startMinutes;
            this.endHours = endHours;
            this.endMinutes = endMinutes;
        }
        
        public int getStartTimes() {
            return startHours * 60 + startMinutes;
        }
        
        public int getEndTimes() {
            return endHours * 60 + endMinutes;
        }
        
        @Override
        public int compareTo(Guest o) {
            if (this.getEndTimes() == o.getEndTimes()) {
                return Integer.compare(this.getStartTimes(), o.getStartTimes());
            }
            
            return Integer.compare(this.getEndTimes(), o.getEndTimes());
        }
    }
}