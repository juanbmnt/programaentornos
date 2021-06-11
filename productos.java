/***
 * 
 * @author Ruben , Joan, JuanPablo
 *
 */
public class productos {
	private int id;
	private String nombre;
	private String descripcion;
	private int cantidad;
	private float precio;
	
	/***
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/***
	 * 
	 * @param id 
	 */
	public void setId(int id) {
		this.id = id;
	}
	/***
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/***
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/***
	 * 
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/***
	 * 
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/***
	 * 
	 * @return cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}
	/***
	 * 
	 * @param cantidad
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	/***
	 * 
	 * @return precio
	 */
	public float getPrecio() {
		return precio;
	}
	/***
	 * 
	 * @param precio
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	/***
	 * constructor por defecto
	 */
	productos() {
		this.id = 0;
		this.nombre = "";
		this.descripcion = "";
		this.cantidad = 0;
		this.precio = (float) 0;
		
	}
	
	/***
	 * 
	 * @param id
	 * @param nombre
	 * @param descripcion
	 * @param cantidad
	 * @param precio
	 */
	productos(int id, String nombre, String descripcion, int cantidad, float precio){
		this.id=id;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.cantidad=cantidad;
		this.precio=precio;
		
	}
	/***
	 * metodo para mostrar info
	 */
	void info() {
		System.out.println("—————————————————————————————————————————————");
		System.out.println("Identificador: "+this.id);
		System.out.println("nombre del producto: "+this.nombre);
		System.out.println("descripcion: "+this.descripcion);
		System.out.println("precio: "+this.precio);
		System.out.println("unidades disponibles: "+this.cantidad);	
	}
}