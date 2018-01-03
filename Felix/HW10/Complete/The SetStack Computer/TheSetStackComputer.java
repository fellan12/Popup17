
public class TheSetStackComputer {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        SetStack setstack = new SetStack();

        int tests = io.getInt();

        for (int i = 0; i < tests; i++) {

            int numOfOperations = io.getInt();
            for (int j = 0; j < numOfOperations; j++) {

                switch (io.getWord()) {
                    case "PUSH":
                          setstack.push();
                          break;
                    case "DUP":
                          setstack.dup();
                          break;
                    case "UNION":
                          setstack.union();
                          break;
                    case "INTERSECT":
                          setstack.intersect();
                          break;
                    case "ADD":
                          setstack.add();
                          break;
                    default: throw new RuntimeException("No Operations Match!");
                }

                io.println(setstack.size());
            }

            io.println("***");
        }

        io.close();

    }
}
