package com.event.EventManagement.eventServiceImpl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.event.EventManagement.dbConnection.ConnectionDB;
import com.event.EventManagement.model.EventModel;
import com.event.EventManagement.model.RegisterModel;
import com.event.EventManagement.service.EventService;

@Service("eventService")
public class EventServiceImp implements EventService{
	private static final Logger LOG = LoggerFactory.getLogger(EventServiceImp.class);
	
	Connection connection;

	@Override
	public List<EventModel> getEvents(String userEmail) throws SQLException {
		String eventRegQuery = null;
		LOG.info("This is info level log");
		String query = "select * from events order by eventid desc";
		
		
		connection = new ConnectionDB().getNewConnection();
		
		List<EventModel> eventList = new ArrayList<>();	
		List<Integer> eventIdListForUser = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);			
			while(rs.next()){
				EventModel event = new EventModel();
				event.setEventId(rs.getInt("eventid"));
				event.setEventName(rs.getString("eventName"));
				event.setEventLocation(rs.getString("eventLocation"));		
				event.setEventDate(rs.getString("eventDate"));	
				event.setNoOfStudents(rs.getString("NUMBER_OF_STUDENTS"));	
				eventList.add(event);				  
	         }
			if(userEmail!=null) {
				eventRegQuery = "SELECT EVENTID FROM EVENT_REG_SUMMARY WHERE REGISTER_BY = '"+userEmail+"'";
				 rs = stmt.executeQuery(eventRegQuery);
				while(rs.next()) {
					eventIdListForUser.add(rs.getInt("EVENTID"));
				}
				
			}			
			stmt.close();
			connection.close();
			
			if(eventIdListForUser.isEmpty() || eventIdListForUser==null) {
				return eventList;
			}else {
				for (EventModel model : eventList) {
					if(eventIdListForUser.contains(model.getEventId())) {
						model.setIsUserRegistered("Y");
					}else {
						model.setIsUserRegistered("N");
					}
				}
				return eventList;
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return eventList;
	}
	
