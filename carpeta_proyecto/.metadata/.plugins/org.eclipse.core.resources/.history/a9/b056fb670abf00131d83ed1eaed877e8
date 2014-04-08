package profilePage;


import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProfileInfo extends JPanel{
	private String img,nombre,ap1,ap2,dir,mail,ciudad,fnac,tlf,codPost;
	private JTextField nombreF,ap1F,ap2F,dirF,mailF,ciudadF,fnacF,tlfF,codPostF;

	
	
	public ProfileInfo(){
		

		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JPanel nP = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		nP.add(new JLabel("Nombre: "));
		nombreF = new JTextField();
		nombreF.setEditable(false);
		nP.add(nombreF);
		this.add(nP);
		
		JPanel ap1P = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		ap1P.add(new JLabel("Primer Apellido: "));
		ap1F = new JTextField();
		ap1F.setEditable(false);
		ap1P.add(ap1F);
		this.add(ap1P);
		
		JPanel ap2P = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		ap2P.add(new JLabel("Segundo Apellido: "));
		ap2F = new JTextField();
		ap2F.setEditable(false);
		ap2P.add(ap2F);
		this.add(ap2P);
		
		JPanel fnacP = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		fnacP.add(new JLabel("Fecha Nacimiento: "));
		fnacF = new JTextField();
		fnacF.setEditable(false);
		fnacP.add(fnacF);
		this.add(fnacP);
		
		JPanel dirP = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		dirP.add(new JLabel("Direccion: "));
		dirF = new JTextField();
		dirF.setEditable(false);
		dirP.add(dirF);
		this.add(dirP);
		
		JPanel cP = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		cP.add(new JLabel("Ciudad: "));
		ciudadF = new JTextField();
		ciudadF.setEditable(false);
		cP.add(ciudadF);
		this.add(cP);
		
		JPanel cpP = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		cpP.add(new JLabel("Codigo Postar: "));
		codPostF = new JTextField();
		codPostF.setEditable(false);
		cpP.add(codPostF);
		this.add(cpP);
		
		JPanel mP = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		mP.add(new JLabel("E-mail: "));
		mailF = new JTextField();
		mailF.setEditable(false);
		mP.add(mailF);
		this.add(mP);
		
		JPanel tlfP = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		tlfP.add(new JLabel("Telefono: "));
		tlfF = new JTextField();
		tlfF.setEditable(false);
		tlfP.add(tlfF);
		this.add(tlfP);

		fillInfo();
	}
	
	private void fillInfo(){
		
		
		nombreF.setText("Bart");
		ap1F.setText("Simpson");
		ap2F.setText("Boubier");
		dirF.setText("Calle Falsa 123");
		mailF.setText("theBarto@theSimpsons.com");
		ciudadF.setText("Springfield");
		fnacF.setText("25/8/1990");
		tlfF.setText("555-555-555");
		codPostF.setText("12345");
	}
	
	public void setInfoEditable(){
		
		nombreF.setEditable(true);
		ap1F.setEditable(true);
		ap2F.setEditable(true);
		dirF.setEditable(true);
		mailF.setEditable(true);
		ciudadF.setEditable(true);
		fnacF.setEditable(true);
		tlfF.setEditable(true);
		codPostF.setEditable(true);
		
		nombreF.setColumns(30);
		ap1F.setColumns(30);
		ap2F.setColumns(30);
		dirF.setColumns(30);
		mailF.setColumns(30);
		ciudadF.setColumns(30);
		fnacF.setColumns(30);
		tlfF.setColumns(30);
		codPostF.setColumns(30);
	}
	
public void setInfoNotEditable(){
		
		nombreF.setEditable(false);
		ap1F.setEditable(false);
		ap2F.setEditable(false);
		dirF.setEditable(false);
		mailF.setEditable(false);
		ciudadF.setEditable(false);
		fnacF.setEditable(false);
		tlfF.setEditable(false);
		codPostF.setEditable(false);
		
		nombreF.setColumns(nombreF.getText().length());
		ap1F.setColumns(ap1F.getText().length());
		ap2F.setColumns(ap2F.getText().length());
		dirF.setColumns(dirF.getText().length());
		mailF.setColumns(mailF.getText().length());
		ciudadF.setColumns(ciudadF.getText().length());
		fnacF.setColumns(fnacF.getText().length());
		tlfF.setColumns(tlfF.getText().length());
		codPostF.setColumns(codPostF.getText().length());
		

	}

}
