import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
    
    public int getNumPoints (Shape s) {
        // Put code here
        int PointsNum = 0;
        for (Point cntPoint : s.getPoints()){
        PointsNum++;
        }
        return PointsNum;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double AverageLength,totalPerim;
        totalPerim = getPerimeter(s);
        AverageLength = totalPerim / getNumPoints(s);
        return AverageLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        // Start with LL = 0
        double LL = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
                if (LL < currDist){
                LL = currDist;
                }
                else LL=LL;
            prevPt = currPt;
        }
        return LL;
    }

    public double getLargestX(Shape s) {
        // Put code here
        // Start with LL = 0
        Point LastPt = s.getLastPoint();
        double LX = LastPt.getX();
        for (Point currPt : s.getPoints()) {
            double currX = currPt.getX();
                if (LX < currX){
                LX = currX;
                }
                else LX=LX;
        }
        return LX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double LP=0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currP=getPerimeter(s);
            if (LP<currP){
            LP=currP;
            }
            else LP=LP;
        }
        return LP;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        double LP=0.0;
        File temp = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currP=getPerimeter(s);
            if (LP<currP){
            temp = f;
            LP = currP;
            }
            else temp = temp;
        }
        return temp.getName();
    }

    public void testPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
        FileResource fr = new FileResource(f);
        Shape s = new Shape(fr);
        System.out.println("The File = " + f.getName());
        int num=getNumPoints(s);
        System.out.println("The number of points of = " + num);
        double perimeter=getPerimeter(s);
        System.out.println("The Perimeter = " + perimeter);
        double averagelength=getAverageLength(s);
        System.out.println("The Average Length = " + averagelength);
        double LS=getLargestSide(s);
        System.out.println("The Largest Side = " + LS);
        double LX=getLargestX(s);
        System.out.println("The Largest X = " + LX);
        }
        double LP=getLargestPerimeterMultipleFiles();
        System.out.println("The Largest Perimeter = " + LP);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String name = getFileWithLargestPerimeter();
        System.out.println("The File with Largest Perimeter = " + name);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
