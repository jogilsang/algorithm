//문제 설명
//        [본 문제는 정확성과 효율성 테스트 각각 점수가 있는 문제입니다.]
//
//        스노우타운에서 호텔을 운영하고 있는 스카피는 호텔에 투숙하려는 고객들에게 방을 배정하려 합니다. 호텔에는 방이 총 k개 있으며, 각각의 방은 1번부터 k번까지 번호로 구분하고 있습니다. 처음에는 모든 방이 비어 있으며 스카피는 다음과 같은 규칙에 따라 고객에게 방을 배정하려고 합니다.
//
//        한 번에 한 명씩 신청한 순서대로 방을 배정합니다.
//        고객은 투숙하기 원하는 방 번호를 제출합니다.
//        고객이 원하는 방이 비어 있다면 즉시 배정합니다.
//        고객이 원하는 방이 이미 배정되어 있으면 원하는 방보다 번호가 크면서 비어있는 방 중 가장 번호가 작은 방을 배정합니다.
//        예를 들어, 방이 총 10개이고, 고객들이 원하는 방 번호가 순서대로 [1, 3, 4, 1, 3, 1] 일 경우 다음과 같이 방을 배정받게 됩니다.
//
//        원하는 방 번호	배정된 방 번호
//        1	1
//        3	3
//        4	4
//        1	2
//        3	5
//        1	6
//        전체 방 개수 k와 고객들이 원하는 방 번호가 순서대로 들어있는 배열 room_number가 매개변수로 주어질 때, 각 고객에게 배정되는 방 번호를 순서대로 배열에 담아 return 하도록 solution 함수를 완성해주세요.
//
//        [제한사항]
//        k는 1 이상 1012 이하인 자연수입니다.
//        room_number 배열의 크기는 1 이상 200,000 이하입니다.
//        room_number 배열 각 원소들의 값은 1 이상 k 이하인 자연수입니다.
//        room_number 배열은 모든 고객이 방을 배정받을 수 있는 경우만 입력으로 주어집니다.
//        예를 들어, k = 5, room_number = [5, 5] 와 같은 경우는 방을 배정받지 못하는 고객이 발생하므로 이런 경우는 입력으로 주어지지 않습니다.
//        [입출력 예]
//        k	room_number	result
//        10	[1,3,4,1,3,1]	[1,3,4,2,5,6]
//        입출력 예에 대한 설명
//        입출력 예 #1
//
//        문제의 예시와 같습니다.
//
//        첫 번째 ~ 세 번째 고객까지는 원하는 방이 비어 있으므로 즉시 배정받을 수 있습니다. 네 번째 고객의 경우 1번 방을 배정받기를 원했는데, 1번 방은 빈 방이 아니므로, 1번 보다 번호가 크고 비어 있는 방 중에서 가장 번호가 작은 방을 배정해야 합니다. 1번 보다 번호가 크면서 비어있는 방은 [2번, 5번, 6번...] 방이며, 이중 가장 번호가 작은 방은 2번 방입니다. 따라서 네 번째 고객은 2번 방을 배정받습니다. 마찬가지로 5, 6번째 고객은 각각 5번, 6번 방을 배정받게 됩니다.

import java.util.ArrayList;
import java.util.HashMap;

class kakaowinter_4 {

    public long[] solution(long k, long[] room_number) {
        long[] answer = {};

        // 1. 주어진 k로 1~k까지 hashmap을만듬
        // 비어있는방 : true , 차있는방 : false
        HashMap<Long, Boolean> hashMap = new HashMap<Long, Boolean>();
        ArrayList<Long> answerList = new ArrayList<>();

        long i = 1;
        while (i <= k) {
            hashMap.put(i, true);
            i++;
        }

        // 2. hashmap의 key에대한 value로 true면 비어있으므로 방을 배정 후 false
        //    false면 배정됬으므로, 값을 하나씩 증가시켜 최대한 가까운 값으로 배정
        for (long room_number_element : room_number) {

            //
            if (hashMap.get(room_number_element)) {
                answerList.add(room_number_element);
                hashMap.put(room_number_element, false);
            }
            // 이미 배정됨
            // 최근 방을 찾아서 배정하자
            else {
                long temp = room_number_element;
                while (true) {

                    if (temp > k) {
                        System.out.println("error");
                        break;
                    }

                    if (hashMap.get(temp)) {
                        answerList.add(temp);
                        hashMap.put(temp, false);
                        break;
                    }
                    temp++;
                }
            }
        }

        // 3. 정답return
        answer = answerList.stream().mapToLong(p -> p.longValue()).toArray();

        return answer;
    }
}





























