import java.awt.geom.Point2D;
import java.util.ArrayList;


public abstract class CurvaBezier {
	
	/**Punti di controllo che definiscono la direzione della curva, il punto iniziale e quello finale appartengono a questa categoria **/
	private ArrayList<Point2D> puntiControllo; 
	private ArrayList<Point2D> puntiCurvaBezier;
	
	/**t è la descrizione del tragitto della curva di Bezier, t=0.25 -> un quarto del percorso della curva; 0<t<1**/
	//private float t;
	
	/**dt è il parametro che determina la precisione e la velocità con cui viene disegnata la curva,  **/
	private float dt;
	
	/**costante che definisce il numero di punti che vengono disegnati per costruire la curva**/
	private final int STEP_DEGREE=50;
	
	/**Formula generica della curva di Bezier: B(t)=E(n->i)*Pi*(1-t)^(n-1)*t^i **/
	public CurvaBezier(ArrayList<Point2D> puntiControllo) {
		this.puntiControllo=puntiControllo;
		this.puntiCurvaBezier=new ArrayList<Point2D>();
		dt=(float) (1.0/STEP_DEGREE);
	}

 
	
	/** Funzione che indica se la curva è lineare, ossia tutti i punti di controllo sono collineari**/
	protected boolean isCurvaLineare(){
		final Point2D p1=puntiControllo.get(0);
		final Point2D p2=puntiControllo.get(1);
		Point2D p;
		Line retta=new Line(p1,p2);
		
		for(int i=2;i<puntiControllo.size();i++){
			p=puntiControllo.get(i);
			if(!retta.isPointOfLine(p)) return false;
		}
		return true;
	}
	

	/**Dichiarazione della funzione che si occupa di calcolare i punti appartenenti alla curva di bezier**/
	public abstract Point2D getPointOfCurvaBezier(float t);
	
	/**Funzione che chiama la funzione sopracitata e salva i risultati nel vettore**/
	public void computeCurvaBezier(){	
		for (int i=0;i<STEP_DEGREE;i++){
			puntiCurvaBezier.add(getPointOfCurvaBezier((float)i*dt));
		}
	}
	
	/**Ritorna la curva di bezier computata**/
	public ArrayList<Point2D> getCurvaBezier(){
		return puntiCurvaBezier;
	}
	
	public ArrayList<Point2D> getPuntiControllo(){
		return puntiControllo;
	}
}
