<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		$('.topMenu').load("/WEB-INF/views/top_menu.jsp");
	});
  
	
</script>
</head>
<body>


<div style="width:33px; height:100%; float:left; background-color:#ff0000">
	왼<br>쪽<br>메<br>뉴
</div>


<div style="height:100%; float:left;">

	<div class="topMenu" style="width:100%; height:82px;" >

	</div>

	<div style="width:100%; height:100%; background-color:#f0f000">
		메인메뉴
	</div>
	
	<div style="width:100%; height:34px; background-color:#f0f0f0">
		하단탭바
	</div>

</div>


<div style="width:21px; height:100%; float:right; background-color:#00ff00">
	오<br>른<br>쪽<br>메<br>뉴
</div>

</body>
</html>