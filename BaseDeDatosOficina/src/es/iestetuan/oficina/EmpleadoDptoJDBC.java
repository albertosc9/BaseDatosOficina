package es.iestetuan.oficina;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmpleadoDptoJDBC {

	 static Scanner read = new Scanner(System.in);
	public static void main(String[] args) {
		

		
		
//		crearDepartamento();
//		modificarDepartamento(60);
//		borrarDepartamento(60);
//		consultarDepartamento(50);
//		crearEmpleado();
//		consultarEmpleados();
//		borrarEmpleado(7369);
//		modificarEmpleado(7521);
		
		
		
	}
	private static Connection conectar() {
		
		Connection conexion = null;
		
		
		String url = "jdbc:mariadb://localhost:3306/oficina";
		
		String user = "ofi";
		String pass = "";
		
		
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			
		conexion = 	DriverManager.getConnection(url,user,pass);
			
			
			
			
			if (conexion!=null) {
				System.out.println("conectada");
			}else {
				System.out.println("no conectada");
			}
	
		
		
		
		
		
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conexion;
		
		
		
		
		
		
	}
	
	public static void crearDepartamento() {
		
		Connection conexion = conectar();
		PreparedStatement stm;
		try {
			
			
			
			
			
			stm  = conexion.prepareStatement("insert into departamentos values (?,?,?)");
		
			
			
			
			
			
			System.out.println("introduce el numero de departamento");
			int numero = read.nextInt();
			System.out.println("introduce el nombre del dep");
			String nombre = read.next();
			System.out.println("introduce la localizacion");
			String loc = read.next();
			
			stm.setInt(1, numero);
			stm.setString(2,nombre);
			stm.setString(3, loc);
			
			ResultSet result  = stm.executeQuery();
			
		
			
//			if (result.rowInserted()) {
//				System.out.println("insertado");
//			}else {
//				System.out.println("fallo");
//			}
			
			
			
			
			
			conexion.close();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}
	private static void modificarDepartamento(int numero) {
		
		Connection conexion = conectar();
		
		
		
		String nombreDep = "Informatica y comuncaciones";
		String localidad = " ";
		
		
		
		
		
		try {
			
			Statement stm = conexion.createStatement();
			
			String q1 = "update departamentos set dnombre = '" + nombreDep + "', loc='"+localidad+"' where dept_no ='"+numero+"'";
			
			
			int x = stm.executeUpdate(q1);
			
			if (x>0) {
				System.out.println("actualizado");
			}else {
				System.out.println("fallo");
			}
			
			
			
			
			
			
			
			conexion.close();
			
			
			
			
			
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	private static void borrarDepartamento(int numero) {
		
		Connection conexion = conectar();
		
		
		try {
			Statement stm = conexion.createStatement();
			
			String q1 = "delete from departamentos where dept_no = "+numero;
			
			
			int x = stm.executeUpdate(q1);
			
			if (x>0) {
				System.out.println("borrado correcto");
			}else {
				System.out.println("fallo");
			}
			
			conexion.close();
			
			
			
			
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}
	private static void consultarDepartamento(int numero) {

	
		
		Connection conexion =  conectar();
		
		
		try {
			PreparedStatement pstm = conexion.prepareStatement("select * from departamentos where dept_no = "+numero);
			
			ResultSet result = pstm.executeQuery();
			
			if(result.next()) {
				System.out.println("numero de departamento "+numero);
				System.out.println("nombre de departamento "+result.getString(2));
				System.out.println("localidad "+result.getString(3));
				
			}
			
			
			
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
	private static void crearEmpleado() {
		
		Connection conexion = conectar();
		
		
		
		
	
		Date date;
	
		
		System.out.println("Introduce su numero empleado");
		int numero = read.nextInt();
		System.out.println("apellido");
		String apellido = read.next();
		System.out.println("introduce profesion");
		String prof = read.next();
		System.out.println("codigo director: ");
		int cod = read.nextInt();
		System.out.println("introduce fecha alta formato año-mes-dia");
		String fecha = read.next();
		date = Date.valueOf(fecha);
		System.out.println("introduce salario");
		float salario = read.nextFloat();
		System.out.println("introduce comision ");
		float comis = read.nextFloat();
		System.out.println("introduce dept_no ");
		int numeroDept = read.nextInt();
		
		
		try {
			Statement stm = conexion.createStatement();
			
			
			String q1 = "insert into empleados values ('"+numero+"','"+apellido+"','"+prof+"','"+cod+"','"+date+"','"
					+salario+"','"+comis+"','"+numeroDept+"')";

			int x = stm.executeUpdate(q1);
			
			if (x>0) {
				System.out.println("insercion correcta");
			}else {
				System.out.println("fallo");
			}

			
			
			conexion.close();
			

			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	private static void consultarEmpleados() {
		
		Connection conex = conectar();
		
		
		try {
			PreparedStatement pstm = conex.prepareStatement("select e.*,dnombre,loc from departamentos d,empleados e "
															+ "where d.dept_no=e.dept_no and salario>1500 and apellido like 'J%'");
	
		
		
			
			ResultSet result = pstm.executeQuery();
			
			while(result.next()) {
				System.out.println("numero empleado: "+result.getInt(1));
				System.out.println("apellido: "+result.getString(2));
				System.out.println("oficio: "+result.getString(3));
				System.out.println("dir: "+result.getInt(4));
				System.out.println("fecha alta: "+result.getDate(5));
				System.out.println("salario: "+result.getFloat(6));
				System.out.println("comision: "+result.getFloat(7));
				System.out.println("numero departamento: "+result.getInt(8));
				System.out.println("nombre departamento: "+result.getString(9));
				System.out.println("localidad: "+result.getString(10));
			}
		
		
		
		
		
		conex.close();
		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	
		
		
	}
	private static void borrarEmpleado(int numero) {
		
		Connection conexion = conectar();
		
		try {
			Statement stm = conexion.createStatement();
		
			String q1 = "delete from empleados where emp_no = "+numero;
			
			
			int x = stm.executeUpdate(q1);
			
			
			if (x>0) {
				System.out.println("borrado efectuado");
			}else {
				System.out.println("fallo");
			}
		
		
		
		conexion.close();
		
		
		
		
		
		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}
	private static void modificarEmpleado(int emp_no) {
		
		
		Connection conexion = conectar();
		
		
		float salario = 1600;
		int dept_no = 50;
		
		
		try {
			Statement stm = conexion.createStatement();
			
			String q1 = "update empleados set salario = '"+salario+"', dept_no = '"+dept_no+"' where emp_no = "+emp_no;

			
			int x = stm.executeUpdate(q1);
			
			
			if (x>0) {
				System.out.println("modificacion correcta");
			}else {
				System.out.println("fallo");
			}
			
			
			
			
			conexion.close();
			
		
		
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
