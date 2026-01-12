class Solution {
    private static int[][] matrix; // matrix[i][j] = 로봇 개수
    
    private static int[][] points;
    private static int[][] routes;
    private static int routeLen; // 경로 길이 (거치는 포인트 개수)
    
    private static int robotCnt; // 로봇 개수
    private static Robot[] robots;
    
    private static int answer; // 충돌 횟수
    
    private static void countBumped() {
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                // 같은 자리에 2대 이상이면 충돌 처리
                if (matrix[i][j] > 1) answer++;
            }
        }
    }
    
    private static boolean isAllArrived() {
        int cnt = 0;
        for (int i = 0; i < robotCnt; i++) {
            if (robots[i].isArrived()) cnt++;
        }

        return cnt == robotCnt;
    }
    
    private static void simulation() {
        while (true) {
            // 도착한 로봇 좌표에서 제거
            for (int i = 0; i < robotCnt; i++) {
                Robot robot = robots[i];
                
                if (robot.isArrived() && robot.r != -1) {
                    matrix[robot.r][robot.c]--;
                    robot.r = -1;
                    robot.c = -1;
                }
            }
            
            // 부딪혔는지 확인
            countBumped();
            
            // 모두 도착했는지 확인
            if(isAllArrived()) break;
            
            // 로봇 한 칸씩 움직이기
            for (int i = 0; i < robotCnt; i++) {
                Robot robot = robots[i];
                
                if (robot.isArrived()) {
                    continue;
                }
                
                matrix[robot.r][robot.c]--;
                
                // 한 칸씩 움직이기
                robot.move();
                
                matrix[robot.r][robot.c]++;
            }
        }
    }
    
    public int solution(int[][] points, int[][] routes) {
        matrix = new int[101][101];
        
        this.points = points;
        this.routes = routes;
        
        robotCnt = routes.length;
        routeLen = routes[0].length;
        robots = new Robot[robotCnt];
        answer = 0;
        
        // 로봇 초기 위치 설정
        for (int i = 0; i < robotCnt; i++) {
            int pos = routes[i][0];
            int r = points[pos - 1][0];
            int c = points[pos - 1][1];
            
            robots[i] = new Robot(i, r, c);
        }
        
        
        simulation();
        
        return answer;
    }
    
    public class Robot {
        int num;
        int r;
        int c;
        int checkPoint; // 현재 목적지인 포인트 번호
        
        Robot(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
            this.checkPoint = 0;
            matrix[this.r][this.c]++;
        }
        
        void move() {
            if (isArrived()) return;
            
            int r = points[routes[num][checkPoint] - 1][0];
            int c = points[routes[num][checkPoint] - 1][1];
            
            // 현재 목적지인 포인트에 다다른 경우
            if (this.r == r && this.c == c) {
                this.checkPoint++;
                move();
            // 한 칸씩 움직이기 (행 우선)
            } else {
                 if (r < this.r) {
                     this.r--;
                 } else if (r > this.r) {
                     this.r++;
                 } else if (c < this.c) {
                     this.c--;
                 } else {
                     this.c++;
                 }
            }
        }
        
        boolean isArrived() {
            return checkPoint == routeLen;
        }
    }
}