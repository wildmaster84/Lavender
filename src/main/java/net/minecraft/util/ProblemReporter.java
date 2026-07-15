package net.minecraft.util;

public interface ProblemReporter {
    ProblemReporter forChild(PathElement pathElement);

    void report(Problem problem);

    interface PathElement {
    }

    final class Problem {
        private final String message;

        public Problem(String message) {
            this.message = message;
        }

        public String message() {
            return message;
        }
    }

    final class Collector implements ProblemReporter {
        private final java.util.List<Problem> problems = new java.util.ArrayList<>();

        @Override
        public ProblemReporter forChild(PathElement pathElement) {
            return this;
        }

        @Override
        public void report(Problem problem) {
            problems.add(problem);
        }

        public boolean isEmpty() {
            return problems.isEmpty();
        }

        public String getTreeReport() {
            StringBuilder sb = new StringBuilder();
            for (Problem p : problems) {
                sb.append(p.message()).append("\n");
            }
            return sb.toString();
        }
    }
}
