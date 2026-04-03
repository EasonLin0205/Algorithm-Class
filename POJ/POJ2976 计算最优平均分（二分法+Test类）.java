class Test implements Comparable<Test> {
    int correct;
    int total;
    double avg;

    Test() {
        this.correct = 0;
        this.total = 0;
        this.avg = 0;
    }

    static Test[] getTests(int len, Scanner input) {
        Test[] tests = new Test[len];
        for (int i = 0; i < len; i++) {
            tests[i] = new Test();
            tests[i].correct = input.nextInt();
        }
        for (int i = 0; i < len; i++) {
            tests[i].total = input.nextInt();
        }
        return tests;
    }

    @Override
    public int compareTo(Test t) {
        return Double.compare(this.correct - this.total * this.avg, t.correct - t.total * t.avg);
    }
}
1