	@Override
	public boolean isUserExist(RegisterModel userModel) {
		String email = null;
		String query = "select emailId from eventusers where emailId = '"+userModel.getEmailId()+"'";
		Statement stmt= null;
		boolean userExist = false;
		connection = new ConnectionDB().getNewConnection();
		
		try {	
			
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				 email = rs.getString("emailId");
			}			
			
			if(email==null || email.isEmpty()) {
				userExist = false;
			}else if(email.equalsIgnoreCase(userModel.getEmailId())) {
				userExist = true;
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {			
			e.printStackTrace();
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			
		}
		
		return userExist;
	}
	
	@Override
	public String createEvent(EventModel model) {
		connection = new ConnectionDB().getNewConnection();
		int seq =0;
		Integer count = 0;
		String queryForDuplicateRecord = "SELECT COUNT(*) AS DUPLICATERECORD FROM EVENTS WHERE EVENTNAME = '"+model.getEventName()+"'"
				+ "AND EVENTLOCATION = '"+model.getEventLocation()+"'AND EVENTDATE = '"+model.getEventDate()+"'";
		try {
			Statement stmtcount = connection.createStatement();
			ResultSet rsCount = stmtcount.executeQuery(queryForDuplicateRecord);
			while(rsCount.next()) {
				count = rsCount.getInt("DUPLICATERECORD");
			}
			
			if(count>0) {
				return "Event Already Created";
			}else {
				
			
			String sequence = "select seq_eventId.nextval from dual";
			Statement stmtSeq = connection.createStatement();
			ResultSet rs = stmtSeq.executeQuery(sequence);
			
			while(rs.next()){
				
				seq = (rs.getInt("nextval"));			  
	         }
			String query = "INSERT INTO EVENTS(EVENTID,EVENTNAME,EVENTDATE,EVENTLOCATION,NUMBER_OF_STUDENTS,CREATED_BY,EVENT_DESCRIPTION) "
					+ "VALUES('" + seq + "','" + model.getEventName() + "','" + model.getEventDate()
					+ "','" + model.getEventLocation() + "'," + "'" + model.getNoOfStudents() + "','"
					+ model.getCreatedBy() + "','" + model.getEventdescription() + "')";
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			connection.close();
			return "Success";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		

	}

	@Override
	public void registerUser(RegisterModel userModel) {
		connection = new ConnectionDB().getNewConnection();
		int seq = 0;
		
		try {
			
			String sequence = "select seq_eventId.nextval from dual";
			Statement stmtSeq = connection.createStatement();
			ResultSet rs = stmtSeq.executeQuery(sequence);
			
			while(rs.next()){
				
				seq = (rs.getInt("nextval"));			  
	         }
			String query = "INSERT INTO EVENTUSERS(USERID,FIRSTNAME,LASTNAME,EMAILID,COMPANY,CITY,COUNTRY,PASSWORD,ADDRESS1,ADDRESS2,ROLE,DESIGNATION)"
					+ "VALUES('"+seq+"','" + userModel.getFirstName() + "','" + userModel.getLastName() + "','"
					+ userModel.getEmailId() + "'," + "'" + userModel.getOrganisation() + "','" + userModel.getCity()
					+ "','" + userModel.getCountry() + "'," + "'" + userModel.getPassword() + "','"
					+ userModel.getAddress1() + "','" + userModel.getAddress2() + "','student','"+userModel.getDesignation()+"')";

			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			connection.close();

		} catch (Exception e) {
			LOG.info("Exception occures while registering user:", e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public EventModel getEventBasedOnId(int id) {
		connection = new ConnectionDB().getNewConnection();
		String query = "SELECT * FROM EVENTS WHERE EVENTID = '"+id+"'";
		Statement stmt = null;
		EventModel model = new EventModel();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {				
				model.setEventDate(rs.getString(query));
				model.setEventLocation(query);
				model.setEventName(query);
				model.setNoOfStudents(query);
				model.setEventdescription(query);				
			}
			stmt.close();
			connection.close();
		}catch (Exception e) {
			LOG.info("Exception occred while retriving event in EventServiceImpl",e.getMessage());
		}		
		
		return model;
	}

	@Override
	public void deleteEvent(int id) {
		connection = new ConnectionDB().getNewConnection();
		String query = "DELETE * FROM EVENTS WHERE EVENTID = '"+id+"'";
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			 stmt.executeUpdate(query);			
			stmt.close();
			connection.close();
		
	}catch(Exception e) {
		LOG.info("Exception occred while deleting event in EventServiceImpl");
	}
		}

	@Override
	public List<EventModel> searchEvents(String searchParameter, String searchText) {
		String query = null;
		List<EventModel> eventList = new ArrayList<>();
		connection = new ConnectionDB().getNewConnection();
		try {
			switch (searchParameter) {
			case "eventId":
				query = "SELECT * FROM EVENTS WHERE EVENTID = '"+searchText+"'";
				break;
			case "eventName":
				query = "SELECT * FROM EVENTS WHERE EVENTname = '"+searchText+"'";
				break;
			case "eventLocation":
				query = "SELECT * FROM EVENTS WHERE EVENTname = '"+searchText+"'";
			default:
				break;
			}
			Statement stmt = null;
						
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
					EventModel event = new EventModel();
					event.setEventId(rs.getInt("eventid"));
					event.setEventName(rs.getString("eventName"));
					event.setEventLocation(rs.getString("eventLocation"));		
					event.setEventDate(rs.getString("eventDate"));	
					event.setNoOfStudents(rs.getString("NUMBER_OF_STUDENTS"));	
					eventList.add(event);				  
		         }
				stmt.close();
				connection.close();			
			
		}catch(Exception e) {
			LOG.info("Exception occred while search event in EventServiceImpl",e.getMessage());
		}
		return eventList;
	}

	@Override
	public void registerEvents(int eventId, String noOfStudents, String userEmail) {
		String eventName = null;
		String eventLocation = null;
		Date eventDate = null;
		Statement stmt = null;
		String eventNameQuery = "SELECT * FROM EVENTS WHERE EVENTID = '"+eventId+"'";
		try {
			connection = new ConnectionDB().getNewConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(eventNameQuery);
			while(rs.next()) {
				eventName = rs.getString("EVENTNAME");
				eventLocation = rs.getString("EVENTLOCATION");
				eventDate = rs.getDate("EVENTDATE");				
			}
			
			String newDate = new SimpleDateFormat("dd-MM-yyyy").format(eventDate);			
				
			
		
		String query = "INSERT INTO EVENT_REG_SUMMARY(EVENTID,EVENTNAME,REGISTER_BY,REGISTER_ON,NO_OF_STUDENTS,APPROVAL_STATUS,"
				+ "EVENTDATE,EVENTLOCATION)"				
				+ "VALUES('"+eventId+"','"+eventName+"','"+userEmail+"',SYSDATE,'"+noOfStudents+"','PENDING','"+newDate+"','"+eventLocation+"')";
		stmt.executeUpdate(query);
		stmt.close();
		connection.close();
		
		}catch(Exception e) {
			LOG.info("Exception occures while registerEvent in EventServiceImpl:"+e.getMessage());
			e.printStackTrace();
		}
		
	}

	@Override
	public List<EventModel> getPendingApprovalsForAdmin() {
		String query = "SELECT REGISTER_BY,(select COMPANY  from eventusers where emailid = register_by )organization,EVENTID,"
				+ "EVENTNAME,no_of_students,EVENTDATE,EVENTLOCATION,APPROVAL_STATUS FROM EVENT_REG_SUMMARY WHERE APPROVAL_STATUS = 'PENDING' "
				+ "and register_by is not null";
		Statement stmt = null;
		List<EventModel> list = new ArrayList<>();
		try {
			connection = new ConnectionDB().getNewConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				EventModel model = new EventModel();
				model.setEventId(rs.getInt("EVENTID"));
				model.setEventName(rs.getString("EVENTNAME"));
				model.setEventLocation(rs.getString("EVENTLOCATION"));
				model.setEmailId(rs.getString("REGISTER_BY"));
				model.setEventDate(rs.getString("EVENTDATE"));
				model.setNoOfStudents(rs.getString("no_of_students"));
				model.setOrganization(rs.getString("organization"));
				list.add(model);
			}
		}catch(Exception e) {
			LOG.info("Exception occures while getting pending approval data for admin in EventServiceImpl"+e.getMessage());
			e.printStackTrace();
		}
		return list;		
		
	}

	@Override
	public List<EventModel> getPendingApprovalsForStudent(String emailId) {
		String query = "SELECT * FROM EVENT_REG_SUMMARY WHERE REGISTER_BY = '"+emailId+"'";
		Statement stmt = null;
		List<EventModel> list = new ArrayList<>();
		try {
			connection = new ConnectionDB().getNewConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				EventModel model = new EventModel();
				model.setEventId(rs.getInt("EVENTID"));
				model.setEventName(rs.getString("EVENTNAME"));
				model.setEventLocation(rs.getString("EVENTLOCATION"));
				model.setEmailId(rs.getString("REGISTER_BY"));
				model.setEventDate(rs.getString("EVENTDATE"));
				model.setApprovalStatus(rs.getString("APPROVAL_STATUS"));
				list.add(model);
			}
		}catch(Exception e) {
			LOG.info("Exception occures while getting pending approval data for student in EventServiceImpl"+e.getMessage());
			e.printStackTrace();
		}
		return list;		
	}

	@Override
	public void updateApprovalStatus(String eventId, String eventName, String status) {
		Statement stmt = null;
		String query = "UPDATE EVENT_REG_SUMMARY SET STATUS = '"+status+"' WHERE EVENTID = '"+eventId+"'";
		String queryForEmail = "SELECT REGISTER_BY,EVENTLOCATION FROM  EVENT_REG_SUMMARY WHERE EVENTID = '"+eventId+"'";
		String userEmail = null; 
		String location = null;
		try {
			connection = new ConnectionDB().getNewConnection();
			stmt = connection.createStatement();
			stmt.executeUpdate(query);
			
			ResultSet rs = stmt.executeQuery(queryForEmail);
			while(rs.next()) {
				userEmail = rs.getString("REGISTER_BY");
				location = rs.getString("EVENTLOCATION");
			}
			stmt.close();
			connection.close();	
			//Mail sent code			
			//sendEmail(eventId,status);
			sendEmailtoUser(status,userEmail,location);
			
		}catch(Exception e) {
			LOG.info("Exception occures while updating approval status"+e.getMessage());
		}
		
	}
	
	public void sendEmailtoUser(String status, String userEmail,String location) {
		String message = "THE CURRENT STATUS OF YOUR REGISTRATION IS - "+status;
		String subject = "Approval status for your Event will be held at -"+location;		
		String from = "sbkfmspw@gmail.com";
		String host = "smtp.gmail.com";
		
		Properties properties = System.getProperties();
		System.err.println("Properties:"+properties);
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
				
		javax.mail.Session session = javax.mail.Session.getInstance(properties, new javax.mail.Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {			
				
				return new PasswordAuthentication("sbkfmspw@gmail.com", "mjklaagoljptbmax");
			}			
		});	
		
		session.setDebug(true);
		MimeMessage mimeMessage = new MimeMessage(session);
		
		try {
			mimeMessage.setFrom(new InternetAddress(from));
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(message);
			
			Transport.send(mimeMessage);
			System.out.println("Mail Send successfully");
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
	}


	
	@Override
	public void downloadEventData(HttpServletRequest request,HttpServletResponse response) {
		String query = "SELECT * FROM EVENT_REG_SUMMARY";
		Statement stmt = null;
		List<EventModel> list = new ArrayList<>();
		try {
			connection = new ConnectionDB().getNewConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				EventModel eventObj = new EventModel();
				eventObj.setEventId(rs.getInt("EVENTID"));
				eventObj.setEventName(rs.getString("EVENTNAME"));
				eventObj.setEmailId(rs.getString("REGISTER_BY"));
				eventObj.setEventDate(rs.getString("EVENTDATE"));
				eventObj.setNoOfStudents(rs.getString("NO_OF_STUDENTS"));
				eventObj.setApprovalStatus(rs.getString("APPROVAL_STATUS"));
				eventObj.setEventLocation(rs.getString("EVENTLOCATION"));
				list.add(eventObj);
			}
			
			downloadEventList(list,request,response);
		}catch(Exception e) {
			LOG.info("Exception occures while downloading excel file"+e.getMessage());
			e.printStackTrace();
		}		
		
	}

