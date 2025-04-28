import javax.swing.ImageIcon;

public class TrainCard {
	
	private String color;
	private ImageIcon card;
	
	public TrainCard(String c) {
		color = c;
		
		card = new ImageIcon(getClass().getResource("/Images/Cards/"+ color +".png"));
	}
	
	public String getColor() {
		return color;
	}
	
	public boolean isWild() {
		return color.equals("wild");
	}
    
	public ImageIcon getImage() {
		return card;
	}


}
