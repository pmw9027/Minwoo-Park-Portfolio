<%@page import="Home.popularity"%>
<%@page import="Home.popularityDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="Que.Query"%>
<jsp:useBean id="Lee" class="Que.Query" />
<jsp:useBean id="popularityDao" scope="request"
   class="Home.popularityDao" />
<%
   request.setCharacterEncoding("euc-kr");
%>
<%
   //
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
   //이미지를 설정하세요
   src = [ "1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg",
         "8.jpg", "9.jpg", "10.png" ]

   // 각 이미지를 보여줄 시간을 초 단위로 설정 하세요
   duration = 4;
   ads = [];
   ct = 0;
   function switchAd() {
      var n = (ct + 1) % src.length;
      if (ads[n] && (ads[n].complete || ads[n].complete == null)) {
         document["Ad_Image"].src = ads[ct = n].src;
      }
      ads[n = (ct + 1) % src.length] = new Image;
      ads[n].src = src[n];
      setTimeout("switchAd()", duration * 1000);
   }
   onload = function() {
      if (document.images)
         switchAd();
   }
</script>
<style>
#menu_list {
   margin-top: 80px;
}

#menu_list th {
   height: 100px;
   width: 150px;
}

</style>
</head>

<body>

   <form name=form action="LoginProc.jsp" method="post"
      onsubmit="CheckID()">
      <table align="center">
         <tbody>
            <tr>
               <td width="40%" height="10%" align="center"><span> <img
                     src="http://i57.tinypic.com/b4hv29.gif"
                     alt="Image and video hosting by TinyPic" width="600"
                     style='cursor: pointer'
                     onClick="javascript:location.replace('http://localhost:8080/NPNC/Home/home.jsp')">
               </span> <br></td>
               <td align="center">
               <input  type="button" value="은엽아 니꺼야" onclick="javascript:location.replace('http://localhost:8080/NPNC/Custom/CarSearchjsp_nonmember.jsp')"/>
                  </td>  
            </tr>

            <tr align="center">
               <td align="center" width="300">
                  <div class="input_box">
                     <label for="id" id="label_id" class="lbl_in" height="10"></label>
                     <input type="text" id="id" name="_id" maxlength="41" title="아이디"
                        accesskey="L" placeholder="아이디" class="int"> <input
                        type="submit" value="로그인" />
                  </div> <br>
                  <div class="input_box">
                     <label for="pw" id="label_pw" class="lbl_in2" height="10"></label>
                     <input type="password" id="pw" name="_pw" maxlength="16"
                        title="비밀번호" placeholder="비밀번호" class="int"> <input
                        type="button" value="회원가입"
                        onClick="javascript:location.replace('http://localhost:8080/NPNC/Join/Join.jsp')" />
                  </div>
               </td>

               <td>
                  <p align=center>
                     <a href="javascript:doLink();"
                        onMouseOver="status=url[ct];return true;" onMouseOut="status=''"><img
                        name="Ad_Image" src="1.jpg" border=0 height="300" width="800"></a>
                  </p>
               </td>
            </tr>

            <tr>

               <td align="center" height="300" width="300"><marquee
                      style="visibility: visible;" direction="up" scrolldelay="100"
                     onmouseover="stop();" onmouseout="start();">
                     <table id="menu_list">
                        <thead>
                           <tr>
                              <th>순위</th>
                              <th>자동차</th>
                              <th>인기도</th>
                           </tr>
                        </thead>
                        <tbody>
                           <%
                              int i = 1;
                              popularityDao.popularity_order();
                              for (Home.popularity ab : popularityDao.getList()) {
                           %>
                           <tr>                              
                              <td align=center><%=i++%> 위</td>
                              <td align=center><%=ab.getName()%></td>
                              <td align=center><%=ab.getCount()%></td>                            
                           </tr>
                           <%
                              }
                           %>
                        </tbody>

                     </table>
                  </marquee></td>
               <td align="center"><img src="ge.jpg" height="300" width="800"
                  onClick="javascript:location.replace('http://localhost:8080/NPNC/Board/board.jsp')">
               </td>
            </tr>
         </tbody>
      </table>
   </form>
   <br>

   <p rows="20" cols="150" align="center">
      <font face="돋움" size="3">(주)No pain No code 대표 : 김희중 사업자번호:
         263-12-48652 E-mail : joo02king@naver.com<br> 주소: 경기도 오산시 한신대길
         137 60주년기념관 18203-1 (우) 447-791 대표전화: 010-4949-9990
      </font>
   </p>
   <table align="center" height="90">
      <tr>
         <td><img src="award.jpg" alt="김성기 교수님 인증" width="20%"
            height="90"> <img src="award_01.jpg" alt="한신대학교 인증"
            width="20%" height="90"> <img src="award_02.jpg" alt="해커스 인증"
            width="20%" " height="90"> <img src="award_03.gif" alt="A+인증"
            width="20%" height="90"></td>
         <td><img src="18.jpg" align="right"></td>
      </tr>
   </table>
</body>
</html>