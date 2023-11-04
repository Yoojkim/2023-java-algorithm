import java.util.*;
import java.io.*;

enum Part {
    LEFTARM(0, -1), RIGHTARM(0, 1), WAIST(1, 0), LEG(1, 0);

    int i;
    int j;

    Part(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

class Point {
    int i;
    int j;

    public Point(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

class Main {
    static char[][] fields;
    static int n;
    static int leftArm;
    static int rightArm;
    static int waist;
    static int leftLeg;
    static int rightLeg;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        fields = new char[n][n];

        for (int i = 0; i < n; i++) {
            char[] fieldsForLine = br.readLine().toCharArray();

            for (int j = 0; j < n; j++) {
                fields[i][j] = fieldsForLine[j];
            }
        }

        Point head = getHeadPoint();
        Point heart = new Point(head.i + 1, head.j);

        leftArm=setBodyPartLength(heart.i, heart.j - 1, Part.LEFTARM, 0);
        rightArm=setBodyPartLength(heart.i, heart.j + 1, Part.RIGHTARM, 0);
        waist=setBodyPartLength(heart.i + 1, heart.j, Part.WAIST, 0);
        leftLeg=setBodyPartLength(heart.i + waist + 1, heart.j - 1, Part.LEG, 0);
        rightLeg=setBodyPartLength(heart.i + waist + 1, heart.j + 1, Part.LEG, 0);

        StringBuilder sb = new StringBuilder();
        sb.append(heart.i+1).append(" ").append(heart.j+1).append("\n");
        sb.append(leftArm).append(" ").append(rightArm).append(" ").append(waist).append(" ").append(leftLeg).append(" ").append(rightLeg).append("\n");
        System.out.print(sb);
    }

    private static Point getHeadPoint() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (fields[i][j] == '*')
                    return new Point(i, j);
            }
        }

        return null;
    }

    private static int setBodyPartLength(int i, int j, Part part, int length) {
        if (i==-1 || j==-1 || i == n || j == n || fields[i][j]!='*') {
            return length;
        }

        return setBodyPartLength(i + part.i, j + part.j, part, length+1);
    }
}