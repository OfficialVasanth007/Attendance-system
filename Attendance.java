import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.*;

import java.awt.*;

class login {
	aview av=new aview();
	tview tv=new tview();
	sview sv=new sview();
	int usr;
	JFrame frame = new JFrame("ATTENDANCE");
	public void login() {
		JLabel at=new JLabel("ATTENDACE");
		at.setForeground(Color.decode("#37474F"));
		at.setBounds(150, 260, 400, 50);
		at.setFont(new Font("Verdana", Font.BOLD, 50));
		frame.add(at);
		JLabel ms=new JLabel("MAINTENANCE SYTSEM");
		ms.setForeground(Color.decode("#37474F"));
		ms.setBounds(240,300,350,50);
		ms.setFont(new Font("verdana",Font.BOLD,18));
		frame.add(ms);
		
		JPanel panel=new JPanel();
		panel.setBounds(0,0,500,600);
		panel.setBackground(Color.decode("#DEE4E7"));
		frame.add(panel);
		
		JLabel log=new JLabel("LOGIN");
		log.setForeground(Color.decode("#DEE4E7"));
		log.setBounds(650, 60, 400, 50);
		log.setFont(new Font("Verdana", Font.BOLD, 50));
		frame.add(log);
		
		JLabel user=new JLabel("USERNAME");
		user.setForeground(Color.decode("#DEE4E7"));
		user.setBounds(600, 150, 400, 50);
		user.setFont(null);
		frame.add(user);
		JTextField uf=new JTextField();
		uf.setBounds(600,190, 280, 30);
		uf.setBackground(Color.decode("#DEE4E7"));
		uf.setForeground(Color.decode("#37474F"));
		uf.setFont(new Font("Times New Roman", Font.BOLD, 15));
		frame.add(uf);
		
		JLabel pass=new JLabel("PASSWORD");
		pass.setForeground(Color.decode("#DEE4E7"));
		pass.setBounds(600,230, 400, 50);
		pass.setFont(null);
		frame.add(pass);
		JPasswordField pf=new JPasswordField();
		pf.setBounds(600,270, 280, 30);
		pf.setBackground(Color.decode("#DEE4E7"));
		pf.setForeground(Color.decode("#37474F"));
		pf.setFont(new Font("Times New Roman", Font.BOLD, 15));
		frame.add(pf);
		
		JLabel warning=new JLabel();
		warning.setForeground(Color.RED);
		warning.setBounds(700, 450, 250, 20);
		warning.setHorizontalTextPosition(warning.CENTER);
		
		
		frame.add(warning);
		JButton go=new JButton("Login");
		go.setBounds(690,370, 100, 40);
		go.setBackground(Color.decode("#DEE4E7"));
		go.setForeground(Color.decode("#37474F"));
		go.setFont(new Font("verdana", Font.BOLD, 20));
		frame.add(go);
		
		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int res=dbCheck(uf.getText(),pf.getText());
					if(res==0) {
						warning.setText("no user found!!");
						uf.setText("");
						pf.setText("");
					}
					else if(res==-1) {
						warning.setText("INCORRECT PASSWORD");
						uf.setText("");
						pf.setText("");
					}
					else {
					if(res==1) 
						av.adview(usr);	    
					else if(res==2)
						tv.thview(usr);
					else if(res==3)
					     sv.stview(usr);
								
						  frame.dispose(); 
							}
						 
				}
				
				catch (Exception e1){
					JOptionPane.showMessageDialog(null,e1);
				}
			}
			
		});
		
	
		frame.setSize(1000,600);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);  
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.getContentPane().setBackground(Color.decode("#37474F"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
	}
	public int dbCheck(String name,String password)throws SQLException{
		String str="SELECT * FROM user WHERE username = '"+name+"'";
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance2","root","vasanthvasanth");
		Statement smt=con.createStatement();
		ResultSet rst=smt.executeQuery(str);
	
				
		if(rst.next()) {
			if(rst.getString("pass").equals(password)) {
				usr=rst.getInt("id");
				return rst.getInt("priority");
			}
			else 
				return -1;
		}
		else 
			return 0;
		}
	
    		
	}
class sview{

	 LocalDate currentDate = LocalDate.now();
	 DayOfWeek day = currentDate.getDayOfWeek();
	 DefaultTableModel model=new DefaultTableModel();

	public void stview(int id) throws SQLException {
		JFrame frame=new JFrame("STUDENT");
		
		Font text = new Font("Times New Roman", Font.PLAIN, 18);
		Font btn = new Font("Times New Roman", Font.BOLD, 20);
		
		
		
	
		
		
		
		
		String names[]=welcome(id);
		JLabel wel=new JLabel("WELCOME  "+names[0]+",");
		wel.setFont(btn); 
		wel.setBounds(5,40,220,40);
		wel.setForeground(Color.decode("#DEE4E7"));
		frame.add(wel);
		
		JLabel date=new JLabel("DATE:");
		date.setFont(text);
		date.setBounds(280,40,100,50);
		date.setForeground(Color.decode("#DEE4E7"));
		
		
		JTextField dates=new JTextField();
		dates.setBounds(350,47, 120, 30);
		dates.setBackground(Color.decode("#DEE4E7"));
		dates.setForeground(Color.decode("#37474F"));
		dates.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		dates.setText(String.valueOf(currentDate));
		dates.setEditable(false);
		
		JLabel clss=new JLabel("CLASS :   "+names[1]);
		clss.setBounds(10,130,200,50);
		clss.setForeground(Color.decode("#DEE4E7"));
		clss.setFont(btn);
		
		
		JLabel tc=new JLabel("TOTAL CLASS :");
		tc.setBounds(10,200,200,50);
		tc.setForeground(Color.decode("#DEE4E7"));
		tc.setFont(btn);
		
		
		JLabel ca=new JLabel("CLASSES ATTENDED :");
		ca.setBounds(10,280,250,50);
		ca.setForeground(Color.decode("#DEE4E7"));
		ca.setFont(btn);
		
		
		JLabel ap=new JLabel("ATTENDACE PERCENTAGE:");
		ap.setBounds(10,350,400,50);
		ap.setForeground(Color.decode("#DEE4E7"));
		ap.setFont(btn);
		
		
		
		
		
		JLabel txt = new JLabel();
	    txt.setBounds(40, 70, 400, 200);
		
		txt.setFont(text);
		txt.setBackground(Color.decode("#37474F"));
		txt.setForeground(Color.decode("#DEE4E7"));
		
		frame.add(txt);
		
		
		
		
		if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
	      
	       txt.setText("<html>OOPS! You have logged in during the weekends,<br>the attendance will not be calculated on weekends,<br>go and enjoy outside!</html>");
	    }
		else {
			int newid=getid();
			insert(newid,id,txt);
		}
		
		

