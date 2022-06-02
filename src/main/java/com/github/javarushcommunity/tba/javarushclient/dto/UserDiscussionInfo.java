package com.github.javarushcommunity.tba.javarushclient.dto;

import lombok.Data;

/**
 * DTO for User discussion info.
 */
@Data
public class UserDiscussionInfo {
    private boolean isBookmarked;
    private Integer lastTime;
    private Integer newCommentsCount;
}
