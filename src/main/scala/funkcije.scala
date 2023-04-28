import java.io.File
import javax.imageio.ImageIO
import java.awt.image.BufferedImage

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
  
  val photo2 = mirror(photo1) 

  val photo3 = rotate(photo1)

  // sacuvaj
  ImageIO.write(photo2, "jpg", new File("mirror.jpg"))
  ImageIO.write(photo3, "jpg", new File("rotate.jpg"))
}

/*
@main def Pokreni = {
   test()
}
*/