		JTable table=new JTable() {
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		model=(DefaultTableModel) table.getModel();
		
		model.addColumn("DATE");
		model.addColumn("STATUS");
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		

		JScrollPane sc=new JScrollPane(table);
		sc.setBounds(500,30,490,550);
		table.setFont(new Font("Times New Roman", Font.BOLD, 20));
		table.setRowHeight(50);
		
		JButton see =new JButton("SEE MORE");
		see.setFont(btn);
		see.setBounds(100,250,250,50);
		see.setForeground(Color.decode("#37474F"));
		see.setBackground(Color.decode("#DEE4E7"));
		frame.add(see);	
		see.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.setVisible(false);
				frame.remove(txt);
				
				frame.add(date);
				frame.add(dates);
				frame.add(sc);
                frame.add(clss);
                
                frame.add(ca);
                
                frame.add(ap);
               
                frame.add(tc);
               
                see.setVisible(false);
                frame.revalidate();
                frame.repaint();
			
				see.setVisible(false);
				try {
					getdetails(id,currentDate,day,tc,ca,ap);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			
			
		});
		
		
		frame.setSize(1000,600);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);  
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.getContentPane().setBackground(Color.decode("#37474F"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
	private int getid() throws SQLException {

	    String url="jdbc:mysql://localhost:3306/attendance2";
		Connection  con=DriverManager.getConnection(url,"root","vasanthvasanth");
		Statement smt=con.createStatement();
		ResultSet rst=smt.executeQuery("SELECT MAX(id) FROM attendance");
		if(rst.next())
		{
			return rst.getInt("MAX(id)")+1;
		}	
		return 1;
	}
private void getdetails(int id,LocalDate currentDate,DayOfWeek day,JLabel tc,JLabel ca ,JLabel ap) throws SQLException {
	 String url="jdbc:mysql://localhost:3306/attendance2";
		Connection  con=DriverManager.getConnection(url,"root","vasanthvasanth");
		String attendanceQuery="SELECT * FROM attendance WHERE userid=?";
		PreparedStatement pst=con.prepareStatement(attendanceQuery);
				pst.setInt(1, id);
				ResultSet rs=pst.executeQuery();
				
				
				Set<LocalDate> attendanceDates=new HashSet<>();
				while(rs.next()) {
					attendanceDates.add(rs.getDate("date").toLocalDate());
				}
				
				LocalDate startDate=LocalDate.of(2024, 06, 01);
				
				if(day==DayOfWeek.SUNDAY) {
					currentDate=currentDate.minusDays(2);
				}
				else if(day==DayOfWeek.SATURDAY) {
					currentDate=currentDate.minusDays(1);
				}
				
				int totalClasses=0;
				int attendedClasses=0;
				for(LocalDate date=startDate;!date.isAfter(currentDate);date=date.plusDays(1)) {
					DayOfWeek dofweek=date.getDayOfWeek();
					if(dofweek == DayOfWeek.SATURDAY||dofweek == DayOfWeek.SUNDAY) {
						continue;
					}
					totalClasses++;
					String status=attendanceDates.contains(date)?"Present":"Absent";
					if(status.equals("Present")) {
						attendedClasses++;
					}
					model.addRow(new Object[] {date.toString(),status});
					
				}
				double percentage=(totalClasses>0)?((double)attendedClasses/totalClasses)*100:0.0;
			    tc.setText("TOTAL CLASS:  "+totalClasses);
			    ca.setText("CLASSES ATTENDED :  "+attendedClasses);
			    ap.setText("ATTENDANCE PERCENTAGE :   "+percentage);
			}

	public String[] welcome(int id) throws SQLException {
		String s="SELECT * FROM student WHERE id="+id;
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance2","root","vasanthvasanth");
		Statement smt=con.createStatement();
		ResultSet rst=smt.executeQuery(s);
		String data[]=new String[3];
		if(rst.next()) {
		data[0]=String.valueOf(rst.getString("name"));	
		data[1]=String.valueOf(rst.getString("class"));
		
		return data;
		}
		return null;
		
	}
	private void insert(int newid,int id,JLabel txt) throws SQLException {
		String checkQuery="Select * From attendance where userid = ? AND date = ?";
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance2", "root", "vasanthvasanth");
		  PreparedStatement checkStmt = con.prepareStatement(checkQuery);
		  checkStmt.setInt(1, id);
		  checkStmt.setDate(2, Date.valueOf(currentDate)); 
		ResultSet check=checkStmt.executeQuery();
		
		if(check.next()) {
			txt.setText("You have already marked attendance for today");
		}
		else {
			String insertQuery="INSERT INTO attendance (id,userid,date) values (?,?,?) ";
			PreparedStatement insertValue=con.prepareStatement(insertQuery);
			insertValue.setInt(1, newid);
			insertValue.setInt(2, id);
			insertValue.setDate(3,Date.valueOf(currentDate));
			insertValue.executeUpdate();
			txt.setText("<html> Your Attendace For "+Date.valueOf(currentDate)+"<br>has succefully marked");
			
		}
		
		
	}

}

class aview {
	
	
	public void adview(int id) throws SQLException {
	
		
		JFrame frame=new JFrame("ADMIN");
	Font tnr=new Font("Times New Roman", Font.BOLD, 20);
		String name=welcome(id);
	JLabel wel=new JLabel("WELCOME ");
	wel.setFont(new Font("verdana",Font.BOLD,15));
	wel.setBounds(5,0,160,40);
	wel.setForeground(Color.decode("#DEE4E7"));
	frame.add(wel);
	

	
	
	JButton stud=new JButton("Student");
	stud.setFont(tnr);
	stud.setBounds(250,100,500,50);
	stud.setForeground(Color.decode("#37474F"));
	stud.setBackground(Color.decode("#DEE4E7"));
	frame.add(stud);	
	
	stud.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			student st=new student();
			try {
				st.studentView();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	});
	JButton at=new JButton("View Attendance");
	at.setFont(tnr);
	at.setBounds(250,200,500,50);
	at.setForeground(Color.decode("#37474F"));
	at.setBackground(Color.decode("#DEE4E7"));
	frame.add(at);
	at.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			vattendance vat=new vattendance();
			try {
				vat.viewAtt();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	});
	
	
	
	
	JButton th=new JButton("Teachers");
	th.setFont(tnr);
	th.setBounds(250,300,500,50);
	th.setForeground(Color.decode("#37474F"));
	th.setBackground(Color.decode("#DEE4E7"));
	frame.add(th);
	
	JButton cls=new JButton("CLASS");
	cls.setFont(tnr);
	cls.setBounds(250,400,500,50);
	cls.setForeground(Color.decode("#37474F"));
	cls.setBackground(Color.decode("#DEE4E7"));
	frame.add(cls);
	cls.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			classes cls=new classes();
			cls.classess();
		}
		
	});
	
	
	th.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			teachers tt=new teachers();
			try {
				tt.teacher();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}	
	});
		wel.setText("WELCOME "+name);
	frame.setSize(1000,600);
	frame.setResizable(false);
	frame.setLayout(null);
	frame.setLocationRelativeTo(null);  
	frame.setVisible(true);
	frame.setFocusable(true);
	frame.getContentPane().setBackground(Color.decode("#37474F"));
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       }
	public String welcome(int id) throws SQLException {
		String s="SELECT * FROM user WHERE id="+id;
	
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance2","root","vasanthvasanth");
		Statement smt=con.createStatement();
		ResultSet rst=smt.executeQuery(s);
		if(rst.next()) {
		String name=String.valueOf(rst.getString("name"));	
		return name;
		}
		return null;
		
	}
		
		
	
	}
