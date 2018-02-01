/**
 * Create a gray scale version of an image by setting all color components of each pixel to the same value.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class BatchInversions {
    
    public ImageResource invertedImages(ImageResource inImage){
       int inPixelsum = 0;
       int outR = 0;
       int outG = 0;
       int outB = 0;
       ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
       //for each pixel in outImage
       for (Pixel pixel : outImage.pixels()){
           Pixel inpixel = inImage.getPixel(pixel.getX(),pixel.getY());
           pixel.setRed(255-inpixel.getRed());
           pixel.setGreen(255-inpixel.getGreen());
           pixel.setBlue(255-inpixel.getBlue());
        }
        //outImage is your answer
        return outImage;
    }
    
    public void selectAndConvertAndSave(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
           ImageResource ir = new ImageResource(f);
           ImageResource invert = invertedImages(ir);
           invert.setFileName("inverted-"+ir.getFileName());
           invert.save();
        }
    }
    
   

}
