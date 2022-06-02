package com.github.javarushcommunity.tba.javarushclient;

import com.github.javarushcommunity.tba.javarushclient.dto.GroupDiscussionInfo;
import com.github.javarushcommunity.tba.javarushclient.dto.GroupInfo;
import com.github.javarushcommunity.tba.javarushclient.dto.GroupInfoType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;

@DisplayName("Integration-level testing for JavaRushGroupClientImplTest")
class JavaRushGroupClientTest {
    private final JavaRushGroupClient javaRushGroupClient =
            new JavaRushGroupClientImpl("https://javarush.ru/api/1.0/rest");

    @Test
    public void shouldProperlyGetGroupsWithEmptyArgs() {
        GroupRequestArgs args = GroupRequestArgs.builder().build();
        List<GroupInfo> responseGroupList = javaRushGroupClient.getGroupList(args);
        List<String> expectedTitles = List.of("Android", "Freelancer", "Frontend", "Immigrant",
                "Java Developer", "Java-проекты", "Java-университет", "JavaRush", "JavaRush Beta Testing",
                "QA Automation", "Random", "Алматы", "Архив info.javarush.ru", "Вакансии", "Владивосток",
                "Днепр", "Екатеринбург", "Истории успеха", "Казань", "Киев", "Компания Bellintegrator",
                "Компания SmartSoft", "Краснодар", "Львов", "Минск", "Москва", "Нижний Новгород",
                "Новосибирск", "Одесса", "Онлайн-стажировка ", "Санкт-Петербург", "Харьков");

        //then
        Assertions.assertNotNull(responseGroupList);
        Assertions.assertFalse(responseGroupList.isEmpty());
        List<String> responseTitles = responseGroupList.stream()
                .map(GroupInfo::getTitle)
                .collect(Collectors.toList());
        Assertions.assertEquals(expectedTitles.size(), responseTitles.size());
        Assertions.assertTrue(responseTitles.containsAll(expectedTitles));
    }

    @Test
    public void shouldProperlyGetWithLimit() {
        GroupRequestArgs args = GroupRequestArgs.builder()
                .limit(5)
                .build();
        List<GroupInfo> responseGroupList = javaRushGroupClient.getGroupList(args);

        //then
        Assertions.assertNotNull(responseGroupList);
        Assertions.assertEquals(5, responseGroupList.size());
    }
    @Test
    public void shouldProperlyGetGroupDiscWithLimit() {
        GroupRequestArgs args = GroupRequestArgs.builder()
                .limit(5)
                .build();

        List<GroupDiscussionInfo> responseGroupList = javaRushGroupClient.getGroupDiscussionList(args);

        //then
        Assertions.assertNotNull(responseGroupList);
        Assertions.assertEquals(5, responseGroupList.size());
    }

    @Test
    public void shouldProperlyGetGroupCount() {
        GroupsCountRequestArgs args = GroupsCountRequestArgs.builder().build();
        Integer responseGroupCount = javaRushGroupClient.getGroupCount(args);

        //then
        Assertions.assertEquals(32, responseGroupCount);
    }

    @Test
    public void shouldProperlyGetGroupTECHCount() {
        GroupsCountRequestArgs args = GroupsCountRequestArgs.builder()
                .type(GroupInfoType.TECH)
                .build();
        Integer responseGroupCount = javaRushGroupClient.getGroupCount(args);

        //then
        Assertions.assertEquals(7, responseGroupCount);
    }

    @Test
    public void shouldProperlyGetGroupById() {
        Integer androidGroupId = 16;
        GroupDiscussionInfo responseGroupById = javaRushGroupClient.getGroupById(androidGroupId);

        //then
        Assertions.assertNotNull(responseGroupById);
        Assertions.assertEquals(16, responseGroupById.getId());
        Assertions.assertEquals(GroupInfoType.TECH, responseGroupById.getType());
        Assertions.assertEquals("android", responseGroupById.getKey());
    }
}
