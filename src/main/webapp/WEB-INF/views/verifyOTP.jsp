<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Dashboard - Admin</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<!--base css styles-->
<link rel="stylesheet"
	href="resources/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="resources/assets/font-awesome/css/font-awesome.min.css">

<!--page specific css styles-->

<!--flaty css styles-->
<link rel="stylesheet" href="resources/css/flaty.css">
<link rel="stylesheet" href="resources/css/flaty-responsive.css">

<link rel="shortcut icon" href="resources/img/favicon.png">

</head>
<body class="login-page">
<c:url value="/reGenOtp1" var="reGenOtp1"></c:url>
	<!-- BEGIN Main Content -->
	<div class="login-wrapper">
	
	<!-- BEGIN Forgot Password Form -->
		<form id="form-forgot" action="${pageContext.request.contextPath}/OTPVerification" method="post">
			<h3>Verify OTP</h3>
			<hr />
			<div class="form-group">
				<div class="controls">
					<input type="text" placeholder="OTP" class="form-control" id="otp" name="otp" maxlength="6" />
				</div>
			</div>
			<input type="hidden" id="username" name="username" value="${username}">
			<div class="form-group">
				<div class="controls">
					<button type="submit" class="btn btn-primary form-control">Verify OTP</button>
				</div>
			</div>
			<hr />
			<p class="clearfix">
				<input
							type="button" class="btn btn-accent btn-block"
							onclick="reGenOtp()" value="Re Generate OTP" />
			</p>
		</form>
		<!-- END Forgot Password Form -->
	
	<%-- 
		<!-- BEGIN Login Form -->
		<form id="form-login" action="loginProcess" method="post">
			<h3>Forget Password</h3>
		
		


			<hr />
			<div class="form-group">
				<div class="controls">
					<input type="text" placeholder="Username" class="	form-control"
						path="username" name="username" id="username" required/>

				</div>
			</div>
			<!-- <div class="form-group">
				<div class="controls">
					<input type="password" placeholder="Password" class="form-control"
						path="userpassword" name="userpassword" id="userpassword"  required/>
				</div>
			</div> -->
			<!-- <div class="form-group">
				<div class="controls">
					<label class="checkbox"> <input type="checkbox"
						value="remember" /> Remember me
					</label>
				</div>
			</div> -->
			<div class="form-group">
				<div class="controls">
					<button type="submit" class="btn btn-primary form-control" onclick="getUserDate()">Submit
						</button>
				</div>
				
				<c:if test="${not empty loginResponseMessage}">
   <!-- here would be a message with a result of processing -->
    <div> ${loginResponseMessage} </div>
        	
</c:if>
				
				
			</div>
			<hr />
			<p class="clearfix">
				<a href="#" class="goto-forgot pull-left">Forgot Password?</a> <a
					href="#" class="goto-register pull-right">Sign up now</a>
			</p>
		</form>
		<!-- END Login Form -->
 --%>
		

	
	</div>
	<!-- END Main Content -->

	<!--basic scripts-->
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script>window.jQuery || document.write('<script src="resources/assets/jquery/jquery-2.0.3.min.js"><\/script>')</script>
	<script src="resources/assets/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">
function reGenOtp() {
	//alert("Hi");

	var form = document.getElementById("form-forgot");
	
	form.setAttribute("method", "post");

	form.action = ("reGenOtp1");

	form.submit();

			 var username = document.getElementById("username").value;
			// alert(username);
			
			$
					.getJSON(
							'${reGenOtp1}',
							{
								username : username,
							
								ajax : 'true',

							},
							function(data) {

									alert("Data  " +JSON.stringify(data));
							location.reload(true);
								
							});

		}

function getUserInfo(){
	var username = $("#username").val();
	alert(username);
	$(document)
	.ready(
			function() {
				$
						.getJSON(
								'${getUserInfo}',
								{
									username : username,
									ajax : 'true'
								},
								function(data) {
									//alert(JSON.stringify(data))
									

								});

			});
}

</script>

	<script type="text/javascript">
            function goToForm(form)
            {
                $('.login-wrapper > form:visible').fadeOut(500, function(){
                    $('#form-' + form).fadeIn(500);
                });
            }
            $(function() {
                $('.goto-login').click(function(){
                    goToForm('login');
                });
                $('.goto-forgot').click(function(){
                    goToForm('forgot');
                });
                $('.goto-register').click(function(){
                    goToForm('register');
                });
            });
        </script>
</body>
</html>
