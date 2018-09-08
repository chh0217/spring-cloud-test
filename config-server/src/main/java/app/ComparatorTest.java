package app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author chenhang
 * @date 2018/8/5 下午8:57
 * @description
 */
public class ComparatorTest {

    public static void main(String[] args) {
        Com c1 = new Com("a",1);
        Com c2 = new Com("b",2);
        Com c3 = new Com(null,3);
        Com c4 = new Com(null,4);
        Com c5 = new Com("c",5);
        List<Com> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        list.add(c5);
        System.out.println(list);
        list.sort((Com o1,Com o2)->{
             {
                if(o1.getName() != null && o2.getName() == null){
                    return -1;
                }
                if(o1.getName() == null && o2.getName() != null){
                    return 1;
                }
                return 0;
            }
        });
        System.out.println(list);
    }


    static class Com{

        private String name;

        private Integer id;

        public Com(){}
        public Com(String name,Integer id){
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String toString(){
            return name + "-" + id;
        }
    }
}
