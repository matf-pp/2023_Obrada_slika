import scalafx.application.JFXApp3
import scalafx.geometry.{Insets,Pos}

import scalafx.scene.Scene
import scalafx.scene.layout.{HBox,VBox}
import scalafx.scene.layout._ //
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color._
import scalafx.scene.paint._
import scalafx.scene.text.Text
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.image.{Image, ImageView, PixelReader}
import scalafx.scene.Cursor

object Main extends JFXApp3 :
  override def start(): Unit =

    // ucitavanje slike lokalno:
    val image = new Image("file:/home/irina/Desktop/image.jpeg")
    val imageView = new ImageView(image)

    // url slika:
    val url = "https://images.squarespace-cdn.com/content/v1/54e7a1a6e4b08db9da801ded/1600464166638-5C0HPLJ9HT1TZBEHWVJR/Screen+Shot+2020-09-17+at+2.44.15+PM.png"
    val image2 = Image(url)
    val editImage = new EditImage(image2)
    val imageView2 = new ImageView(image2)
    // namestanje dimenzija:
    imageView2.fitWidth = 200
    imageView2.fitHeight = 200 * image2.height.value / image2.width.value

    /*
    // rgb (0,0) piksela
    val pixelReader = image.pixelReader.get
    val color = pixelReader.getColor(0,0)
    val red = (color.red * 255).toInt
    val green = (color.green * 255).toInt
    val blue = (color.blue * 255).toInt
    println(s"r:$red, g:$green, b:$blue")
    */


    stage = new JFXApp3.PrimaryStage:
      width = 800
      height = 500
      title = "Obrada slika"

      // SADRZAJ ------------------------------------------------

      // stil svih dugmica
      def setButtonStyle(button: Button): Unit = {
        button.layoutX = 50
        button.layoutY = 100

        button.style = "-fx-font: normal bold 8pt sans-serif; -fx-text-fill: gray; -fx-background-color: null; -fx-border-color: null;"
        button.onMouseEntered = _ => {
          button.cursor = Cursor.HAND
          button.style = "-fx-font: normal bold 8pt sans-serif; -fx-text-fill: black; -fx-background-color: null; -fx-border-color: null;"
        }
        button.onMouseExited  = _ => {
          button.cursor = Cursor.DEFAULT
          button.style = "-fx-font: normal bold 8pt sans-serif; -fx-text-fill: gray; -fx-background-color: null; -fx-border-color: null;"
        }
      }


      // dugme za ucitavanje slike iz baze, treba je napraviti!!!
      val buttonLoadFromLibrary = new Button {
        text = "Load from library"
        onAction = _ => {
          val image2 = Image("file:/home/irina/Desktop/image.jpeg")
          imageView2.image = image2
          // resetovanje dimenzija slike
          imageView2.fitWidth = 200
          imageView2.fitHeight = 200 * image2.height.value / image2.width.value
        }
      }

      // text field za pisanje URL-a
      val textFieldURL=new TextField {
          layoutX = 50
          layoutY = 50
          prefWidth = 50
        }

      // dugme za ucitavanje slike pomocu URL iz text field-a
      val buttonLoadURL = new Button {
        text = "Load using URL:"
        onAction = _ => {
          val url = textFieldURL.text()
          val image2 = Image(url)
          // npr: https://img.freepik.com/free-photo/red-white-cat-i-white-studio_155003-13189.jpg?w=360
          imageView2.image = image2
          // resetovanje dimenzija slike
          imageView2.fitWidth = 200
          imageView2.fitHeight = 200 * image2.height.value / image2.width.value
        }
      }

      // DUGMICI ZA SLIKE:
      // dugme za izvrsavanje neke funkcije
      val buttonAction1 = new Button {
        text = "Do function"        
        onAction = _ => {
          editImage.myMethod2()
        }
      }

      // dugme za cuvanje slike
      val buttonSave = new Button {
        text = "Save image"        
        onAction = _ => {
          editImage.save()
        }
      }

      // SADRZAJ ------------------------------------------------

      setButtonStyle(buttonLoadFromLibrary)
      setButtonStyle(buttonLoadURL)
      setButtonStyle(buttonAction1)
      setButtonStyle(buttonSave)

      val vBoxLeft = new VBox {
          style = "-fx-background-color: #F0F0F0;"
          padding = Insets(5, 5, 5, 5)
          prefWidth = 150
          prefHeight = 440

          children = Seq(
            buttonLoadFromLibrary,
            buttonLoadURL,
            textFieldURL,
            buttonSave,
            buttonAction1
          )
      }
        
      scene = new Scene :
        fill = Color.rgb(210, 210, 210)

        content = new HBox {
            padding = Insets(10, 10, 10, 10)
            children = Seq(
              vBoxLeft,
              new HBox{
                prefWidth = 630
                prefHeight = 440
                alignment = Pos.Center
                children = Seq(imageView2)
              }
          )
        }
