layui.use(['table', 'form', 'layer', 'vip_table','laydate','vip_tab','element'], function () {

    // 操作对象
    var form = layui.form
        , table = layui.table
        , layer = layui.layer
        , vipTable = layui.vip_table
        , $ = layui.jquery
        , vip_tab = layui.vip_tab;

    // 表格渲染
    var tableIns = table.render({
        elem: '#dateTable'                  //指定原始表格元素选择器（推荐id选择器）
        , height: vipTable.getFullHeight()    //容器高度
        , cols: [[                  //标题栏
            {field: 'userName', title: '用户名', width: 200}
            ,{field: 'realName', title: '真实姓名', width: 200}
            ,{field: 'mobile', title: '手机号', width: 150}
            ,{field: 'loginTimes', title: '登录次数', width: 100}
            ,{field: 'lastLoginDate', title: '最后一次登录时间', width: 200}
            , {field: 'createTime', title: '创建时间', width: 200}
            , {field: 'right1', title: '操作', align: 'center',width:240, toolbar: '#barOption'} //这里的toolbar值是模板元素的选择器
        ]]
        , url: '/user/selectAll'
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
                "userName":$("#user_name_s").val(),
                "mobile":$("#mobile_s").val()
            }
        });
    });

    $("#user-defined-reset").click(function () {
        $("#user_name_s").val('');
        $("#mobile_s").val('');
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
                    url : "/user/deleteUser",
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
                                tableIns.reload();
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
        }else if(layEvent === 'set'){ //设置角色权限
            var groupCheckbox1 = $("input[name='roleId']");
            for (i = 0; i < groupCheckbox1.length; i++) {
                groupCheckbox1[i].checked=false;
            }
            var userId = data.id;
            $("#userId").val(userId);
            $.get("/role/getRoleId?userId="+userId,null,function (res) {
                if(res.success){
                    var roleId = res.data;
                    if(roleId != null){
                        var groupCheckbox = $("input[name='roleId']");
                        for (i = 0; i < groupCheckbox.length; i++) {
                            var val =groupCheckbox[i].value;
                            if(roleId.indexOf(val)!=-1){
                                groupCheckbox[i].checked=true;
                            }
                        }
                        form.render();
                    }
                }else{
                    layer.msg(res.msg);
                }
            },'json');
            layer.open({
                title: '设置用户角色'
                ,type:1
                ,moveOut:true
                ,area:["710px","320px"]
                ,skin: 'layui-layer-molv'
                ,content: $("#open-div-set-role")
            });
        }
    });

    $('#btn-add').on('click',function () {
        vip_tab.add(null,"新增用户","/html/add-user.html",'1');
    });

    $(function () {
        $.get("/role/selectAll",null,function (res) {
            if(res.success){
                var data = res.data;
                var view = "";
                $(data).each(function (k, v) {
                    view += '<input type="checkbox" class="layui-form-checked" name="roleId" '+' title="'+v.roleName+'" value="'+v.roleId+'" />';
                });
                $("#role").append(view);
                form.render();
            }else{
                layer.msg(res.msg);
            }
        },'json');
    })

    form.on('submit(addUserRole)', function(data){
        var arr = new Array();
        $("input:checkbox[name='roleId']:checked").each(function(i){
            arr[i] = $(this).val();
        });
        data.field.roleId = arr.join(",");//将数组合并成字符串
        $.post('/user/addUserRole',data.field,function (res) {
            if(res.success){
                layer.msg('设置用户角色成功', {
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