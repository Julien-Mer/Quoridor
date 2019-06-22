package Client.Views.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelInfoPlayer extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel nameLblBarr1;
	private JLabel nameLblBarr2;
	private String pathToImage;
	private String pathToImage2;
	
	private JLabel imageLbl1;
	private JLabel imageLbl2;
	int nbBarrier1;
	int nbBarrier2;
	
	public PanelInfoPlayer(String nLbl1, String bLbl1, String nLbl2, String bLbl2, String pathToImage, String pathToImage2, String imJ1, String imJ2) {
		super();
		if(bLbl1!=null) {
			this.nbBarrier1 = Integer.parseInt(bLbl1);
		}
		if(bLbl2!=null) {
			this.nbBarrier2 = Integer.parseInt(bLbl2);
		}
		if(bLbl1!=null && nLbl1!=null && imJ1!=null) {
			this.nameLblBarr1 = new JLabel("<html> Nom: "+nLbl1+"<br> Il vous reste: "+bLbl1+" barrieres</html>");
			this.imageLbl1 = new JLabel(new ImageIcon(imJ1));
		}else {
			this.imageLbl1 =new JLabel("");
			this.nameLblBarr1=new JLabel("");
		}
		if(bLbl2!=null && nLbl2!=null && imJ2!=null) {
			this.nameLblBarr2 = new JLabel("<html> Nom: "+nLbl2+"<br> Il vous reste: "+bLbl2+" barrieres</html>");
			this.imageLbl2 = new JLabel(new ImageIcon(imJ2));
		}else {
			this.nameLblBarr2 = new JLabel("");
			this.imageLbl2 = new JLabel("");
		}
		this.pathToImage = pathToImage;
		this.pathToImage2 = pathToImage2;
		this.initComponent();
		
	}
	
	public void initComponent() {
		Font myFont = new Font(Font.SERIF,Font.BOLD,20);
		
		this.nameLblBarr1.setFont(myFont);
		this.nameLblBarr2.setFont(myFont);

		ImagePanel panNameBarrLbl1 = new ImagePanel(this.pathToImage);
		panNameBarrLbl1.setLayout(new FlowLayout(FlowLayout.CENTER,20,50));
		panNameBarrLbl1.add(this.nameLblBarr1);
		panNameBarrLbl1.add(this.imageLbl1);
		
		ImagePanel panNameBarrLbl2 = new ImagePanel(this.pathToImage2);
		panNameBarrLbl2.setLayout(new FlowLayout(FlowLayout.CENTER,20,50));
		panNameBarrLbl2.add(this.nameLblBarr2);
		panNameBarrLbl2.add(this.imageLbl2);
				
		JPanel panLbl1 = new JPanel();
		panLbl1.setPreferredSize(new Dimension(800,525));
		panLbl1.setLayout(new GridLayout(2,1));
		panLbl1.add(panNameBarrLbl1);
		panLbl1.add(panNameBarrLbl2);
		
		this.setBackground(Color.BLACK);
		add(panLbl1);
	
	}
	

}
