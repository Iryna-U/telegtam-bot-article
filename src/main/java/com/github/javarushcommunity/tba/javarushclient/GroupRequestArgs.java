package com.github.javarushcommunity.tba.javarushclient;

import static java.util.Objects.nonNull;

import com.github.javarushcommunity.tba.javarushclient.dto.GroupFilter;
import com.github.javarushcommunity.tba.javarushclient.dto.GroupInfoType;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

/**
 * Request arguments for group requests.
 */
@Builder
@Getter
public class GroupRequestArgs {

    private final String query;
    private final GroupInfoType type;
    private final GroupFilter filter;

    /**
     * specified where to start getting groups
     */
    private final Integer offset;
    /**
     * Limited number of groups.
     */
    private final Integer limit;

    public Map populateQueries() {
        Map queries = new HashMap<>();
        if (nonNull(query)) {
            queries.put("query", query);
        }
        if (nonNull(type)) {
            queries.put("type", type);
        }
        if (nonNull(filter)) {
            queries.put("filter", filter);
        }
        if (nonNull(offset)) {
            queries.put("offset", offset);
        }
        if (nonNull(limit)) {
            queries.put("limit", limit);
        }
        return queries;
    }
}
