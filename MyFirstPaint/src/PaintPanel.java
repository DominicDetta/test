import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;


public class PaintPanel extends JPanel implements MouseListener{

	private ArrayList<Point2D> puntiControllo;
	private KeyListener keyListener;
	private MouseMotionListener mouseMotionListener;
	private boolean isReadyToCompute;
	private boolean isPointReadyToDragged;
	private Point2D pointToDrag;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaintPanel() {
		// TODO Auto-generated constructor stub
		
		puntiControllo=new ArrayList<Point2D>();
		isReadyToCompute=false;
		isPointReadyToDragged=false;
		
		
		
	
		this.setFocusable(true);
		this.setBackground(new Color(255,255,255));	

		this.setPreferredSize(new Dimension(1000,500));
		
		keyListener=new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					isReadyToCompute=true;
					repaint();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					isReadyToCompute=true;
					repaint();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					isReadyToCompute=true;
					repaint();
				}
			}
		};
		
		mouseMotionListener=new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
				if(isPointReadyToDragged){
					pointToDrag.setLocation(new Point(e.getX(),e.getY()));
					repaint();
				}
				
			}
		};
		
		addMouseMotionListener(mouseMotionListener);
		addMouseListener(this);
		addKeyListener(keyListener);
		
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		
		Graphics2D gr2d=(Graphics2D)g;
		
		gr2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gr2d.setColor(new Color(0,0,0));
		
		Point2D p,pBefore;	
		for (int i=0;i<puntiControllo.size();i++){
			p=puntiControllo.get(i);
			gr2d.fillOval((int)p.getX()-5,(int)p.getY()-5,10,10);
			//gr2d.drawLine((int)p.getX(), (int)p.getY(),(int)p.getX(),(int)p.getY());
			if(i>0){
				pBefore=puntiControllo.get(i-1);
				gr2d.drawLine((int)pBefore.getX(),(int)pBefore.getY(),(int)p.getX(),(int)p.getY());
			}
		}
		
		if(isReadyToCompute)
		drawCurvaBezier(gr2d);
	}
	
	public void drawCurvaBezier(Graphics graphic){
		 
		CurvaBezier curva = null;
		Point2D p,pk;
		
		if(puntiControllo.size()==0 || puntiControllo.size()==1){
			//punti di controllo insufficienti
			return;
		}else if (puntiControllo.size()==2){
			//curva lineare
			curva=new CurvaBezierLineare(puntiControllo);
		}else if(puntiControllo.size()==3){
			//curva quadratica
			curva=new CurvaBezierQuadratica(puntiControllo);
		}else if(puntiControllo.size()==4){
			//curva cubica
			curva=new CurvaBezierCubica(puntiControllo);
		}else if(puntiControllo.size()>4){
			//curva di ordine superiore
			//TODO algoritmo di casteljau o B-spline
			return;
		}
		
		curva.computeCurvaBezier();
		graphic.setColor(new Color(0,0,255));
		//((Graphics2D) graphic).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		for(int i=0;i<curva.getCurvaBezier().size();i++){
			p=curva.getCurvaBezier().get(i);
			
			graphic.fillOval((int)(p.getX()-5),(int)(p.getY()-5),10,10);
//			if(i>0){
//				pk=curva.getCurvaBezier().get(i-1);
//				graphic.drawLine((int)pk.getX(), (int)pk.getY(),(int)p.getX(),(int)p.getY());	
//			}
//			
		}
		
	
	}
	
	//Pulisce il paint
	public void clear(){
		puntiControllo.clear();
		this.isReadyToCompute=false;
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		this.requestFocus();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Point2D p;
		//sposto il punto in base al trascinamento del mouse
		for(int i=0;i<puntiControllo.size();i++){
			p=puntiControllo.get(i);
			
			//il mouse si trova all'interno del riquadro,  
			if(p.getX()+10>e.getX() && p.getX()-10<e.getX()){
				if(p.getY()+10>e.getY() && p.getY()-10<e.getY()){
					
					isPointReadyToDragged=true;
					pointToDrag=p;
				}
			}
		}
		
		if(!isPointReadyToDragged){
			puntiControllo.add(e.getPoint());
			repaint();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	
		isPointReadyToDragged=false;	
	}
}