class vattendance{
	JFrame frame=new JFrame("VIEW ATTENDACE");
	Connection con;
	 DefaultTableModel model=new DefaultTableModel();
	 JButton save,edit,submit,view;
	  LocalDate currentDate = LocalDate.now();

	public void viewAtt() throws SQLException {
		
		Font text = new Font("Times New Roman", Font.PLAIN, 18);
		Font btn = new Font("Times New Roman", Font.BOLD, 20);
			JLabel back = new JLabel("< BACK");
			back.setForeground(Color.black);
			back.setFont(new Font("Times New Roman", Font.BOLD, 17));
			back.setBounds(18, 10, 100, 20);
			frame.add(back);
			back.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
				}
			});
			
			JPanel panel = new  JPanel();
			panel.setBounds(0, 0, 1000, 35);
			panel.setBackground(Color.decode("#DEE4E7"));
			frame.add(panel);
			
			
			
			
			JLabel classes = new JLabel("CLASS : ");
			classes.setFont(text);
			classes.setBounds(25, 130, 100, 20);
			classes.setForeground(Color.decode("#DEE4E7"));
			frame.add(classes);
			
			@SuppressWarnings("unchecked")
			JComboBox clss= new JComboBox(classEt());
			clss.setBounds(100, 130, 200, 25);
			clss.setEnabled(true);
			clss.setEditable(false);
			frame.add(clss);
			clss.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
				
			});
			
			
			JLabel date = new JLabel("DATE : ");
			date.setFont(text);
			date.setBounds(25, 200, 150, 20);
			date.setForeground(Color.decode("#DEE4E7"));
			frame.add(date);
			JTextField dbox= new JTextField();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        dbox.setText(currentDate.format(formatter));
			dbox.setBounds(100, 200, 220, 35);
			dbox.setBackground(Color.decode("#DEE4E7"));
			dbox.setFont(text);
			
			dbox.setForeground(Color.decode("#37474F"));
			dbox.setEditable(true);
			frame.add(dbox);
			
			JLabel status=new JLabel("STATUS :");
			status.setFont(text);
			status.setBounds(25, 280, 120, 20);
			status.setForeground(Color.decode("#DEE4E7"));
			
			String[]stattus= {"Present","Absent"};
			JComboBox stausbox= new JComboBox(stattus);
			stausbox.setBounds(120, 280, 180, 25);
			stausbox.setEnabled(true);
			stausbox.setEditable(false);
			
			
			stausbox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
				
			});
			
			
			save = new JButton("SAVE");
			save.setBounds(25, 500, 100, 50);
			save.setFont(btn);
			save.setBackground(Color.decode("#DEE4E7"));
			save.setForeground(Color.decode("#37474F"));
			save.setEnabled(false);
			
			
			edit = new JButton("EDIT");
			edit.setBounds(175, 500, 100, 50);
			edit.setFont(btn);
			edit.setEnabled(false);
			edit.setBackground(Color.decode("#DEE4E7"));
			edit.setForeground(Color.decode("#37474F"));
			frame.add(edit);
			
			
			
			view = new JButton("VIEW");
			view.setBounds(175, 432, 100, 50);
			view.setFont(btn);
			view.setBackground(Color.decode("#DEE4E7"));
			view.setForeground(Color.decode("#37474F"));
			view.setEnabled(true);
			frame.add(view);
			view.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        try {
			            String curdate = dbox.getText();
			            LocalDate selectedDate = LocalDate.parse(curdate, formatter);
			            
			            if (isValidDate(selectedDate)) {
			                while (model.getRowCount() > 0) {
			                    model.removeRow(0);
			                }
			                String selectedClass = (String) clss.getSelectedItem();
			                tableupt(curdate, selectedClass);
			                edit.setEnabled(true);
			            }
			        } catch (DateTimeParseException ex) {
			            JOptionPane.showMessageDialog(frame, "Invalid date format", "Error", JOptionPane.ERROR_MESSAGE);
			        } catch (SQLException e1) {
			            e1.printStackTrace();
			        }
			    }
			});
			
			submit = new JButton("SUBMIT");
			submit.setBounds(300, 500, 150, 50);
			submit.setFont(btn);
			submit.setBackground(Color.decode("#DEE4E7"));
			submit.setForeground(Color.decode("#37474F"));
			submit.setEnabled(false);
			submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				stausbox.setVisible(false);
				view.setEnabled(true);
				status.setVisible(false);
				edit.setEnabled(false);
				save.setVisible(false);
				submit.setVisible(false);
				dbox.setEditable(true);
				clss.setEnabled(true);
				}
				
			});
			
			
			

			JTable table=new JTable() {
				public boolean isCellEditable(int row,int column) {
					return false;
				}
			};
			model=(DefaultTableModel) table.getModel();
			model.addColumn("ID");
			model.addColumn("NAME");
			model.addColumn("CLASS");
			model.addColumn("ATTENDANCE STATUS");
			model.addColumn("DATE");
			
			table.getColumnModel().getColumn(0).setPreferredWidth(20);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(120);
			table.getColumnModel().getColumn(3).setPreferredWidth(160);
			table.getColumnModel().getColumn(4).setPreferredWidth(100);

			JScrollPane sc=new JScrollPane(table);
			sc.setBounds(500,50,490,525);
			frame.add(sc);
			

			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					
					
					status.setVisible(true);
		            stausbox.setVisible(true);
		            frame.add(status);
		            frame.add(stausbox);
		            frame.revalidate();
		            frame.repaint();
					int row=table.getSelectedRow();
					
					 String currentStatus = (String) table.getValueAt(row, 3); // Assuming status is in the 4th column (index 3)
			            stausbox.setSelectedItem(currentStatus);
					
				}
			});
			
			edit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.add(save);
					frame.add(submit);
					save.setEnabled(true);
					submit.setEnabled(true);
					submit.setVisible(true);
					view.setEnabled(false);
					clss.setEnabled(false);
					dbox.setEditable(false);
					save.setVisible(true);
					
				}
				
			});
			save.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        int selectedRow = table.getSelectedRow();
			        if (selectedRow != -1) {
			            int studentId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
			            String oldStatus = (String) table.getValueAt(selectedRow, 3);
			            String newStatus = (String) stausbox.getSelectedItem();
			            String date = dbox.getText();
			            LocalDate attendanceDate = LocalDate.parse(date);
			            
			            if (!isValidDate(attendanceDate)) {
			                return;
			            }
			            
			            if (oldStatus.equals(newStatus)) {
			                JOptionPane.showMessageDialog(frame, "No changes made. Update successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
			            } else {
			                try {
			                    updateAttendanceStatus(studentId, newStatus, date);
			                    JOptionPane.showMessageDialog(frame, "Attendance updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
			                    // Refresh the table
			                    String selectedClass = (String) clss.getSelectedItem();
			                    while (model.getRowCount() > 0) 
			                        model.removeRow(0);
			                    tableupt(date, selectedClass);
			                } catch (SQLException ex) {
			                    JOptionPane.showMessageDialog(frame, "Error updating attendance: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			                }
			            }
			        } else {
			            JOptionPane.showMessageDialog(frame, "Please select a row to update", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			});
			frame.setSize(1000,600);
			frame.setResizable(false);
			frame.setLayout(null);
			frame.setLocationRelativeTo(null);  
			frame.setVisible(true);
			frame.setFocusable(true);
			frame.getContentPane().setBackground(Color.decode("#37474F"));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
	}
	public boolean isValidDate(LocalDate date) {
		 LocalDate academicYearStart = LocalDate.of(2024, 6, 1);
		    DayOfWeek day = date.getDayOfWeek();

		    if (date.isAfter(LocalDate.now())) {
		        JOptionPane.showMessageDialog(frame, "Future dates are not allowed", "Error", JOptionPane.ERROR_MESSAGE);
		        return false;
		    } else if (date.isBefore(academicYearStart)) {
		        JOptionPane.showMessageDialog(frame, "Dates before June 1, 2024 are not allowed as the academic year starts from that date", "Error", JOptionPane.ERROR_MESSAGE);
		        return false;
		    } else if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
		        JOptionPane.showMessageDialog(frame, "Attendance cannot be managed for weekends", "Error", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }
		return true;
	}
	public void updateAttendanceStatus(int studentId, String newStatus, String date) throws SQLException {
				
		String url = "jdbc:mysql://localhost:3306/attendance2";
	    Connection con = DriverManager.getConnection(url, "root", "vasanthvasanth");
	    
	    if (newStatus.equals("Present")) {
	        // Insert attendance record
	    	int id=getid();
	        String insertQuery = "INSERT INTO attendance (id,userid, date) VALUES (?,?, ?)";
	        PreparedStatement pstmt = con.prepareStatement(insertQuery);
	        pstmt.setInt(1, id);
	        pstmt.setInt(2, studentId);
	        pstmt.setString(3, date);
	        pstmt.executeUpdate();
	    } else if (newStatus.equals("Absent")) {
	        // Remove attendance record
	        String deleteQuery = "DELETE FROM attendance WHERE userid = ? AND date = ?";
	        PreparedStatement pstmt = con.prepareStatement(deleteQuery);
	        pstmt.setInt(1, studentId);
	        pstmt.setString(2, date);
	        pstmt.executeUpdate();
	    }
	    
	    
	}
			
	public int getid() throws SQLException {
		

	    String url="jdbc:mysql://localhost:3306/attendance2";
		 con=DriverManager.getConnection(url,"root","vasanthvasanth");
		Statement smt=con.createStatement();
		ResultSet rst=smt.executeQuery("SELECT MAX(id) FROM attendance");
		if(rst.next())
		{
			return rst.getInt("MAX(id)")+1;
		}	
		return 1;
	    
	}
	public void tableupt(String dta, String selectedClass) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/attendance2";
		con = DriverManager.getConnection(url, "root", "vasanthvasanth");
		 try {
		        ResultSet rst = DbSearch(dta, selectedClass);
		        
		        
		        while (rst.next()) {
		            model.addRow(new Object[]{
		                rst.getInt("student_id"),
		                rst.getString("student_name"),
		                rst.getString("class"),
		                rst.getString("attendance_status"),
		                rst.getString("date")
		            });
		        }
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, e);
		    }
    }


public ResultSet DbSearch(String fetchdate, String selectedClass) throws SQLException {
	String url = "jdbc:mysql://localhost:3306/attendance2";
    String query;
    
    if (selectedClass.equals("All")) {
        query = "SELECT s.id AS student_id, s.name AS student_name, s.class, " +
                "CASE WHEN a.id IS NOT NULL THEN 'Present' ELSE 'Absent' END AS attendance_status, " +
                "COALESCE(a.date, ?) AS date " +
                "FROM student s " +
                "LEFT JOIN attendance a ON s.id = a.userid AND a.date = ? " +
                "ORDER BY s.id";
    } else {
        query = "SELECT s.id AS student_id, s.name AS student_name, s.class, " +
                "CASE WHEN a.id IS NOT NULL THEN 'Present' ELSE 'Absent' END AS attendance_status, " +
                "COALESCE(a.date, ?) AS date " +
                "FROM student s " +
                "LEFT JOIN attendance a ON s.id = a.userid AND a.date = ? " +
                "WHERE s.class = ? " +
                "ORDER BY s.id";
    }
    
    con = DriverManager.getConnection(url, "root", "vasanthvasanth");
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setString(1, fetchdate);
    pstmt.setString(2, fetchdate);
    if (!selectedClass.equals("All")) {
        pstmt.setString(3, selectedClass);
    }
    return pstmt.executeQuery();
}
 
	
		
		public String[] classEt() throws SQLException {
			 String url="jdbc:mysql://localhost:3306/attendance2";
			 con=DriverManager.getConnection(url,"root","vasanthvasanth");
			String str1 = "SELECT name from class";
			Statement stm = con.createStatement();
			ResultSet rst = stm.executeQuery(str1);
			String[] rt = new String[25];
			int i=1;
			rt[0]="All";
			while(rst.next()) {
				rt[i] = rst.getString("name");
				i++;
			}
			return rt;  
	    }
	
		
	
	
}
class tview{
	JFrame frame=new JFrame("TEACHER");
	public void thview(int id)throws SQLException {
		Font tnr=new Font("Times New Roman", Font.BOLD, 20);
		String name=welcome(id);
		JLabel wel=new JLabel("WELCOME "+name);
		wel.setFont(new Font("verdana",Font.BOLD,15));
		wel.setBounds(5,0,160,40);
		wel.setForeground(Color.decode("#DEE4E7"));
		frame.add(wel);
		
	JButton view =new JButton("View Attendance");
	view.setBounds(350,200,300,50);
	view.setFont(tnr);
	view.setForeground(Color.decode("#37474F"));
	view.setBackground(Color.decode("#DEE4E7"));
	frame.add(view);
	view.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			vattendance vat=new vattendance();
			try {
				vat.viewAtt();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	});
	
