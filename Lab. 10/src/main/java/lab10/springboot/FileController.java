package lab10.springboot;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FileController {
    public static int id=100;
    //Picture Container declaration
    private static Map<String, BufferedImage> pics=new HashMap<>();

    //Adding pictures to HashMap
    static String addImage(BufferedImage img){
        id++;
        String key=String.valueOf(id);
        pics.put(key,img);
        return key;
    }
    //Get image from Map
    static BufferedImage getImg(String key){
        return pics.get(key);
    }
    //Remove image from map
    static void removeImage(String key){
        pics.remove(key);
    }
    //checks pic's key in map
    static boolean containsKey(String key) {
        return pics.containsKey(key);
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World! :)";
    }

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public String uploadFile(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InputStream in = new ByteArrayInputStream(file.getBytes());
        BufferedImage img = ImageIO.read(in);
        String id = addImage(img);
        return "{id : "+id+", height: "+img.getHeight()+", width: "+img.getWidth()+"}";
    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable String id) throws Exception {
        BufferedImage img = getImg(id);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(img, "png", out);
        byte[] imageInByte = out.toByteArray();
        return imageInByte;
    }
    @GetMapping("/image/{id}/size")
    public String getSize(@PathVariable String id) {
        BufferedImage img = getImg(id);
        if(img != null){
            return "{height: " + img.getHeight() + ", width: " + img.getWidth() + "}";
        } else {
            return "404\n";
        }
    }

    @GetMapping("/image/{id}/histogram")
    public String getHistogram(@PathVariable String id) {
        BufferedImage img = getImg(id);
        if(img != null){
            return Histogram.calculate(img);
        } else {
            return "404\n";
        }
    }

    @DeleteMapping("/image/{id}")
    public ResponseEntity deleteById(@PathVariable String id) {
        if(!containsKey(id)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        BufferedImage img = getImg(id);
        removeImage(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/image/{id}/greyscale", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getGrayImage(@PathVariable String id) throws Exception {
        BufferedImage img = getImg(id);
        BufferedImage greyimg = PictureEdition.getGrayImage(img);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(greyimg, "png", out);
        byte[] imageInByte = out.toByteArray();

        return imageInByte;
    }

    @RequestMapping(value = "/image/{id}/crop/{start}/{stop}/{width}/{height}", method = RequestMethod.GET, produces =
            MediaType.IMAGE_PNG_VALUE)
    public byte[] getCroppedImage(@PathVariable String id, @PathVariable int start, @PathVariable int stop, @PathVariable int width, @PathVariable int height ) throws Exception {
        BufferedImage img = getImg(id);

        Rectangle rectangle = new Rectangle(start, stop, width, height);
        BufferedImage cropped = PictureEdition.cropImage(img, rectangle);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(cropped, "png", out);
        byte[] imageInByte = out.toByteArray();

        return imageInByte;
    }

    @RequestMapping(value = "/image/{id}/scale/{percentage}", method = RequestMethod.GET, produces =
            MediaType.IMAGE_PNG_VALUE)
    public byte[] getScaledImage(@PathVariable String id, @PathVariable double percentage) throws Exception {

        BufferedImage img = getImg(id);
        BufferedImage after = PictureEdition.scaleImage(img, percentage);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(after, "png", out);
        byte[] imageInByte = out.toByteArray();

        return imageInByte;
    }

    @RequestMapping(value = "/image/{id}/blur/{radius}", method = RequestMethod.GET, produces =
            MediaType.IMAGE_PNG_VALUE)
    public byte[] getBlurredImage(@PathVariable String id, @PathVariable int radius) throws Exception {
        BufferedImage img = getImg(id);
        byte[] imageInByte;
    if(radius<0 || radius>100){
        System.out.println("WRONG RADIUS (0-100)");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(img, "png", out);
        imageInByte = out.toByteArray();
    }
    else{
        BufferedImage after = PictureEdition.getBlurredImage(img,radius);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(after, "png", out);
        imageInByte = out.toByteArray();
    }
        return imageInByte;
    }
}