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
<link rel="stylesheet" href="static/css/layui.css" />
<link rel="stylesheet" href="static/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" href="static/css/public.css">
<link rel="stylesheet" href="static/css/bootstrap-table.css" />
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/layui.js" charset="utf-8"></script>
<script type="text/javascript"
	src="static/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="static/js/bootstrap-table.js"></script>
<script type="text/javascript"
	src="static/js/locales/bootstrap-datetimepicker.fr.js"></script>
<!--[if lt IE 9]>
  <script src="js/ie/respond.min.js"></script>
  <script src="js/ie/html5.js"></script>
  <![endif]-->
<title>注册图书明细</title>
</head>

<body>
	<!--头部start-->
	<header id="header" class="navbar">
		<a class="logo-img"><img
			src="static/images/header-top-logo-img.png"></a> <a
			class="change-passward-btn" href="${webUrlPath}change-password"><i
			class="icon-cog"></i> 修改密码</a> <a class="logo-img"></a> <a
			class="change-passward-btn" href="${webUrlPath}api/v1/user/logout"><i
			class="icon-cog"></i> 退出登录</a>
	</header>
	<!--头部end-->

	<!--左侧栏start-->
	<nav id="nav" class="nav-primary visible-lg nav-vertical">
		<ul class="nav affix-top" data-spy="affix" data-offset-top="50">
			<li class="active"><a href="${webUrlPath}re-book-detail"><i
					class="icon-table icon-xlarge"></i>注册图书明细</a></li>
			<li><a href="${webUrlPath}re-log-detail"><i
					class="icon-file-text icon-xlarge"></i>注册日志</a></li>
			<li><a href="${webUrlPath}login-log-detail"><i
					class="icon-paste icon-xlarge"></i>登录日志</a></li>
		</ul>
	</nav>
	<!--左侧栏end-->

	<section id="content">
		<section class="main padder clearfix">
			<div class="">
				<div class="input-group input-group-lg col-lg-4"
					style="margin-bottom: 16px;">
					<span class="input-group-addon">图书编号 :</span> <input type="text"
						oninput="value=value.replace(/[^\d]/g,'')" minlength="22"
						maxlength="22" id="bookNumber" class="form-control"
						placeholder="请输入图书编号" aria-describedby="sizing-addon1">
				</div>
				<div class="input-group input-group-lg col-lg-4"
					style="margin-bottom: 16px;">
					<span class="input-group-addon">注册开始时间段 :</span> <input type="text"
						id="datetimepicker1" class="form-control" placeholder="请选择注册时间区间"
						aria-describedby="sizing-addon1" style="width: 180px;"> <span
						class="input-group-addon">-</span> <input type="text"
						id="datetimepicker2" class="form-control" placeholder="请选择注册结束时间段"
						aria-describedby="sizing-addon1" style="width: 180px;">
				</div>
				<script>
					$("#datetimepicker1").datetimepicker({
						format : 'yyyy-mm-dd 00:00:00',
						minView : "month"
					});
					$("#datetimepicker2").datetimepicker({
						format : 'yyyy-mm-dd 00:00:00',
						minView : "month"
					});
				</script>
				<button type="submit" class="login-btn btn btn-primary col-lg-2"
					onclick="creatmodlelist()">查询</button>
				<div class="col-lg-12">
					<table id="test" class="layui-hide"></table>
				</div>
				<script>
					function creatmodlelist() {
						if ($("#bookNumber").val().length > 0
								&& $("#bookNumber").val().length < 22) {
							alert("图书编号为22位纯数字！");
						} else if ($("#bookNumber").val().length == 22) {

							layui
									.use(
											'table',
											function() {
												var table = layui.table;

												var start = $("#bookNumber")
														.val();
												table
														.render({
															elem : '#test',
															method : 'post',
															url : '${webUrlPath}api/v1/searchRegBook/byNum',
															where : {
																bookNumber : start,
															},
															title : '图书信息查询',
															response : {
																statusName : 'code',
																statusCode : 100000,
																msgName : 'msg',
																countName : 'totalcount',
																dataName : 'data'
															},
															cols : [ [
																	{
																		field : 'id',
																		title : '编号'
																	},
																	{
																		field : 'idcode',
																		title : '识别码'
																	},
																	{
																		field : 'booknumber',
																		title : '图书编号'
																	},
																	{
																		field : 'regtime',
																		title : '注册时间',
																		sort : true,
																		templet : "<div>{{layui.util.toDateString(d.regtime, 'yyyy-MM-dd HH:mm:ss')}}</div>"

																	},
																	{
																		field : 'ip',
																		title : 'ip',
																		sort : true
																	} ] ]
														});
											});
						} else {
							var start = $("#datetimepicker1").val();
							var end = $("#datetimepicker2").val();
							if (start.length == 0 || end.length == 0) {
								alert("时间区间都不能为空！！");
							} else {
								layui.use('table', function() {
									var table = layui.table;
									var start = $("#datetimepicker1").val();
									var end = $("#datetimepicker2").val();
									table.render({
										elem : '#test',
										method : 'post',
										url : '${webUrlPath}api/v1/searchRegBook/byDate',
										where : {
											startDate : start,
											endDate : end
										},
										title : '图书信息查询',
										response : {
											statusName : 'code',
											statusCode : 100000,
											msgName : 'msg',
											countName : 'totalcount',
											dataName : 'data'
										},
										request : {
											pageName : 'pageNum',
											limitName : 'pageSize'
										},
										cols : [ [ {
											field : 'id',
											title : '编号',
											sort : true
										}, {
											field : 'idcode',
											title : '识别码',
										}, {
											field : 'booknumber',
											title : '图书编号',
											sort : true
										},
										{
											field : 'regtime',
											title : '注册时间',
											sort : true,
											templet : "<div>{{layui.util.toDateString(d.regtime, 'yyyy-MM-dd HH:mm:ss')}}</div>"

										}, {
											field : 'ip',
											title : 'ip',
											sort : true
										} ] ],
										page : true,
									});
								});
							}
						}
					}
				</script>
			</div>
</body>

</html>