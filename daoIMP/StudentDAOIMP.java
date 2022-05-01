package daoIMP;
import bean.Student;
import dao.StudentDAO;
import java.sql.*;
import java.util.ArrayList; // 引入 ArrayList 类
import connection.DataBaseConnection;

public abstract class StudentDAOIMP implements StudentDAO{
	// 添加操作
//	PreparedStatement ps = conn.prepareStatement("INSERT INTO students (name, gender, grade, score) VALUES (?, ?, ?, ?)"))
	public void insert(Student s){
	      String sql = "INSERT INTO student (id, name) values (?,?)";
	    PreparedStatement pstmt = null;//预编译语句
	    DataBaseConnection conn = null;
	    //针对数据库的具体操作
	    try{
	        conn = new DataBaseConnection();
	        pstmt = conn.getConnection().prepareStatement(sql);
	        pstmt.setLong(1,s.getID());
	        //pstmt.setString(1,s.getID());
	        pstmt.setString(2,s.getName());
	  
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	        }
	     catch(Exception e){  }
	      }

	    public void update(Student s){   String sql = "UPDATE student set name = ? where id = ?";
			PreparedStatement pstmt = null;
			DataBaseConnection conn = null;
			try{
				conn = new DataBaseConnection();
				pstmt = conn.getConnection().prepareStatement(sql);
				pstmt.setString(1,s.getName());
				pstmt.setLong(2,s.getID());
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			}
			catch(Exception e){  }   }

                    public void delete(long id){ PreparedStatement pstmt = null;
						DataBaseConnection conn = null;
						String sql = "DELETE FROM student where id = ?";
						try{
							conn = new DataBaseConnection();
							pstmt = conn.getConnection().prepareStatement(sql);
							pstmt.setLong(1,id);
							pstmt.executeUpdate();
							pstmt.close();
							conn.close();
						} catch (Exception e) {
							e.printStackTrace();
						}    }

	public ArrayList<Student> findAll() {
		ArrayList<Student> students = new ArrayList<Student>();
		String sql = "select * from student";
		PreparedStatement pstmt = null;
		DataBaseConnection conn = null;

		try {
			conn = new DataBaseConnection();
			pstmt = conn.getConnection().prepareStatement(sql);

			//执行sql，返回结果集
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setID(rs.getInt("id"));
				student.setName(rs.getString("name"));

				students.add(student);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return students;
	}
                    public Student findByID(long iD){   Student student = null;
						String sql = "SELECT * from student where id = ?";
						PreparedStatement pstmt = null;
						DataBaseConnection conn = null;
						try{
							conn = new DataBaseConnection();
							pstmt = conn.getConnection().prepareStatement(sql);
							pstmt.setLong(1,iD);
							ResultSet rs = pstmt.executeQuery();
							if(rs.next())
							{
								student = new Student();
								student.setID(rs.getInt("id"));
								student.setName(rs.getString("name"));
							}
							pstmt.close();
							conn.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						return student;
					} }


