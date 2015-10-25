<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

//时间需要进行修改
//缺少医生的id和验证码动态获取程序；
//定时调用post即可，写到java程序中去；
ordertype=doctor&vefirycode=62MX&doctorid=00000000010650&usercardid=&hospitalid=42502656400&templateid=14110500000195&doctorname=王曙&hospitalname=瑞金医院&resdeptl2=门诊内分泌&date=2014-12-02 星期二&time=14:00-14:59&fee=17&address=


ordertype=doctor&vefirycode=474B&usercardid=&hospitalid=42502656400&templateid=14110400000534&doctorname=李小英&hospitalname=瑞金医院&resdeptl2=门诊内分泌&date=2014-12-03 星期三&time=14:00-14:59&fee=20&address=
<img src="/Images/Common/btn_reservation.png" onclick="javascript:SaveReservation('42502656400', '14110400000534', '李小英', '瑞金医院', '门诊内分泌', '2014-12-03 星期三', '14:00-14:59', '20', '', 'doctor');">

</body>
</html>