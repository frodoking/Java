package cn.com.frodo.knowledge.spring.design.friends;

import org.springframework.stereotype.Service;

@Service
public class FriendsService {

    /**
     * 关注
     * @param uId 当前人
     * @param someoneUId 被关注人
     *
     */
    public void subscribe(Long uId, Long someoneUId) {
        // A 表
        Friends myFriends = new Friends();
        myFriends.setUId(uId);
        myFriends.setFriendsId(someoneUId);
        myFriends.setFriendsType(FriendsType.follow);

        // B 表
        Friends someoneFriends = new Friends();
        someoneFriends.setUId(someoneUId);
        someoneFriends.setFriendsId(uId);
        someoneFriends.setFriendsType(FriendsType.followers);

        // 如此冗余设计的好处就是查询性能，也可以通过单个表的写扩散成为多个子表的读
    }
}
