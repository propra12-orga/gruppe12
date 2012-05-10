package spielfeld;

public class Objekte {
  

  //Dekleration von Variabeln
  protected int      xPosition;
  protected int      yPosition;
  protected int      width;
  protected int      height;
  protected String   pic;
  
  protected String   type;
  
  // Konstruktor
  
  public Objekte(int xPos ,int yPos,int widht, int height, String pic, String type)
  {
    xPosition        = xPos;
    yPosition        = yPos;
    this.width       = widht;
    this.height      = height;
    this.pic         = pic ;
    this.type        = type ;
  }
  
  //Methode zum bewegen von Objekten. Muss überschrieben werden
  public void move(){
  }
  
  //Löschen von Objekten
  public void kill(){
  }
  
  
  // Set- und Get-Methoden
  
  //Set:
  
  public void setXPosition(int pos)
  {
    xPosition = pos;
  }
  public void setYPosition(int pos)
  {
    yPosition = pos;
  }
  public void setWidth(int width)
  {
    this.width = width;
  }
  public void setHeight(int height)
  {
    this.height = height;
  }
  


  //Get:
  public double getXPosition()
  {
    return xPosition;
  }
  public double getYPosition()
  {
    return yPosition;
  }
  public int getWidth()
  {
    return width;
  }
  public int getHeight()
  {
    return height;
  }
  public String getPic()
  {
    return pic;
  }
  public String getType()
  {
    return type;
  }


  
  
  
}
