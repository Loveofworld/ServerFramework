
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript">



var isIPHONE = (navigator.userAgent.match(/iPhone/i) != null || navigator.userAgent.match(/iPad/i) != null);
var isIPAD = (navigator.userAgent.match(/iPad/i) != null);
var isANDROID = (navigator.userAgent.toString().indexOf("Android") != -1);


var executeApp = function (url) {
	

    //    installApp();
        if (isIPHONE || isIPAD) {
                window.location.href = url;
        } else if (isANDROID) {
        	
        	alert("111111");
        	
        	  window.location.href = url;
        	  
        	  /*
                $('applinkDiv').innerHTML = '<iframe src="' + url + '" width="0" height="0" frameborder="0"></iframe>';
                
            	alert("222222");
                setTimeout(function(){
                        var b = $('applinkDiv');
                        var c = b.getElementsByTagName('iframe');
                        if (c.length > 0) { b.removeChild(c[0]); }
                }, 1000);*/
        }
}
 
var installApp = function () {
	var _APP_INSTALL_URL_IOS = "http://itunes.apple.com/kr/app/id393499958?mt=8";
	var _APP_INSTALL_URL_ANDROID = "intent://viewer?#Intent;scheme='com.vinflux.mobile';action='android.intent.action.VIEW';category='android.intent.category.BROWSABLE';package=com.vinflux.mobile;end";
	var _APP_INSTALL_CONFIRM = "NONO";
    var b = new Date();
       setTimeout(function(){
               if (new Date() - b < 1500) {
                       if (isIPHONE || isIPAD) {
                               if (confirm(_APP_INSTALL_CONFIRM)) { window.location.href = _APP_INSTALL_URL_IOS; }
                       } else if (isANDROID) {
                               if (confirm(_APP_INSTALL_CONFIRM)) { window.location.href = _APP_INSTALL_URL_ADROID; }
                       }
               }
       }, 500);
}

var RPRICKEY = '${RPRICKEY}';
var RCM = '${RCM}';
var iOS_AppUrl = "iOSDevTips://?"+RPRICKEY+"=="+RCM;
var Android_AppUrl = "com.vinflux.mobile://sesame/?RPRICKEY="+RPRICKEY+"&RCM="+RCM;

if (isIPHONE || isIPAD) {
	executeApp(iOS_AppUrl);
} else if (isANDROID) {
	executeApp(Android_AppUrl);
}
else{
	alert("미지원 단말입니다.");	
}

</script>
</head>
<body>
 
 
<div id="applinkDiv"></div>
 
</body>
</html>

