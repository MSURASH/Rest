package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/demo")
public class Demo {
	
	public Demo() {
		
	}

/*	@GET
	@Produces(MediaType.TEXT_XML)
	
	@Path("{name}")
	public String sayHelloXML(@PathParam("name") String name) {
		String response = 
				"<?xml version='1.0'?>"+
		"<hello>Hello " + name + " ANYWAY</hello>";
		return response;
	}*/
	
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sayHelloJSON() {
		String response = "TEST";
		return response;
	}*/
	
/*	@GET
	@Produces(MediaType.TEXT_HTML)
	
	public String sayHelloHTML() {
		
		List<String> data = new ArrayList<>();
		
		Data d1 = new Data("Jacob", 1234, 21, new Date(), "Transfer");
		//d1.age = 21;
		//d1.dob = new Date();
		//d1.file = "Transfer";
		//d1.name = "Jacob";
		//d1.ssn = 123-45-6789;
		
		Data d2 = new Data("Sim", 1234, 22, new Date(), "Transfer");
		//d2.age = 22;
		//d2.dob = new Date();
		//d2.file = "Transfer";
		//d2.name = "Sim";
		//d2.ssn = 123-45-6789;
		
		Data d3 = new Data("Mike", 1234, 23, new Date(), "Transfer");
		//d3.age = 23;
		//d3.dob = new Date();
		//d3.file = "Transfer";
		//d3.name = "Mike";
		//d3.ssn = 123-45-6789;
		
		Data d4 = new Data("Shawn", 12345, 24, new Date(), "Transfer");
		//d4.age = 24;
		//d4.dob = new Date();
		//d4.file = "Transfer";
		//d4.name = "Shawn";
		//d4.ssn = 123-45-6789;
		
		Data d5 = new Data("Charlie", 1234, 25, new Date(), "Transfer");
		//d5.age = 25;
		//d5.dob = new Date();
		//d5.file = "Transfer";
		//d5.name = "Charlie";
		//d5.ssn = 123-45-6789;
		//Data response = "<h1>Hello HTML</h1>";
		data.add("<li>"+d1.toString()+"</li>");
		data.add("<li>"+d2.toString()+"</li>");
		data.add("<li>"+d3.toString()+"</li>");
		data.add("<li>"+d4.toString()+"</li>");
		data.add("<li>"+d5.toString()+"</li>");

		return data.toString();
	}*/
	
	@GET
	//@Path("json/{incoming}")
	@Path("json")
	//@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

	@Produces(MediaType.APPLICATION_JSON)
	//public List<Data> Show(@PathParam("incoming") String incoming) throws ClassNotFoundException, SQLException{
	public List<Data> Show(@QueryParam("teams") String teams) throws ClassNotFoundException, SQLException{

		
		//System.out.print(test());
		//System.out.print(incoming);
		System.out.println("developer 1");
		List<Data> list = showAll(teams);
		//return showAll(incoming);
		return list;

		//return test();

	
	}
	
	@POST
	@Path("insert")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String insert(Data data) throws ClassNotFoundException, SQLException {
		
		//System.out.println(data);
		System.out.println("check");

		Connection con = connect();
		Statement st = con.createStatement();
		String sql = "select MAX(id) as id from team"; 
		 ResultSet rs = st.executeQuery(sql); 
		 int maxID = 0;
		 if (rs.next()) 
		 {
			 maxID = rs.getInt("id");
		 } 
		int ID = maxID + 1;
		System.out.println(ID);
        String sql2 = "insert into team(id, teams, city) "
        + "values('" +ID+ "', '" +data.getTeam().toUpperCase()+ "', '" +data.getCity().toUpperCase()+ "')"; 
        int rows = st.executeUpdate(sql2); 
        if (rows > 0) {
    		System.out.println(rows);

            con.close(); 
    		return "Insert Successful";
        }             
        else  {
        	con.close(); 
    		return "Insert Failed";

        }          
        
	}

