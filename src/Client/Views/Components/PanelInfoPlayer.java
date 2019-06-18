package Client.Views.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelInfoPlayer extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel nameLbl1;
	private JLabel barrLbl1;
	private JLabel nameLbl2;
	private JLabel barrLbl2;
	private String pathToImage;
	int nbBarrier1;
	int nbBarrier2;
	
	public PanelInfoPlayer(String nLbl1, String bLbl1, String nLbl2, String bLbl2, String pathToImage) {
		super();
		this.nbBarrier1 = Integer.parseInt(bLbl1);
		this.nbBarrier2 = Integer.parseInt(bLbl2);
		this.nameLbl1 = new JLabel("Nom: "+nLbl1);
		this.nameLbl2 = new JLabel("Nom: "+nLbl2);
		this.barrLbl1 = new JLabel("Il vous reste: "+bLbl1+" barrieres");
		this.barrLbl2 = new JLabel("Il vous reste: "+bLbl2+" barrieres");
		this.pathToImage = pathToImage;
		this.initComponent();
		
	}
	
	public void initComponent() {
		Font myFont = new Font(Font.SERIF,Font.BOLD,20);
		this.nameLbl1.setFont(myFont);
		this.nameLbl2.setFont(myFont);
		
		if(this.nbBarrier1<2) {
			this.barrLbl1.setBackground(Color.RED);
		}
		if(this.nbBarrier2<2) {
			this.barrLbl2.setBackground(Color.RED);
		}
		this.barrLbl1.setFont(myFont);
		this.barrLbl2.setFont(myFont);
		ImagePanel panNameBarrLbl1 = new ImagePanel(this.pathToImage);
		panNameBarrLbl1.setPreferredSize(new Dimension(300,300));
		panNameBarrLbl1.setLayout(new FlowLayout(FlowLayout.CENTER,50,30));
		panNameBarrLbl1.add(this.nameLbl1);
		panNameBarrLbl1.add(this.barrLbl1);
		
		ImagePanel panBarName1 = new ImagePanel(this.pathToImage);
		panBarName1.setLayout(new FlowLayout());
		panBarName1.add(panNameBarrLbl1);
		
		
		ImagePanel panNameBarrLbl2 = new ImagePanel(this.pathToImage);
		panNameBarrLbl2.setPreferredSize(new Dimension(300,300));
		panNameBarrLbl2.setLayout(new FlowLayout(FlowLayout.CENTER,50,30));
		panNameBarrLbl2.add(this.nameLbl2);
		panNameBarrLbl2.add(this.barrLbl2);
		
		ImagePanel panBarName2 = new ImagePanel(this.pathToImage);
		panBarName2.setLayout(new FlowLayout());
		panBarName2.add(panNameBarrLbl2);
		
		
		ImagePanel panLbl1 = new ImagePanel(this.pathToImage);
		panLbl1.setLayout(new GridLayout(2,1));
		panLbl1.setPreferredSize(new Dimension(800,400));
		panLbl1.add(panBarName1);
		panLbl1.add(panBarName2);
		
		add(panLbl1);
	
	}
	

}
