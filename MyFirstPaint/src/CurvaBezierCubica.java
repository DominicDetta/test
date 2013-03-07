import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;


public class CurvaBezierCubica extends CurvaBezier {

	
	public CurvaBezierCubica(ArrayList<Point2D> puntiControllo) {
		super(puntiControllo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Point2D getPointOfCurvaBezier(float t) {
		// TODO Auto-generated method stub
		float ax,bx,cx,ay,by,cy,tcubo,tquadrato;
		
		Point2D p0=super.getPuntiControllo().get(0);
		Point2D p1=super.getPuntiControllo().get(1);
		Point2D p2=super.getPuntiControllo().get(2);
		Point2D p3=super.getPuntiControllo().get(3);
		
		int x,y;
		
		if(this.isCurvaLineare()){
			CurvaBezier curva=new CurvaBezierLineare(super.getPuntiControllo());
			return curva.getPointOfCurvaBezier(t);
		}
		
		// Calcolo dei coefficienti polinomio di terzo grado
		ax=(float) (p3.getX()-3.0*p2.getX()+3.0*p1.getX()-p0.getX());
		bx=(float)(p2.getX()-2.0*p1.getX()+p0.getX());
		cx=(float)(p1.getX()-p0.getX());
		
		ay=(float) (p3.getY()-3.0*p2.getY()+3.0*p1.getY()-p0.getY());
		by=(float)(p2.getY()-2.0*p1.getY()+p0.getY());
		cy=(float)(p1.getY()-p0.getY());
		
		tquadrato=t*t;
		tcubo=tquadrato*t;
		
		x=(int)(tcubo*ax+3.0*tquadrato*bx+3.0*t*cx+p0.getX());
		y=(int)(tcubo*ay+3.0*tquadrato*by+3.0*t*cy+p0.getY());
		
		return new Point(x,y);
	}

}
