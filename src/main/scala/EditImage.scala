import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.image.Image
import scalafx.scene.image
import scalafx.scene.image.ImageView
import scalafx.scene.layout.BorderPane

import java.io.File
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import javafx.embed.swing.SwingFXUtils

class EditImage(val image: Image) {
    def myMethod2(): Unit = {
        println("Button clicked! Width:")
        println(image.width.value)
    }

    // ne radi :(
    def save() = {
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "jpg", new File("image.jpg"))
        println("Saved image!")
    }

    /*
    def myMethod(): Image = {
    //nadji sirinu w i visinu h og slike
    val w = img.width
    val h = img.height
    //napravi praznu sliku odgovarajuce sirine i visine
    val out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)
    //kopiraj piksel po piksel, u ogledalu
    for (x <- 0 until w)
        for (y <- 0 until h)
            out.setRGB(x, y, img.getRGB(w - x - 1, y) & 0xffffff)

    out
    }*/

    def mirror(img: BufferedImage): BufferedImage = {
        //nadji sirinu w i visinu h og slike
        val w = img.getWidth
        val h = img.getHeight
        //napravi praznu sliku odgovarajuce sirine i visine
        val out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)
        //kopiraj piksel po piksel, u ogledalu
        for (x <- 0 until w)
            for (y <- 0 until h)
                out.setRGB(x, y, img.getRGB(w - x - 1, y) & 0xffffff)

        out
    }

    def rotate(img: BufferedImage): BufferedImage = {
    val w = img.getWidth
    val h = img.getHeight

    val out = new BufferedImage(h, w, BufferedImage.TYPE_INT_RGB)

    for (x <- 0 until w){
        for (y <- 0 until h){
            out.setRGB(h - y - 1, x, img.getRGB(x, y) & 0xffffff)
        }
    }
    
    
    out
    }

    def test() = {
    // procitaj og sliku
    val photo1 = ImageIO.read(new File("samara.jpg"))
    
    //val photo2 = myMethod(photo1) 
    val photo3 = rotate(photo1)

    // sacuvaj
    //ImageIO.write(photo2, "jpg", new File("mirror.jpg"))
    ImageIO.write(photo3, "jpg", new File("rotate.jpg"))
    }

    /*
    @main def Pokreni = {
    test()
    }
    */
}

