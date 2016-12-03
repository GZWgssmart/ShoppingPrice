var homeTabTitle = '主页';

function addTab(title, url) {
    if ($('#tabs').tabs('exists', title)) {
        $('#tabs').tabs('select', title);
        var currTab = $('#tabs').tabs('getSelected');
        var url1 = $(currTab.panel('options').content).attr('src');
        if(url1 != undefined && currTab.panel('options').title != homeTabTitle) {
            $('#tabs').tabs('update',{
                tab:currTab,
                options:{
                    content:createFrame(url1)
                }
            })
        }
    } else {
        $('#tabs').tabs('add', {
            title: title,
            content: createFrame(url),
            border: false,
            closable: true
        });
    }
    tabClose();
}

function createFrame(url) {
    return '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
}

function tabClose() {
    $(".tabs-inner").dblclick(function(){
        var subtitle = $(this).children(".tabs-closable").text();
        $('#tabs').tabs('close', subtitle);
    })
    $(".tabs-inner").bind('contextmenu',function(e){
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });

        var subtitle =$(this).children(".tabs-closable").text();

        $('#mm').data("currtab", subtitle);
        $('#tabs').tabs('select', subtitle);
        return false;
    });
}

function tabCloseEvent() {
    $('#mm-tabupdate').click(function(){
        var currTab = $('#tabs').tabs('getSelected');
        var url = $(currTab.panel('options').content).attr('src');
        if(url != undefined && currTab.panel('options').title != homeTabTitle) {
            $('#tabs').tabs('update',{
                tab:currTab,
                options:{
                    content:createFrame(url)
                }
            })
        }
    })
    $('#mm-tabclose').click(function(){
        var currtab_title = $('#mm').data("currtab");
        $('#tabs').tabs('close',currtab_title);
    })
    $('#mm-tabcloseall').click(function(){
        $('.tabs-inner span').each(function(i,n){
            var t = $(n).text();
            if(t != homeTabTitle) {
                $('#tabs').tabs('close',t);
            }
        });
    });
    $('#mm-tabcloseother').click(function(){
        var prevall = $('.tabs-selected').prevAll();
        var nextall = $('.tabs-selected').nextAll();
        if(prevall.length>0){
            prevall.each(function(i,n){
                var t=$('a:eq(0) span',$(n)).text();
                if(t != homeTabTitle) {
                    $('#tabs').tabs('close',t);
                }
            });
        }
        if(nextall.length>0) {
            nextall.each(function(i,n){
                var t=$('a:eq(0) span',$(n)).text();
                if(t != homeTabTitle) {
                    $('#tabs').tabs('close',t);
                }
            });
        }
        return false;
    });
    $('#mm-tabcloseright').click(function(){
        var nextall = $('.tabs-selected').nextAll();
        if(nextall.length==0){
            alert('后边没有啦~~');
            return false;
        }
        nextall.each(function(i,n){
            var t=$('a:eq(0) span',$(n)).text();
            $('#tabs').tabs('close',t);
        });
        return false;
    });

    $('#mm-tabcloseleft').click(function(){
        var prevall = $('.tabs-selected').prevAll();
        if(prevall.length==0){
            alert('到头了，前边没有啦~~');
            return false;
        }
        prevall.each(function(i,n){
            var t=$('a:eq(0) span',$(n)).text();
            $('#tabs').tabs('close',t);
        });
        return false;
    });

    $("#mm-exit").click(function(){
        $('#mm').menu('hide');
    })
}

$(function() {
    tabCloseEvent();
    $('.site-navi-tab').click(function() {
        var $this = $(this);
        var href = $this.attr('src');
        var title = $this.text();
        addTab(title, href);
    });
});

function setPagination(tableId) {
    var p = $(tableId).datagrid('getPager');
    $(p).pagination({
        pageSize: 40,
        pageList: [20, 25, 30, 35, 40],
        beforePageText: '第',
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
        onBeforeRefresh: function () {
            $(this).pagination('loading');
            $(this).pagination('loaded');
        }
    });
}

function openWin(id) {
    $("#" + id).window("open");
}

function openWinFitPos(id) {
    var top = ($(document.body).height() - $("#" + id).height()) / 2 - 28;
    var left = ($(document.body).width() - $("#" + id).width()) / 2 - 18;
    $("#" + id).window({
        top:top,
        left:left
    });
    openWin(id);
}

function closeWin(id) {
    $("#" + id).window("close");
}

function selectedRow(id) {
    return $("#" + id).datagrid("getSelected");
}

function selectedRows(id) {
    return $("#" + id).datagrid("getChecked");
}

function dataGridReload(id) {
    $("#" + id).datagrid("reload");
}

function closeTab(title) {
    $('#tabs').tabs('close', title);
}

$(function(){
    $('.validatebox-text').bind('blur', function(){
        $(this).validatebox('enableValidation').validatebox('validate');
    });
})

function toValidate(formId) {
    $('#' + formId + ' .validatebox-text').validatebox('enableValidation').validatebox('validate');
}

function validateForm(id) {
    return $("#" + id).form("validate");
}

function getQueryParams(dataGridId, formId) {
    var fields =$('#' + formId).serializeArray();
    var params = $("#" + dataGridId).datagrid('options').queryParams;
    $.each( fields, function(i, field){
        params[field.name] = field.value;
    });
    return params;
}

function toPage(url) {
    window.location.href = url;
}

//////////////////////////////////////

function formatterDate(value) {
    if (value == undefined || value == null || value == '') {
        return "";
    }
    else {
        var date = new Date(value);
        var year = date.getFullYear().toString();
        var month = (date.getMonth() + 1);
        var day = date.getDate().toString();
        var hour = date.getHours().toString();
        var minutes = date.getMinutes().toString();
        var seconds = date.getSeconds().toString();
        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        if (hour < 10) {
            hour = "0" + hour;
        }
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        if (seconds < 10) {
            seconds = "0" + seconds;
        }
        return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
    }
}

function formatterUrl(value) {
    return "<a href='javascript:void();' onclick='addTab(\"详情\",\"" + value + "\")'>打开链接</a>";
}

function formatterImage(value) {
    return "<img src='" + value + "' class='product-img' />";
}
/////////////////////////////////////