package profilePage;

public class VectorInfo {
	
	/**
	 * static String[] woth the fata.
	 */
	private static String[] data = {"NIF","Nombre","Primer Apellido","Segundo Apellido","Fecha Nacimiento",
			"Direccion","Ciudad","Codigo Postal","Telefono","E-mail"};
	
	
	/**
	 * get the data[i]
	 * @param i data to get
	 * @return data[i]
	 */
	public static String getOnedata(int i){
		return data[i];
	}

}
