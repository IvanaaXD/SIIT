package analyzer;

public class GradeAnalyzer {

    public double calculateAverageGrade(int[] grades) {
        if (grades == null || grades.length == 0) {
            throw new IllegalArgumentException("Grades array is empty or null.");
        }
        int sum = 0;
        for (int grade : grades) {
            if (grade < 6 || grade > 10) {
                throw new IllegalArgumentException("Invalid grade value: " + grade);
            }
            sum += grade;
        }
        return (double) sum / grades.length;
    }

    public boolean isPassingGrade(int grade) {
        if (grade < 5 || grade > 10) {
            throw new IllegalArgumentException("Invalid grade value: " + grade);
        }
        return grade >= 6;
    }
}

