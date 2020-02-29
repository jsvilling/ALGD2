package ch.fhnw.algd2.l2.railwaystation;


import ch.fhnw.algd2.l2.stack.Stack;

public class RailwayStation {

    private static final String A = "A";
    private static final String B = "B";

    private final Stack<String> s1 = new Stack<>();
    private final Stack<String> s2 = new Stack<>();
    private final Stack<String> s3 = new Stack<>();

    public RailwayStation() {
        fillWithRandomWagons(s1);
        fillWithRandomWagons(s2);
    }

    private void fillWithRandomWagons(Stack<String> rail) {
        int length = (int) (Math.random() * 10) + 1;
        for (int i = 0; i < length; i++) {
            String wagon = Math.random() >= 0.5 ? A : B;
            rail.push(wagon);
        }
    }

    public void organize() {
        moveAll(s1, s3);
        moveAll(s2, s3);
        while (! s3.isEmpty()) {
            String wagon = s3.pop();
            if (A.equals(wagon)) {
                s1.push(wagon);
            } else {
                s2.push(wagon);
            }
        }
    }

    private void moveAll(Stack<String> from, Stack<String> to) {
        while (! from.isEmpty()) {
            to.push(from.pop());
        }
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Rail 3: ");
        sb.append(s3.toString());
        sb.append("\n");
        sb.append("Rail 2: ");
        sb.append(s2.toString());
        sb.append("\n");
        sb.append("Rail 1: ");
        sb.append(s1.toString());
        sb.append("\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        RailwayStation railwayStation = new RailwayStation();

        System.out.println("RailwayStation");
        System.out.println("Before: \n");
        System.out.println(railwayStation.toString());

        railwayStation.organize();
        System.out.println("After: \n");
        System.out.println(railwayStation.toString());
    }

}
