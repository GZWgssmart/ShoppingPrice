<%--
  Created by IntelliJ IDEA.
  User: WangGenshen
  Date: 5/17/16
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>四大平台比价</title>
    <link rel="stylesheet" href="<%=path %>/js/jquery-easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" href="<%=path %>/css/site_main.css"/>
    <script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=path %>/js/site_easyui.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" class="north">
    <img class="main_logo" src="<%=path %>/images/logo.jpg"/>

    <div class="north wel_msg">
        欢迎
    </div>
</div>
<div data-options="region:'west',split:true,title:'功能菜单'" class="west">
    <div class="easyui-accordion" data-options="border:false">
        <div title="系统管理" class="site_menu">
            <a href="javascript:void(0);" src="<%=path %>/pro/list_page" class="site-navi-tab">搜索商品</a></p>
            <a href="javascript:void(0);" src="<%=path %>/pro/setting" class="site-navi-tab">基本设置</a></p>
        </div>
    </div>
</div>
<div id="mainPanle" data-options="region:'center',border:true">
    <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
        <div title="主页">
            四大平台比价
        </div>
    </div>
</div>

<div data-options="region:'south',border:false" style="padding:10px auto 10px auto;text-align:center;">
    四大平台比价
</div>

<div id="mm" class="easyui-menu">
    <div id="mm-tabupdate">刷新</div>
    <div class="menu-sep"></div>
    <div id="mm-tabclose">关闭</div>
    <div id="mm-tabcloseother">关闭其他</div>
    <div id="mm-tabcloseall">关闭全部</div>
</div>
</body>
</html>
