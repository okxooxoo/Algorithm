import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int M;
    // 이름을 사전순으로 정렬하기 위해 해시맵이 아닌 트리맵 사용
    private static TreeMap<String, Person> people;
    private static ArrayList<Person> ancestors; // 시조
    private static ArrayDeque<Person> dq;

    // 시조 구하기
    private static void findAncestor() {
        for (Person person : people.values()) {
            if (person.inDegree == 0) {
                ancestors.add(person);
            }
        }
    }

    // 자식들의 이름 출력
    private static void findChildren() {
        for (Person person : ancestors) {
            dq.addLast(person);
        }

        while (!dq.isEmpty()) {
            Person parent = dq.removeFirst();
            for (Person child : parent.descendants) {
                child.inDegree--;

                if (child.inDegree == 0) {
                    dq.addLast(child);
                    parent.children.add(child); // 직계 자손으로 추가
                }
            }
            parent.sortChildren(); // 직계 자손 사전순 정렬
        }
    }

    private static void init() throws IOException {
        int N = Integer.parseInt(br.readLine());
        people = new TreeMap<>();
        ancestors = new ArrayList<>();
        dq = new ArrayDeque<>();

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String name = tokens.nextToken();
            people.put(name, new Person(i, name, 0));
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            Person child = people.get(tokens.nextToken());
            Person parent = people.get(tokens.nextToken());

            parent.descendants.add(child);
            child.inDegree++;
        }
    }

    private static void output() {
        sb.append(ancestors.size()).append("\n");
        for (Person person : ancestors) {
            sb.append(person.name).append(" ");
        }
        sb.append("\n");
        for (Person person : people.values()) {
            sb.append(person.name).append(" ");
            sb.append(person.children.size()).append(" ");
            for (Person child : person.children) {
                sb.append(child.name).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        init();
        findAncestor();
        findChildren();
        output();
    }

    static class Person {
        int id;
        String name;
        int inDegree;
        ArrayList<Person> children; // 직계 자손
        ArrayList<Person> descendants; // 자손

        Person(int id, String name, int inDegree) {
            this.id = id;
            this.name = name;
            this.inDegree = inDegree;
            this.children = new ArrayList<>();
            this.descendants = new ArrayList<>();
        }

        // 직계 자손 사전순 정렬
        void sortChildren() {
            children.sort((child1, child2) -> child1.name.compareTo(child2.name));
        }
    }
}