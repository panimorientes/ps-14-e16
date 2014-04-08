package profilePage;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class DataManager {
	
	private static final String configPath ="profile/profile.txt";
	private static final String[] configInfo ={"nif:=","name:=","ap1:=",
										"ap2:=","fnac:=","dir:=",
										"ciudad:=","codP:=","tlf:=","e-mail:="};
	public static boolean isInstalled(){
		try{
			Scanner sc = new Scanner(new File(configPath));
			String isInstalled = sc.nextLine();
			if(isInstalled.contains("true")){
				return true;
			}
			else{
				return false;
			}
		}
		catch(Exception e){System.out.println("error is installerd"); return false;}
		
	}
	
	public static String[] getData(){
		try{
			String[] data = new String[configInfo.length];
			Scanner sc = new Scanner(new File(configPath));
			sc.nextLine();
			sc.useDelimiter(":=");
			int i=0;
			while(sc.hasNextLine()){
				sc.next();
				data[i]=sc.nextLine().substring(2);
				i++;
			}
			return data;
		}
		catch(Exception e){System.out.println("error getData"); return null;}
	}
	
	public static void updateData(String[] data){
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(configPath);
            pw = new PrintWriter(fichero);
            pw.println("installed:=true");
            
            for (int i = 0; i < data.length; i++)
            	pw.println(configInfo[i]+data[i]);
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}
		

}
