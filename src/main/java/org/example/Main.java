package org.example;

import org.example.event.*;
import org.example.event.update.UpdateTodo;
import org.example.schedule.Schedule;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        // 0. 초기화 (기본값 불러오기)
        Schedule schedule = new Schedule();
        System.out.println();
        System.out.println("=========================");

        // 1. 전체 출력
        System.out.println();
        System.out.println("=== 전체 출력 ======================");
        schedule.printAll();

        // 2. 신규 이벤트 추가
        System.out.println();
        System.out.println("=== 신규 이벤트 추가 ======================");
        NoDisturbance noDisturbance = new NoDisturbance(5, ZonedDateTime.now(), ZonedDateTime.now());
        schedule.add(noDisturbance);
        schedule.printAll();

        // 3. To_Do 이벤트 조회
        List<Todo> todos = schedule.findTodos();

        // 4. 수정
        System.out.println();
        System.out.println("=== 수정 ======================");
        Todo todo1 = todos.get(0);

        System.out.println();
        System.out.println("수정 대상");
        todo1.print();

        todo1.validateAndUpdate(new UpdateTodo("신규 섦영", "신규 타이틀", ZonedDateTime.now(), ZonedDateTime.now(), false));

        System.out.println();
        System.out.println("수정 완료");
        todo1.print();


        // 5. 삭제
        System.out.println();
        System.out.println("=== 삭제 ======================");

        System.out.println("삭제 전");
        schedule.printAll();
        schedule.remove(1);

        System.out.println();
        System.out.println("삭제 후");
        schedule.printAll();

        System.out.println("=========================");
        System.out.println();

        // 6. 특정 날짜의 이벤트 검색
        System.out.println("1월 21일 검색");
        List<AbstractEvent> eventsBy = schedule.findEventsBy(ZonedDateTime.of(2023, 1, 21, 0, 0, 0, 0, ZoneId.systemDefault()));
        eventsBy.forEach(Event::print);
    }

}