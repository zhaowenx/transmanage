layui.use(['form', 'laydate'], function(){
    var form = layui.form
        ,layer = layui.layer
        ,laydate = layui.laydate
        ,$ = layui.$;

    //日期
    laydate.render({
        elem: '#hireDate'
    });

    var hobby=$("#hobby").val();
    var groupCheckbox = $("input[name='hobby']");
    for (i = 0; i < groupCheckbox.length; i++) {
        var val =groupCheckbox[i].value;
        if(hobby.split(',').indexOf(val)!=-1){
            groupCheckbox[i].checked=true;
        }
    }
    form.render();

    //提交
    form.on('submit(replenishInformation)', function(data){
        var arr = new Array();
        $("input:checkbox[name='hobby']:checked").each(function(i){
            arr[i] = $(this).val();
        });
        data.field.hobby = arr.join(",");//将数组合并成字符串
        $("#submit").hide();
        $.post('/user/updateUser',data.field,function (res) {
            if(res.success){
                layer.msg('修改用户信息成功', {
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
                        $("#submit").show();
                        layer.close(index);
                    }
                });
            }
        });
        return false;
    });
});