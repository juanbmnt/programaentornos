import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/***
 * 
 * @author Ruben , Joan, JuanPablo
 *
 */
public class usuarios {
	private int idusuario;
	private String nombreUsu;
    private String telefono;
    private String contrasena;
    private boolean administrador;
    /***
     * 
     * @return Id usuario
     */
    public int getIdusuario() {
		return idusuario;
	}
    /***
     * 
     * @param idusuario
     */
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	/***
	 * 
	 * @return nombre de usuario
	 */
	public String getNombreUsu() {
		return nombreUsu;
	}
	/***
	 * 
	 * @param nombreUsu
	 */
	public void setNombreUsu(String nombreUsu) {
		this.nombreUsu = nombreUsu;
	}
	/***
	 * 
	 * @return telefono 
	 */
	public String getTelefono() {
		return telefono;
	}
	/***
	 * 
	 * @param telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/***
	 * 
	 * @return contraseña
	 */
	public String getContrasena() {
		return contrasena;
	}
	/***
	 * 
	 * @param contrasena
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	/***
	 * 
	 * @return devuelve si es administrador
	 */
	public boolean isAdministrador() {
		return administrador;
	}
	/***
	 * 
	 * @param administrador
	 */
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
	/***
	 * metodo constructor
	 */
	usuarios() {
		this.idusuario = 0;
		this.nombreUsu = "";
		this.telefono = "" ;
		this.contrasena ="";
		this.administrador = false;

	}
	/***
	 * metodo para imprimir la informacion
	 */
	void info() {
		System.out.println("—————————————————————————————————————————————");
		System.out.println("Nombre del cliente: "+this.nombreUsu.toUpperCase());
		System.out.println("Telefono: "+this.telefono);
		System.out.println("Id: "+this.idusuario);
		System.out.println("—————————————————————————————————————————————\n");

	}
		
	/***
	 * metodo para regitrar un nuevo usuario
	 */
	public static void registro() {
		try {
			
			Scanner t=new Scanner(System.in);
			Statement st = conexion.conectar().createStatement();		    
			System.out.println("Nombre usuario:");
			String nombre = t.next();
			
			//compruebo que usuario este libre
			String query = ("Select * from usuarios where NombreUsuarios like '" + nombre + "'");
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {//usuario ocupado
				System.out.println("¡El usuario ya existe!");
				
			}else {//usuario disponible
				System.out.println("Introduce contraseña");
				String contrasena = t.next();
				System.out.println("Telefono");
				String telefono = t.next();
				
				String insert = "INSERT INTO `programacion`.`usuarios` VALUES (default,'"
						+ nombre+"','"
						+ contrasena+"','"
						+ telefono+"',"
						+ "'0')";
				st.executeUpdate(insert);
				System.out.println("¡Usuario creado correctamente!");
				
			}	

			conexion.conectar().close();
		
		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}
		
	}
	
	/***
	 * metodo para localizar clientes
	 */
	public static void buscarCliente() {
		try {
			
			Scanner t=new Scanner(System.in);
			System.out.println("Introduce nombre del cliente");
			String nombre=t.next();
			
			/*¿El cliente existe?*/
		    Statement st = conexion.conectar().createStatement();
			String query = ("SELECT * FROM programacion.usuarios where NombreUsuarios like '"
							+ nombre+"'; ");
			ResultSet rs = st.executeQuery(query);
				    		
			if(rs.next()) {/*encuentra el usuario*/
				
				usuarios aux=new usuarios();
				aux.nombreUsu=rs.getString("nombreUsuarios");
				aux.telefono=rs.getString("telefono");
				aux.idusuario = rs.getInt("idusuario");
				
				aux.info();
				t.nextLine();
				
			}else {/*el usuario no encontrado*/
				System.out.println("Usuario no encontrado");
			}
		
		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}
	}
	
