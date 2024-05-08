package javaprojectcrud;


import java.sql.*;

import javax.swing.JOptionPane;

public class Conexion {
	
	public static void main (String[] args) {
		
		String usuario = "root";
		String passwordBD = "";
		String url = "jdbc:mysql://localhost:3306/ejemplocrudjava";
		
		Connection conexion;
		Statement statement;
		ResultSet rs;
		
		String option = JOptionPane.showInputDialog("Ingrese opcion:"+ '\n'
				+"1: Insertar registro"+'\n'
				+ "2: Consultar registro"+'\n'
				+ "3: Borrar registro"+'\n'
				+ "4: Actualizar registro");
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			conexion = DriverManager.getConnection(url,usuario,passwordBD);
			if(option.equals("1")) {
				String userName = JOptionPane.showInputDialog("Ingrese el usuario");
				String userPassword = JOptionPane.showInputDialog("Ingrese password");
                                String especialidad = JOptionPane.showInputDialog("Ingrese especialidad");
				insertarUser(userName, userPassword, especialidad, conexion);
			}
			if(option.equals("2")) {
				String userName = JOptionPane.showInputDialog("Ingrese el usuario");
				consultUser(userName,conexion);
			}
			if(option.equals("3")) {
				String userName = JOptionPane.showInputDialog("Ingrese el usuario");
				deleteUser(userName,conexion);
			}
			if(option.equals("4")) {
				String userId = JOptionPane.showInputDialog("Ingrese el Id del usuario");
				String userPassword = JOptionPane.showInputDialog("Ingrese password actualizado");
				updateUser(userId, userPassword, conexion);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertarUser(String userName, String password, String especialidad, Connection conexion) {
		try {
			Statement statement = conexion.createStatement();
			statement.executeUpdate("INSERT INTO userdata(username,userpassword,especialidad) VALUES('"+userName+"','"+password+"','"+especialidad+"')");
			ResultSet rs = statement.executeQuery("SELECT * FROM USERDATA");
			rs.next();
		do {
			System.out.println(rs.getInt("userId")+" : "+rs.getString("username")+" : "+rs.getString("especialidad"));
		}while(rs.next());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void consultUser(String userName, Connection conexion) {
		try {
		Statement statement = conexion.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM USERDATA WHERE userName LIKE '%"+userName+"%'");
		rs.next();
		do {
			System.out.println(rs.getInt("userId")+" : "+rs.getString("username")+" : "+rs.getString("especialidad"));
		}while(rs.next());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteUser(String userName, Connection conexion) {
		try {
			Statement statement = conexion.createStatement();
			statement.executeUpdate("DELETE FROM userdata WHERE userName LIKE '%" + userName + "%'");
			ResultSet rs = statement.executeQuery("SELECT * FROM USERDATA");
			rs.next();
		do {
			System.out.println(rs.getInt("userId")+" : "+rs.getString("username"));
		}while(rs.next());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateUser(String userId, String userPassword, Connection conexion) {
		try {
			Statement statement = conexion.createStatement();
			statement.executeUpdate("UPDATE userdata SET userPassword = ' "+ userPassword +" ' WHERE userId = " + userId +"");
			ResultSet rs = statement.executeQuery("SELECT * FROM USERDATA");
			rs.next();
		do {
			System.out.println(rs.getInt("userId")+" : "+rs.getString("username")+" : "+rs.getString("userPassword")+" : "+rs.getString("especialidad"));
		}while(rs.next());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
