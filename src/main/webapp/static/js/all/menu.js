// layui方法
layui.use(['tree', 'table', 'vip_table', 'layer' ,'form'], function () {

    // 操作对象
    var table = layui.table
        , vipTable = layui.vip_table
        , layer = layui.layer
        , $ = layui.jquery
        , form = layui.form;

    var parentId = 0;
    
    function getTree() {
        var tree = '';
        $.ajax({
            type : "get",
            url : "/menu/tree",
            dataType : "json",
            success : function(data) {
                if (data.success) {
                    tree = data.data;
                    layui.tree({
                        elem: '#tree' //传入元素选择器
                        , click: function (item) { //点击节点回调
                            tableIns.reload({
                                where:{
                                    "parentId":item.id
                                }
                            });
                            var menuLevel = '';
                            $.ajax({
                                type : "get",
                                url : "/menu/getMenuLevel?id="+item.id,
                                success : function(data) {
                                    if (data.success) {
                                        menuLevel = data.data+1;
                                        menuLevel = "新增"+menuLevel+"级子菜单";
                                        $("#layui-id-add-menu").html(menuLevel);
                                    } else {
                                        layer.open({
                                            title: 'ERROR'
                                            ,content: data.msg
                                            ,closeBtn: 1
                                            ,skin: 'layui-layer-molv'
                                            ,icon :2
                                        });
                                    }
                                }
                            })
                            $("#parentId").val(item.id);
                        }
                        , nodes: tree
                    });
                } else {
                    layer.open({
                        title: 'ERROR'
                        ,content: data.msg
                        ,closeBtn: 1
                        ,skin: 'layui-layer-molv'
                        ,icon :2
                    });
                }
            }
        })
    }
    
    $(function () {
        getTree();
        $("#layui-id-add-menu").html("新增1级子菜单");
        $("#parentId").val(0);
    })

    // 表格渲染
    var tableIns = table.render({
        elem: '#dateTable'                  //指定原始表格元素选择器（推荐id选择器）
        // , height: vipTable.getFullHeight()    //容器高度
        , cols: [[                  //标题栏
            // {checkbox: true, fixed: true, space: true,width:80},
            {field: 'id', title: 'ID',width:50},
            {field: 'text', title: '菜单名称',width:100}
            , {field: 'icon', title: '菜单图标',width:100}
            , {field: 'href', title: '访问地址',templet:function (data) {
                return data.href == ''?'/-/-':data.href;}}
            , {field: 'parentId', title: '父菜单ID',width:100}
            , {field: 'right1', title: '操作', align: 'center', toolbar: '#barOption'} //这里的toolbar值是模板元素的选择器
        ]]
        , id: 'dataCheck'
        , url: '/menu/show'
        , method: 'get'
        // , loading: true
        , where:{
            "parentId":parentId
        }
    });

    $("#layui-id-shouye").click(function () {
        parentId = 0;
        // 刷新表格
        tableIns.reload({
            where:{
                "parentId":parentId
            }
        });
        $("#parentId").val(0);
        $("#layui-id-add-menu").html("新增1级子菜单");
    })

    table.on('tool(dateTable)', function(obj){
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        if(layEvent === 'delete'){ //删除
            layer.confirm('确认删除嘛，删除后不可恢复', {icon: 3, title:'提示'}, function(index){
                var param = {"id":data.id};
                $.ajax({
                    type : "post",
                    url : "/menu/delete",
                    dataType : "json",
                    contentType : "application/json",
                    data : JSON.stringify(param),
                    success : function(data) {
                        if (data.success) {
                            layer.msg('删除成功', {
                                offset: '20px'
                                ,icon: 1
                                ,time: 1000
                            }, function(){
                                // window.location.reload();
                                parent.location.href="/login/toIndex";
                            });
                        } else {
                            layer.open({
                                title: 'ERROR'
                                ,content: data.msg
                                ,closeBtn: 0
                                ,btn: "重新删除"
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
                            ,btn: "重新删除"
                            ,skin: 'layui-layer-molv'
                            ,icon :2
                            ,yes: function (index, layero) {
                                layer.close(index);
                            }
                        });
                    }
                })
            });
        } else if(layEvent === 'update'){ //编辑
            $("#text").val(data.text);
            $("#href").val(data.href);
            $("#id").val(data.id);
            layer.open({
                title: '修改'
                ,type:1
                ,moveOut:true
                ,area:["600px","220px"]
                ,skin: 'layui-layer-molv'
                ,content: $("#open-div-update-menu")
            });
        }
    });

    $('#add-menu').on('click',function () {
        $("#parentId_1").val($("#parentId").val());
        layer.open({
            title: '新增'
            ,type:1
            ,moveOut:true
            ,area:["600px","220px"]
            ,skin: 'layui-layer-molv'
            ,content: $("#open-div-add-menu")
        });
    });

    form.on('submit(addMenu)', function(data){
        $("#submit").hide();
        $.post('/menu/add',data.field,function (res) {
            if(res.success){
                layer.msg('新增菜单成功', {
                    offset: '20px'
                    ,icon: 1
                    ,time: 1000
                }, function(){
                    parent.location.href="/login/toIndex";
                    // window.location.reload();
                });
            }else{
                layer.open({
                    title: 'ERROR'
                    ,content: res.msg
                    ,closeBtn: 0
                    ,btn: "确定"
                    ,skin: 'layui-layer-molv'
                    ,icon :2
                    ,yes: function (index, layero) {
                        $("#submit").show();
                        layer.close(index);
                    }
                });
            }
        });
        return false;
    });

    form.on('submit(updateMenu)', function(data){
        $("#submit").hide();
        $.post('/menu/update',data.field,function (res) {
            if(res.success){
                layer.msg('修改菜单成功', {
                    offset: '20px'
                    ,icon: 1
                    ,time: 1000
                }, function(){
                    parent.location.href="/login/toIndex";
                    // window.location.reload();
                });
            }else{
                layer.open({
                    title: 'ERROR'
                    ,content: res.msg
                    ,closeBtn: 0
                    ,btn: "确定"
                    ,skin: 'layui-layer-molv'
                    ,icon :2
                    ,yes: function (index, layero) {
                        $("#submit").show();
                        layer.close(index);
                    }
                });
            }
        });
        return false;
    });

});