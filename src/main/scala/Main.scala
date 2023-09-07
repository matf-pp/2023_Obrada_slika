import scalafx.application.JFXApp3
import scalafx.geometry.{Insets,Pos}

import scalafx.scene.Scene
import scalafx.scene.layout.{HBox,VBox}
import scalafx.scene.layout._ //
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color._
import scalafx.scene.paint._
import scalafx.scene.text.Text
import scalafx.scene.control.{Button, TextField, Slider, Label}
import scalafx.scene.image.{Image, ImageView, PixelReader, WritableImage}
import scalafx.scene.Cursor
import scala.math._
//FILE CHOOSER:
import scalafx.stage.FileChooser

import javafx.embed.swing.SwingFXUtils
import java.io.File
import java.net.URL
import javax.imageio.ImageIO
import java.awt.image.BufferedImage

object Main extends JFXApp3 :
  override def start(): Unit =
        
    // ucitavanje slike pomocu url:
    val url = "https://images.squarespace-cdn.com/content/v1/54e7a1a6e4b08db9da801ded/1600464166638-5C0HPLJ9HT1TZBEHWVJR/Screen+Shot+2020-09-17+at+2.44.15+PM.png"
    var image = ImageIO.read(new URL(url))

    val writableImage = new WritableImage(image.getWidth, image.getHeight)
    val pixelWriter = writableImage.pixelWriter
    for (x <- 0 until image.getWidth; y <- 0 until image.getHeight) {
      val argb = image.getRGB(x, y)
      pixelWriter.setArgb(x, y, argb)
    }

    val imageView = new ImageView(writableImage)
    var editImage = new EditImage(image)
    var nbImage = editImage

    // namestanje dimenzija:
    var imageViewWidth = min(600, image.getWidth())
    var imageViewHeight = min(350, imageViewWidth * image.getHeight() / image.getWidth())
    imageViewWidth = imageViewHeight * image.getWidth() / image.getHeight()
    imageView.fitWidth = imageViewWidth
    imageView.fitHeight = imageViewHeight


    // promena slike nakon izvrsene funkcije
    def updateImage(image: BufferedImage, width : Int = imageViewWidth, height : Int = imageViewHeight) = {
      editImage = new EditImage(image)
      val newImage = SwingFXUtils.toFXImage(image, null)
      imageView.setImage(newImage)

      // namestanje dimenzija:
      imageViewWidth = min(600,width)
      imageViewHeight = min(350, imageViewWidth * height / width)
      imageViewWidth = imageViewHeight * width / height

      imageView.fitWidth = imageViewWidth
      imageView.fitHeight = imageViewHeight
    }

    stage = new JFXApp3.PrimaryStage:
      width = 800
      height = 500
      title = "Obrada slika"

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


      // SADRZAJ ------------------------------------------------

      val buttonSave = new Button {
        text = "Save image"        
        onAction = _ => {
          editImage.save()
        }
      }

      val buttonUndo = new Button { 
        val imgUndo = Image("https://img.icons8.com/?size=512&id=70793&format=png");
        val undo = new ImageView(imgUndo)
        undo.setFitHeight(15)
        undo.setFitWidth(15)
        style = "-fx-background-color: null; -fx-border-color: null;"
        graphic = undo

        onMouseEntered = _ => {cursor = Cursor.HAND}
        onMouseExited  = _ => cursor = Cursor.DEFAULT

        onAction = _ => {
          println("undo")
        }
      }

      val buttonRedo = new Button { 
        val imgRedo = Image("https://img.icons8.com/?size=512&id=70816&format=png");
        val redo = new ImageView(imgRedo)
        redo.setFitHeight(15)
        redo.setFitWidth(15)
        style = "-fx-background-color: null; -fx-border-color: null;"
        graphic = redo

        onMouseEntered = _ => {cursor = Cursor.HAND}
        onMouseExited  = _ => cursor = Cursor.DEFAULT

        onAction = _ => {
          println("redo")
        }
      }

      // dugme za ucitavanje slike iz baze
      val buttonLoadFromLibrary = new Button {
        text = "Load from library"
        onAction = _ => {
          val FileChooser = new FileChooser
          val selectedFile = FileChooser.showOpenDialog(stage)
          image = ImageIO.read(selectedFile)
          updateImage(image, image.getWidth(), image.getHeight())
          nbImage = editImage
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
          image = ImageIO.read(new URL(url))
          updateImage(image, image.getWidth(), image.getHeight())
        }
      }


      // DUGMICI ZA SLIKE:
      
      val buttonMirror = new Button {
        text = "Mirror"        
        onAction = _ => {
          updateImage(editImage.mirror())
          nbImage = editImage
        }
      }

      val buttonRotate = new Button {
        text = "Rotate"        
        onAction = _ => {
          updateImage(editImage.rotate(), imageViewHeight, imageViewWidth)
          nbImage = editImage
        }
      }
      
      val labelBrightness = new Label("Brightness:"){
        layoutX = 50
        layoutY = 100
        style = "-fx-font: normal bold 8pt sans-serif; -fx-text-fill: gray; -fx-background-color: null; -fx-border-color: null;"
      }

      var prevValue = 1.0
      val sliderBrightness = new Slider(0.5,1.5,1){
        onMouseReleased = _ => {
          editImage = nbImage
          updateImage(editImage.brightness(value.value))
          prevValue = value.value
        }
      }

      // SADRZAJ ------------------------------------------------

      setButtonStyle(buttonLoadFromLibrary)
      setButtonStyle(buttonLoadURL)
      setButtonStyle(buttonMirror)
      setButtonStyle(buttonRotate)
      setButtonStyle(buttonSave)

      val vBoxLeft = new VBox {
          style = "-fx-background-color: #F0F0F0;"
          padding = Insets(5, 5, 5, 5)
          prefWidth = 150
          prefHeight = 440

          children = Seq(
            new HBox(buttonUndo,buttonRedo),
            buttonSave,
            buttonLoadFromLibrary,
            buttonLoadURL, textFieldURL,
            buttonMirror,
            buttonRotate,
            labelBrightness, sliderBrightness
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
                children = Seq(imageView)
              }
          )
        }
