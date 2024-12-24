import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int M;
    private static boolean[][] map; // 지나갈 수 있는지 여부 기록

    private static Store[] stores;
    private static Person[] people;
    private static ArrayList<BaseCamp> baseCamps;

    private static int[][] moves = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    private static int simulation() {
        int time = 0;

        while (true) {
            time++;

            // ① 격자 안의 사람들 편의점으로 1칸 이동
            for (Person person : people) {
                person.goToStore();
            }

            // ② 편의점에 도착했는지 확인
            for (Person person : people) {
                person.checkArrived();
            }

            // ③ 격자 밖의 사람 베이스캠프로 이동
            if (time <= M) {
                people[time - 1].setDestStore();
                people[time - 1].goToBaseCamp();
            }

            // ④ 모든 사람이 편의점에 도착했으면 종료
            boolean flag = true;

            for (Person person : people) {
                if (!person.isArrived) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                break;
            }
        }

        return time;
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new boolean[N][N];

        baseCamps = new ArrayList<>();
        int num = 1;

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int grid = Integer.parseInt(tokens.nextToken());
                if (grid == 1) {
                    baseCamps.add(new BaseCamp(num++, i, j));
                }
            }
        }

        stores = new Store[M];
        people = new Person[M];

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(tokens.nextToken()) - 1;
            int c = Integer.parseInt(tokens.nextToken()) - 1;
            stores[i] = new Store(i + 1, r, c);
            people[i] = new Person(i + 1, -1, -1);
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        int answer = simulation();
        System.out.println(answer);
    }

    private static int bfs(int sr, int sc, int dr, int dc) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        dq.offer(new int[] {sr, sc, 0});

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int r = cur[0];
            int c = cur[1];
            int distance = cur[2];

            if (r == dr && c == dc) {
                return distance;
            }

            if (r < 0 || c < 0 || r >= N || c >= N) {
                continue;
            }

            if (map[r][c]) {
                continue;
            }

            if (visited[r][c]) {
                continue;
            }

            visited[r][c] = true;

            for (int[] move : moves) {
                int nr = r + move[0];
                int nc = c + move[1];

                dq.addLast(new int[] {nr, nc, distance + 1});
            }
        }

        return Integer.MAX_VALUE;
    }

    static class Store {
        int num;
        int r;
        int c;
        boolean disable;

        Store(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
            this.disable = false;
        }

        void disable() {
            this.disable = true;
            map[r][c] = true; // 맵에서 비활성화
        }

        // 편의점과 가장 가까운 베이스캠프의 번호를 반환
        BaseCamp getMinBaseCamp() {
            int minDistance = Integer.MAX_VALUE;
            BaseCamp minBaseCamp = null;

            for (BaseCamp baseCamp: baseCamps) {
                int distance = baseCamp.getDistance(this);
                if (minDistance > distance) {
                    minDistance = distance;
                    minBaseCamp = baseCamp;
                }
            }

            return minBaseCamp;
        }
    }

    static class Person {
        int num;
        int r;
        int c;
        boolean isDeparted;
        boolean isArrived;
        Store destStore;

        Person(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
            this.isDeparted = false;
            this.isArrived = false;
        }

        void setDestStore() {
            this.destStore = stores[num - 1];
        }

        boolean canMove() {
            return isDeparted && !isArrived;
        }

        void checkArrived() {
            if (!canMove()) return;

            if (this.r == this.destStore.r && this.c == this.destStore.c) {
                this.isArrived = true;
                this.destStore.disable();
            }
        }

        // 편의점 방향을 향해서 최단 거리로 1칸 이동
        void goToStore() {
            if (!canMove()) return;

            int minDistance = Integer.MAX_VALUE;
            int[] minMove = {0, 0};

            for (int[] move: moves) {
                int sr = r + move[0];
                int sc = c + move[1];
                int distance = bfs(sr, sc, destStore.r, destStore.c);

                if (minDistance > distance) {
                    minDistance = distance;
                    minMove = move;
                }
            }

            this.r += minMove[0];
            this.c += minMove[1];
        }

        void goToBaseCamp() {
            BaseCamp destBaseCamp = this.destStore.getMinBaseCamp();
            destBaseCamp.disable();

            this.r = destBaseCamp.r;
            this.c = destBaseCamp.c;
            this.isDeparted = true;
        }
    }

    static class BaseCamp {
        int num;
        int r;
        int c;
        boolean disable;

        BaseCamp(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
            this.disable = false;
        }

        void disable() {
            this.disable = true;
            map[r][c] = true; // 맵에서 비활성화
        }

        int getDistance(Store store) {
            if (disable) return Integer.MAX_VALUE;
            return bfs(r, c, store.r, store.c);
        }
    }
}
