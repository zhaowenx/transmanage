//package com.zwx.transmanage.initialization.thymeleaf;
//
//import com.zwx.transmanage.commen.constant.KeyNameConstant;
//import com.zwx.transmanage.commen.constant.RedisTimeConstant;
//import com.zwx.transmanage.domain.vo.SysDictItemVo;
//import com.zwx.transmanage.domain.vo.SysDictVo;
//import com.zwx.transmanage.service.SysDictService;
//import com.zwx.transmanage.util.RedisUtil;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by zhaowenx on 2018/10/12.
// */
//@Component
//@Order(1)
//public class InitializationRun implements CommandLineRunner {
//
//    private final static Logger logger = LoggerFactory.getLogger(InitializationRun.class);
//
//    /**
//     * 该类用于项目启动的时候初始化执行
//     * 创建自定义类实现 CommandLineRunner接口，重写run（）方法。
//     * springboot启动之后会默认去扫描所有实现了CommandLineRunner的类，并运行其run（）方法。
//     * @param args
//     * @throws Exception
//     */
//
//    @Autowired
//    private SysDictService sysDictService;
//    @Autowired
//    private RedisUtil redisUtil;
//
//    @Override
//    public void run(String... args) throws Exception {
//        loadSysDict();
//    }
//
//    private void loadSysDict(){
//        logger.info("************************项目加载的时候初始化*****************************");
//        Map<String,Map<String,String>> map=new HashMap<>();
//        List<SysDictVo> sysDictVoList = sysDictService.selectSysDictVo();
//        for(SysDictVo sysDictVo:sysDictVoList){
//            List<SysDictItemVo> sysDictItemVoList = sysDictService.selectSysDictItem(sysDictVo.getDict());
//            Map<String,String> dictItemMap = new HashMap<>();
//            for(SysDictItemVo sysDictItemVo:sysDictItemVoList){
//                dictItemMap.put(sysDictItemVo.getItemKey(),sysDictItemVo.getItemVal());
//            }
//            map.put(sysDictVo.getDict(),dictItemMap);
//        }
//        logger.info("SYS_DICT："+map.toString());
//        JSONObject json =new JSONObject(map);
//        logger.info("SYS_DICT："+json.toString());
//        redisUtil.set(KeyNameConstant.SYS_DICT,json.toString(), RedisTimeConstant.ONE_DAY);
//    }
//}
