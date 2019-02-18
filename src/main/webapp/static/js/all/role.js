layui.use(['table', 'form', 'layer', 'vip_table','laydate','vip_tab','element'], function () {

    // 操作对象
    var form = layui.form
        , table = layui.table
        , layer = layui.layer
        , vipTable = layui.vip_table
        , $ = layui.jquery
        , vip_tab = layui.vip_tab
        , element = layui.element;

    var option1;

    //获取下拉框值的函数
    function getItemVal(dict) {
        var option = '';
        var param = {"dict":dict};
        $.post({
            url : "/dict/selectDictItemByDict",
            contentType : "application/json",
            dataType : "json",
            data : JSON.stringify(param),
            success : function(data) {
                option += "<option value=''>----请选择----</option>";
                for(var i=0;i<data.data.length;i++){
                    option +="<option value=\""+data.data[i].itemKey+"\">"+data.data[i].itemKey+"-"+data.data[i].itemVal+"</option>"; //动态添加数据
                }
                if("roletype" == dict){
                    option1 = option;
                    $("select[name=roleType]").empty();
                    $("select[name=roleType]").append(option1);
                    form.render('select');
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
    }

    $(function () {
        getItemVal("roletype");
    })

    // 表格渲染
    var tableIns = table.render({
        elem: '#dateTable'                  //指定原始表格元素选择器（推荐id选择器）
        , height: vipTable.getFullHeight()    //容器高度
        , width:1300
        , cols: [[                  //标题栏
            // {checkbox: true, fixed: true, space: true,width:80},
            {field: 'roleId', title: '角色ID',width:100},
            {field: 'roleName', title: '角色名称',width:200}
            , {field: 'roleType', title: '角色类型',width:200,templet:function (data) {
                return data.roleType == 1?'超级管理员':'普通用户';
            }}
            , {field: 'descript', title: '描述'}
            , {field: 'right1', title: '操作', align: 'center',width:300, toolbar: '#barOption'} //这里的toolbar值是模板元素的选择器
        ]]
        , id: 'dataCheck'
        , url: '/role/show'
        , method: 'get'
        , page: true
        , limits: [10, 20, 30, 40, 50]
        , limit: 10
        , loading: true
        , request: {pageName: 'currentPage', limitName: 'pageSize'}
        , done: function (res, curr, count) {
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            // console.log(res);
        }
    });

    $("#user-defined-select").click(function () {
        tableIns.reload({
            where:{
                "roleName":$("#role_name_s").val(),
                "roleType":$("#role_type_s").val()
            }
        });
    });

    $("#user-defined-reset").click(function () {
        $("#role_name_s").val('');
        $("#role_type_s").val('');
    })

    // 获取选中行
    // table.on('checkbox(dataCheck)', function (obj) {
    //     console.log(obj.checked); //当前是否选中状态
    //     console.log(obj.data); //选中行的相关数据
    //     console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
    // });

    table.on('tool(dateTable)', function(obj){
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        if(layEvent === 'delete'){ //删除
            layer.confirm('确认删除嘛，删除后不可恢复', {icon: 3, title:'提示'}, function(index){
                var param = {"roleId":data.roleId};
                $.ajax({
                    type : "post",
                    url : "/role/delete",
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
                                window.location.reload();
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
            $("#roleName").val(data.roleName);
            $("#roleId").val(data.roleId);
            $("select[name=roleType]").empty();
            $("select[name=roleType]").append(option1);
            $("#roleType").val(data.roleType);
            form.render('select');
            $("#descript").val(data.descript);
            layer.open({
                title: '修改'
                ,type:1
                ,moveOut:true
                ,area:["710px","320px"]
                ,skin: 'layui-layer-molv'
                ,content: $("#open-div-update-role")
            });
        }else if(layEvent === 'set'){ //设置角色权限
            var roleId = data.roleId;
            vip_tab.add(null,"设置角色权限","/role/set-role-menu?roleId="+roleId,'1');
        }
    });

    $('#btn-add').on('click',function () {
        $("select[name=roleType]").empty();
        $("select[name=roleType]").append(option1);
        form.render('select');
        layer.open({
            title: '新增'
            ,type:1
            ,moveOut:true
            ,area:["710px","320px"]
            ,skin: 'layui-layer-molv'
            ,content: $("#open-div-add-role")
        });
    });

    form.on('submit(addRole)', function(data){
        $.post('/role/add',data.field,function (res) {
            if(res.success){
                layer.msg('新增角色成功', {
                    offset: '20px'
                    ,icon: 1
                    ,time: 1000
                }, function(){
                    // window.location.href="/login/toIndex";
                    window.location.reload();
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
                        layer.close(index);
                    }
                });
            }
        });
        return false;
    });

    form.on('submit(updateRole)', function(data){
        $.post('/role/update',data.field,function (res) {
            if(res.success){
                layer.msg('修改角色成功', {
                    offset: '20px'
                    ,icon: 1
                    ,time: 1000
                }, function(){
                    // window.location.href="/login/toIndex";
                    window.location.reload();
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
                        layer.close(index);
                    }
                });
            }
        });
        return false;
    });
});