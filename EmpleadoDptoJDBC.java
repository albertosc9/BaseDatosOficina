package es.iestetuan.oficina;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmpleadoDptoJDBC {

	 static Scanner read = new Scanner(System.in);
	public static void main(String[] args) {
		

		Connection conex = conectar();
		
//		crear();
		modificar(60);
		
		
		
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
	
	public static void crear() {
		
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
	private static void modificar(int numero) {
		
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
}