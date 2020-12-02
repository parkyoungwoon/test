package com.test.pyw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private static String dburl = "jdbc:mysql://192.168.0.114:3306/pyw_test?serverTimezone=Asia/Seoul";

	private static String dbUser = "root";

	private static String dbpasswd = "park1104";
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		System.out.println("들어오는지나 보자");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		
		//영운 db 연결 테스트 코드
		Role role = null;

		Connection conn = null;

		PreparedStatement ps = null;

		ResultSet rs = null;

		

		try {

			// 1. DBMS에 맞게 Driver를 로드.

			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");


			// 2. java SQL 패키지의 DriverMager를 통해서 DBMS에 연결

			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);



			// 3.Sql 쿼리 String 생성

			String query = "select Column1 from test;";



			// 4.DB에 Sql 쿼리 전달 하기 위한 객체 생성

			ps = conn.prepareStatement(query);



			// 5. 쿼리 실행 및 결과 얻기

			rs = ps.executeQuery();

			

			// 결과 값 빼내기 nex()함수의 결과 첫번재 컬럼을 빼난다.

			if (rs.next()) {

				// index로 뽄는 방법

				String description = rs.getString(1);
				System.out.println("description"+description);


			}

		

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			

			if (rs != null) {

				try {

					rs.close();

				} catch (SQLException e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}

			}

			if (ps != null) {

				try {

					ps.close();

				} catch (SQLException e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}

			}

			

			if (conn != null) {

				try {

					conn.close();

				} catch (SQLException e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}

			}

			

		}

		
		
		
		
		return "home";
	}
	
}
