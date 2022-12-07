package aoc22;

import java.util.List;

public class Day5 extends Day<Integer>{
    protected Day5(int day) {
        super(day);
    }

    public List<String> loadFile(String path){
        FileReader fileReader = new FileReader(path);
        return fileReader.readStrings();
    }

    @Override
    public Integer getFirstStar() {
        System.out.println("I don't even know where to start from with this one");
        return null;
    }



    @Override
    public Integer getSecondStar() {
        System.out.println("sure ");
        return null;
    }

}