	/***
	 * metodo para dar de baja un cliente
	 */
	public static void bajaCliente() {
		try {
			
			Scanner t=new Scanner(System.in);
			System.out.println("Introduce el nombre cliente");
			String nombre =t.next();
			System.out.println("Ahora el ID");
			String id =t.next();
			
			/*¿El cliente existe?*/
		    Statement st = conexion.conectar().createStatement();
			String query = ("SELECT * FROM programacion.usuarios where nombreUsuarios like '"
							+ nombre+"'and idusuario like '"
							+ id+ "'; ");
			ResultSet rs = st.executeQuery(query);
			
			if(rs.next()){/*cliente encontrado*/
				System.out.println("¡Producto localizado!\n");
				
				/*Elimino cliente*/
				Statement st1 = conexion.conectar().createStatement();
				String drop = ("DELETE FROM `programacion`.`usuarios` WHERE (`"
						+ "idusuario` = '"+id+ "');");
				st.executeUpdate(drop);
				System.out.println("El cliente '"+nombre+"'a sido eliminado correctamente");
				conexion.conectar().close();
				
			}else {/*cliente no localizado*/
					System.out.println("¡Cliente no encontrado! \n No se ha podido eliminar");
			}
			
			System.out.println("—————————————————————————————————————————————\n");
			conexion.conectar().close();
		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}
	}
	
	/***
	 * metodo para listar clientes 
	 */
	public static void listarCliente() {
		try {
			Statement st = conexion.conectar().createStatement();
			String query = ("SELECT * FROM programacion.usuarios where administrador = '0'; ");
			ResultSet rs = st.executeQuery(query);
			
			System.out.println ("Id:\t				Cliente:\t 				Telefono:\t");
		    System.out.println("---------------------------------------------------------");
		    
			// Recorremos el resultado, mientras haya registros para leer.
			while (rs.next()) {
			    System.out.println(rs.getInt (1)+"\t" 
			    		+rs.getString (2)+"\t\t"
			    		+rs.getInt(4));
			}
			System.out.println("-----------------------------------------------------------\n");

			conexion.conectar().close();
			rs.close();
			
		}catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}
	}
	
	/***
	 * metodo para crear un nuevo administrador
	 */
	public static void nuevoAdministrador() {
		try {			
			Scanner t=new Scanner(System.in);
			System.out.println("Nombre:");
			String nombre =t.next();
			System.out.println("Telefono");
			String telefono = t.next();
			
			/*¿El usuario existe?*/
			Statement st = conexion.conectar().createStatement();
			String query = ("SELECT * FROM programacion.usuarios where NombreUsuarios like '"
							+ nombre+"'and telefono like '"
							+ telefono+ "'; ");
			ResultSet rs = st.executeQuery(query);
				    		
			if(rs.next()) {/*encuentra el usuario*/
				
			    Statement st1 = conexion.conectar().createStatement();
				String update = ("UPDATE `programacion`.`usuarios` SET `administrador` = '1' "
						+ "WHERE `NombreUsuarios` like '"+nombre+"'"
						+ "and `telefono` like '"+telefono+"';");
				st1.executeUpdate(update);
				System.out.println("El usaurio '"+nombre.toUpperCase()+"' ahora es administrador");
				
			}else {/*el usuario no encontrado*/
				System.out.println("Usuario no encontrado");
			}

			conexion.conectar().close();
			
		}catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}
	}
	
	/***
	 * metodo para visualizar los administradores existentes en el sistema
	 */
	public static void verAdministradores() {
		try {
			Statement st = conexion.conectar().createStatement();
			String query = ("SELECT * FROM programacion.usuarios where administrador = '1'; ");
			ResultSet rs = st.executeQuery(query);
			
			System.out.println ("Id:\t				Nombre:\t 				Telefono:\t");
		    System.out.println("---------------------------------------------------------");
		    
			
			while (rs.next()) {// Recorremos el resultado, mientras haya registros para leer.
			    System.out.println(rs.getInt (1)+"\t" 
			    		+rs.getString (2)+"\t\t"
			    		+rs.getInt(4));
			}
			System.out.println("-----------------------------------------------------------\n");

			conexion.conectar().close();
			rs.close();
			
		}catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
