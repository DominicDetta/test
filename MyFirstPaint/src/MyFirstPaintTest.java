

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFirstPaintTest extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private PaintPanel myPaintPanel;
	private JPanel buttonPanel;
	private JButton clearButton;
	
	public MyFirstPaintTest() {
		// TODO Auto-generated constructor stub

		
		this.myPaintPanel=new PaintPanel();
		
		this.buttonPanel=new JPanel();
		this.clearButton=new JButton();
		this.clearButton.setText("Clear");
		
		this.setTitle("IL MIO PAINT");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    this.setLocation(200,200);
		this.setVisible(true);
		
		this.setLayout(new BorderLayout());
		
		clearButton.addActionListener(this);
		buttonPanel.add(clearButton);
		add(buttonPanel,BorderLayout.WEST);
		add(myPaintPanel,BorderLayout.CENTER);
		
		//setContentPane(myPaintPanel);
		
		this.pack();
		myPaintPanel.requestFocus();

	}
	




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyFirstPaintTest paint=new MyFirstPaintTest();
		
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(clearButton)){
			myPaintPanel.clear();
		}
	}
}
