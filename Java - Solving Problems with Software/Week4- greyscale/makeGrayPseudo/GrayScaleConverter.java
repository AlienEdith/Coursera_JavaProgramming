/**
 * Create a gray scale version of an image by setting all color components of each pixel to the same value.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    //I started with the image I wanted (inImage)
    public ImageResource makeGray(ImageResource inImage) {
        int inPixelsum = 0;
        int average = 0;
        
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel : outImage.pixels()){
            //look at the corresponding pixel in inImage
            Pixel inpixel = inImage.getPixel(pixel.getX(),pixel.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            inPixelsum = inpixel.getRed()+inpixel.getGreen()+inpixel.getBlue();
            //divide that sum by 3 (call it average)
            average = inPixelsum /3;
            //set pixel's red to average
            pixel.setRed(average);
            //set pixel's green to average
            pixel.setGreen(average);
            //set pixel's blue to average
            pixel.setBlue(average);
        }
        //outImage is your answer
        return outImage;
    }
    
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource ir = new ImageResource(f);
            ImageResource gray = makeGray(ir);
            gray.draw();
        }
    }
    
    public void doSave(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
           ImageResource inImage = new ImageResource(f);
           ImageResource gray = makeGray(inImage);
           gray.setFileName("grey-"+inImage.getFileName());
           gray.save();
            
        }
    }
    
    
    public void testGray() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
}
