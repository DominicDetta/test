import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;


public class CurvaBezierLineare extends CurvaBezier {

	public CurvaBezierLineare(ArrayList<Point2D> puntiControllo) {
		super(puntiControllo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Point2D getPointOfCurvaBezier(float t) {
		float ax,ay;
		
		Point2D p0=super.getPuntiControllo().get(0); //punto iniziale
		Point2D p1=super.getPuntiControllo().get(super.getPuntiControllo().size()-1); //punto finale
		
		int x,y;
		
		//calcolo dei coefficienti polinomio lineare
		ax=(float) (p1.getX()-p0.getX());
		ay=(float) (p1.getY()-p0.getY());
		
		x=(int)(t*ax+p0.getX());
		y=(int)(t*ay+p0.getY());
		
		return new Point(x,y);
	}

}
