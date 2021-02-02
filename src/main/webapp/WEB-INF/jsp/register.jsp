<html>
<%@ page language="java" pageEncoding="UTF-8"%>
<head>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transition al.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link href="static/reg/css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<title>图书注册</title>
</head>
<body style="position: relative">
	<div class="top-bg-box"></div>
	<div class="top-logo-box">
		<img src="static/reg/image/re-top-logo-img.png" />
	</div>
	<!--注册窗体start-->
	<div class="login-box">
		<div class="right-panel">
			<div class="tips-issue" id="byLoginNameTips">
				<span id="byLoginName-Tips-Text">密码错误！</span>
			</div>
			<h2 class="title-block">生成注册码</h2>
			<!--密码登录窗体start-->
			<div class="input-box password-input-box">
				<div class="item item-fore1 clearfix" id="copyCode">
					<label class="login-label name-label">识&nbsp;&nbsp;别&nbsp;&nbsp;码&nbsp;:</label>
					<input oninput="if(value.length>4)value=value.slice(0,4)"
						id="idcode1" type="text" class="itxt itxt-1" name="loginname"
						tabindex="1" autocomplete="off"> <span class="fege-text">-</span>
					<input oninput="if(value.length>4)value=value.slice(0,4)"
						id="idcode2" type="text" class="itxt itxt-1" name="loginname"
						tabindex="1" autocomplete="off"> <span class="fege-text">-</span>
					<input oninput="if(value.length>4)value=value.slice(0,4)"
						id="idcode3" type="text" class="itxt itxt-1" name="loginname"
						tabindex="1" autocomplete="off"> <span class="fege-text">-</span>
					<input oninput="if(value.length>4)value=value.slice(0,4)"
						id="idcode4" type="text" class="itxt itxt-1" name="loginname"
						tabindex="1" autocomplete="off">
				</div>
				<div class="item item-fore1  clearfix">
					<label class="login-label pwd-label" for="nloginpwd">图书编号&nbsp;:</label>
					<input oninput="value=value.replace(/[^\d]/g,'')" maxlength="22"
						
						id="nloginpwd" name="nloginpwd" class="itxt itxt-error"
						tabindex="2" placeholder="请输入图书编号">
				</div>
				<div id="code" style="display: none"
					class="item item-fore2  clearfix">
					<label class="login-label pwd-label" for="nloginpwd">注&nbsp;&nbsp;册&nbsp;&nbsp;码&nbsp;:</label>
					<input id="codes" class="register-number-text"></input> <span
						onclick="copy()" class="copy-btn">复制</span>
				</div>

				<button onclick="change()">生成注册码</button>
			</div>
			<script type="text/javascript">
				function change() {
					var oldP = $("#nloginpwd").val();
					var newP = $("#idcode1").val()+ $("#idcode1").val()+ $("#idcode1").val()+ $("#idcode1").val();
					$.ajax({
						url : "${webUrlPath}reg",
						type : "get",
						async : false,
						data : {
							booknumber : oldP,
							idcode : newP
						},
						success : function(data) {
							if(data.code == 100000) {
								$("#codes").val(data.data);
								$("#code").attr("style","display:block;");
							}else{
								alert(data.msg);
							}
							
						}
					});
				}
			</script>
			<script>
			function copy() {
				let input = document.createElement("input");
				input.value = $("#codes").val();
				console.log(input.value);
				input.style.opacity = "0";
				document.body.appendChild(input);
				input.select();
				if(document.execCommand("copy")){
					document.execCommand("copy");
					console.log("复制成功");
				}
				input.remove();
				alert("已复制到剪贴板");
				};
			</script>
			<script>
				//识别码的对象
				var codeArray = document.getElementById("copyCode")
						.getElementsByTagName("input");
				//监听粘贴事件
				document
						.getElementById('copyCode')
						.addEventListener(
								'paste',
								function(e) {
									//不支持监听粘贴事项
									if (!(e.clipboardData && e.clipboardData.items)) {
										return;
									}
									//获取点击的输入框
									var copyObj = e.srcElement;
									var codyData = "";
									for (var i = 0, len = e.clipboardData.items.length; i < len; i++) {
										var item = e.clipboardData.items[i];
										if (item.kind === "string") {
											item
													.getAsString(function(str) {
														if (str.length != 16) {
															alert("识别码位数不正确");
															cleanCode();
															return;
														} else {
															//获取粘贴过来的识别码，转为数据，填充input框
															copyData = str;
															var copyDataArray = sameSplit(copyData);
															for (i = 0; i < codeArray.length; i++) {
																codeArray[i].value = copyDataArray[i];
															}
														}
													})
										}
									}
								});
				//字符串四等分
				function sameSplit(str) {
					var reg = /.{4}/g, rs = str.match(reg);
					rs.push(str.substring(rs.join('').length));
					return rs;
				}
				//清空识别码
				function cleanCode() {
					for (i = 0; i < codeArray.length; i++) {
						codeArray[i].value = "";
					}
				}
			</script>
		</div>
	</div>
	<!--注册窗体end-->

</body>