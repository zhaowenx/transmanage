layui.define('layer', function (exports) {

    // 封装方法
    var mod = {
        // 添加选项卡 [操作对象，标签标题，url地址]
        add: function (elem, tit, url,status) {
            parent.addTab(elem, tit, url,status);
        }
        // 获取当前选中的选项卡的lay-id
        ,getThisTabId: function () {
            // 获取并返回 id
            return parent.getThisTabID();
        }
        // 删除选项卡[标签lay-id]
        ,del: function (id) {
            parent.delTab(id);
        }
    };

    // 输出
    exports('vip_tab', mod);
});