	@PUT
	@Path("update")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String update(Data data) throws ClassNotFoundException, SQLException {
		
		Connection con = connect();
		Statement st = con.createStatement();
		String sql = "UPDATE team set teams = '" + data.getTeam().toUpperCase() + "', city = '" + data.getCity().toUpperCase() + "' "
				+ "WHERE id = '" +data.getID()+ "'"; 
        int rows = st.executeUpdate(sql); 
          
        if (rows > 0)  {
        	con.close(); 
    		return "Update Successful";
        }           
        else {
        	con.close(); 
    		return "Update Failed";
        }           
          
        
	}

	
	@DELETE
	@Path("delete")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String delete(Data data) throws ClassNotFoundException, SQLException {
		
		Connection con = connect();
		Statement st = con.createStatement();
		String sql = "DELETE from team WHERE id = '" + data.getID() + "'"; 
                  
        int rows = st.executeUpdate(sql); 
          
        if (rows > 0)  {
        	con.close();
    		return "Delete Successful";
        }           
        else {
        	con.close();
    		return "Delete Failed";
        }
        
        
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/xml")
	public Data XML(){
		//System.out.println("Toast");
		Data d = new Data();
		d.setID(1);
		d.setTeam("Seahawks");
		d.setCity("Seattle");
		
		return d;
	}
	
	

/*	@GET
	@Path("/xml")
	@Produces(MediaType.APPLICATION_XML)
	public Data[] test(){
		List<Data> data = new ArrayList<>();
		Data d1 = new Data(1, "Seahawks", "Seattle");
		Data d2 = new Data(2, "Panthers", "Carolina");
		data.add(d1);
		data.add(d2);
		
		return data.toArray(new Data[data.size()]);
		
	}*/

	public List<Data> showAll(String inteams) throws ClassNotFoundException, SQLException {

		String all, query = "";
		//String all = "";
		List<Data> data = new ArrayList<>();
		Connection con = connect();
		Statement st = con.createStatement();
		//System.out.print(incoming);
		//String response = "";
		Data d;
		if(inteams == null) {
			
			System.out.println("Para is null");
				query  = "select * from team order by id";
				ResultSet rs = st.executeQuery(query);

				while(rs.next()) {
					//String idTemp = Integer.toString(rs.getInt("ID"));

					//all = all +" "+ idTemp +" "+ rs.getString("TEAMS")+" "+ rs.getString("CITY")+ "\r\n";
					//Data i = new Data(rs.getInt("ID"), rs.getString("TEAMS"), rs.getString("CITY"));
					//data.add("<li>"+i.toString()+"</li>");
					//d = new Data(rs.getInt("ID"), rs.getString("TEAMS"), rs.getString("CITY"));
					d = new Data();
					d.setID(rs.getInt("id"));
					d.setTeam(rs.getString("teams"));
					d.setCity(rs.getString("city"));
					data.add(d);

				}
		}else {
			System.out.println("Para is not null");

		String val  = inteams;
		
		String val2 = val.toUpperCase().toString();

		
		
				query  = "select * from team where Teams LIKE '%" + val2 +"%' order by id";

		
		


		ResultSet rs = st.executeQuery(query);
		//data.add("<?xml version='1.0'?>");
		while(rs.next()) {
			//String idTemp = Integer.toString(rs.getInt("ID"));

			//all = all +" "+ idTemp +" "+rs.getString("TEAMS")+" "+ rs.getString("CITY")+ "\r\n";
			//Data i = new Data(rs.getInt("ID"), rs.getString("TEAMS"), rs.getString("CITY"));
			//data.add("<li>"+i.toString()+"</li>");
			//response = response + "<id>"+idTemp+"</id>"+"<team>"+rs.getString("TEAMS")+"</team>"+"<city>"+rs.getString("CITY")+"</city>";		
			//d = new Data(rs.getInt("ID"), rs.getString("TEAMS"), rs.getString("CITY"));
			//System.out.println(d);
			d = new Data();
			d.setID(rs.getInt("id"));
			d.setTeam(rs.getString("teams"));
			d.setCity(rs.getString("city"));
			//data.add(d);

			data.add(d);
		}
		}
		
		//System.out.print(response);
		//return data.toString();
		//System.out.print(data);
		return data;

		

	}
	

	
	private Connection connect() throws SQLException, ClassNotFoundException {
		
		String url = "jdbc:oracle:thin:@localhost:1521/E1LOCAL";
		String user = "JDE";
		String password = "jde";
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection(url,user,password);
		System.out.println("Database Ready...");

		return con;
	}
	
	
}
