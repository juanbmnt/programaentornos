import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/***
 * 
 * @author Ruben , Joan, JuanPablo
 *
 */
public class conexion {
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/programacion?useSSL=false";
	private static final String USUARIO = "programacion";
	private static final String CLAVE = "root1234567890";
/***
 * 
 * @return retorna el metodo de conexion a la base de datos
 */
	
	public static Connection conectar() {
		/*establezco conexion y creo tablas*/
		Connection con = null;		
		
		try {
			Class.forName(CONTROLADOR);
			con = DriverManager.getConnection(URL, USUARIO, CLAVE);			
			
			Statement st=con.createStatement();
			String query= "CREATE TABLE if not exists producto("
					+ "  idproducto int PRIMARY KEY NOT NULL,"
					+ "  nombre varchar(45) NOT NULL,"
					+ "  descripcion varchar(200) DEFAULT NULL,"
					+ "  cantidad int NOT NULL,"
					+ "  precio float NOT NULL);";
			st.executeUpdate(query);

			
			String query1="CREATE TABLE if not exists usuarios ("
					+ "  idusuario int PRIMARY KEY NOT NULL AUTO_INCREMENT,"
					+ "  nombreUsuarios varchar(45) NOT NULL,"
					+ "  password varchar(45) NOT NULL,"
					+ "  telefono int DEFAULT NULL,"
					+ "  administrador tinyint NOT NULL);";
			st.executeUpdate(query1);

			
			String query2="CREATE TABLE if not exists reserva ("
					+ "  idreserva int  PRIMARY KEY NOT NULL AUTO_INCREMENT,"
					+ "  nombreUsuario varchar(45) REFERENCES usuarios (nombreUsuarios),"
					+ "  idProducto int REFERENCES producto (idproducto),"
					+ "  cantidad int DEFAULT NULL,"
					+ "  precio float DEFAULT NULL,"
					+ "  fecha date DEFAULT NULL);";
			st.executeUpdate(query2);

		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();

		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}
		
		return con;
	}
	
}