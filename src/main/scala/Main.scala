import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.{HBox,VBox}
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color._
import scalafx.scene.paint._
import scalafx.scene.text.Text

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
    val imageView2 = new ImageView(image2)
    imageView2.fitWidth = 200
    imageView2.fitHeight = 200 * image.height.value / image.width.value

    // rgb (0,0) piksela
    val pixelReader = image.pixelReader.get
    val color = pixelReader.getColor(0,0)
    val red = (color.red * 255).toInt
    val green = (color.green * 255).toInt
    val blue = (color.blue * 255).toInt
    println(s"r:$red, g:$green, b:$blue")


    stage = new JFXApp3.PrimaryStage {
      title = "Aplikacija"
      scene = new Scene {
        fill = Color.rgb(38, 38, 38)

        val hBox1 = new HBox {
            padding = Insets(50, 80, 50, 80)
            children = Seq(
              new Text {
                text = "Scala"
                style = "-fx-font: normal bold 60pt sans-serif"
                fill = new LinearGradient(
                  endX = 0,
                  stops = Stops(Red, DarkRed))
              },
              new Text {
                text = "FX"
                style = "-fx-font: italic bold 60pt sans-serif"
                fill = new LinearGradient(
                  endX = 0,
                  stops = Stops(White, DarkGray)
                )
              }
            )
        }

        content = new VBox {
            padding = Insets(50, 80, 50, 80)
            children = Seq(
              hBox1,
              imageView,
              imageView2
          )
        }
      }
    }

/*
@main def hello: Unit =
  println("Hello world!!!!!!")
  println(msg)
def msg = "I was compiled by Scala 3. :)"
*/