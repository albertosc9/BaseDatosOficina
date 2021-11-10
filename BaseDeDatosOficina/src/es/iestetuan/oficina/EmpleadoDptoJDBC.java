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
		

		
//		crearDepartamento(50,"Informática","Madrid");
//		crearDepartamento(60,"Comunicaciones","Madrid");
//		modificarDepartamento(60,"Informática y comunicaciones", "");
//		borrarDepartamento(60);
//		consultarDepartamento(50);
//		crearEmpleado(8001,"Justo","Prog.MP",7782,"2021-11-10",1570,0,50);
//		consultarEmpleados();
//		borrarEmpleado(7499);
//		modificarEmpleado(7369,1105,50);
		
		
		
	}
	private static Connection conectar() {
		
		Connection conexion = null;
		
		String DRIVER = "org.mariadb.jdbc.Driver";
	    
		
		String url = "jdbc:mariadb://localhost:3306/oficina";
		
		String user = "ofi";
		String pass = "";
		
		
		
		try {
			Class.forName(DRIVER);
			
			
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
	
	public static void crearDepartamento(int numero,String nombre,String loc) {
		
		Connection conexion = conectar();
		PreparedStatement stm;
		try {
			
			
			
			
			
			stm  = conexion.prepareStatement("insert into departamentos values (?,?,?)");
		
			
			
			
			stm.setInt(1, numero);
			stm.setString(2,nombre);
			stm.setString(3, loc);
			
			int x = stm.executeUpdate();
			
			if (x>0) {
				System.out.println("insertado correctamente");
			}else {
				System.out.println("fallo");
			}
			
		
			
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
	public static void modificarDepartamento(int numero,String nombreDep,String localidad) {
		
		Connection conexion = conectar();
		
		
		

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
	public static void borrarDepartamento(int numero) {
		
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
	public static void consultarDepartamento(int numero) {

	
		
		Connection conexion =  conectar();
		
		
		try {
			PreparedStatement pstm = conexion.prepareStatement("select * from departamentos where dept_no = "+numero);
			
			ResultSet result = pstm.executeQuery();
			
			if(result.next()) {
				System.out.println("numero de departamento "+numero);
				System.out.println("nombre de departamento "+result.getString(2));
				System.out.println("localidad "+result.getString(3));
				
			}
			
			
			
			
			
			conexion.close();
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
	public static void crearEmpleado(int numero,String apellido,String prof,int cod,String fecha,float salario,float comis,int numeroDept) {
		
		Connection conexion = conectar();
		
		
		
		
	
		Date date;
	
		date = Date.valueOf(fecha);
		
		
		
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
	public static void consultarEmpleados() {
		
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
	public static void borrarEmpleado(int numero) {
		
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
	public static void modificarEmpleado(int emp_no,float salario,int dept_no) {
		
		
		Connection conexion = conectar();
	
		
		
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
