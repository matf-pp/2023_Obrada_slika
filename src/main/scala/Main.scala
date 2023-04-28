import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.{HBox,VBox}
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color._
import scalafx.scene.paint._
import scalafx.scene.text.Text
import scalafx.scene.control.{Button, TextField}

import java.io.FileInputStream
import scalafx.scene.image.{Image, ImageView, PixelReader}

object Main extends JFXApp3 :
  override def start(): Unit =

    // ucitavanje slike lokalno
    val image = new Image(new FileInputStream("/home/irina/Desktop/image.jpeg"))
    val imageView = new ImageView(image)

    // url slika
    val url = "https://images.squarespace-cdn.com/content/v1/54e7a1a6e4b08db9da801ded/1600464166638-5C0HPLJ9HT1TZBEHWVJR/Screen+Shot+2020-09-17+at+2.44.15+PM.png"
    val image2 = Image(url)
    val editImage = new EditImage(image2)
    val imageView2 = new ImageView(image2)
    imageView2.fitWidth = 200
    imageView2.fitHeight = 200 * image2.height.value / image2.width.value

    // rgb (0,0) piksela
    val pixelReader = image.pixelReader.get
    val color = pixelReader.getColor(0,0)
    val red = (color.red * 255).toInt
    val green = (color.green * 255).toInt
    val blue = (color.blue * 255).toInt
    println(s"r:$red, g:$green, b:$blue")


    stage = new JFXApp3.PrimaryStage:
      width = 800
      height = 500
      title = "Obrada slika"


      val buttonLoadFromLibrary = new Button {
        text = "Load from library"
        layoutX = 50
        layoutY = 100
        
        style = "-fx-font: normal bold 10pt sans-serif; -fx-text-fill: gray; -fx-background-color: null; -fx-border-color: null;"
        onMouseEntered = _ => style = "-fx-font: normal bold 10pt sans-serif; -fx-text-fill: black; -fx-background-color: null; -fx-border-color: null;"
        onMouseExited  = _ => style = "-fx-font: normal bold 10pt sans-serif; -fx-text-fill: gray; -fx-background-color: null; -fx-border-color: null;"
        
        onAction = _ => {
          val url = textField.text()
          val image2 = Image(new FileInputStream("/home/irina/Desktop/image.jpeg"))
          imageView2.image = image2
          imageView2.fitWidth = 200
          imageView2.fitHeight = 200 * image2.height.value / image2.width.value
          editImage.myMethod()
        }
      }

      val textField=new TextField {
          layoutX = 50
          layoutY = 50
          prefWidth = 50
        }

      val buttonLoadURL = new Button {
        text = "Load using URL:"
        layoutX = 50
        layoutY = 100
        
        style = "-fx-font: normal bold 10pt sans-serif; -fx-text-fill: gray; -fx-background-color: null; -fx-border-color: null;"
        onMouseEntered = _ => style = "-fx-font: normal bold 10pt sans-serif; -fx-text-fill: black; -fx-background-color: null; -fx-border-color: null;"
        onMouseExited  = _ => style = "-fx-font: normal bold 10pt sans-serif; -fx-text-fill: gray; -fx-background-color: null; -fx-border-color: null;"
        
        onAction = _ => {
          val url = textField.text()
          val image2 = Image(url)
          // println(url)
          // npr. https://img.freepik.com/free-photo/red-white-cat-i-white-studio_155003-13189.jpg?w=360
          imageView2.image = image2
          imageView2.fitWidth = 200
          imageView2.fitHeight = 200 * image2.height.value / image2.width.value
        }
      }

      val vBoxLeft = new VBox {
            style = "-fx-background-color: #FFFFFF;"
            padding = Insets(10, 10, 10, 10)
            prefWidth = 200

            children = Seq(
              buttonLoadFromLibrary,
              buttonLoadURL,
              textField
            )
        }

        
      scene = new Scene :
        fill = Color.rgb(210, 210, 210)

        content = new HBox {
            padding = Insets(10, 10, 10, 10)
            children = Seq(
              vBoxLeft,
              imageView2
          )
        }

/*
@main def hello: Unit =
  println("Hello world!!!!!!")
  println(msg)
def msg = "I was compiled by Scala 3. :)"
*/