	private void downloadEventList(List<EventModel> list,HttpServletRequest request,HttpServletResponse response) {


		//logger.info("Entering method : writeUploadedLogtoExcel");
		String fileName = "EVENTLIST" + ".xlsx";
		try {
			//deleteTempFile(fileName);
			SXSSFWorkbook wb = new SXSSFWorkbook(100);	
			Font headerFont = wb.createFont();
			headerFont.setFontHeightInPoints((short) 12);
			headerFont.setColor(IndexedColors.WHITE.getIndex());
			((XSSFFont) headerFont).setBold(true);
	
			Font datacellFont = wb.createFont();
			datacellFont.setFontHeightInPoints((short) 10);
			datacellFont.setColor(IndexedColors.BLACK.getIndex());
	
			CellStyle headerCellStyle = wb.createCellStyle();
			headerCellStyle = wb.createCellStyle();
			headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			HSSFWorkbook hwb = new HSSFWorkbook();
			HSSFPalette palette = hwb.getCustomPalette();
			HSSFColor headerColor = palette.findSimilarColor(113, 112, 116);
			headerCellStyle.setFillForegroundColor(headerColor.getIndex());
			headerCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			headerCellStyle.setFont(headerFont);
	
			Sheet sh = wb.createSheet();
			Row headerRow = sh.createRow(0); // For Titles
			headerRow.setHeightInPoints(20);
			Cell headerCell = null;
	
			int index = 0;
			String[] headerCols = { "EVENT ID", "EVENT NAME", "REGISTER_BY", "EVENT DATE","NO_OF_STUDENTS","APPROVAL_STATUS",
					"EVENT LOCATION"};
	
			if (headerCols != null && headerCols.length > 0) {
				for (String headerTitle : headerCols) {
					headerCell = headerRow.createCell(index);
					headerCell.setCellValue(headerTitle); // Header Title - Set a
					// string value for the
					// cell.
					headerCell.setCellStyle(headerCellStyle); // Header style - Set
					// the style for the
					// cell
					sh.autoSizeColumn(index); // Adjusts the column width to fit the
					// contents.
					index = index + 1;
				}
			}
			CellStyle dataCellStyle = wb.createCellStyle();
			dataCellStyle = wb.createCellStyle();
			dataCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			dataCellStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
			dataCellStyle.setBorderRight(CellStyle.BORDER_THIN);
			dataCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
			dataCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
			dataCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			dataCellStyle.setBorderTop(CellStyle.BORDER_THIN);
			dataCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
			dataCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
			dataCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			dataCellStyle.setWrapText(true);
			dataCellStyle.setFont(datacellFont);
	
			int rownum = 1;
			for (EventModel model : list) {
				
				Row row = sh.createRow(rownum);
	
				// Set the Log ID value at position 0
				Cell cellA0 = row.createCell(0);
				cellA0.setCellValue(model.getEventId() );
				cellA0.setCellStyle(dataCellStyle);
	
				// Set the Contract Number value at position 1
				Cell cellA1 = row.createCell(1);
				cellA1.setCellValue(model.getEventName());
				cellA1.setCellStyle(dataCellStyle);
	
				// Set the Opportunity Number value at position 2
				Cell cellA2 = row.createCell(2);
				cellA2.setCellValue(model.getEmailId());
				cellA2.setCellStyle(dataCellStyle);
				
				Cell cellA3 = row.createCell(3);
				cellA3.setCellValue(model.getEventDate());
				cellA3.setCellStyle(dataCellStyle);
				
				Cell cellA4 = row.createCell(4);
				cellA4.setCellValue(model.getNoOfStudents());
				cellA4.setCellStyle(dataCellStyle);
				
				Cell cellA5 = row.createCell(5);
				cellA5.setCellValue(model.getApprovalStatus());
				cellA5.setCellStyle(dataCellStyle);
				
				Cell cellA6 = row.createCell(6);
				cellA6.setCellValue(model.getEventLocation());
				cellA6.setCellStyle(dataCellStyle);	
				
				
				rownum++;
			}
	
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.addHeader("content-disposition", "attachment; filename=" + fileName);
			Cookie fileDownloadCookie = new Cookie("fileDownload", "done");
			fileDownloadCookie.setMaxAge(1000 * 60 * 60);
			fileDownloadCookie.setPath("/");
			response.addCookie(fileDownloadCookie);
			ServletOutputStream out = response.getOutputStream();
			wb.write(out);
			wb.dispose();
			//deleteTempFile(fileName);
			out.close();
			//logger.info("Exiting method : writeOpportunitiestoExcel");
		} catch(IOException ie) {
			ie.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		
	
	}	
	
	@Override
	public void downloadEventPendingData(HttpServletRequest request,HttpServletResponse response) {
		String query = "SELECT * FROM EVENT_REG_SUMMARY WHERE STATUS = 'PENDING'";
		Statement stmt = null;
		List<EventModel> list = new ArrayList<>();
		try {
			connection = new ConnectionDB().getNewConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				EventModel eventObj = new EventModel();
				eventObj.setEventId(rs.getInt("EVENTID"));
				eventObj.setEventName(rs.getString("EVENTNAME"));
				eventObj.setEmailId(rs.getString("REGISTER_BY"));
				eventObj.setEventDate(rs.getString("EVENTDATE"));
				eventObj.setNoOfStudents(rs.getString("NO_OF_STUDENTS"));
				eventObj.setApprovalStatus(rs.getString("APPROVAL_STATUS"));
				eventObj.setEventLocation(rs.getString("EVENTLOCATION"));
				list.add(eventObj);
			}
			
			downloadEventList(list,request,response);
		}catch(Exception e) {
			LOG.info("Exception occures while downloading excel file"+e.getMessage());
			e.printStackTrace();
		}		
		
	}	
	
	
}
