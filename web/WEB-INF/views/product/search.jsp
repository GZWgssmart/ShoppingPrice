<%--
  Created by IntelliJ IDEA.
  User: WangGenshen
  Date: 11/28/16
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>商品比价搜索</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="<%=path %>/js/jquery-easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" href="<%=path %>/js/jquery-easyui/themes/icon.css"/>
    <link rel="stylesheet" href="<%=path %>/css/site_main.css"/>

    <script src="<%=path %>/js/jquery.min.js"></script>
    <script src="<%=path %>/js/jquery.form.js"></script>
    <script src="<%=path %>/js/jquery-easyui/jquery.easyui.min.js"></script>
    <script src="<%=path %>/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script src="<%=path %>/js/json2.js"></script>
    <script src="<%=path %>/js/site_easyui.js"></script>
    <script src="<%=path %>/js/product/product.js"></script>
</head>
<body>
<div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
    <div title="搜索结果">
        <table id="list" class="easyui-datagrid" toolbar="#tb" style="height:100%;"
               data-options="
                url:'<%=path %>/pro/search_pager',
                method:'get',
                        rownumbers:true,
                        singleSelect:true,
                        autoRowHeight:false,
                        pagination:true,
                        border:false,
                        pageSize:40,
                        rowStyler: function(index,row){
                            if (row.status == 'N') {
                                return 'color:red;';
                            } else if (row.checkStatus == 'checking') {
                                return 'color:orange';
                            } else if (row.checkStatus == 'checked') {
                                return 'color:green;';
                            }
                        }">
            <thead>
            <tr>
                <th field="title" width="100">商品名称</th>
                <th field="imageUrl" width="250" formatter="formatterImage">商品图片</th>
                <th field="originalPrice" width="100">原价</th>
                <th field="salePrice" width="100">优惠价</th>
                <th field="platform" width="100">平台</th>
                <th field="seller" width="100">卖家</th>
                <th field="searchKey" width="100">搜索关键字</th>
                <th field="searchUrl" width="100" formatter="formatterUrl">搜索链接</th>
                <th field="url" width="100" formatter="formatterUrl">商品链接</th>
                <th field="saleCount" width="100">销量</th>
                <th field="commentCount" width="100">评论数</th>
            </tr>
            </thead>
        </table>
        <div id="tb">
            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true"
               onclick="javascript:void(0);">收藏</a>
            <div class="input_small">
                <form id="searchForm" modalAttribute="product">
                    关键字:<input type="text" name="keyword" class="easyui-textbox"/>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
                       onclick="doSearch();">搜索</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
                       onclick="searchAll();">查询所有</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-reload'"
                       onclick="refreshAll();">刷新</a>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
