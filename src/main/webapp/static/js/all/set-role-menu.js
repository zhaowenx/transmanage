layui.use(['form','vip_tab'], function () {
    var form = layui.form
        ,$ = layui.jquery
        ,vip_tab = layui.vip_tab;
    var roleId = $("#roleId").val();
    //一套json数据下面会使用，同时供你参考
    // var json = [
    //     {
    //         title: "节点1", value: "jd1", data: [
    //         { title: "节点1.1", checked: true, disabled: true, value: "jd1.1", data: [] }
    //         , { title: "节点1.2", value: "jd1.2", checked: true, data: [] }
    //         , { title: "节点1.3", value: "jd1.3", disabled: true, data: [] }
    //         , { title: "节点1.4", value: "jd1.4", data: [] }
    //     ]
    //     }
    //     , {
    //         title: "节点2", value: "jd2", data: [
    //             { title: "节点2.1", value: "jd2.1", data: [] }
    //             , { title: "节点2.2", value: "jd2.2", data: [] }
    //             , { title: "节点2.3", value: "jd2.3", data: [] }
    //             , { title: "节点2.4", value: "jd2.4", data: [] }
    //         ]
    //     }
    //     , { title: "节点3", value: "jd3", data: [] }
    //     , { title: "节点4", value: "jd4", data: [] }
    // ];

    var xtree = new layuiXtree({
        elem: 'xtree'
        , form: form
        , data: '/role/show_role_menu?roleId='+ roleId
        , isopen: false  //加载完毕后的展开状态，默认值：true
        , ckall: false    //启用全选功能，默认值：false
        , ckallback: function(){} //全选框状态改变后执行的回调函数
        , icon: {        //三种图标样式，更改几个都可以，用的是layui的图标
            open: "&#xe623;"       //节点打开的图标
            , close: "&#xe625;"    //节点关闭的图标
            , end: "&#xe621;"      //末尾节点的图标
        }
        , color: {       //三种图标颜色，独立配色，更改几个都可以
            open: "#EE9A00"        //节点图标打开的颜色
            , close: "#EEC591"     //节点图标关闭的颜色
            , end: "#828282"       //末级节点图标的颜色
        }
        , click: function (data) {  //节点选中状态改变事件监听，全选框有自己的监听事件
            // console.log(data.elem); //得到checkbox原始DOM对象
            // console.log(data.elem.checked); //开关是否开启，true或者false
            // console.log(data.value); //开关value值，也可以通过data.elem.value得到
            // console.log(data.othis); //得到美化后的DOM对象
        }
    });

    $("#addRoleMenu").on('click',function () {
        var _allck = xtree.GetAllCheckBox();
        var arr = new Array();
        var menuId = '';
        var arrIndex = 0;
        for (var i = 0; i < _allck.length; i++) {
            if (_allck[i].checked) {
                arr[arrIndex] = _allck[i].value;
                menuId+=_allck[i].value+"/";
                arrIndex++;
            }
        }
        var param = {"roleId":roleId,"menuId":menuId};
        $.post({
            url : "/role/addRoleMenu",
            contentType : "application/json",
            dataType : "json",
            data : JSON.stringify(param),
            success : function(data) {
                if (data.success) {
                    layer.msg('保存成功', {
                        offset: '20px'
                        ,icon: 1
                        ,time: 1000
                    }, function(){
                        vip_tab.del(vip_tab.getThisTabId());
                    });
                } else {
                    layer.open({
                        title: 'ERROR'
                        ,content: data.msg
                        ,closeBtn: 0
                        ,btn: "重试"
                        ,skin: 'layui-layer-molv'
                        ,icon :2
                        ,yes: function (index, layero) {
                            layer.close(index);
                        }
                    });
                }
            },
            error : function(xmlq, errq) {
                layer.open({
                    title: 'ERROR'
                    ,content: errq
                    ,closeBtn: 0
                    ,btn: "取消"
                    ,skin: 'layui-layer-molv'
                    ,icon :2
                    ,yes: function (index, layero) {
                        layer.close(index);
                    }
                });
            }
        });
    });

})