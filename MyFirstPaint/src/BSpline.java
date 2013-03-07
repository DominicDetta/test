
/**B-spline: curva creata connettendo un numero determinato di curve di Bezier in modo che 
 * 
 * i punti di connessione sono continui
 * i punti di controllo influiscono localmente e non su tutta la curva
 * 
 * 
 * Soluzione: suddividere il dominio di definizione della B-spline in sottointervalli in punti specifici chiamati knots
 * Ogni sottointervallo possiede una curva di Bezier con i suoi punti di controllo
 * 
 * Parametri: 
 * p - grado della curva
 * i - contatore punti curva
 * Ni,p - i -esima B-spline di grado p 
 * */
public class BSpline {

	private float knots[];
	
	public BSpline() {
		// TODO Auto-generated constructor stub
		knots=new float[9];
	}
}
