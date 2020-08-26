package cn.cobight;

import java.util.List;
import java.util.Map;

public interface roomDao {
    List<Map<String,Object>> sel_all_room();//查所有房
    Map<String ,Object> getPriceByRoomId(Integer roomid);//获取房间
    //void updateRoomFlag(Integer roomid,Integer flag);
    void updateRoomFlag(Integer roomNumber, Integer flag);
}
