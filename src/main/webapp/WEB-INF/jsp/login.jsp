<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="description"
	content="mobile first, app, web app, responsive, admin dashboard, flat, flat ui">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="static/css/bootstrap.css">
<link rel="stylesheet" href="static/css/font-awesome.min.css">
<link rel="stylesheet" href="static/css/login.css">
<script type="text/javascript" src="static/js/url.js"></script>
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<!--[if lt IE 9]>
    <script src="js/ie/respond.min.js"></script>
    <script src="js/ie/html5.js"></script>
  <![endif]-->
<title>登录</title>
</head>


<body>
	<div class="login-box">
		<div class="login-content">
			<h2>安全评价图书</h2>
			<div class="error-issue-box" style="display: none;">登录名或登录密码错误</div>
			<div class="input-group input-group-lg" style="margin-bottom: 16px;">
				<span class="input-group-addon"><i class="icon-user"></i></span> <input
					type="text" id="name" name="name" class="form-control"
					placeholder="请输入用户登录名称" aria-describedby="sizing-addon1">
			</div>

			<div class="input-group input-group-lg">
				<span class="input-group-addon"><i class="icon-unlock-alt"></i></span>
				<input type="password" id="password" name="password"
					class="form-control" placeholder="请输入用户登录密码"
					aria-describedby="sizing-addon1">
			</div>

			<div id="yzm" data-show='0' class="input-group input-group-lg"
				style='margin-top: 10px; display: none; width: 230px'>
				<span class="input-group-addon" style="width: 60px; float: left">验证码</span>
				<input type="text" id='inputyzm' name="yzm" class="form-control"
					placeholder="请输入验证码" aria-describedby="sizing-addon1"
					style='width: 170px; float: left'>
			</div>
			<div>
				<span id='getYzm'
					style='margin-top: 10px; width: 120px; height: 30px; border: 1px solid #000; text-align: center; line-height: 30px; display: none; border-radius: 6px; margin-top: 4px; margin-left: 10px;'>获取验证码</span>
				<div id='tit'></div>
			</div>
			<button type="submit" id="b01" class="login-btn btn btn-primary">登录</button>
		</div>
		<script>
			$("#b01")
					.click(
							function() {
								if (!Number($('#yzm').attr('data-show'))
										|| $('#inputyzm').val().toUpperCase() === $(
												'#getYzm').text().toUpperCase()) {
									$
											.ajax({
												url : "${webUrlPath}api/v1/user/login",
												type : "post",
												async : false,

												data : {
													name : $('#name').val(),
													password : $('#password')
															.val()
												},
												success : function(data) {
													if (data.code == 100000) {
														location.href = "${webUrlPath}re-book-detail";
													} else if (data.code === 100005) {
														alert(data.msg);
														$("#getYzm").css(
																'display',
																'inline-block');
														$("#yzm").css(
																"display",
																'block').attr(
																'data-show',
																'1');

														$('#inputyzm')
																.change(
																		function(
																				e) {
																			if (e.target.value
																					.toUpperCase() !== getYzm
																					.text()
																					.toUpperCase()) {
																				$(
																						"#tit")
																						.css(
																								"display",
																								'block');
																				$(
																						'#tit')
																						.html(
																								'验证码错误');
																			} else {
																				$(
																						"#tit")
																						.css(
																								"display",
																								'block');
																				$(
																						'#tit')
																						.html(
																								'验证码正确');
																			}
																		})

														$("#getYzm")
																.click(
																		function() {
																			$(
																					this)
																					.html(
																							randomCode(4));
																		})
													} else {
														alert(data.msg);
													}
												}
											})
								}
							});

			function randomCode(n) {
				var str = "";
				for (var i = 0; i < n; i++) {
					var num = parseInt(48 + Math.random() * (122 - 48 + 1));
					while ((num >= 58 && num <= 64) || (num >= 91 && num <= 96)) {
						num = parseInt(48 + Math.random() * (122 - 48 + 1));
					}
					str += String.fromCharCode(num);
				}
				return str;
			}
		</script>
	</div>
</body>


</html>