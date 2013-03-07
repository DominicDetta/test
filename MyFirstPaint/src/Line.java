import java.awt.geom.Point2D;


/** La retta è definita dalla seguente formula esplicita: y=a*x+b **/
public class Line {

	/**La pendenza è denotata con a**/
	private float pendenza;
	
	/**L'ordinata all'origine è denotata con b**/
	private float ordinataOrigine;
	
	public Line(Point2D p1, Point2D p2) {
		// TODO Auto-generated constructor stub
		
		//risolvo un sistema 2x2
		this.pendenza=((float)(p1.getY()-p2.getY()))/(float)(p1.getX()-p2.getX());
		this.ordinataOrigine=(float)p2.getY()-(float)p2.getX()*pendenza;
	}
	
	public float getPendenza(){
		return pendenza;
	}
	
	public float getOrdinataOrigine(){
		return ordinataOrigine;
	}
	
	public boolean isPointOfLine(Point2D p){
		
		return p.getY()==(p.getX()*getPendenza() + getOrdinataOrigine());
	}
}
