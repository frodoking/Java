package cn.com.frodo.knowledge.spring.design.friends;

import lombok.Data;

@Data
public class Friends {
    private Long uId;
    private Long friendsId;

    /**
     * @see  FriendsType
     */
    private FriendsType friendsType;

}
