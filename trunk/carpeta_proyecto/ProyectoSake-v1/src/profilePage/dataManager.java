package profilePage;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class DataManager {
	
	/**
	 * That class implements static methods to get the info of the profile
	 */
	private static final String configPath ="profile/profile.txt";
	private static final String[] configInfo ={"nif:=","name:=","ap1:=",
										"ap2:=","fnac:=","dir:=",
										"ciudad:=","codP:=","tlf:=","e-mail:="};
	
	/**
	 * return true only if that profile has been installed at least one time
	 * @return if the profile has been stalled
	 */
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
	
	/**
	 * Return the data of the profile
	 * @return String[] with the profile info
	 */
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
	
	/**
	 * Set the profile info in the profile config file
	 * @param data,info of the profile
	 */
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
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}
	
}
