package profilePage;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

import properties.PropertiesManager;

import com.aeat.valida.Validador;

public class DataProfileManager {

	private static final String INSTALLED_CONFIN_STRING = "installed";
	private static final String DELIMITER_CONFIG_STRING = ":=";
	private static final String TRUE_CONFIG_STRING = "true";
	/**
	 * That class implements static methods to get the info of the profile
	 */
	private static final String configPath = "config/configProfile.txt";
	private static final String[] configInfo = { "nif:=", "name:=", "ap1:=",
			"ap2:=", "fnac:=", "dir:=", "ciudad:=", "codP:=", "tlf:=",
			"e-mail:=" };

	public static String[] dataLabels = { "NIF", "Nombre", "Primer apellido",
			"Segundo apellido", "Fecha nacimiento", "Direcci\u00f3n", "Ciudad",
			"C\u00f3digo postal", "Tel\u00e9fono", "E-mail" };

	private static DateFormat dateFormat = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");

	/**
	 * return true only if that profile has been installed at least one time
	 * 
	 * @return if the profile has been stalled
	 */
	public static boolean isInstalled() {
		try (Scanner sc = new Scanner(new File(configPath));) {

			String isInstalled = sc.nextLine();
			if (isInstalled.contains(TRUE_CONFIG_STRING)) {

				return true;
			} else {

				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Return the data of the profile
	 * 
	 * @return String[] with the profile info
	 */
	public static String[] getData() {
		try (Scanner sc = new Scanner(new File(configPath));) {
			String[] data = new String[configInfo.length];

			sc.nextLine();
			sc.useDelimiter(DELIMITER_CONFIG_STRING);
			int i = 0;
			while (sc.hasNextLine()) {
				sc.next();
				data[i] = sc.nextLine().substring(2);
				i++;
			}

			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Set the profile info in the profile config file
	 * 
	 * @param data
	 *            ,info of the profile
	 */
	public static void updateData(String[] data) {

		try (FileWriter fichero = new FileWriter(configPath);
				PrintWriter pw = new PrintWriter(fichero);) {

			pw.println(INSTALLED_CONFIN_STRING + DELIMITER_CONFIG_STRING
					+ TRUE_CONFIG_STRING);

			for (int i = 0; i < data.length; i++)
				pw.println(configInfo[i] + data[i]);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean verifyData() {
		boolean verified = true;

		String[] data = getData();
		if (!verifyNIF(data[0])) {
			JOptionPane.showMessageDialog(null, PropertiesManager
					.getProperties(PropertiesManager.SD_ERROR_DNI_TEXT));
			return false;
		}
		for (int i = 1; i < data.length - 1; i++) {
			if (data[i].length() == 0) {
				JOptionPane
						.showMessageDialog(
								null,
								(PropertiesManager
										.getProperties(PropertiesManager.SD_ERROR_DATA_TEXT))
										+ dataLabels[i]);
				return false;
			}
		}
		if (!data[data.length - 1].contains("@")) {
			JOptionPane.showMessageDialog(null, PropertiesManager
					.getProperties(PropertiesManager.SD_ERROR_MAIL_TEXT));
			return false;
		}
		return verified;
	}

	private static boolean verifyNIF(String nif) {
		Validador validador = new Validador();
		int e = validador.checkNif(nif);

		if (e > 0) {

			return true;
		} else {

			return false;
		}
	}

	public static String getSystemDate() {

		Date date = new Date();
		return dateFormat.format(date);
	}
	
	

}
