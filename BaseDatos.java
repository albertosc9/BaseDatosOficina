package es.iestetuan.asc.conexionBaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.iestetuan.asc.fichtexto.AlumnoProperties;
import es.iestetuan.asc.vo.Alumno;

public class BaseDatos {

	public static void main(String[] args) {
	
		String alumno = "alumno";
		insertarDatos();
		consultarInfo(alumno);
		
		
	}

	private static Connection conectar() {
		Connection conexion = null;
	
		
		
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/insti", "root","");
			
			
			if (conexion!=null) {
				System.out.println("conectada");
			}else {
				System.out.println("no conectada");
			}
			
			
		
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conexion;
		
	}
	public static void insertarDatos() {
		
		AlumnoProperties al = new AlumnoProperties();
		List<Alumno> lista = new ArrayList<Alumno>();

		lista = al.getAlumnos();
		Connection conex = null;
		try {
		
			
			conex = conectar();
			
			
			
			java.sql.Statement stmt = conex.createStatement();
				
		
			for (Alumno a : lista) {
			

				
				PreparedStatement s = conex.prepareStatement("select nia from alumno where nia = ?");
				s.setLong(1, a.getNia());
				
				
				
			ResultSet result =	s.executeQuery();
				
			
			if (result.first()) {
					
					}else {
						String q1 = "insert into alumno values ('"+a.getNie()+"', '"+a.getNombre()+"','"+a.getApellido1()+"','"+a.getApellido2()+
								"','"+a.getEmail()+"','"+a.getNia()+"')";
			int y = stmt.executeUpdate(q1);
			
			if (y>0) {
				System.out.println("insercion completada");
			}else {
				System.out.println("fallo ");
			
					}
			}					
					}
				
				
				
			
			
				
				
			
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
			
//			
			
				
				
				
			
		try {
			conex.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
			
			
		
		
		
	}
	public static void consultarInfo(String clave) {
		
		Connection conex;
		
		conex = conectar();
		
		
		PreparedStatement pstmt;
		try {
			pstmt = conex.prepareStatement("select * from "+clave);
			
//			pstmt.setString(1,clave); 
			
			ResultSet result = pstmt.executeQuery();
			
			while(result.next()) {
				System.out.println("nie "+result.getString(1));
				System.out.println("nombre "+result.getString(2));
				System.out.println("apellido1 "+result.getString(3));
				System.out.println("apellido2 "+result.getString(4));
				System.out.println("email "+result.getString(5));
				//System.out.println("nia "+nia);
			}
			
			
			
			conex.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
}
