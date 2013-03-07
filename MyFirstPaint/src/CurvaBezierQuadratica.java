import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;


public class CurvaBezierQuadratica extends CurvaBezier {

	
	/**Formula curva bezier quadratica: B(t)=((1-t)^2)*P0 + 2t(1-t)*P1 +(t^2)*P2 **/
	public CurvaBezierQuadratica(ArrayList<Point2D> puntiControllo) {
		super(puntiControllo);
		
	}

	@Override
	public Point2D getPointOfCurvaBezier(float t) {
		// TODO Auto-generated method stub
		float ax,bx,ay,by, tquadrato;
		Point2D p0=super.getPuntiControllo().get(0);
		Point2D p1=super.getPuntiControllo().get(1);
		Point2D p2=super.getPuntiControllo().get(2);
		int x,y;
	
		if(this.isCurvaLineare()){
			CurvaBezier curva=new CurvaBezierLineare(super.getPuntiControllo());
			return curva.getPointOfCurvaBezier(t);
		}
		
		// Calcolo dei coefficienti un polinomio di secondo grado
		ax=(float) (p0.getX()-2.0*p1.getX()+p2.getX());
		bx=(float)(p1.getX()-p0.getX());
		
		ay=(float) (p0.getY()-2.0*p1.getY()+p2.getY());
		by=(float)(p1.getY()-p0.getY());
		
		tquadrato=t*t;
		
		x=(int) (tquadrato*ax+2.0*t*bx + p0.getX());
		y=(int) (tquadrato*ay+2.0*t*by +p0.getY());
		
		return new Point(x,y);
	}

}