	JButton st =new JButton("STUDENTS");
	st.setBounds(350,300,300,50);
	st.setFont(tnr);
	st.setForeground(Color.decode("#37474F"));
	st.setBackground(Color.decode("#DEE4E7"));
	frame.add(st);
	st.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			student st=new student();
			try {
				st.studentView();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		
	});
	
		
	frame.setSize(1000,600);
	frame.setResizable(false);
	frame.setLayout(null);
	frame.setLocationRelativeTo(null);  
	frame.setVisible(true);
	frame.setFocusable(true);
	frame.getContentPane().setBackground(Color.decode("#37474F"));
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
	public String welcome(int id) throws SQLException {
		String s="SELECT * FROM user WHERE id="+id;
	
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance2","root","vasanthvasanth");
		Statement smt=con.createStatement();
		ResultSet rst=smt.executeQuery(s);
		if(rst.next()) {
		String name=String.valueOf(rst.getString("name"));	
		return name;
		}
		return null;
		
	}
}
 class teachers{
	 DefaultTableModel model=new DefaultTableModel();
	 int check;
	 Connection con;
	
	
	 JButton edit,delete,add,save;
	 
	 public void teacher() throws SQLException{ 
		
			Font text = new Font("Times New Roman", Font.PLAIN, 18);
			Font btn = new Font("Times New Roman", Font.BOLD, 20);

			JFrame frame = new JFrame();
			JLabel back = new JLabel("< BACK");
			back.setForeground(Color.black);
			back.setFont(new Font("Times New Roman", Font.BOLD, 17));
			back.setBounds(18, 10, 100, 20);
			frame.add(back);
			back.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
				}
			});
			JPanel panel = new  JPanel();
			panel.setBounds(0, 0, 1000, 35);
			panel.setBackground(Color.decode("#DEE4E7"));
			
			frame.add(panel);
			
			
			JLabel id=new JLabel("ID: ");
			id.setFont(text);
			id.setBounds(25, 60, 40, 20);
			id.setForeground(Color.decode("#DEE4E7"));
			frame.add(id);
			JTextField idbox=new JTextField();
			idbox.setBounds(60, 60, 50, 25);
			idbox.setBackground(Color.decode("#DEE4E7"));
			idbox.setFont(text);
			idbox.setForeground(Color.decode("#37474F"));
			idbox.setEditable(false);
			idbox.setText(String.valueOf(getid()));
			frame.add(idbox);
			
			JLabel user=new JLabel("USERNAME :");
			user.setFont(text);
			user.setBounds(25, 120, 150, 20);
			user.setForeground(Color.decode("#DEE4E7"));
			frame.add(user);
			JTextField username= new JTextField();
			username.setBounds(25, 160, 400, 35);
			username.setBackground(Color.decode("#DEE4E7"));
			username.setFont(text);
			username.setForeground(Color.decode("#37474F"));
			username.setEditable(false);
			frame.add(username);
			
			JLabel nm = new JLabel("NAME : ");
			nm.setFont(text);
			nm.setBounds(25, 240, 150, 20);
			nm.setForeground(Color.decode("#DEE4E7"));
			frame.add(nm);
			JTextField name= new JTextField();
			name.setBounds(25, 270, 400, 35);
			name.setBackground(Color.decode("#DEE4E7"));
			name.setFont(text);
			name.setForeground(Color.decode("#37474F"));
			name.setEditable(false);
			frame.add(name);
			
			JLabel pass = new JLabel("PASSWORD : ");
			pass.setFont(text);
			pass.setBounds(25, 350, 150, 20);
			pass.setForeground(Color.decode("#DEE4E7"));
			frame.add(pass);
			JTextField password= new JTextField();
			password.setBounds(25, 380, 400, 35);
			password.setBackground(Color.decode("#DEE4E7"));
			password.setFont(text);
			password.setForeground(Color.decode("#37474F"));
			password.setEditable(false);
			frame.add(password);
			
			 save = new JButton("SAVE");
			save.setBounds(175, 500, 125, 50);
			save.setFont(btn);
			save.setBackground(Color.decode("#DEE4E7"));
			save.setForeground(Color.decode("#37474F"));
			save.setEnabled(false);
			frame.add(save);
			save.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				
					if(check==1)
					{
						try {
							adder(Integer.parseInt(idbox.getText()),username.getText(),name.getText(),password.getText());
						} catch (NumberFormatException | SQLException e1) {
							JOptionPane.showMessageDialog(null, e1);
						}
					}
					else if(check==2) {
						save.setEnabled(false);
						if(password.getText().equals("")) {
						try {
							editor(Integer.parseInt(idbox.getText()),username.getText(),name.getText());
						} catch (NumberFormatException | SQLException e1) {
							JOptionPane.showMessageDialog(null, e1);
						}
						
						}
						else {
							try {
								editor(Integer.parseInt(idbox.getText()),username.getText(),name.getText(),password.getText());
							} catch (NumberFormatException | SQLException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, e1);
							}
						}
					}
					try {
						idbox.setText(String.valueOf(getid()));
					
					
					edit.setEnabled(false);
					delete.setEnabled(false);
					username.setText("");
					name.setText("");
					password.setText("");
					while(model.getRowCount()>0)
						model.removeRow(0);
					tableupt();
					} 
					catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}

				

				
	 
			
				}
			});
			
				
			
			edit = new JButton("EDIT");
			edit.setBounds(25, 500, 125, 50);
			edit.setFont(btn);
			edit.setEnabled(false);
			edit.setBackground(Color.decode("#DEE4E7"));
			edit.setForeground(Color.decode("#37474F"));
			frame.add(edit);
			edit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					edit.setEnabled(false);
					save.setEnabled(true);
					check=2;
					username.setEditable(true);
					name.setEditable(true);
					password.setEditable(true);
				}
				
			});
			
			add = new JButton("ADD");
			add.setBounds(325, 500, 125, 50);
			add.setFont(btn);
			add.setBackground(Color.decode("#DEE4E7"));
			add.setForeground(Color.decode("#37474F"));
			frame.add(add);
			add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					add.setEnabled(false);
					edit.setEnabled(false);
					save.setEnabled(true);
					delete.setEnabled(true);
					username.setEditable(true);
					name.setEditable(true);
					password.setEditable(true);
					check=1;
					try {
						idbox.setText(String.valueOf(getid()));
					} catch (SQLException e1) {
				
						e1.printStackTrace();
					}
					
				}
				
			});
			
			delete = new JButton("DELETE");
			delete.setBounds(175, 432, 125, 50);
			delete.setFont(btn);
			delete.setBackground(Color.decode("#DEE4E7"));
			delete.setForeground(Color.decode("#37474F"));
			delete.setEnabled(false);
			frame.add(delete);
			delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					edit.setEnabled(false);
					add.setEnabled(true);
				
					username.setEditable(false);
					password.setEditable(false);
					name.setEditable(false);
					try {
						deleter(Integer.parseInt(idbox.getText()));
						idbox.setText(String.valueOf(getid()));
					username.setText("");
					password.setText("");
					name.setText("");
					
					
					while(model.getRowCount()>0)
						model.removeRow(0);
					tableupt();
					
					
					} catch (SQLException e1) {
					
						e1.printStackTrace();
					}
				}
				

				
				
			});
			
		
			JTable table=new JTable() {
				public boolean isCellEditable(int row,int column) {
					return false;
				}
			};
			model=(DefaultTableModel) table.getModel();
			model.addColumn("ID");
			model.addColumn("USERNAME");
			model.addColumn("NAME");
			
			tableupt();
			table.getColumnModel().getColumn(0).setPreferredWidth(50);
			table.getColumnModel().getColumn(1).setPreferredWidth(200);
			table.getColumnModel().getColumn(2).setPreferredWidth(200);
			JScrollPane sc=new JScrollPane(table);
			sc.setBounds(500,50,485,525);
			frame.add(sc);
			
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row=table.getSelectedRow();
				password.setText("");
				idbox.setText(String.valueOf(table.getModel().getValueAt(row,0)));
				username.setText(String.valueOf(table.getModel().getValueAt(row,1)));
				name.setText(String.valueOf(table.getModel().getValueAt(row,2)));
				edit.setEnabled(true);
				save.setEnabled(false);
				delete.setEnabled(true);
				username.setEditable(false);
				password.setEditable(false);
				name.setEditable(false);


			}
		});
			frame.setSize(1000,600);
			frame.setResizable(false);
			frame.setLayout(null);
			frame.setLocationRelativeTo(null);  
			frame.setVisible(true);
			frame.setFocusable(true);
			frame.getContentPane().setBackground(Color.decode("#37474F"));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	 }
	 public void editor(int id, String username, String name) throws SQLException {
		 String s1="UPDATE user set username='"+username+"',name='"+name+"' WHERE id= "+id;
		 Statement smt=con.createStatement();
		 smt.executeUpdate(s1);
		 
					
				}
                   public  void deleter(int id) throws SQLException {
                	   
                	   String del = "DELETE FROM user WHERE id = ?";
                	    PreparedStatement pstmt = con.prepareStatement(del);
                	    pstmt.setInt(1, id);
                	    pstmt.executeUpdate();
						
				}
				public void editor(int id, String username, String name,String password) throws SQLException {
					String s1="UPDATE user SET username='"+username+"',name='"+name+"',pass='"+password+"' WHERE id="+id;
					Statement smt=con.createStatement();
					smt.executeUpdate(s1);
				}

				public void adder(int id, String username, String name, String password) throws SQLException {
					String s1="INSERT into user values("+id+", '"+username+"' , '"+name+"' , '"+password+"' ,2)";
					Statement smtt=con.createStatement();
					smtt.executeUpdate(s1);
				
				}

	private int getid() throws SQLException {
		
		String url="jdbc:mysql://localhost:3306/attendance2";
		 con=DriverManager.getConnection(url,"root","vasanthvasanth");
		Statement smt=con.createStatement();
		ResultSet rst=smt.executeQuery("SELECT MAX(id) FROM user");
		if(rst.next())
		{
			return rst.getInt("MAX(id)")+1;
		}	
		return 1;
	}

	

	private void tableupt() {
		try {
			ResultSet rst=DbSearch();
			for(int i=0;rst.next();i++)
			{
				model.addRow(new Object[0]);
				model.setValueAt(rst.getInt("id"),i,0);
				model.setValueAt(rst.getString("username"),i,1);
				model.setValueAt(rst.getString("name"), i, 2);		
			}
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null,e);
		}
	
	}
	private ResultSet DbSearch() throws SQLException {
		String url="jdbc:mysql://localhost:3306/attendance2";
		String ss=" SELECT * FROM user WHERE priority = 2 ";
		 con=DriverManager.getConnection(url,"root","vasanthvasanth");
		 Statement smt=con.createStatement();
		ResultSet rst=smt.executeQuery(ss);
		return rst;
	} 
	
}
 class classes{
	 DefaultTableModel model=new DefaultTableModel();
	 Connection con;
	 int check;
	 JButton save,edit,delete,add;
	 
	 public void classess() {
		 JFrame frame=new JFrame("CLASS DATABASE");
		 Font text = new Font("Times New Roman", Font.PLAIN, 18);
			Font btn = new Font("Times New Roman", Font.BOLD, 20);
			JLabel back = new JLabel("< BACK");
			back.setForeground(Color.decode("#37474F"));
			back.setFont(new Font("Times New Roman", Font.BOLD, 17));
			back.setBounds(18, 10, 100, 20);
			frame.add(back);
			back.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
				}
			});
			JPanel panel = new  JPanel();
			panel.setBounds(0, 0, 1000, 35);
			panel.setBackground(Color.decode("#DEE4E7"));
			frame.add(panel);
			
			JLabel id = new JLabel("ID : ");
			id.setFont(text);
			id.setBounds(25, 150, 40, 20);
			id.setForeground(Color.decode("#DEE4E7"));
			frame.add(id);
			JTextField idbox= new JTextField();
			idbox.setBounds(60, 150, 50, 25);
			idbox.setBackground(Color.decode("#DEE4E7"));
			idbox.setFont(text);
			idbox.setForeground(Color.decode("#37474F"));
			idbox.setEditable(false);
			frame.add(idbox);
			
			JLabel nm = new JLabel("NAME : ");
			nm.setFont(text);
			nm.setBounds(25, 240, 150, 20);
			nm.setForeground(Color.decode("#DEE4E7"));
			frame.add(nm);
			JTextField name= new JTextField();
			name.setBounds(25, 270, 400, 35);
			name.setBackground(Color.decode("#DEE4E7"));
			name.setFont(text);
			name.setForeground(Color.decode("#37474F"));
			name.setEditable(false);
			frame.add(name);
			
			save = new JButton("SAVE");
			save.setBounds(25, 500, 125, 50);
			save.setFont(btn);
			save.setBackground(Color.decode("#DEE4E7"));
			save.setForeground(Color.decode("#37474F"));
			save.setEnabled(false);
			frame.add(save);
			save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if(check==1)
					{
						try {
							adder(Integer.parseInt(idbox.getText()),name.getText());
						} catch (NumberFormatException | SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e1);
						}
					}
					else if(check==2) {
						save.setEnabled(false);
						try {
							editor(Integer.parseInt(idbox.getText()),name.getText());
						} catch (NumberFormatException | SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e1);
						}
						
						
						
						}
					
					try {
						idbox.setText(String.valueOf(getid()));
					edit.setEnabled(false);
					delete.setEnabled(false);
					add.setEnabled(true);
					save.setEnabled(false);
					
					name.setText("");
					while(model.getRowCount()>0)
						model.removeRow(0);
					tableupt();
					} 
					catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}

				}
				
			});
			
			edit = new JButton("EDIT");
			edit.setBounds(175, 500, 125, 50);
			edit.setFont(btn);
			edit.setEnabled(false);
			edit.setBackground(Color.decode("#DEE4E7"));
			edit.setForeground(Color.decode("#37474F"));
			frame.add(edit);
			edit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					edit.setEnabled(false);
					save.setEnabled(true);
					check=2;
					name.setEditable(true);
				}
				
			});
			
			add = new JButton("ADD");
			add.setBounds(325, 500, 125, 50);
			add.setFont(btn);
			add.setBackground(Color.decode("#DEE4E7"));
			add.setForeground(Color.decode("#37474F"));
			add.setEnabled(true);
			frame.add(add);
			add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					add.setEnabled(false);
					save.setEnabled(true);
					check=1;
					name.setEditable(true);
					try {
						idbox.setText(String.valueOf(getid()));
					} catch (SQLException e1) {
				
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				
			});
			
			
			delete = new JButton("DELETE");
			delete.setBounds(175, 432, 125, 50);
			delete.setFont(btn);
			delete.setBackground(Color.decode("#DEE4E7"));
			delete.setForeground(Color.decode("#37474F"));
			delete.setEnabled(false);
			frame.add(delete);
			delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					edit.setEnabled(false);
					add.setEnabled(true);
					name.setEditable(false);
					delete.setEnabled(false);
					try {
						deleter(Integer.parseInt(idbox.getText()));
						idbox.setText(String.valueOf(getid()));
					    name.setText("");
					while(model.getRowCount()>0)
						model.removeRow(0);
					tableupt();
					}
					catch (SQLException e1) {
						
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				
			});
			
			JTable table=new JTable() {
				public boolean isCellEditable() {
					return false;
				}
				
			};
			model=(DefaultTableModel)table.getModel();
			model.addColumn("ID");
			model.addColumn("NAME");
			tableupt();
			table.getColumnModel().getColumn(0).setPreferredWidth(100);
			table.getColumnModel().getColumn(1).setPreferredWidth(300);
			JScrollPane scPane=new JScrollPane(table);
			scPane.setBounds(500, 50, 480, 525);
			frame.add(scPane);
			
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int row=table.getSelectedRow();
					idbox.setText(String.valueOf(table.getModel().getValueAt(row, 0)));
					name.setText(String.valueOf(table.getModel().getValueAt(row, 1)));
					edit.setEnabled(true);
					delete.setEnabled(true);
					save.setEnabled(false);
					add.setEnabled(false);
					
				}
			});
			frame.setSize(1000,600);
			frame.setResizable(false);
			frame.setLayout(null);
			frame.setLocationRelativeTo(null);  
			frame.setVisible(true);
			frame.setFocusable(true);
			frame.getContentPane().setBackground(Color.decode("#37474F"));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		 
		 
	 }
	 public void tableupt() {
		 try {
				ResultSet rst=DbSearch();
				for(int i=0;rst.next();i++)
				{
					model.addRow(new Object[0]);
					model.setValueAt(rst.getInt("id"),i,0);
					model.setValueAt(rst.getString("name"),i,1);
				
				}
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null,e);
			}
		 
	 }
	 public ResultSet DbSearch() throws SQLException {
		 String url="jdbc:mysql://localhost:3306/attendance2";
			String ss=" SELECT * FROM class ";
			 con=DriverManager.getConnection(url,"root","vasanthvasanth");
			 Statement smt=con.createStatement();
			ResultSet rst=smt.executeQuery(ss);
			return rst;
		
		 
	 }
	 public void editor(int id,  String name) throws SQLException {
		 String s1="UPDATE class set name='"+name+"' WHERE id= "+id;
		 Statement smt=con.createStatement();
		 smt.executeUpdate(s1);
	 }
	 public  void deleter(int id) throws SQLException {
  	   
  	   String del = "DELETE FROM class WHERE id = ?";
  	    PreparedStatement pstmt = con.prepareStatement(del);
  	    pstmt.setInt(1, id);
  	    pstmt.executeUpdate();
			
	}
	public int getid() throws SQLException {
			
			String url="jdbc:mysql://localhost:3306/attendance2";
			 con=DriverManager.getConnection(url,"root","vasanthvasanth");
			Statement smt=con.createStatement();
			ResultSet rst=smt.executeQuery("SELECT MAX(id) FROM class");
			if(rst.next())
			{
				return rst.getInt("MAX(id)")+1;
			}	
			return 1;
		}
	public void adder(int id,String name) throws SQLException {
		String s1="INSERT into class values("+id+",  '"+name+"' )";
		Statement smtt=con.createStatement();
		smtt.executeUpdate(s1);
	
	}
 }
 class student{
	 DefaultTableModel model=new DefaultTableModel();
	 Connection con;
	 int check;
	 JButton save,edit,delete,add;
	 
	 public void studentView() throws SQLException {
			Font text = new Font("Times New Roman", Font.PLAIN, 18);
			Font btn = new Font("Times New Roman", Font.BOLD, 20);

			JFrame frame = new JFrame("STUDENT DATABASE");
			JLabel back = new JLabel("< BACK");
			back.setForeground(Color.decode("#37474F"));
			back.setFont(new Font("Times New Roman", Font.BOLD, 17));
			back.setBounds(18, 10, 100, 20);
			frame.add(back);
			back.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.dispose();
				}
			});
			JPanel panel = new  JPanel();
			panel.setBounds(0, 0, 1000, 35);
			panel.setBackground(Color.decode("#DEE4E7"));
			frame.add(panel);
			JTable table =new JTable() {
				public boolean isCellEditable(int row,int column) {
					return false;
				}};
				model=(DefaultTableModel)table.getModel();
				model.addColumn("ID");
				model.addColumn("USERNAME");
				model.addColumn("NAME");
				model.addColumn("CLASS");
				tableupt();
				table.getColumnModel().getColumn(0).setPreferredWidth(50);
				table.getColumnModel().getColumn(1).setPreferredWidth(150);
				table.getColumnModel().getColumn(2).setPreferredWidth(150);
				table.getColumnModel().getColumn(3).setPreferredWidth(150);
				JScrollPane sc=new JScrollPane(table);
				sc.setBounds(500,50,485,525);
				frame.add(sc);
				
				
				
				JLabel id = new JLabel("ID : ");
				id.setFont(text);
				id.setBounds(25, 60, 40, 20);
				id.setForeground(Color.decode("#DEE4E7"));
				frame.add(id);
				JTextField idbox= new JTextField();
				idbox.setBounds(60, 60, 50, 25);
				idbox.setBackground(Color.decode("#DEE4E7"));
				idbox.setFont(text);
				idbox.setForeground(Color.decode("#37474F"));
				idbox.setEditable(false);
				idbox.setText(String.valueOf(getid()));
				frame.add(idbox);
				
				JLabel classes = new JLabel("CLASS : ");
				classes.setFont(text);
				classes.setBounds(150, 60, 100, 20);
				classes.setForeground(Color.decode("#DEE4E7"));
				frame.add(classes);
				
				@SuppressWarnings("unchecked")
				JComboBox clss= new JComboBox(classEt());
				clss.setBounds(250, 60, 200, 25);
				clss.setEnabled(true);
				clss.setEditable(false);
				frame.add(clss);
				clss.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
					
				});
				
				JLabel user = new JLabel("USERNAME : ");
				user.setFont(text);
				user.setBounds(25, 120, 150, 20);
				user.setForeground(Color.decode("#DEE4E7"));
				frame.add(user);
				JTextField username= new JTextField();
				username.setBounds(25, 160, 400, 35);
				username.setBackground(Color.decode("#DEE4E7"));
				username.setFont(text);
				username.setForeground(Color.decode("#37474F"));
				username.setEditable(false);
				frame.add(username);
				
				JLabel nm = new JLabel("NAME : ");
				nm.setFont(text);
				nm.setBounds(25, 240, 150, 20);
				nm.setForeground(Color.decode("#DEE4E7"));
				frame.add(nm);
				JTextField name= new JTextField();
				name.setBounds(25, 270, 400, 35);
				name.setBackground(Color.decode("#DEE4E7"));
				name.setFont(text);
				name.setForeground(Color.decode("#37474F"));
				name.setEditable(false);
				frame.add(name);
				
				JLabel pass = new JLabel("PASSWORD : ");
				pass.setFont(text);
				pass.setBounds(25, 350, 150, 20);
				pass.setForeground(Color.decode("#DEE4E7"));
				frame.add(pass);
				JTextField password= new JTextField();
				password.setBounds(25, 380, 400, 35);
				password.setBackground(Color.decode("#DEE4E7"));
				password.setFont(text);
				password.setForeground(Color.decode("#37474F"));
				password.setEditable(false);
				frame.add(password);
				
				save = new JButton("SAVE");
				save.setBounds(25, 500, 125, 50);
				save.setFont(btn);
				save.setBackground(Color.decode("#DEE4E7"));
				save.setForeground(Color.decode("#37474F"));
				save.setEnabled(false);
				frame.add(save);
				save.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String className = (String) clss.getSelectedItem();
						if(check==1)
						{
							try {
								  
								adder(Integer.parseInt(idbox.getText()),username.getText(),name.getText(),password.getText(),String.valueOf(className));
							} catch (NumberFormatException | SQLException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, e1);
							}
						}
						else if(check==2) {
							save.setEnabled(false);
							if(password.getText().equals("")) {
							try {
								editor(Integer.parseInt(idbox.getText()),username.getText(),name.getText(),String.valueOf(className));
							} catch (NumberFormatException | SQLException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, e1);
							}
							
							}
							else {
								try {
									editor(Integer.parseInt(idbox.getText()),username.getText(),name.getText(),password.getText(),String.valueOf(className));
								} catch (NumberFormatException | SQLException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(null, e1);
								}
							}
						}
						try {
							idbox.setText(String.valueOf(getid()));
						
						
						edit.setEnabled(false);
						delete.setEnabled(false);
						username.setText("");
						name.setText("");
						password.setText("");
						while(model.getRowCount()>0)
							model.removeRow(0);
						tableupt();
						} 
						catch (SQLException e1) {
							e1.printStackTrace();
					}

					

					}

					

					
					
					
				});
				
				add = new JButton("ADD");
				add.setBounds(325, 500, 125, 50);
				add.setFont(btn);
				add.setBackground(Color.decode("#DEE4E7"));
				add.setForeground(Color.decode("#37474F"));
				add.setEnabled(true);
				frame.add(add);
				add.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						add.setEnabled(false);
						edit.setEnabled(false);
						save.setEnabled(true);
						username.setEditable(true);
						name.setEditable(true);
						password.setEditable(true);
						clss.setEditable(true);
						check=1;
						try {
							idbox.setText(String.valueOf(getid()));
						} catch (SQLException e1) {
					
							e1.printStackTrace();
						}
					}
					
				});
				
				
				delete = new JButton("DELETE");
				delete.setBounds(175, 432, 125, 50);
				delete.setFont(btn);
				delete.setBackground(Color.decode("#DEE4E7"));
				delete.setForeground(Color.decode("#37474F"));
				delete.setEnabled(false);
				frame.add(delete);
				
				edit = new JButton("EDIT");
				edit.setBounds(175, 500, 125, 50);
				edit.setFont(btn);
				edit.setEnabled(false);
				edit.setBackground(Color.decode("#DEE4E7"));
				edit.setForeground(Color.decode("#37474F"));
				frame.add(edit);
				edit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						edit.setEnabled(false);
						save.setEnabled(true);
						check=2;
						clss.setEditable(true);
						username.setEditable(true);
						name.setEditable(true);
						password.setEditable(true);
					}
					
				});
				
			table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						int row=table.getSelectedRow();
						password.setText("");
						
						idbox.setText(String.valueOf(table.getModel().getValueAt(row,0)));
						username.setText(String.valueOf(table.getModel().getValueAt(row,1)));
						name.setText(String.valueOf(table.getModel().getValueAt(row,2)));
						 String className  = String.valueOf(table.getModel().getValueAt(row, 3));
					        clss.setSelectedItem(className);
						edit.setEnabled(true);
						save.setEnabled(false);
						delete.setEnabled(true);
						username.setEditable(false);
						password.setEditable(false);
						name.setEditable(false);
						clss.setEditable(false);


					}
				});
			
			frame.setSize(1000,600);
			frame.setResizable(false);
			frame.setLayout(null);
			frame.setLocationRelativeTo(null);  
			frame.setVisible(true);
			frame.setFocusable(true);
			frame.getContentPane().setBackground(Color.decode("#37474F"));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	 }
	 public void adder(int id, String username, String name, String password, String classes) throws SQLException {
		 String s1="INSERT into user values("+id+", '"+username+"' , '"+name+"' , '"+password+"' ,3)";
		 String s2="INSERT into student values("+id+",'"+name+"','"+classes+"')";
			Statement smtt=con.createStatement();
			smtt.executeUpdate(s1);
			smtt.executeUpdate(s2);
					}
	 public void editor(int id, String username, String name,String classes) throws SQLException {
		 String s1="UPDATE user set username='"+username+"',name='"+name+"' WHERE id= "+id;
		 String s2="UPDATE student set name='"+name+"',class='"+classes+"' WHERE id= "+id;
		 Statement smt=con.createStatement();
		 smt.executeUpdate(s1);
		 smt.executeUpdate(s2);
					}
	 public void editor(int id, String username, String name, String password, String classes) throws SQLException {
					
		 String s1="UPDATE user SET username='"+username+"',name='"+name+"',pass='"+password+"' WHERE id= "+id;
		 String s2="UPDATE student SET name='"+name+"',class='"+classes+"' WHERE id= "+id;
			Statement smt=con.createStatement();
			smt.executeUpdate(s1);
			smt.executeUpdate(s2);
					}
	 
			public String[] classEt() throws SQLException {
				String str1 = "SELECT name from class";
				Statement stm = con.createStatement();
				ResultSet rst = stm.executeQuery(str1);
				String[] rt = new String[25];
				int i=0;
				while(rst.next()) {
					rt[i] = rst.getString("name");
					i++;
				}
				return rt;
			}
		
	
	 public int getid() throws SQLException {
		 String url="jdbc:mysql://localhost:3306/attendance2";
		 con=DriverManager.getConnection(url,"root","vasanthvasanth");
		Statement smt=con.createStatement();
		ResultSet rst=smt.executeQuery("SELECT MAX(id) FROM user");
		if(rst.next())
		{
			return rst.getInt("MAX(id)")+1;
		}	
		return 1;
		 
	 }

	public void tableupt() {
		 try {
				ResultSet rst=dbSearch();
				for(int i=0;rst.next();i++)
				{
					model.addRow(new Object[0]);
					model.setValueAt(rst.getInt("id"),i,0);
					model.setValueAt(rst.getString("username"),i,1);
					model.setValueAt(rst.getString("name"),i,2);
					model.setValueAt(rst.getString("class"), i, 3);
				
				}
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(null,e);
			}
		 
	 }
	 
	public ResultSet dbSearch() throws SQLException {
		 String url="jdbc:mysql://localhost:3306/attendance2";
			String ss=" SELECT user.id,user.username,user.name,student.class FROM student,user where student.id=user.id ";
			 con=DriverManager.getConnection(url,"root","vasanthvasanth");
			 Statement smt=con.createStatement();
			ResultSet rst=smt.executeQuery(ss);
			return rst;
	 }
 }
public class main {
	public static void main(String[] args) {
		login ln=new login();
		ln.login();
	}
	}


