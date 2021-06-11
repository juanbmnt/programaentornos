
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Principal{
	

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		//conexion.conectar();  /*establezco conexion*/
		int opc = 0;
		System.out.println("¡Bienvenido!");
	do {

		System.out.println("Indica que deseas hacer:\n"
				+ "1. Registrarse \n"
				+ "2. Identificarse \n"
				+ "0. Salir");
		Scanner t = new Scanner(System.in);
		opc = t.nextInt();
		t.nextLine();
		switch (opc) {
		case 1:/*Registrar*/
			System.out.println("Introduce los siguientes datos:");
			usuarios.registro();
			break;
			
		case 2:/*identificar */
		    Statement st1 = conexion.conectar().createStatement();
		    usuarios cliente = new usuarios();
			System.out.println("Introduzca sus datos de inicio de sesion");
			System.out.println("Nombre usuario:");
			cliente.setNombreUsu(t.next());
			System.out.println("Contraseña");
			cliente.setContrasena(t.next());
			
			/*consulto nombre y contraseña*/
			String validar = ("Select * from usuarios where NombreUsuarios like '" 
					+cliente.getNombreUsu()+"'and password like '"
					+cliente.getContrasena()+"';");
			ResultSet consulta1 = st1.executeQuery(validar);

			if(consulta1.next()) {//datos introducidos correctos
			    Statement st2 = conexion.conectar().createStatement();
				String admin = ("Select * from usuarios where administrador like '1'"
						+ "and NombreUsuarios like '"+ cliente.getNombreUsu()
						+"'and password like '"+cliente.getContrasena()+"';");
				ResultSet consulta2 = st2.executeQuery(admin);

				/*¿Es administrador?*/
				if(consulta2.next()) {
				    System.out.println(cliente.getNombreUsu().toUpperCase()+". Tienes privilegios de administrador");
				    int opcadm = 0;
				    do {
				    	System.out.println("Estas en el menu principal\n"
				    			+ "1. Almacen\n"
				    			+ "2. Clientes \n"
				    			+ "3. Reservas\n"
				    			+ "0. Salir");
				    	opcadm = t.nextInt();
				    	
				    	int opcalm = 0;
				    	switch (opcadm) {
				    	
				    	case 1:/*ADMINISTRADOR gestion almacen*/
				    		System.out.println("ALMACEN DE PRODUCTOS");
				    		System.out.println("Presiona la tecla que corresponda");
				    		System.out.println("1. Añadir productos nuevos \n"
				    				+ "2. Actualizar inventario\n"
				    				+ "3. Eliminar productos\n"
				    				+ "4. Ver listado de productos disponibles\n"
				    				+ "5. Volver\n"
				    				+ "0. Salir");
				    		do {
					    		opcalm = t.nextInt();
			    				productos producto = new productos();
				    			switch(opcalm) {
				    			
				    			case 1:/*añadir nuevos productos*/ 	
									boolean anadir = false;					
				    				do {
				    					
					    				System.out.println("Introduce los datos del NUEVO PRODUCTO");
				  
					    				System.out.println("Numero de Id.");	
					    				producto.setId(t.nextInt());
					    				t.nextLine();
					    								    				
					    				/*¿Producto existe?*/
					    				Statement stvp = conexion.conectar().createStatement();
					    				String verprodu = ("SELECT * FROM programacion.producto where idproducto like '"
					    						+ producto.getId()+"'; ");
					    				ResultSet rsvp = stvp.executeQuery(verprodu);
					    				
					    				if (rsvp.next()){/*El producto existe*/
					    					System.out.println("¡El producto ya existe!\n"
					    							+ "No se puede añadir\n");
											anadir = true;
					    				}else {/*¿No existe? Recojo datos*/ 		
					    					System.out.println("Nombre del producto:");
					    					producto.setNombre(t.nextLine());
						    				System.out.println("Descripcion.");				
						    				producto.setDescripcion(t.next());
						    				System.out.println("Cuantos vas a añadir.");								
						    				producto.setCantidad(t.nextInt());
						    				System.out.println("Precio.(0,00)");
						    				producto.setPrecio(t.nextFloat());
						    				
						    				/*insercion de productos en la base de datos*/						
						    				Statement stap = conexion.conectar().createStatement();
						    				String nuevoprodu = ("INSERT INTO `programacion`.`producto` VALUES ('"
						    						+ producto.getId()+"','"
						    						+ producto.getNombre()+"','"
						    						+ producto.getDescripcion()+"','"
						    						+ producto.getCantidad()+"','"
						    						+ producto.getPrecio()+"');");
						    				stap.executeUpdate(nuevoprodu);
						    				System.out.println("Producto añadido ('"+producto.getNombre()+"')\n");
						    				System.out.println("Deseas añadir otro producto SI/NO \n");
											String anadiryes = t.next();
											
											if(anadiryes.equals("SI")||anadiryes.equals("Si")||anadiryes.equals("si")){
												anadir = true;
							    				System.out.println(anadir);

											}else {
												anadir = false;

											}
											conexion.conectar().close();
						    				rsvp.close();
						    				opcalm = 0 ;
					    				}				    				
				    				}while(anadir != false);

				    				break;
				    				
				    			case 2:/*actualizar*/															
				    				boolean actua = false;
				    				do {
				    					
					    				System.out.println("Introduce el ID del producto que vas a ACTUALIZAR");
					    				System.out.println("Numero de Id.");
					    				producto.setId(t.nextInt());
					    				
					    				/*¿El producto existe?*/
					    			    Statement stvp1 = conexion.conectar().createStatement();
					    				String verprodu1 = ("SELECT * FROM programacion.producto where idproducto like '"
					    						+ producto.getId()+"'; ");
					    				ResultSet rsvp1 = stvp1.executeQuery(verprodu1);
	
					    				if(rsvp1.next()){/*producto encontrado*/
					    					System.out.println("¡Producto localizado!\n"
					    							+ "actualiza los siguientes datos\n");
						    				System.out.println("Nombre producto.");
						    				producto.setNombre(t.next());
						    				System.out.println("Descripcion.");	
						    				producto.setDescripcion(t.next());
						    				System.out.println("Unidades disponibles.");											
						    				producto.setCantidad(t.nextInt());								
						    				System.out.println("Precio.(0,00)");
						    				producto.setPrecio(t.nextFloat());
						    				
						    				/*actualizo datos*/
						    				Statement stmp = conexion.conectar().createStatement();
						    				String modprodu = ("UPDATE `programacion`.`producto` SET"
						    						+ " `idproducto` = '"+producto.getId()+"',"
						    						+ " `nombre` = '"+producto.getNombre()+"',"
						    						+ " `descripcion` = '"+producto.getDescripcion()+"',"
						    						+ " `cantidad` = '"+producto.getCantidad()+"',"
						    						+ " `precio` = '"+producto.getPrecio()+"'"
						    						+ " WHERE (`idproducto` like '"+producto.getId()
						    						+ "');");
						    				stmp.executeUpdate(modprodu);													
						    				System.out.println("El producto '"+producto.getNombre()+"' ha sido actualizado.");
						        			conexion.conectar().close();
						    				
					    				}else {/*producto no localizado*/
					    						System.out.println("¡Producto no encontrado! \n");
					    						opcalm = 0 ;
					    				}
					    				System.out.println("Deseas ACTUALIZAR otro producto SI/NO \n");
										String actuayes = t.next();
										
										if(actuayes.equals("SI")||actuayes.equals("Si")||actuayes.equals("si")){
											actua = true;
	
										}
										conexion.conectar().close();
					    				rsvp1.close();
		    							    				
				    				}while(actua != false);
				    			    								    				
				    			case 3:/*eliminar producto*/																//ok
				    				System.out.println("Introduce el nombre del producto que deseas ELIMINAR");
				    				producto.setNombre(t.next());
				    				System.out.println("Ahora el ID");
				    				producto.setId(t.nextInt());
				    				
				    				/*¿El producto existe?*/
				    			    Statement stvp2 = conexion.conectar().createStatement();
				    				String verprodu2 = ("SELECT * FROM programacion.producto where idproducto like '"
				    								+ producto.getId()+"'and nombre like '"
				    								+ producto.getNombre()+ "'; ");
				    				ResultSet rsvp2 = stvp2.executeQuery(verprodu2);
				    				System.out.println(verprodu2);
				    				
				    				if(rsvp2.next()){/*producto encontrado*/
				    					System.out.println("¡Producto localizado!\n");
				    					
					    				/*Elimino producto*/
					    				Statement step = conexion.conectar().createStatement();
					    				String eliprodu = ("DELETE FROM `programacion`.`producto` WHERE (`"
					    						+ "idproducto` = '"+producto.getId()+ "');");
					    				step.executeUpdate(eliprodu);
					    				System.out.println("El producto '"+producto.getNombre()+"'a sido eliminado correctamente");
					    				opcalm = 0;
					        			conexion.conectar().close();
					    				
				    				}else {/*producto no localizado*/
				    						System.out.println("¡Producto no encontrado! \n"
				    								+ "No se ha podido eliminar");
				    						opcalm = 0 ;
				    				}
				        			conexion.conectar().close();
				    				rsvp2.close();
				    				break;
				    				
				    			case 4:/*listado de productos en pantalla*/
				    				System.out.println("Este en un listado de los productos disponibles\n");
				    				Statement stlp = conexion.conectar().createStatement();
					    			String lisprodu = ("SELECT * FROM programacion.producto; ");
					    			ResultSet rslp = stlp.executeQuery(lisprodu);
					    			
					    			System.out.println ("Id:\t "
				    			    		+ "Produto:\t "
				    			    		+ "Descripcion:\t"
				    			    		+ "Uds.Disponibles:\t"
				    			    		+ "Precio:");
				    			    System.out.println("\t---------------------------------------------------------");
					    			// Recorremos el resultado, mientras haya registros para leer.
					    			while (rslp.next()) {
					    			    System.out.println(rslp.getInt (1)+"\t" 
					    			    		+rslp.getString (2)+"\t\t"
					    			    		+rslp.getString (3)+ "\t\t\t "
					    			    		+rslp.getInt(4)+ "\t\t " 		
					    			    		+rslp.getFloat(5));
					    			}
					    			System.out.println("\t-----------------------------------------------------------\n");
					    			conexion.conectar().close();
					    			rslp.close();
					    			opcalm = 0;
				    				break;
				    				
				    			case 5:/*volver a menu admin*/
				    				System.out.println("¡Bienvenido!");
				    				opcalm = 0;
				    				break;
				    				
						    	case 0:/*Salir del sistema*/
				    				System.out.println("¡Gracias por la visita "
				    						+cliente.getNombreUsu().toUpperCase()+", esperamos verte pronto!\n");
				    				break;
				    				
								default:
									System.out.println("Opción incorrecta.");
				    			}/*Cierro las opciones de almacen del admisitrador*/
				    			
				    		}while(opcalm != 0);
				    		break;
				    		
				    	case 2:/*Gestion clientes del administrador*/ 	
				    		boolean rep = true;
				    		do {
				    			System.out.println("Gestionar CLIENTES");
				    			System.out.println("Presiona la tecla que corresponda\n"
				    					+ "1. Listar clientes\n"
				    					+ "2. Ver datos cliente \n"
				    					+ "3. Dar de baja cliente \n"
				    					+ "4. Convertir en administrador\n"
				    					+ "5. Ver administradores\n"
				    					+ "0. Salir");
				    			int opcac = t.nextInt();
				    			switch (opcac) {
				    			case 1:/* listar cliente*/
				    				System.out.println("Clientes registrados en el almacen");
				    				usuarios.listarCliente();
				    				break;  				
				    								   
				    			case 2:/* BUSCAR cliente*/
				    				System.out.println("¡Buscador de clientes!");
				    				usuarios.buscarCliente();
				    				break;
				    								    				
				    			case 3:/* BAJA cliente*/
				    				System.out.println("¡Dar de baja cliete!");
				    				usuarios.bajaCliente();				    				
				    				break;
				    				
				    			case 4:/* convert ADMINISTRADOR cliente*/
				    				System.out.println("Introduce datos del nuevo administrador");
				    				usuarios.nuevoAdministrador();
				    				break;
				    				
				    			case 5: /*Listar administradores*/
				    				System.out.println("Administradores del sistema");
				    				usuarios.verAdministradores();
				    				break;
				    				
				    			case 0:/*SALIR*/
				    				rep = false;
				    			break;
				    			
				    			default:
				    				System.out.println("Opcion incorrecta");
				    			}/*cierro gestion clientes*/
				    			
				    		}while(rep !=false);
				    					    	
							    		
				    	case 3:/*Gestion ventas del administrador*/
				    		
				    	case 0:/*Salir del sistema opcion administrador*/
		    				System.out.println("¡Gracias por la visita "
		    						+cliente.getNombreUsu().toUpperCase()+", esperamos verte pronto!\n");
		    				break;
		    				
						default:
							System.out.println("Opción incorrecta.");				    	
				    	}/*final del menu administrador (switch)*/
				    	
				    	
				    }while(opcadm !=0);

					
					
				}else {/*ESTE ES USUARIO DEL SISTEMA*/
					System.out.println("¡Bienvenido!"+cliente.getNombreUsu().toUpperCase());
				}
				
			}else {//datos de acceso al sistema incorrectos .
				System.out.println("¡Los datos de acceso son incorrectos!");
				
			}
			break;
		case 0:
			System.out.println("Gracias por usar este programa.");
			break;
		
		default:
			System.out.println("Opción incorrecta.");
		
			}
		}while (opc != 0);
	}
}