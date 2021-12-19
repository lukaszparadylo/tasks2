package com.crud2.tasks.mapper;

import com.crud2.tasks.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    private List<TrelloList> generateTrelloList(Integer amountOfData){
        List<TrelloList> list = new ArrayList<>();
        for (int i = 0; i<amountOfData; i++){
            list.add(new TrelloList("1"+i+1,"1"+i*i+1,true));
        }
        return list;
    }
    private List<TrelloListDto> generateTrelloListDto(Integer amountOfData){
        List<TrelloListDto> list = new ArrayList<>();
        for (int i = 0; i<amountOfData; i++){
            list.add(new TrelloListDto("1"+i+1,"1"+i*i+1,true));
        }
        return list;
    }

    private List<TrelloBoard> generateTrelloBoardList(Integer amountOfBoard){
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        for (int i = 0; i<amountOfBoard; i++){
            trelloBoardList.add(new TrelloBoard("111"+i,"1111"+i,generateTrelloList(3)));
        }
        return trelloBoardList;
    }

    private List<TrelloBoardDto> generateTrelloBoardDtoList(Integer amountOfBoardDto){
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        for (int i = 0; i<amountOfBoardDto; i++){
            trelloBoardDtoList.add(new TrelloBoardDto("111"+i,"1111"+i,generateTrelloListDto(3)));
        }
        return trelloBoardDtoList;
    }

    @Test
    public void mapToBoardDtoTest(){
        List<TrelloBoard> trelloBoardList =generateTrelloBoardList(3);

        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        Long amountOfBoardsByName = trelloBoardDtoList.stream()
                .filter(n->n.getName().contains("111")).count();
        Long amountOfListsContainedDesiredId = trelloBoardDtoList.stream()
                .map(n->n.getLists().stream()
                        .filter(m->m.getId().contains("11"))).count();

        Assertions.assertEquals(3, trelloBoardDtoList.size());
        Assertions.assertEquals(3L,amountOfBoardsByName);
        Assertions.assertEquals(3L, amountOfListsContainedDesiredId);
    }

    @Test
    public void mapToBoardTest(){
        List<TrelloBoardDto> trelloBoardDtoList = generateTrelloBoardDtoList(3);

        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);

        Long amountOfBoardsByName = trelloBoardList.stream()
                .filter(n->n.getName().contains("111")).count();
        Long amountOfListsContainedDesiredId = trelloBoardList.stream()
                .map(n->n.getLists().stream()
                        .filter(m->m.getId().contains("11"))).count();

        Assertions.assertEquals(3, trelloBoardDtoList.size());
        Assertions.assertEquals(3L,amountOfBoardsByName);
        Assertions.assertEquals(3L, amountOfListsContainedDesiredId);
    }

    @Test void mapToList(){
        List<TrelloList> trelloList = trelloMapper.mapToList(generateTrelloListDto(4));
        Long amountOfListsByName = trelloList.stream()
                .filter(n->n.getName().contains("11")).count();
        Integer amountOfLists = trelloList.size();

        Assertions.assertEquals(1, amountOfListsByName);
        Assertions.assertEquals(4, amountOfLists);
    }

    @Test
    public void mapToListDto(){
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(generateTrelloList(4));

        Long amountOfListsByName = trelloListDto.stream()
                .filter(n->n.getName().contains("11")).count();
        Integer amountOfLists = trelloListDto.size();

        Assertions.assertEquals(1, amountOfListsByName);
        Assertions.assertEquals(4, amountOfLists);
    }
    @Test
    public void mapToCard(){
        List<TrelloCard> trelloCardList = new ArrayList<>();
        trelloCardList.add(trelloMapper.mapToCard(new TrelloCardDto("111","1111", "1111", "11111")));
        trelloCardList.add(trelloMapper.mapToCard(new TrelloCardDto("222","2222", "2222", "22222")));
        trelloCardList.add(trelloMapper.mapToCard(new TrelloCardDto("333","3333", "3333", "33333")));

        Long amountOfListsByName = trelloCardList.stream()
                .filter(n->n.getName().contains("111")).count();
        Integer amountOfLists = trelloCardList.size();

        Assertions.assertEquals(1, amountOfListsByName);
        Assertions.assertEquals(3, amountOfLists);
    }

    @Test
    public void mapToCardDto(){
        List<TrelloCardDto> trelloCardDtoList = new ArrayList<>();
        trelloCardDtoList.add(trelloMapper.mapToCardDto(new TrelloCard("111","1111", "1111", "11111")));
        trelloCardDtoList.add(trelloMapper.mapToCardDto(new TrelloCard("222","2222", "2222", "22222")));
        trelloCardDtoList.add(trelloMapper.mapToCardDto(new TrelloCard("333","3333", "3333", "33333")));

        Long amountOfListsByName = trelloCardDtoList.stream()
                .filter(n->n.getName().contains("111")).count();
        Integer amountOfLists = trelloCardDtoList.size();

        Assertions.assertEquals(1, amountOfListsByName);
        Assertions.assertEquals(3, amountOfLists);
    }
}