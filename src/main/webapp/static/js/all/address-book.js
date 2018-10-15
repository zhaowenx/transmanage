layui.use(['table', 'form', 'layer','laydate'], function () {

    // 操作对象
    var form = layui.form
        , table = layui.table
        , layer = layui.layer
        , laydate = layui.laydate
        , $ = layui.jquery;

    $('#btn-refresh').on('click', function () {
        window.location.reload();
    });

    $('#btn-add').on('click',function () {
        $("select[name=profession]").empty();
        $("select[name=type]").empty();
        var param1 = {"dict":"profession"};
        var param2 = {"dict":"type"};
        $.ajax({
            type : "post",
            url : "/dict/selectDictItemByDict",
            contentType : "application/json",
            dataType : "json",
            data : JSON.stringify(param1),
            success : function(data) {
                if (data.success) {
                    var option = '';
                    option += "<option value=''>----请选择----</option>";
                    for(var i=0;i<data.data.length;i++){
                        option +="<option value=\""+data.data[i].itemKey+"\">"+data.data[i].itemKey+"-"+data.data[i].itemVal+"</option>"; //动态添加数据
                    }
                    $("select[name=profession]").append(option);
                    form.render('select');
                    $.ajax({
                        type : "post",
                        url : "/dict/selectDictItemByDict",
                        contentType : "application/json",
                        dataType : "json",
                        data : JSON.stringify(param2),
                        success : function(data) {
                            if (data.success) {
                                var option1 = '';
                                option1 += "<option value=''>----请选择----</option>";
                                for(var i=0;i<data.data.length;i++){
                                    option1 +="<option value=\""+data.data[i].itemKey+"\">"+data.data[i].itemKey+"-"+data.data[i].itemVal+"</option>"; //动态添加数据
                                }
                                $("select[name=type]").append(option1);
                                form.render('select');
                            } else {
                                layer.open({
                                    title: 'ERROR'
                                    ,content: data.msg
                                    ,closeBtn: 0
                                    ,btn: "离开"
                                    ,skin: 'layui-layer-molv'
                                    ,icon :2
                                    ,yes: function (index, layero) {
                                        layer.close(index);
                                    }
                                });
                            }
                        }
                    });
                    layer.open({
                        title: '新增'
                        ,type:1
                        ,moveOut:true
                        ,area:["700px","500px"]
                        ,skin: 'layui-layer-molv'
                        ,content: $("#open-div-add-address-book")
                    });
                } else {
                    layer.open({
                        title: 'ERROR'
                        ,content: data.msg
                        ,closeBtn: 0
                        ,btn: "离开"
                        ,skin: 'layui-layer-molv'
                        ,icon :2
                        ,yes: function (index, layero) {
                            layer.close(index);
                        }
                    });
                }
            }
        });
    });

    form.on('submit(addAddressBook)', function(data){
        $.post('/address/add',data.field,function (res) {
            if(res.success){
                layer.msg('通讯录添加成功', {
                    offset: '20px'
                    ,icon: 1
                    ,time: 1000
                }, function(){
                    // window.location.href="/login/toIndex";
                    window.location.reload();
                    // parent.location.reload();
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

    form.on('submit(updateAddressBook)', function(data){
        $.post('/address/update',data.field,function (res) {
            if(res.success){
                layer.msg('通讯录修改成功', {
                    offset: '20px'
                    ,icon: 1
                    ,time: 1000
                }, function(){
                    // window.location.href="/login/toIndex";
                    window.location.reload();
                    // parent.location.reload();
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

// $(function () {
//     transition();
// })
//
// function transition() {
//     var sysDict = $("#sysDict").val();
//     var dict = eval('(' + sysDict + ')');
//     var profession = $("#profession").val();
//     var type = $("#type").val();
//     var profession1 = dict.profession;
//     var type1 = dict.type;
//     var profession2;
//     var type2;
//     for(var k in profession1){
//         if(k == ""+profession){
//             profession2 = profession1[k];
//             break;
//         }
//     }
//     for(var k in type1){
//         if(k == ""+type){
//             type2 = type1[k];
//             break;
//         }
//     }
//     $("#profession1").html(profession2);
//     $("#type1").html(type2);
// }

function updateAddress(id) {
    var param = {"id":id};
    $.ajax({
        type : "post",
        url : "/address/selectAddressBookById",
        contentType : "application/json",
        dataType : "json",
        data : JSON.stringify(param),
        success : function(data) {
            if (data.success) {
                $("#chineseName").val(data.data.chineseName);
                $("#englishName").val(data.data.englishName);
                $("#anotherName").val(data.data.anotherName);
                $("#qqNumber").val(data.data.qqNumber);
                $("#weixin").val(data.data.weixin);
                $("#domicile").val(data.data.domicile);
                $("#address").val(data.data.address);
                $("#email").val(data.data.email);
                $("#phone").val(data.data.phone);
                $("#weibo").val(data.data.weibo);
                $("#profession").val(data.data.profession);
                $("#type").val(data.data.type);
                $("#id").val(data.data.id);
                // var option1 = '';
                // option1 += "<option value=''>----请选择----</option>";
                // for(var i=0;i<data.data.length;i++){
                //     option1 +="<option value=\""+data.data[i].itemKey+"\">"+data.data[i].itemKey+"-"+data.data[i].itemVal+"</option>"; //动态添加数据
                // }
                // $("select[name=type]").append(option1);
                // form.render('select');
                layer.open({
                    title: '编辑'
                    ,type:1
                    ,moveOut:true
                    ,area:["700px","500px"]
                    ,skin: 'layui-layer-molv'
                    ,content: $("#open-div-update-address-book")
                });
            } else {
                layer.open({
                    title: 'ERROR'
                    ,content: data.msg
                    ,closeBtn: 0
                    ,btn: "离开"
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
}

function deleteAddress(id) {
    layer.confirm('确认删除嘛，删除后不可恢复', {icon: 3, title:'提示'}, function(index){
        var param = {"id":id};
        $.ajax({
            type : "post",
            url : "/address/delete",
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
}

