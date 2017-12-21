import java.sql.*;


public class DBConnect {
	private Connection con;
	private Statement  st;
	private ResultSet  rs;
	public DBConnect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection ("jdbc:mysql://localhost:3306/librarydb?autoReconnect=true&useSSL=false","root","9000");
			st = con.createStatement();
			//System.out.println("driver connected");
		} catch (Exception e) {
			System.out.println("Error "+e);
		}
	}
	
	public  ResultSet getData(){
		try {
			String query = "select * from details";
			rs = st.executeQuery(query);
			System.out.println(rs);
			System.out.println("From the database:");
			/**
			while(rs.next()){
				String bookName = rs.getString("bookName");
				String isWith = rs.getString("isWith");
				System.out.println(bookName + "is with" + isWith);
			}
			*/
		} catch (Exception e) {
			System.out.println("getData ERROR: "+e);
		}
		
		return rs;
	}
	
	public void setData(String bookName, String isWith, String mobile){
		try {
			//eg: UPDATE details SET mobile="123" WHERE bookName="The Hunger Games"
			String query = "UPDATE details SET isWith = ?, mobile = ? WHERE bookName = ?";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection ("jdbc:mysql://localhost:3306/librarydb?autoReconnect=true&useSSL=false","root","9000");
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString   (1, isWith);
			preparedStatement.setString   (2, mobile);
			preparedStatement.setString   (3, bookName);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("setData ERROR: "+e);
		}
	}
}
