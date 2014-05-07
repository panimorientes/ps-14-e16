package profilePage;


import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProfileInfo extends JPanel{
	
	private String[] actualData;	
	private JTextField[] textFieldVector;
	
	/**
	 * Constructor
	 */
	public ProfileInfo(){
		
		actualData=DataManager.getData();
		textFieldVector = new JTextField[actualData.length];
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		for(int i=0;i<textFieldVector.length;i++){
			JPanel panel = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
			panel.add(new JLabel(VectorInfo.getOnedata(i)+": "));
			textFieldVector[i] = new JTextField();
			textFieldVector[i].setEditable(false);
			panel.add(textFieldVector[i]);
			this.add(panel);
		}
		fillInfo(actualData);
	}
	
	/**
	 * sets the info of the prifile
	 * @param data, info to be setted
	 */
	private void fillInfo(String[] data){
		for(int i=0;i<textFieldVector.length;i++){
			textFieldVector[i].setText(data[i]);
		}
	}
	
	/**
	 * set the info editable
	 */
	public void setInfoEditable(){
		for(int i=0;i<textFieldVector.length;i++){
			textFieldVector[i].setEditable(true);
			textFieldVector[i].setColumns(30);
		}
	}
	
	/**
	 * set the info not editable
	 */
	public void setInfoNotEditable(){
		for(JTextField txt: textFieldVector){
			txt.setEditable(false);
			txt.setColumns(txt.getText().length());
		}
	}
	
	/**
	 * Set the chanfe if it have to be setted
	 * @param change, if true, set the  change, if false, dont set.
	 */
	public void setChanges(boolean change){
		if(!change){
			fillInfo(actualData);
		}
		else{
			String[] newData = new String[actualData.length];
			for(int i=0;i<actualData.length;i++){
				newData[i]=textFieldVector[i].getText();
			}
			
			fillInfo(newData);
			actualData=newData;
			DataManager.updateData(actualData);
		}
	}
}
