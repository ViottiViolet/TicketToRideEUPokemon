import java.awt.image.*;

public class TrainCard {
	
	public static final String WILD = "wild";
	private String color;
	private BufferedImage card;
	
	public TrainCard(String c, BufferedImage i) {
		color = c;
		card = i;
	}
	
	public String getColor() {
		return color;
	}
	
	public boolean isWild() {
		return color.equals("wild");
	}
    
	public BufferedImage getImage() {
		return card;
	}


}
